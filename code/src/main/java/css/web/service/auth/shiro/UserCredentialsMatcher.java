package css.web.service.auth.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * 用户认证
 * 
 * @author yangshuo
 *
 */
public class UserCredentialsMatcher extends HashedCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		return true;
	}

}
