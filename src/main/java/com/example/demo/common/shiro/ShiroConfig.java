package com.example.demo.common.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro 拦截模块
 */
@Configuration
public class ShiroConfig {
	@Bean
	public UserRealm userRealm() {
		UserRealm userRealm = new UserRealm();
		return userRealm;
	}

	/**
	 * 认证
 	 * @return
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(userRealm());
		return manager;
	}

	/**
	 * shiro 过滤器设置
	 * @return
	 */

	/**
	 * 当运行一个Web应用程序时,Shiro将会创建一些有用的默认Filter实例,并自动地在[main]项中将它们置为可用自动地可用的默认的Filter实例是被DefaultFilter枚举类定义的,枚举的名称字段就是可供配置的名称
	 * anon---------------org.apache.shiro.web.filter.authc.AnonymousFilter 没有参数，表示可以匿名使用。
	 * authc--------------org.apache.shiro.web.filter.authc.FormAuthenticationFilter 表示需要认证(登录)才能使用，没有参数
	 * authcBasic---------org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter 没有参数表示httpBasic认证
	 * logout-------------org.apache.shiro.web.filter.authc.LogoutFilter
	 * noSessionCreation--org.apache.shiro.web.filter.session.NoSessionCreationFilter
	 * perms--------------org.apache.shiro.web.filter.authz.PermissionAuthorizationFilter 参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
	 * port---------------org.apache.shiro.web.filter.authz.PortFilter port[8081],当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。
	 * rest---------------org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter 根据请求的方法，相当于/admins/user/**=perms[user:method] ,其中method为post，get，delete等。
	 * roles--------------org.apache.shiro.web.filter.authz.RolesAuthorizationFilter 参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，例如admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。
	 * ssl----------------org.apache.shiro.web.filter.authz.SslFilter 没有参数，表示安全的url请求，协议为https
	 * user---------------org.apache.shiro.web.filter.authz.UserFilter 没有参数表示必须存在用户，当登入操作时不做检查

	  * 通常可将这些过滤器分为两组
	 * anon,authc,authcBasic,user是第一组认证过滤器
	 * perms,port,rest,roles,ssl是第二组授权过滤器
	 * 注意user和authc不同：当应用开启了rememberMe时,用户下次访问时可以是一个user,但绝不会是authc,因为authc是需要重新认证的
	 * user表示用户不一定已通过认证,只要曾被Shiro记住过登录状态的用户就可以正常发起请求,比如rememberMe 说白了,以前的一个用户登录时开启了rememberMe,然后他关闭浏览器,下次再访问时他就是一个user,而不会authc
	 *
	 *
	 * 举几个例子
	 *  /admin=authc,roles[admin]      表示用户必需已通过认证,并拥有admin角色才可以正常发起'/admin'请求
	 *  /edit=authc,perms[admin:edit]  表示用户必需已通过认证,并拥有admin:edit权限才可以正常发起'/edit'请求
	 *  /home=user     表示用户不一定需要已经通过认证,只需要曾经被Shiro记住过登录状态就可以正常发起'/home'请求

	 * 各默认过滤器常用如下(注意URL Pattern里用到的是两颗星,这样才能实现任意层次的全匹配)
	 *  /admins/**=anon             无参,表示可匿名使用,可以理解为匿名用户或游客
	 *  /admins/user/**=authc       无参,表示需认证才能使用
	 *  /admins/user/**=authcBasic  无参,表示httpBasic认证
	 *  /admins/user/**=ssl         无参,表示安全的URL请求,协议为https
	 *  /admins/user/**=perms[user:add:*]  参数可写多个,多参时必须加上引号,且参数之间用逗号分割,如/admins/user/**=perms["user:add:*,user:modify:*"]。当有多个参数时必须每个参数都通过才算通过,相当于isPermitedAll()方法
	 *  /admins/user/**=port[8081] 当请求的URL端口不是8081时,跳转到schemal://serverName:8081?queryString。其中schmal是协议http或https等,serverName是你访问的Host,8081是Port端口,queryString是你访问的URL里的?后面的参数
	 *  /admins/user/**=rest[user] 根据请求的方法,相当于/admins/user/**=perms[user:method],其中method为post,get,delete等
	 *  /admins/user/**=roles[admin]  参数可写多个,多个时必须加上引号,且参数之间用逗号分割,如：/admins/user/**=roles["admin,guest"]。当有多个参数时必须每个参数都通过才算通过,相当于hasAllRoles()方法
	 *
	 */


	//Shiro验证URL时,URL匹配成功便不再继续匹配查找(所以要注意配置文件中的URL顺序,尤其在使用通配符时)

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		//当此用户是一个非认证用户,需要先登陆进行认证
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		//下边表示允许匿名访问的文件夹 ， 前面为固定的文件夹的路径（根据自己的醒目而定例如图片等等）
		//anon 都可以访问 logout 退出  authc 需要鉴权
		Map<String, String> filterChainDefinitionMap = new HashMap<>();
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/docs/**", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/blog", "anon");
		filterChainDefinitionMap.put("/map/**", "anon");
		//filterChainDefinitionMap.put("/webjars/**", "anon");
		//退出系统
		filterChainDefinitionMap.put("/logout", "logout");
		// 其中/** 表示所有请求  authc 表示需要鉴权 这个必须放最后 上面所有请求过滤后，剩余的请求在继续拦截（是否修改为配置文件会比较好
		// 即使修改部分需求后 也可以通过配置文件实现快速启动，而不需要去再次修改代码 ？）
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	/**
	 * Shiro生命周期处理器 ---可以自定的来调用配置在 Spring IOC 容器中 shiro bean 的生命周期方法.
	 *
	 * @return
	 */

	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	/**
	 * 开启shiro注解 ----启用 IOC 容器中使用 shiro 的注解. 但必须在配置了 LifecycleBeanPostProcessor
	 * 之后才可以使用
	 *
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
		proxyCreator.setProxyTargetClass(true);
		return proxyCreator;
	}

	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
	/**
	 * 开启shiro注解 ---- 注解权限
	 *
	 * @param securityManager
	 * @return
	 */

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

}
