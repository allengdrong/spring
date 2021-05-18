/**
 * 게시판 댓글 처리에 필요한 JS(JQuery) 
 */
 
 // 브라우저의 console tab에 출력 - alert() 사용 가능
 console.log("Reply Module....");
 
 // replyService 변수 - 타입은 Object - JSON {} -{K:v, K:v, K:func()}
 // replyService 처리 시 결과 function으로 나온다. 결과 뒤에 ()를 붙이면 실행.
 var replyService = (
	function(){
		// list
		// param : JSON {no:2, repPage:1, repPerPageNum:5}
		// list(JSON - 넘겨줄 데이터, 데이터를 성공적으로 가져왔을 때의 실행함수, 실패한 경우의 실행함수)
		function list(param, callback, error){
	
			var no = param.no;
			// param.repPage || 1 -> param.repPage의 값이 없으면 1 사용
			var page = param.repPage || 1;
			// param.repPerPageNum || 5; -> param.repPerPageNum의 값이 없으면 5 사용
			var perPageNum = param.repPerPageNum || 5;
			
	
			// Ajax를 이용한 데이터 가져오기 -> 형식에 맞을 시 Ajax 사용
			// Ajax 함수 : $.getJSON() - $.ajax() --> 원래 표준를 통해서 JSON 데이터를 받아오게 만든 함수
			//					$(selector).load(url[, success]) - 아이디 중복체크 시 사용
			// $.ajax() --> 표준
			$.getJSON(
				// ajax를 호툴하는 url
				"/replies/list.do?no=" + no + "&repPage=" + page + "&repPerPageNum=" + perPageNum,
				// success() - 성공 상태일 때 처리되는 함수
				// 데이터 처리가 성공해서 데이터를 가져오면 data로 넣어줌. list이름으로 배열이 넘어옴
				function(data){
//					alert(data);
					// callback : 데이터를 가져오면 처리하는 함수 - 가져온 목록을 가지고 HTML을 만들어 주고 지정한 곳에 넣어 준다.
					if(callback){
						callback(data);
					}
				}
			)
			// error 상태일 때의 처리되는 함수
			.fail(
				function(xhr, status, err){
					// 오류 함수가 있으면 오류 함수 실행
					if(error){
						error();
					} else {
						// 오류 출력 - {}, []구조 가능 / <> - JSON데이터가 아니므로 오류 
						alert(err);
					}
				}
			); // $.getJSON().fail()의 끝
		} // list()의 끝
					
		// write() ---------------------------------------
		function write(reply, callback, error){
			console.log("reply write() --------------------------");
			console.log("reply data " + JSON.stringify(reply));
			// Ajax를 이용하여 데이터를 서버에 전송
			$.ajax({
				// 전송 방법의 타입 : get, post, put, patch, delete, update .....
				type : "post",
				// 요청 url
				url : "/replies/write.do",
				// 전송되는 데이터
				data : JSON.stringify(reply),
//				data : reply,
				// 전송되는 데이터의 타입과 인코딩 방식
				contentType : "application/json; charset=utf-8",
				// 정상적으로 성공 시 처리되는 함수
				success : function(result, status, xhr){
//					if(callback){
//						callback(result);
//					}
					if(callback) callback(result);
					else alert("댓글 작성 성공");
				},
				// 처리 도중 오류가 난 경우의 처리하는 함수
				error : function(xhr, status, er){
//					if(error){
//						error(er);
//					}
					if(error) error();
					else alert("댓글 작성 오류");
				}
			}); // $.ajax()
		}
					
		// update() --------------------------------------
		// update(JSON data, 성공함수, 실패함수)
		function update(reply, callback, error){
			console.log("reply update() --------------------------");
			// ajax를 이용해서 데이터 넘기기
			$.ajax({
				type:"patch",
				url : "/replies/update.do",
				data : JSON.stringify(reply),
				contentType : "application/json; charset=utf-8",
				success : function(result, status, xhr){
					if(callback) callback(result, status);
					else alert("댓글 수정 완료");
				},
				error : function(xhr, status, err){
					if(error) error(err, status);
					else alert(err);
				}
			});
			
		}

		// delete() : delete가 예약어이므로 변수나 함수로 사용불가 -> deleteReply()-----
		function deleteReply(reply, callback, error){
			console.log("reply deleteReply() ----------------------------");
			
			$.ajax({
				type:"delete",
				url:"/replies/delete.do",
				data : JSON.stringify(reply),
				contentType : "application/json; charset=utf-8",
				success:function(result, status, xhr){
					if(callback) callback(result, status);
					else alert("댓글 삭제 완료 - 새로 고침하세요.");
				},
				error : function(xhr, status, err){
					if(error)  error(err);
					else alert(err);
				}
			});
			
		}
		
	return{
		// replyService.list(param, callback, error)
		list : list,
		write : write,
		update : update,
		delete : deleteReply,
		// util.js 파일에서 displayTime 함수를 찾는다. 앞에 있어야 함
		displayTime : displayTime
		}
	}
)();

 console.log(replyService);