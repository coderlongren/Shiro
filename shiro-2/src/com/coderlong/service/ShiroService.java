package com.coderlong.service;

import org.apache.shiro.authz.annotation.RequiresRoles;

/**
* @author ���� : coderlong
* @version ����ʱ�䣺2017��12��13�� ����10:26:52
* ��˵��: 
*/
public class ShiroService {
	@RequiresRoles({"admin"})
	public void testMethod(){
		System.out.println("testMethod, time: " + new Date());
		
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("key");
		
		System.out.println("Service SessionVal: " + val);
	}
}
