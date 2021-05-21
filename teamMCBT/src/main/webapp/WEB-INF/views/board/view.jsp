<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글보기</title>

<!-- bootstrap 라이브러리 등록 - CDN 방식 : sitemesh에서 decorator.jsp에서 한꺼번에 해결-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- Awesome 4 icons lib : class="fa~ -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
#modifyReply {
	width: 600px;
	height: 130px;
	background-color: gray;
	padding: 10px;
	z-index: 10;
	visibility: hidden;
}
</style>

</head>
<body>
	<div class="container">
		<h1>게시판 글보기</h1>
		<c:choose>
			<c:when test="${vo.show == 'y'}">
				<!-- show가 y면 -->
				<!-- 게시물 상세보기 영역 -->
				<form name="form1" id="form1" method="post">
					<div>
						<!-- 원하는 날짜형식으로 출력하기 위해 fmt태그 사용 -->
						작성일자 :
						<fmt:formatDate value="${vo.writedate}"
							pattern="yyyy-MM-dd a HH:mm:ss" />
						<!-- 날짜 형식 => yyyy 4자리연도, MM 월, dd 일, a 오전/오후, HH 24시간제, hh 12시간제, mm 분, ss 초 -->
					</div>
					<div>조회수 : ${vo.hit}</div>
					<div>
						제목 <input name="title" id="title" size="80" value="${vo.title}"
							placeholder="제목을 입력해주세요">
					</div>
					<div>
						내용
						<textarea name="content" id="content" rows="4" cols="80"
							placeholder="내용을 입력해주세요">${vo.content}</textarea>
					</div>
					<div>
						이름
						${vo.writer}
					</div>
					<div style="width: 650px; text-align: center;">
						<!-- 게시물번호를 hidden으로 처리 -->
						<input type="hidden" name="no" value="${vo.no}">
						<!-- 본인이 쓴 게시물만 수정, 삭제가 가능하도록 처리 -->
						<c:if test="${sessionScope.userId == vo.writer}">
							<button type="button" id="btnUpdete">수정</button>
							<button type="button" id="btnDelete">삭제</button>
						</c:if>
						<!-- 상세보기 화면에서 게시글 목록화면으로 이동 -->
						<button type="button" id="btnList">목록</button>
					</div>
				</form>
				<!-- 게시물 상세보기 영역 -->

				<!-- 댓글 작성 영역 -->
				<div style="width: 650px; text-align: center;">
					<br>
					<!-- 로그인 한 회원에게만 댓글 작성폼이 보이게 처리 -->
					<c:if test="${sessionScope.userId != null}">
						<textarea rows="5" cols="80" id="replytext"
							placeholder="댓글을 작성해주세요"></textarea>
						<br>
						<!-- 비밀댓글 체크박스 -->
						<input type="checkbox" id="show">비밀 댓글
					<button type="button" id="btnReply">댓글 작성</button>
					</c:if>
				</div>
				<!-- 댓글 작성 영역 -->

			</c:when>
		<c:otherwise>
			<!-- show가 n이면 -->
			삭제된 게시글 입니다.
		</c:otherwise>
		</c:choose>
		<!-- 댓글 목록 영역 -->
<!-- 		<div id="listReply"></div> -->
		<!-- 댓글 목록 영역 -->
	</div>
</body>
</html>