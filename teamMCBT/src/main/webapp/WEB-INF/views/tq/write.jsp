<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제 출제 게시판 글쓰기</title>

<!-- bootstrap 라이브러리 등록 CDN방식 : sitemesh에서 decorator.jsp에서 한꺼번에 해결 -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<!-- <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<!-- <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->

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
		<h1>문제 출제 게시판 글쓰기 Form</h1>
		<form action="write.do" method="post">
		<input name="perPageNum" type="hidden" value="${param.perPageNum }">
			<div class="form-group">
			<label for="lev">난이도:</label>
			<label class="radio-inline"><input type="radio" name="optradio">상</label>
			<label class="radio-inline"><input type="radio" name="optradio">중</label>
			<label class="radio-inline"><input type="radio" name="optradio">하</label>
			</div>
		
			<div class="form-group">
				<label for="quiz">문제:</label>
				<!-- required : 필수 입력, placeholder : 입력의 초기 안내,
					 pattern : 정규표현식으로 유효성 검사, title : 패턴이 맞지 않을때 나오는 메시지 -->
				<input name="quiz" type="text" class="form-control" id="quiz"
				pattern=".{4,100}" maxlength="100" required="required"
				title="4자이상 100자 이하 입력" placeholder="4자이상 100자 이하 입력">
			</div>
			<div class="form-group">
				<label for="">보기:</label>
					<div class="radio">
				  <label><input type="radio" name="optradio">문제보기1</label>
				</div>
				<div class="radio">
				  <label><input type="radio" name="optradio">문제보기2</label>
				</div>
				<div class="radio">
				  <label><input type="radio" name="optradio">문제보기3</label>
				</div>
				<div class="radio">
				  <label><input type="radio" name="optradio">문제보기4</label>
				</div>
			</div>
			<div class="form-group">
				<label for="id">아이디:</label> <input name="id" type="text" value="${id }"
					class="form-control" id="id"
					required="required" readonly="readonly">
			</div>
<!-- 			<div class="form-group"> -->
<!-- 				<label for="pw">비밀번호:</label> <input name="pw" type="text" -->
<!-- 					class="form-control" id="pw" -->
<!-- 					pattern="[^가-힣ㄱ-ㅎㅏ-]{4,20}" required="required" -->
<!-- 					title="비밀번호는 4-20자까지 입력, 한글은 입력할 수 없습니다."> -->
<!-- 			</div> -->

			<button class="btn btn-default">등록</button>
			<button class="btn btn-default" type="reset">새로입력</button>
			<button class="btn btn-default cancelBackBtn" type="button">취소</button>
		</form>
	</div>
</body>
</html>