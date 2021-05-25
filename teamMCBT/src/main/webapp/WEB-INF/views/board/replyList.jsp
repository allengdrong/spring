<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table style="width:700px">
		<!-- 댓글 목록 -->
		<c:forEach var="vo" items="${list}">
		<tr>	
			<td>
				${vo.replyer}(<fmt:formatDate value="${vo.replydate}" pattern="yyyy-MM-dd HH:mm:ss"/>)
				<br>
				${vo.replycontent}
				<br>
				<!-- 본인 댓글만 수정버튼 생성되도록 처리 -->
				<input type="button" id="btnModify" value="수정" onclick="showReplyModify('${vo.rno}')">
				<hr>
			</td>
		</tr>
		</c:forEach>
	</table>
	
	<!-- 댓글 수정 영역-->
	<div id="modifyReply"></div>
</body>
</html>