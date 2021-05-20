<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
</head>
<body>
	<h1>회원 리스트</h1>
	<form action="list.do" method="post">
	<table border="1" width="700px">
		<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>닉네임</th>
		<th>이메일</th>
		<th>전화번호</th>
		<th>가입일자</th>
		</tr>
		<c:forEach var="row" items="${list }">
		<tr>
			<td>${row.id }</td>
			<td>${row.name }</td>
			<td>${row.alias }</td>
			<td>${row.email }</td>
			<td>${row.tel }</td>
			<td>${row.regDate }</td>
			</tr>
			</c:forEach>
			</form>
	</table>
</body>
</html>