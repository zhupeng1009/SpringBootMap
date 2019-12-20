package com.example.demo.common.shiro;

import com.example.demo.common.domin.SysUserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * shiro 获取用户相关公共信息
 */
public class ShiroUtils {
	/**
	 * 获取Subject
	 * @return
	 */
	public static Subject getSubjct() {
		return SecurityUtils.getSubject();
	}

	/**
	 *获取用户详细信息
	 * @return
	 */
	public static SysUserDO getUser() {
		return (SysUserDO)getSubjct().getPrincipal();
	}

	/**
	 * 获取用户id
	 * @return
	 */
	public static Long getUserId() {
		return getUser().getUserId();
	}

	/**
	 * 登出
	 */
	public static void logout() {
		getSubjct().logout();
	}
}
