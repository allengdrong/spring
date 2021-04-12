<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글보기</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
<div class = "container">
<h1>게시판 글보기</h1>
<table class="table">
		<tr>
			<th>글번호</th>
			<td class="no">${vo.no }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${vo.title }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><pre style="background: #fff; border: none; padding: 0px;">${vo.content }</pre></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${vo.writer }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><fmt:formatDate value="${vo.writeDate }"  pattern="yyyy년 MM월 dd일" /></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${vo.hit }</td>
		</tr>
		<tfoot>
		<tr>
			<td colspan="2">
<a href="update.do?no=${vo.no }" class="btn btn-default">글수정</a>
<a href="delete.do?no=${vo.no }" class="btn btn-default">글삭제</a>
<a href="list.do" class="btn btn-default">리스트</a>
</td>
		</tr>
	</tfoot>
</table>
</div>
</body>
</html>