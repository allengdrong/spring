<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기</title>

<!-- bootstrap 라이브러리 등록 CDN방식 : sitemesh에서 decorator.jsp에서 한꺼번에 해결 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function(){
		$(".cancelBackBtn").click(function(){
			history.back();
		});
	});
</script>

</head>
<body>
<div class="container">
		<h1>공지사항 글쓰기 Form</h1>
		<form action="write.do" method="post">
		<input name="perPageNum" type="hidden" value="${param.perPageNum }">
			<div class="form-group">
				<label for="title">제목:</label>
				<!-- required : 필수 입력, placeholder : 입력의 초기 안내,
					 pattern : 정규표현식으로 유효성 검사, title : 패턴이 맞지 않을때 나오는 메시지 -->
				<input name="title" type="text" class="form-control" id="title"
				pattern=".{4,100}" maxlength="100" required="required"
				title="4자이상 100자 이하 입력" placeholder="4자이상 100자 이하 입력">
			</div>
			<div class="form-group">
				<label for="content">내용:</label>
				<textarea name="content" class="form-control" rows="7"
				placeholder="내용은 4자이상 700자 이하 입력" id="content"></textarea>
			</div>
			<div class="form-group">
				<label for="startDate">공지시작일:</label> 
				<input name="startDate" type="date" class="form-control" id="startDate" >
			</div>
			<div class="form-group">
				<label for="endDate">공지종료일:</label> 
				<input name="endDate" type="date" class="form-control" id="endDate" >
			</div>

			<button class="btn btn-default">등록</button>
			<button class="btn btn-default" type="reset">새로입력</button>
			<button class="btn btn-default cancelBackBtn" type="button">취소</button>
		</form>
	</div>
</body>
</html>