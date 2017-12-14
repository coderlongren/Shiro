package com.coderlong.service;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;

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
		System.out.println("value:" + session.getAttribute("name"));
//		Session session = SecurityUtils.getSubject().getSession();
//		Object val = session.getAttribute("key");
		
		System.out.println("Service SessionVal: ");
	}
}
