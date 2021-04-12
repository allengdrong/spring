<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글수정 폼</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
<h1>게시판 글수정 폼</h1>
<form action="update.do" method="post">
<div class="form-group">
	<label for="no">번호</label>
	<input name="no" id="no" class="form-control" readonly="readonly" value="${vo.no}"/>
</div>
<div class="form-group">
	<label for="title">제목</label>
	<input name="title" id="title" class="form-control" required="required"
	placeholder="제목을 4자이상 입력하셔야 합니다." value="${vo.title }"/>
</div>
<div class="form-group">
	<label for="content">내용</label>
	<textarea name="content" id="content" rows="5" required="required"
	placeholder="내용은 4자 이상 입력하셔야 합니다." class="form-control">${vo.content }</textarea>
</div>
<div class="form-group">
	<label for="writer">작성자</label>
	<input name="writer" id="writer" class="form-control" required="required"
	placeholder="작성자를 2자이상 입력하셔야 합니다." value="${vo.writer }"/>
</div>
<button class="btn btn-default">수정</button>
<a href="list.do" class="btn btn-default">리스트</a>
</form>
</div>
</body>
</html>