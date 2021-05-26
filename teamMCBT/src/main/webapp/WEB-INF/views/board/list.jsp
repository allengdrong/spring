<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>커뮤니티</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
<link rel="stylesheet" href="/css/board.css" />
<!-- <script src="/static/js/board.js"></script> -->
<script type="text/javascript">
$(function() {
	$("#writeBtn").click(function() {
		location.href = "${path}/board/write.do";
	});
	// 한페이지에 보여주는 데이터 선택의 이벤트 처리 -> 변경이 일어나면 처리
	$("#perPageNumSel").change(function() {
		let key = "${pageObject.key}";
		let word = "${pageObject.word}";
		// 다시 리스트 불러오기 - 전달 정보는 페이지:1, perPageNum을 선택된 값을 전달.
		location.href = "list.do?page=1&perPageNum=" + $(this).val() + "&key=" + key + "&word=" + word;
	});
	$(".dataRow").click(function(){
		var no = $(this).find(".no").text();
		var query = ${(empty pageObject)?"''":"'&page=" += pageObject.page
				+= "&perPageNum=" += pageObject.perPageNum += "'"};
		// 검색 정보 붙이기
		query += ${(empty pageObject.word)?"''":"'&key=" += pageObject.key
				+= "&word=" += pageObject.word += "'"};
		location = "view.do?no=" + no + "&inc=1" + query;
	});
});
</script>
</head>
<body>
	<div class="container">
		<!-- 숨김정보 -->
		<%
		Date now = new Date();
		%>
		<%
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sf.format(now);
		%>
		<h1 style="font-size: 20px; width: 100%;">Total : ${listCnt }</h1>

		<form>
			<input name="page" value="1" type="hidden" /> <input
				name="perPageNum" value="${pageObject.perPageNum }" type="hidden" />
			<div class="top-bar">
				<select name="key" class="selectOption classStyle">
					<option value="tcw" ${(pageObject.key == "tcw")?"selected":"" }>전체</option>
					<option value="t" ${(pageObject.key == "t")?"selected":"" }>제목</option>
					<option value="c" ${(pageObject.key == "c")?"selected":"" }>내용</option>
					<option value="w" ${(pageObject.key == "w")?"selected":"" }>작성자</option>
				</select>
				<div class="searchBox selectOption classStyle">
					<input type="text" maxlength="20" class="searchWord" name="word"
						value="${pageObject.word }" placeholder="Search"
						autocomplete="off">

					<button class="searchBtn btn" type="submit">
						<i class="fas fa-search"></i>
					</button>
				</div>
				<button id="writeBtn" type="button" class="writeBtn selectOption">글쓰기</button>
				<select id="perPageNumSel" class="selectOption classStyle">
					<c:choose>
						<c:when test="${pageObject.perPageNum == 5}">
							<option value="5" selected>5개</option>
						</c:when>
						<c:otherwise>
							<option value="5">5개</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${pageObject.perPageNum == 10}">
							<option value="10" selected>10개</option>
						</c:when>
						<c:otherwise>
							<option value="10">10개</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${pageObject.perPageNum == 15}">
							<option value="15" selected>15개</option>
						</c:when>
						<c:otherwise>
							<option value="15">15개</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${pageObject.perPageNum == 20}">
							<option value="20" selected>20개</option>
						</c:when>
						<c:otherwise>
							<option value="20">20개</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${pageObject.perPageNum == 30}">
							<option value="30" selected>30개</option>
						</c:when>
						<c:otherwise>
							<option value="30">30개</option>
						</c:otherwise>
					</c:choose>
				</select>
			</div>
		</form>
		<div class="board">
			<table class="board">
				<c:if test="${empty list }">
					<tr>
						<th colspan="6">해당하는 게시글이 없습니다.</th>
					</tr>
				</c:if>
				<c:if test="${!empty list }">
					<tr>
						<th>#</th>
						<th></th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
					<c:forEach var="vo" items="${list}">
						<c:choose>
							<c:when test="${vo.show == 'Y'}">
								<tr class="board dataRow">
									<td><span class="no">${vo.no}</span></td>
									<td><span class="category">[${vo.category }]</span></td>
									<td>${vo.title }<c:if test="${vo.replyhit != 0}">
											<span class="reply"> [${vo.replyhit}]</span>
										</c:if></td>
									<td>${vo.writer }</td>
									<c:set var="today" value="<%=today%>" />
									<fmt:formatDate value="${vo.writedate}" pattern="yyyy-MM-dd"
										var="searchFormated" />
									<c:set var="todayVar" value="${searchFormated}" />
									<td><c:if test="${today == todayVar}">
											<fmt:formatDate value="${vo.writedate}" pattern="HH:mm" />
										</c:if> <c:if test="${today != todayVar}">
											<fmt:formatDate value="${vo.writedate}" pattern="yyyy.MM.dd." />
										</c:if></td>
									<td>${vo.hit}</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td><span class="no">${vo.no}</span></td>
									<td></td>
									<td>삭제된 게시글 입니다.<c:if test="${vo.replyhit != 0}">
											<span class="reply"> [${vo.replyhit}]</span>
										</c:if></td>
									<td>익명</td>
									<c:set var="today" value="<%=today%>" />
									<fmt:formatDate value="${vo.writedate}" pattern="yyyy-MM-dd"
										var="searchFormated" />
									<c:set var="todayVar" value="${searchFormated}" />
									<td><c:if test="${today == todayVar}">
											<fmt:formatDate value="${vo.writedate}" pattern="HH:mm" />
										</c:if> <c:if test="${today != todayVar}">
											<fmt:formatDate value="${vo.writedate}" pattern="yyyy.MM.dd." />
										</c:if></td>
									<td>${vo.hit}</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
			</table>
		</div>
		<div class="articlePaginate">
			<pageObject:pageNav listURI="list.do" pageObject="${pageObject }"
			query="&key=${pageObject.key }&word=${pageObject.word }" />
		</div>
	</div>
</body>
</html>