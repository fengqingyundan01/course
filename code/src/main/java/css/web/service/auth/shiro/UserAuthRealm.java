package css.web.service.auth.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.google.common.collect.Sets;

/**
 * 用户权限认证
 *
 * @author yangshuo
 *
 */
public class UserAuthRealm extends AuthorizingRealm {

	/**
	 * 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRoles(Sets.newHashSet("manager", "student"));
		authorizationInfo.addStringPermissions(Sets.newHashSet("perm1", "perm2"));
		return authorizationInfo;
	}

	/**
	 * 身份认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		return new SimpleAuthenticationInfo();

	}

}
