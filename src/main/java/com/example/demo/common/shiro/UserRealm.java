package com.example.demo.common.shiro;
import com.example.demo.common.domin.SysUserDO;
import com.example.demo.system.dao.UserMapper;
import com.example.demo.system.service.MenuService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Shiro认证授权
 */
public class UserRealm extends AuthorizingRealm {
	@Autowired
	UserMapper userMapper;
	@Autowired
	MenuService menuService;

	/**
	 *授权
	 * @param arg0
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		Long userId = ShiroUtils.getUserId();
		Set<String> perms = menuService.listPerms(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(perms);
		return info;
	}

	/**
	 *认证 用户登陆
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		Map<String, Object> map  = new HashMap<>();
		map.put("username", username);
		String password = new String((char[]) token.getCredentials());
		List<SysUserDO> users=userMapper.list(map);
		if(null ==users ||users.size()==0){
			throw new UnknownAccountException("账号或密码不正确");
		}
		// 查询用户信息
		SysUserDO user = users.get(0);
		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}
		// 密码错误
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}
		// 账号锁定
		if (user.getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
