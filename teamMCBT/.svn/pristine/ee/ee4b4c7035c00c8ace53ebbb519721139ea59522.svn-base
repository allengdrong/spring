<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제 등록</title>
<!-- bootstrap 라이브러리 등록 / CDN 방식 : sitemesh decoreator.jsp에서 한꺼번에 해결  -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<form action="testReg01.do" method="post">
	<input name="perPageNum" value="${param.perPageNum }" type="hidden">
	<div class="form-group">
		<label for="lev">난이도</label><br>
			<input type="radio" id="top" name="lev" value="top" >
			<label for="top">상</label>
			<input type="radio" id="mid" name="lev" value="mid">
			<label for="mid">중</label>
			<input type="radio" id="bottom" name="lev" value="bottom">
			<label for="bottom">하</label> 
			
	</div>
	<div class="form-group">
		<label for="quiz">문제</label>
		<input type="text" id="quiz" name="quiz" class="form-control">
	</div>
	
	
	 <button class="btn wBtn">등록</button>
    <button type="reset" class="btn wBtn">새로입력</button>
    <button type="button" class="cancelBtn btn wBtn">취소</button>
</form>
</div>
</body>
</html>