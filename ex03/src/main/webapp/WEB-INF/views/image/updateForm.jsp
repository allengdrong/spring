<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.image.vo.ImageVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 자바 부분
// 전달되는 데이터 받기
String strNo = request.getParameter("no");
String strCurPage = request.getParameter("page");
String strPerPageNum = request.getParameter("perPageNum");

Long no = Long.parseLong(strNo);

// DB에서 수정할 데이터 가져오기
ImageVO vo = (ImageVO) ExeService.execute(Beans.get("/image/view.jsp"), no);

// 서버 객체에 담기
request.setAttribute("vo", vo);
request.setAttribute("path", request.getContextPath());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 정보 수정</title>

<script type="text/javascript">
$(function(){
	// 전달할 때의 데이터 찍기
	$("#writeForm").submit(function(){
		// alert("submit()");
		// alert($("#title").val());
		// alert($("#content").val());
		// C:\fakepath\flower01.jpg
		// alert($("#imageFile").val());
		// 첨부파일이 이미지 파일인지 알아내는 프로그램 작성 -> 확장자 : 파일명의 마지막 "." 이후의 글자
		var fileName = $("#imageFile").val();
		// substring(시작[, 끝]) - 부분 문자열 잘라내기
		// lastIndexOf(찾는 문자열) - 뒤에서 부터 찾는 문자열의 위치. 찾는 문자열이 없으면 -1이 된다.
		// toUpperCase() -> 모든 영문자를 대문자로 만들어 준다. <--> toLowerCase()
		var ext = fileName.substring(fileName.lastIndexOf(".")+1).toUpperCase();
		// alert(ext);
		
		// 이미지 확장자인지 확인하는 반복문
		var checkExt = false; // 지원하지 않는 확장자를 기본으로 셋팅
		for(i = 0; i < imageExt.length; i++){
			if(ext == imageExt[i]){
				checkExt = true; // 지원하는 확장자로 바꾼다.
				break;
			}
		}
		// 지원하지 않는 이미지 파일 선택경의 처리
		if(!checkExt){
			alert("지원하지 않는 이미지 파일입니다.");
			$("#imageFile").focus();
			return false;
		}
		
		// submit을 취소
		// return false;
	});
});
</script>

</head>
<body>
<div class="container">
	<h1>이미지 등록</h1>
	<!-- 파일첨부를 하는 입력에는 반드시 post방식이여야 하고 enctype 을 지정해야만 한다.
	  input tag의 type="file"로 지정한다. -->
	<form action="update.jsp" method="post" enctype="multipart/form-data" id="writeForm" >
		<input name="page" value="${param.page }" type="hidden">
		<input name="perPageNum" value="${param.perPageNum }" type="hidden">
		<div class="form-group">
			<label for="no">번호</label>
			<input name="no" id="no" class="form-control" value="${vo.no }" 
			readonly="readonly"/>
		</div>
		<div class="form-group">
			<label for="title">제목</label>
			<input name="title" id="title" class="form-control" value="${vo.title }" />
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea name="content" id="content" class="form-control" rows="5"
			 >${vo.content }</textarea>
		</div>
		<button class="btn btn-defualt">수정</button>
		<button type="reset" class="btn btn-defualt">새로입력</button>
		<button type="button" id="cancelBtn" class="btn btn-defualt">취소</button>
	</form>
</div>
</body>
</html>