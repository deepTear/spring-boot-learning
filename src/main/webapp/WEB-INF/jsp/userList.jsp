<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>用户信息列表</h1>
<div>
	总条数(${total})
	<table width="500px" cellpadding="0" cellspacing="0">
		<th>
			<td>ID</td>
			<td>NAME</td>
			<td>BIRTHDAY</td>
			<td>ADDRESS</td>
		</th>
		<c:choose>
			<c:when test="${not empty users && fn:length(users) > 0}">
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.name}</td>
						<td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${user.address}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td rowspan="3">无数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
</div>
</body>
</html>