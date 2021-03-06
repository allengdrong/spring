package org.zerock.util.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mcbt.member.vo.LoginVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class AuthorityInterceptor extends HandlerInterceptorAdapter {
	
	// url에 대한 권한 정보를 저장하는 Map
	// Map<url, gradeNo>
	private static Map<String, Integer> authMap = new HashMap<>();
	
	// 이 클래스에서만 사용하는 변수
	
	private String url = null;
	
	// 페이지에 대한 등급 정보를 저장하는 메서드
	// 데이터를 넣는 방법 : static 초기화 블록
	static {

		
		// 이미지 게시판 - 등록, 수정, 삭제 - 회원 : 1
//		authMap.put("/image/write.do", 1);
//		authMap.put("/image/update.do", 1);
//		authMap.put("/image/updateFile.do", 1);
//		authMap.put("/image/delete.do", 1);
		
		//문제 제작(관리자만 사용 가능)
		authMap.put("/test/list.do", 9);
		authMap.put("/test/view.do", 9);
		authMap.put("/test/testReg01.do", 9);
		authMap.put("/test/testReg02.do", 9);
		
		//문제 제작(회원만 작성/수정/삭제 사용 가능)
		authMap.put("/tq/write.do", 1);
		authMap.put("/tq/write02.do", 1);
		authMap.put("/tq/update.do", 1);
		authMap.put("/tq/delete.do", 1);

		//상품 관리(관리자만 사용 가능)
		authMap.put("/image/list.do", 9);
		authMap.put("/image/view.do", 9);
		authMap.put("/image/write.do", 9);
		authMap.put("/image/update.do", 9);
		
		// 문제 풀기
		authMap.put("/solve/random.do", 1);
	}

	@Override

	  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)

	    throws Exception {
		
		log.info("+[AuthorityInterceptor]=====================================================");
		
		url = request.getServletPath();
		log.info(" + Authority.preHandle.url : " + url);
		
		// 로그인 객체 꺼내기
		// 로그인 정보는 session에 있다. session이 안보인다. request에서 꺼낼수 있다.
		HttpSession session = request.getSession();
		LoginVO vo = (LoginVO) session.getAttribute("login");
		
		// 새로운 메시지 갯수 처리를 하는데 로그인이 안되어 있으면 바로 로그인 페이지로 이동시킨다.
		// 브라우저에서 페이지 이동이 없이 가만히 놔둔 상태로 있다가 서버가 재실행이 되면 세션이 사라진다.
		if(url.equals("/ajax/getMessageCnt") && vo == null) 
			throw new ServletException("DispatcherServlet - 메시지 갯수 가져 오는데 로그인이 안되어 있습니다.");
		
//			{((HttpServletResponse)response).sendRedirect("/member/LoginForm.do");
//			return;
//		}
		
		// 권한이 없는 경우의 처리
		if(!checkAuthority(vo)) {
			// 오류페이지로 이동 시킵니다.
			response.sendRedirect(request.getContextPath() + "/solve/auth_error.do");
			// 호출한 쪽으로 되돌아 갑니다. -> 없으면 계속 아래로 실행이 된다.
			return false;
		}
		
		// 요청한 내용을 계속 진행
		return super.preHandle(request, response, handler);
		
	} // end of preHandle
	
	private boolean checkAuthority(LoginVO vo) {
		// url 정보가 authMap 있는지 확인 한다.
		// 데이터가 없으면(null이면) 권한체크가 필요 없는 페이지 요청입니다.
		Integer pageGradeNo = authMap.get(url);
		if(pageGradeNo == null) {
			log.info(" + AuthorityFilter.checkAuthority() - 권한이 필요없는 페이지 입니다. ");
			return true;
		}
		// 여기서 부터 로그인이 필요한 처리입니다. vo가 null이면 안된다.
		if(vo == null) {
			log.info(" + AuthorityFilter.checkAuthority() - 로그인이 필요합니다.");
			return false;
			
		}
		log.info(" + AuthorityFilter.checkAuthority().pageGradeNo : " + pageGradeNo);
		log.info(" + AuthorityFilter.checkAuthority().userGradeNo : " + vo.getGradeNo());
		// 권한이 없는 페이지 요청에 대한 처리
		if(pageGradeNo > vo.getGradeNo()) {
			log.info(" + AuthorityFilter.checkAuthority() - 권한이 없습니다. ");
			return false;
		}
		log.info(" + AuthorityFilter.checkAuthority() - 권한이 있습니다. ");
		return true;
	}
	
}
