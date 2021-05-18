/**
 * 여러가지 Utility 함수 선언
 */
 
 // 날짜객체를 날짜 문자열로 표시하는 함수 - dateToDateStr(date, sep) - yyyy.mm.dd
 function dateToDateStr(date, sep){
	// sep : 년월일을 구분하는 문자
	if(!sep) sep = ".";
	
	// 반드시 date - date 객체여야만 한다.
	var yy = date.getFullYear();
	var mm = date.getMonth() + 1; // month는 0~11로 표시하기 때문에 1을 더해줌
	var dd = date.getDate();
	
	return yy+sep+addNumZero(mm)+sep+addNumZero(dd);
}

 // 날짜객체를 시간 문자열로 표시하는 함수 - dateToTimeStr(date, sep) - yyyy.mm.dd
 // java 코도에서 java.util.Date 객체를 사용하여야 한다.
 // java 코도에서 java.sql.Date 객체를 사용하면 날짜만 처리, 시간이 없어짐
 function dateToTimeStr(date){
	
	// 날짜를 찍어서 확인 시 숫자는 출력되지만 시간 정보가 포함되어 있지 않음
	// oracle DB - select to_char(writeDate, 'hh:mi:ss') from board_reply => 확인 가능
	// java 코드 확인 return service.list(); -> list = service.list(); => 찍어 본다.(syso(list)); -> return list;
	
	// 반드시 date - date 객체여야만 한다.
	var hh = date.getHours();
	var mi = date.getMinutes(); // month는 0~11로 표시하기 때문에 1을 더해줌
	var ss = date.getSeconds();
	
//	return ((hh > 9)?"":"0") + hh + ":" + mi + ":" + ss;
	return addNumZero(hh) + ":" + addNumZero(mi) + ":" + addNumZero(ss);
}

// 숫자를 2자리로 만들어 주는 함수 -> 9보다 작거나 같으면 앞에 0이 붙음
function addNumZero(data){
	if(data > 9) return data
	return "0" + data;
}

// 날짜 데이터를 timestamp 라는 Long 타입의 긴 숫자로 받아서 날짜 계산에 의해 현재 시간 기준으로 24시간이 지났으면 날짜를 출력,
// 그렇지 않을 시 숫자 문자열을 돌려주는 함수
function displayTime(timeStamp){
	// 오늘 날짜 객체
	var today = new Date();
	// 오늘 날짜 타임스탬프에서 비교할 날짜의 타임스탬프를 뺌
	var gap = today.getTime() - timeStamp;
	
	// 24시간이 지나지 않았으면 시간을, 지났으면 날짜를 문자열로 보여줌
	if(gap < (1000 * 60 * 60 * 24))
		return dateToTimeStr(new Date(timeStamp));
	else return dateToDateStr(new Date(timeStamp), ".");	
	
	
}

// Ajax를 이용한 댓글이나 게시글의 리스트 페이지 태그 만들기 (li tag만 생성)
// 데이터 한 개당 li - a - data. 선택된 페이지는 class="active"로 지정. 모든 li 태그에 class = "ajax_page"
// 넘어오는 데이터는 pageObject = {"page":1, "perPageNum":5 ... } : JSON -> pageObject.page
// 결과는 페이지네이션의 ul tag 안에 들어갈 li tag 들을 문자열로 넘겨줌
function ajaxPage(pageObject){
	var str = ""; // tag를 만들어서 저장할 변수
		
	str += "<li data-page='1' class='reply_nav_li'>";
	if(pageObject.page > 1){ // 이동시킨다
		
  		str += "<a href='' onclick='return false' class='move' " 
  		+ "title='click to move 1 page!' data-toggle='tooltip' data-placement='top' >";
  		str += "<i class='glyphicon glyphicon-fast-backward'></i>";
  		str += "</a>";
	} else {	// page <= 1 ->> 이동시키지 않는다.
  		str += "<a href='' onclick='return false'";
  		str += "title='no move page!'  data-toggle='tooltip' data-placement='top' >";
  		str += "<i class='glyphicon glyphicon-fast-backward' style='color: #999;'></i>";
  		str += "</a>";
  	}
	str += "</li>";
	
	// 이전 그룹의 페이지로 이동시키기
	str += "<li data-page=" + (pageObject.startPage - 1) + "class='reply_nav_li'>";
	if(pageObject.startPage > 1){
	  	str += "<a href='' title='click to move previous page group!' data-toggle='tooltip' data-placement='top' class='move'>";
  		str += "<i class='glyphicon glyphicon-step-backward'></i>";
  		str += "</a>";
	} else {
  		str += "<a href='' onclick='return false'";
  		str += "title='no move page!'  data-toggle='tooltip' data-placement='top' >";
  		str += "<i class='glyphicon glyphicon-step-backward' style='color: #999;'></i>";
  		str += "</a>";
	}
	str += "</li>";
	
	// startPage ~ endPage 버튼 만들기
	for(i = pageObject.startPage; i <= pageObject.endPage; i++){
		// 여기
		str += "<li data-page='" + i + "' class='reply_nav_li "; 
  		if(pageObject.page == i) str += "active";
  		str += "'>";
	  	if(pageObject.page == i) str += "<a href=' onclick='return false'>" + i + "</a>";
	 	else str += "<a href='' title='click to move " + i + " page' class='move' " + " data-toggle='tooltip' data-placement='top'>" + i + "</a>"; 
	}
	str += "</li>";	
	
	// 다음 그룹으로 이동 : page를 endPage + 1로 이동시킨다.
  	str += "<li data-page= '" + (pageObject.endPage + 1) + "' class='reply_nav_li'>";
	if(pageObject.endPage < pageObject.totalPage){ // endPage가 != totalPage 이므로 이동 가능
	  	str += "<a href='' title='click to move next page group!' class='move'" + "data-toggle='tooltip' data-placement='top'>";
	  	str += "<i class='glyphicon glyphicon-step-forward'></i>";
	  	str += "</a>";
	 } else {
	  	str += "<a href='' onclick='return false'";
  		str += "title='no move page!'" + "data-toggle='tooltip' data-placement='top' >";
	  	str += "<i class='glyphicon glyphicon-step-forward' style='color: #999;'></i>";
	  	str += "</a>";
	}
	  	str += "</li>";

	// 마지막 페이지로 이동 : totalPage로 이동시킨다. page == totalPage 이면 이동 불가
	str += "<li data-page= '" + pageObject.totalPage + "'class='reply_nav_li'>";
	if(pageObject.page < pageObject.totalPage){
		str += "<a href='' title='click to move last page!' data-toggle='tooltip' class='move' data-placement='top'>";
		str += "<i class='glyphicon glyphicon-fast-forward'></i>";
	  	str += "</a>";
	} else {
  		str += "<a href='' onclick='return false'";
  		str += " title='no move page!' data-toggle='tooltip' data-placement='top' >";
		str += "<i class='glyphicon glyphicon-fast-forward' style='color: #999;'></i>";
	  	str += "</a>";
	}
	  	str += "</li>";
	return str;
}
