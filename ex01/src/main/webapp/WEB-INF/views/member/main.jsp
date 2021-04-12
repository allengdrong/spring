<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
<h1>메인 페이지</h1>
<c:if test="${empty id }">
	<button onclick="location='login'">로그인</button>
</c:if>
<c:if test="${!empty id }">
	<div>${id }</div>
	<button onclick="location='logout'">로그아웃</button>
</c:if>


</body>
</html>