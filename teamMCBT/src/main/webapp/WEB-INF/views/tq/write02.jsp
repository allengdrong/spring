<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보기 작성 게시판</title>

<!-- Awesome 4 icons lib : class="fa~ -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- util JS 포함 -->
<script type="text/javascript" src="/js/util.js"></script>

<!-- Reply Module JS 포함 -->
<script type="text/javascript" src="/js/reply.js"></script>

<style type="text/css">
.title_label {
	border: 1px dotted #ddd;
}

ul.chat {
	list-style: none;
}

ul.chat > li{
	margin-bottom: 15px;
}


</style>

<script type="text/javascript">
// alert("aaa" + 10);

$(function(){
	// 모달 안에 삭제 버튼 이벤트
	$("#modal_deleteBtn").click(function(){
		$("#modal_form").submit();
	});
});

$(function(){
	// 보기 작성 버튼
	$("#ex_writeBtn").click(function(){
		$("#").submit();
	});
	
	
});

	

</script>

</head>
<body>
	<div class="container">
		<h1>문제 보기 작성 게시판</h1>
		<!-- 데이터 표시하는 부분 -->
		<ul class="list-group">
			<li class="list-group-item row">
				<div class="col-md-2 title_label">번호</div>
				<div class="col-md-10">${vo.no }</div>
			</li>
			<li class="list-group-item row">
				<div class="col-md-2 title_label">난이도</div>
				<div class="col-md-10">${vo.lev }</div>
			</li>
			<li class="list-group-item row">
				<div class="col-md-2 title_label">문제</div>
				<div class="col-md-10">${vo.quiz }</div>
			</li>
			
			<li class="list-group-item row">
				<div class="col-md-2 title_label">보기</div>
				<div class="col-md-8"><input name="id" type="text" class="form-control" id="id" required="required" ></div>
				<button type="button" class="btn btn-default" id="ex_writeBtn">보기등록</button>
			</li>
			
			<li class="list-group-item row">
				<div class="col-md-2 title_label">정답</div>
				<div class="col-md-10">${vo.right }</div>
			</li>
			<li class="list-group-item row">
				<div class="col-md-2 title_label">아이디</div>
				<div class="col-md-10">${vo.id }</div>
			</li>
			<li class="list-group-item row">
				<div class="col-md-2 title_label">작성일</div>
				<div class="col-md-10">
					<fmt:formatDate value="${vo.writeDate }" pattern="yyyy.MM.dd" />
					<fmt:formatDate value="${vo.writeDate }" pattern="hh:mm:ss" />
				</div>
			</li>
		</ul>
		<a
			href="update.do?no=${vo.no }&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}"
			class="btn btn-default">수정</a> <a class="btn btn-default"
			data-toggle="modal" data-target="#myModal" onclick="return false;">삭제</a>
		<a
			href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}"
			class="btn btn-default">리스트</a>
		
		</div>
		<!-- container 끝 -->

		<!-- Modal - 게시판 글 삭제시 사용되는 모달 창 -->
		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">게시판 글삭제 비밀번호 입력</h4>
					</div>
					<div class="modal-body">
						<form action="delete.do" method="post" id="modal_form">
							<input type="hidden" name="no" value="${vo.no }"> <input
								type="hidden" name="perPageNum"
								value="${pageObject.perPageNum }">
							<div class="form-group">
								<label>비밀번호 : </label> <input name="pw" type="password"
									class="form-control" id="pw" pattern="[^가-힣ㄱ-ㅎㅏ-]{4,20}"
									required="required" title="비밀번호는 4-20자까지 입력, 한글은 입력할 수 없습니다." />
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" id="modal_deleteBtn">삭제</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					</div>
				</div>

			</div>
		</div>
		<!-- Modal - 게시판 글 삭제시 사용되는 모달 창의 끝 -->

	
</body>
</html>