<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Welcome <shiro:principal></shiro:principal>	</br>
	<shiro:hasRole name="admin">
		<a href="admin.jsp">admin page</a>
		</br>
	</shiro:hasRole>
	<a href="user.jsp">User page</a>
	</br>
	<a href="shiro/logout">logout</a>
	</br>
	<a href="shiro/testMethod">testMethod</a>
</body>
</html>