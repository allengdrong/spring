<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
</style>
</head>
<body>
<h1>게시판 리스트</h1>
<table>
<tr>
	<th>글번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일</th>
	<th>조회수</th>
</tr>
<c:forEach items="${list }" var="vo">
	<tr class="dataRow" onclick="location='view.do?no=${vo.no }'">
		<td>${vo.no }</td>
		<td>${vo.title }</td>
		<td>${vo.writer }</td>
		<td>${vo.writeDate }</td>
		<td>${vo.hit }</td>
	</tr>
</c:forEach>
</table>
<a href="write.do">글쓰기</a>
</body>
</html>