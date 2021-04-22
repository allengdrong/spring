<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글보기</title>

<!-- bootstrap 라이브러리 등록 CDN방식 : sitemesh에서 decorator.jsp에서 한꺼번에 해결 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

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
	${(empty msg)?"":"alert('"+= msg +="');"}
	// F5, ctrl + F5, ctrl + r 새로고침 막기
	/*
	$(document).keydown(function (e) {
	     
	            if (e.which === 116) {
	                if (typeof event == "object") {
	                    event.keyCode = 0;
	                }
	                return false;
	            } else if (e.which === 82 && e.ctrlKey) {
	                return false;
	            }
	}); 
	*/
	// 모달 안에 삭제 버튼 이벤트
	$("#modal_deleteBtn").click(function(){
		$("#modal_form").submit();
	});
	
	// 댓글 처리 JS 부분
	console.log("==================================");
	console.log("JS Reply List Test");
	
	// 전역 변수 선언 - $(function(){~~}); 36번줄 안에 선언된 함수에서는 공통으로 사용 가능
	var no = ${vo.no};
	console.log("JS Reply List no : " + no);
	var repPage = 1;
	var repPerPageNum = 5;
	var replyUL = $(".chat");
	
	// 글보기를 하면 바로 댓글 리스트 호출
	showList();
	
	// function showList() - no, page, perPageNum : 전역변수로 선언되어 있으므로 변수명 사용
	function showList(){
	replyService.list(
			// 서버에 넘겨줄 데이터
			{no:no, repPage:repPage, repPerPageNum:repPerPageNum},
			// 성공했을 때의 함수. data라는 이름으로 list가 들어온다.
			function(list){
				// list 데이터 확인
				// alert(list);
				var str = "";
				// li 태그 만들기----------------------------
				// 데이터가 없을 때의 처리
				if(!list || list.length == 0){
					// alert("데이터 없음");
					str += "<li>데이터가 존재하지 않습니다.</li>"
				} else{ // 데이터가 있을 때의 처리
					// alert("데이터 있음");
				for(var i = 0; i < list.length; i++){
					console.log(list[i]);
					// tag 만들기 - 데이터 한개당 li tag 하나가 생긴다.
					str += "<li class='left clearfix' data-rno='"+ list[i].rno +"'>";
					str += "<div>";
					str += "<div class='header'>";
					str += "<strong class='primary-font'>" + list[i].writer + "</strong>";
					str += "<small class='pull-right text-muted'>" 
							+ replyService.displayTime(list[i].writeDate)
							+ "</small>";
					str += "</div>";
					str += "<p><pre style='background:none;'>" + list[i].content + "</pre></p>";
					str += "<div class='text-right'>";
					str += "<button class='btn btn-default btn-xs replyUpdateBtn'>수정</button>";
					str += "<button class='btn btn-default btn-xs replyDeleteBtn'>삭제</button>";
					str += "</div>";
					str += "</div>";
					str += "</li>";
					}
				}
				replyUL.html(str);
			}
		);
	} // showList()의 끝
	
	// 댓글 모달창의 전역 변수
	var replyModal = $("#replyModal");
	
	// 댓글 등록 버튼 이벤트 처리 : 댓글의 모달창 정보 조정과 보이기 --------------------------------
	$("#writeReplyBtn").click(function(){
		// alert("댓글등록");
		
		// 댓글 모달 창의 제목 바꾸기
		$("#replyModalTitle").text("Reply Write");
		
		// reply > Form input 데이터 지우기 : input 중에서 id="replyNo"는 제외시킨다. not
		replyModal.find("input, textarea").not("#replyNo").val("");
		
		// replyModal에 있는 입력 항목을 다 보이게
		replyModal.find("div.form-control").show();
		// rno는 사용하지 않기 때문에 보이지 않게 한다.
		replyModal.find("#replyRnoDiv").hide();
		// replyModal.find("#replyNoDiv").hide();
		
		replyModal.modal("show");
	});
	
	// 댓글 모달창의 등록 버튼에 대한 이벤트 처리 - 입력된 데이터를 가져와서 JSON 데이터 만들기 - 서버에 전송
	$("#replyModalWriteBtn").click(function(){
		var reply = {};
		reply.no = $("#replyNo").val();
		reply.content = $("#replyContent").val();
		reply.writer = $("#replyWriter").val();
		reply.pw = $("#replyPw").val();
		reply.perPageNum = repPerPageNum;
	// alert(reply);
	// alert(JSON.stringify(reply));
		
		// ajax를 이용한 댓글 등록 처리
		replyService.write(reply,
				// 성공했을때의 처리 함수
				function(result){
					alert(result);
					replyModal.modal("hide");
					showList();
			}
		);	
	});
	
	// 댓글 수정 폼 : 모달 (replyModal )----------------------------------
	$(".chat").on("click", ".replyUpdateBtn",function(){
		// alert("댓글 수정");
		replyModal.modal("show");
	});
	
	// 댓글 삭제 폼 : 모달 (replyModal )----------------------------------
	$(".chat").on("click", ".replyDeleteBtn", function(){
		// alert("댓글 삭제");
		replyModal.modal("show");
	});
	
	
	
});

</script>

</head>
<body>
	<div class="container">
		<h1>게시판 글보기</h1>
		<!-- 데이터 표시하는 부분 -->
		<ul class="list-group">
			<li class="list-group-item row">
				<div class="col-md-2 title_label">번호</div>
				<div class="col-md-10">${vo.no }</div>
			</li>
			<li class="list-group-item row">
				<div class="col-md-2 title_label">제목</div>
				<div class="col-md-10">${vo.title }</div>
			</li>
			<li class="list-group-item row">
				<div class="col-md-2 title_label">내용</div>
				<div class="col-md-10">
					<pre>${vo.content }</pre>
				</div>
			</li>
			<li class="list-group-item row">
				<div class="col-md-2 title_label">작성자</div>
				<div class="col-md-10">${vo.writer }</div>
			</li>
			<li class="list-group-item row">
				<div class="col-md-2 title_label">작성일</div>
				<div class="col-md-10">
					<fmt:formatDate value="${vo.writeDate }" pattern="yyyy.MM.dd" />
					<fmt:formatDate value="${vo.writeDate }" pattern="hh:mm:ss" />
				</div>
			</li>
			<li class="list-group-item row">
				<div class="col-md-2 title_label">조회수</div>
				<div class="col-md-10">${vo.hit }</div>
			</li>
		</ul>
		<a
			href="update.do?no=${vo.no }&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}"
			class="btn btn-default">수정</a> <a class="btn btn-default"
			data-toggle="modal" data-target="#myModal" onclick="return false;">삭제</a>
		<a
			href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}"
			class="btn btn-default">리스트</a>

		<!-- 댓글의 시작 -->
		<div class="row" style="margin-top: 20px- 40px;">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-comments fa-fw"></i> 댓글
						<button class="btn btn-primary btn-xs pull-right"
							id="writeReplyBtn">댓글 쓰기</button>
					</div>
					<div class="panel-body">
						<ul class="chat">
							<!-- 데이터가 있는 만큼 반복 처리 li태그 만들어 내기 -->
							<!-- rno를 표시하지 않고 태그 안에 속성으로 숨겨 놓음 data-rno="12" -->
							<li class="left clearfix" data-rno="12">
								<div>
									<div class="header">
										<strong class="primary-font">user00</strong> <small
											class="pull-right text-muted">2021.04.21 14:21</small>
									</div>
									<p>Good job!</p>
									<div class="text-right">
										<button class="btn btn-default btn-xs replyUpdateBtn">수정</button>
										<button class="btn btn-default btn-xs replyDeleteBtn">삭제</button>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- 댓글의 끝 -->
		
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

		<!-- Modal - 댓글 쓰기 / 수정 시 사용되는 모달 창 -->
		<div id="replyModal" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">
							<i class="fa fa-comments fa-fw"></i>
								<span id="replyModalTitle">Reply Modal</span></h4>
					</div>
					<div class="modal-body">
						<form>
							<div class="form-group" id="replyRnoDiv">
								<label for="replyRno">댓글번호:</label> 
								<input name="rno"type="text" class="form-control" id="replyRno"
								readonly="readonly">
							</div>
							<div class="form-group" id="replyNoDiv">
								<label for="replyNo">게시판 번호:</label> 
								<input name="no"type="text" class="form-control" id="replyNo"
								readonly="readonly" value="${vo. no }">
							</div>
							<div class="form-group" id="replyContentDiv">
								<label for="replyContent">내용:</label>
								<textarea name="content" class="form-control" rows="3" id="replyContent"
								required="required"></textarea>
							</div>
							<div class="form-group" id="replyWriterDiv">
								<label for="replyWriter">작성자:</label> 
								<input name="writer"type="text" class="form-control" id="replyWriter"
								required="required" pattern="[A-Za-z까-힣][A-Za-z까-힣0-9]{1,9}">
							</div>
							<div class="form-group" id="replyPwDiv">
								<label for="replyPW">비밀번호:</label> 
								<input name="pw"type="text" class="form-control" id="replyPw"
								required="required" pattern=".{4,20}">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" id="replyModalWriteBtn">등록</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					</div>
				</div>

			</div>
		</div>
		<!-- Modal - 댓글 쓰기 / 수정 시 사용되는 모달 창의 끝 -->
</body>
</html>