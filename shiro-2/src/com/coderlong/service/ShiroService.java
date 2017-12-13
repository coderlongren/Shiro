package com.coderlong.service;

import org.apache.shiro.authz.annotation.RequiresRoles;

/**
* @author 作者 : coderlong
* @version 创建时间：2017年12月13日 下午10:26:52
* 类说明: 
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
