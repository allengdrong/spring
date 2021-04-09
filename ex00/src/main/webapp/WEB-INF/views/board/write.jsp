<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기 폼</title>
</head>
<body>
<h1>게시판 글쓰기 폼</h1>
<form action="write.do" method="post">
	제목 <input name="title"><br/>
	내용 <textarea rows="7" cols="80" name="content"></textarea><br/>
	작성자 <input name="writer"><br/> 
	<button>등록</button>
</form>

</body>
</html>