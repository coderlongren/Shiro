package com.coderlong.shrio.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
* @author ���� : coderlong
* @version ����ʱ�䣺2017��12��13�� ����9:23:09
* ��˵��: 
*/
public class TestRealm extends AuthorizingRealm {

	// ������Ȩ�ķ��� 
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		
		
		return null;
	}
	// ������֤�ķ��� 
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		
		
		return null;
	}

}
