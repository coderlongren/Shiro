package com.coderlong.shrio.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
* @author 作者 : coderlong
* @version 创建时间：2017年12月13日 下午9:23:09
* 类说明: 
*/
public class TestRealm extends AuthorizingRealm {

	// 用于授权的方法 
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		
		
		return null;
	}
	// 用于认证的方法 
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		
		
		return null;
	}

}
