<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기 폼</title>

  <!-- 부트스트랩 라이브러리 등록 - CDN 방식 -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
<h1>게시판 글쓰기</h1>
<form action="write.jsp" id="writeForm" method="post">
<div class="form-group">
	<label for="title">제목</label>
	<input name="title" id="title" class="form-control" required="required"
	placeholder="제목을 4자이상 입력하셔야 합니다."/>
</div>
<div class="form-group">
	<label for="content">내용</label>
	<textarea name="content" id="content" rows="5" required="required"
	placeholder="내용은 4자 이상 입력하셔야 합니다." class="form-control"></textarea>
</div>
<div class="form-group">
	<label for="writer">작성자</label>
	<input name="writer" id="writer" class="form-control" required="required"
	placeholder="작성자를 2자이상 입력하셔야 합니다."/>
</div>
<button class="btn btn-default">등록</button>
<a href="list.do" class="btn btn-default">리스트</a>
</form>
</div>
</body>
</html>