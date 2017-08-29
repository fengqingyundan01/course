package css.web.service.config;

import java.util.Map;
import java.util.Properties;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.google.common.collect.Maps;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import css.web.service.auth.shiro.UserAuthRealm;
import css.web.service.auth.shiro.UserCredentialsMatcher;

/**
 * 配置Shiro
 *
 * @author yangshuo
 *
 */
@Configuration
public class ShiroConfiguration {
	/** Session标识 */
	private static final String SESSION_ID_NAME = "CSSSESSION";

	/** Session超时时间,默认半个小时（1800秒) */
	@Value("${css.session.timeout:1800000}")
	private long sessionTimeout;

	/** Session验证清楚周期，默认一个小时 */
	@Value("${css.session.validation.interval:3600000}")
	private long sessionValidationInternal;

	/**
	 * 凭证匹配器
	 *
	 * @return
	 */
	@Bean
	public UserCredentialsMatcher credentialsMatcher() {
		return new UserCredentialsMatcher();
	}

	/**
	 * 身份认证realm
	 *
	 * @return
	 */
	@Bean
	public UserAuthRealm userAuthRealm(CredentialsMatcher credentialMatcher) {
		UserAuthRealm userAuthRealm = new UserAuthRealm();
		userAuthRealm.setCredentialsMatcher(credentialMatcher);
		return userAuthRealm;
	}

	/**
	 * 会话管理器。设定会话ID,超时策略，缓存策略。
	 *
	 * @return
	 */
	@Bean
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionIdCookie(new SimpleCookie(SESSION_ID_NAME));
		sessionManager.setGlobalSessionTimeout(sessionTimeout);
		sessionManager.setSessionValidationInterval(sessionValidationInternal);
		return sessionManager;
	}

	@Bean
	public SecurityManager securityManager(Realm realm, SessionManager sessionManager, CacheManager cacheManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);
		securityManager.setSessionManager(sessionManager);
		securityManager.setCacheManager(cacheManager);
		return securityManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 设定安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 拦截器设置
		Map<String, String> filterChainDefinitionMap = Maps.newHashMap();

		// 配置退出过滤器,使用shiro内置的退出逻辑
		filterChainDefinitionMap.put("/logout", "logout");

		// 静态资源不需要权限控制
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/error403", "anon");

		// 登录界面不需要权限控制
		filterChainDefinitionMap.put("/login", "anon");

		// 其他的资源访问都需要权限控制
		filterChainDefinitionMap.put("/**", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		// 设置默认login页面
		shiroFilterFactoryBean.setLoginUrl("/login");

		return shiroFilterFactoryBean;
	}

	/**
	 * 开始Shiro AOP权限注解支持
	 *
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

		// 映射：异常-视图/模板
		Properties mappings = new Properties();
		mappings.put(java.lang.Exception.class.getName(), "error/500");
		mappings.put(java.lang.RuntimeException.class.getName(), "error/500");
		mappings.put(org.apache.shiro.authz.UnauthenticatedException.class.getName(), "error/403");// 未登录
		mappings.put(org.apache.shiro.authz.UnauthorizedException.class.getName(), "error/403");// 未授权
		mappings.put(org.apache.shiro.session.InvalidSessionException.class.getName(), "login");// 会话超时
		exceptionResolver.setExceptionMappings(mappings);
		exceptionResolver.setDefaultErrorView("error/500");

		// 映射：视图/模板-状态码
		Properties statusCodes = new Properties();
		statusCodes.put("error/403", "403");
		statusCodes.put("error/404", "404");
		statusCodes.put("error/500", "500");
		exceptionResolver.setStatusCodes(statusCodes);

		// 设定日志输出级别
		exceptionResolver.setWarnLogCategory(SimpleMappingExceptionResolver.class.getName());

		return exceptionResolver;
	}

	@Bean
	public EhCacheManager cacheManager() {
		return new EhCacheManager();
	}

	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
}
