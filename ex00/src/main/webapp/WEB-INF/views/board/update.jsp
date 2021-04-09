<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8 ">
<title>게시판 글수정 폼</title>
</head>
<body>
<h1>게시판 글수정 폼</h1>
<div>${vo }</div>
<form action="update.do" method="post">
	번호 : <input name = "no" value="${vo.no }" readonly="readonly" /><br/>
	제목 : <input name = "title" value="${vo.title }" /><br/>
	내용 : <textarea rows="7" cols="80" name="content">${vo.content }</textarea><br/>
	작성자 : <input name = "writer" value="${vo.writer }" /><br/>
	<button>수정</button>
</form>

</body>
</html>