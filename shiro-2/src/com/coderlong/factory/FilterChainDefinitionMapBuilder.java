package com.coderlong.factory;

import java.util.LinkedHashMap;

/**
* @author ���� : coderlong
* @version ����ʱ�䣺2017��12��14�� ����12:02:29
* ��˵��: 
*/
public class FilterChainDefinitionMapBuilder {
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		
		LinkedHashMap<String, String> map = new LinkedHashMap<>();

		map.put("/login.jsp", "anon");
		map.put("/shiro/login", "anon");
		map.put("/shiro/logout", "logout");
		map.put("/user.jsp", "authc,roles[user]");
		map.put("/admin.jsp", "authc,roles[admin]");
		map.put("/list.jsp", "user");
		
		map.put("/**", "authc");
		return map;
	}
}
