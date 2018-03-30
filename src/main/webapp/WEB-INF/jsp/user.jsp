<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>用户信息</h1>
<div>
	<h4>姓名:${user.name}</h4>
	<h4>地址:${user.address}</h4>
	<h4>ID:${user.id}</h4>
	<h5><a href="${pageContext.request.contextPath}/file/select">上传照片</a></h5>
</div>
</body>
</html>