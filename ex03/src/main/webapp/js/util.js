/**
 * 여러가지 Utility 함수 선언
 */
 
 // 날짜객체를 날짜 문자열로 표시하는 함수 - dateToDateStr(date, sep) - yyyy.mm.dd
 function dateToDateStr(date,sep){
	// sep 년월일을 구분하는 문자
	if(!sep) sep = ".";
	
	// 반드시 date - date 객체이여만 한다.
	var yy = date.getFullYear();
	var mm = date.getMonth() + 1; // month는 0~11
	var dd = date.getDate();
	
	return yy+sep+addNumZero(mm)+sep+addNumZero(dd);
}

// 날짜객체를 시간문자열로 표시하는 함수 - dateToTimeStr(date, sep) - yyyy.mm.dd
 function dateToTimeStr(date){
	
	// 반드시 date - date 객체이여만 한다.
	var hh = date.getHours();
	var mi = date.getMinutes();
	var ss = date.getSeconds();
	
	return addNumZero(hh) + ":" + addNumZero(mi) + ":" + addNumZero(ss);
}

// 숫자를 2자리로 만들어 주는 함수 9보다 작거나 같으면 앞에 0을 붙인다.
function addNumZero(data){
	if(data > 9 ) return data
	return "0" + data;
}

// 날짜 데이터를 timestemp라는 Long 타입의 긴 숫자를 받아서 날짜 계산에 의해 현재 시간 기준으로
// 24시간이 지났으면 날짜 문자열을 그렇지 않으면 숫자 문자열을 돌려주는 함수.
function displayTime(timeStemp){
	// 오늘 날짜 객체 만들기
	var today = new Date();
	// 오늘 날짜 타임스탬프에서 비교해야할 날짜의 타임스탬프를 뺀다.
	var gap = today.getTime() - timeStemp;
	
	// 24시간이 지나지 않았으면 시간을, 24시간이 지났으면 날짜를 문자열로 돌려 준다.
	if(gap < (1000 * 60 * 60 * 24))
		return dateToTimeStr(new Date(timeStemp));
	else return dateToDateStr(new Date(timeStemp), ".");
}