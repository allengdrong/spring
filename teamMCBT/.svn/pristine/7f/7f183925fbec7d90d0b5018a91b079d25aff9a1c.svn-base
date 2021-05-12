package com.mcbt.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcbt.board.service.BoardService;
import com.mcbt.board.service.ReplyService;
import com.mcbt.board.vo.BoardVO;

import com.mcbt.util.ReplyPage;

// 자동 생성 - @Controller, @Service, @Repository, @Component, @RestController,
// @~~Advice -> component-scan 설정 : servlet-context.xml, root-contenxt.xml
@Controller
@RequestMapping("/board")
public class BoardController {
    private final String DEFAULT_CATEGORY = "ALL";

//	private final String MODULE = "board";

	// 자동 DI
	@Autowired
	@Qualifier("bsi")
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;
	
	// 1. 게시글 목록
	@RequestMapping("list.do")
	// @RequestParam(defaultValue="") ==> 기본값 할당
	public ModelAndView list(@RequestParam(defaultValue = DEFAULT_CATEGORY, required = false) String category, 
			@RequestParam(defaultValue="title") String option, @RequestParam(defaultValue="") String q,
			@RequestParam(defaultValue="1") int curPage) throws Exception {
		// 레코드의 갯수 계산
		int count = boardService.countArticle(option, q);
		// 페이지 나누기 관련 처리
		ReplyPage replyPage = new ReplyPage(count, curPage);
		int start = replyPage.getPageBegin();
		int end = replyPage.getPageEnd();
		
		List<BoardVO> list = boardService.list(category, start, end, option, q);
		
		// 데이터를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", category); // 카테고리
		map.put("list", list); // list
		map.put("count", count); // 레코드의 갯수
		map.put("option", option); // 검색옵션
		map.put("q", q); // 검색키워드
		map.put("ReplyPage", replyPage);
		// ModelAndView - 모델과 뷰
		ModelAndView mav = new ModelAndView();
		/*mav.addObject("list", list); // 데이터를 저장
		mav.addObject("count", count);
		mav.addObject("searchOption", searchOption);
		mav.addObject("keyword", keyword);*/
		mav.addObject("map", map); // 맵에 저장된 데이터를 mav에 저장
		mav.setViewName("board/list"); // 뷰를 list.jsp로 설정
		return mav; // list.jsp로 List가 전달된다.
	}
	// 2. 게시글 작성화면
	// @RequestMapping("board/write.do")
	// value="", method="전송방식"
	@RequestMapping(value="writeForm.do", method=RequestMethod.GET)
	public String write(){
		return "board/write"; // write.jsp로 이동
	}
	
	// 2-1. 게시글 작성처리
	@RequestMapping(value="write.do", method=RequestMethod.POST)
	public String insert(@ModelAttribute BoardVO vo, HttpSession session) throws Exception{
		// session에 저장된 userId를 writer에 저장
		String writer = (String) session.getAttribute("id");
		// vo에 writer를 세팅
		vo.setWriter(writer);
		boardService.write(vo);
		return "redirect:list.do";
	}
	
	// 3. 게시글 상세내용 조회, 게시글 조회수 증가 처리
	// @RequestParam : get/post방식으로 전달된 변수 1개
	// HttpSession 세션객체
	@RequestMapping(value="view.do", method=RequestMethod.GET)
	public ModelAndView view(@RequestParam int no, @RequestParam int curPage, @RequestParam String option,
							@RequestParam String q, HttpSession session) throws Exception{
		// 조회수 증가 처리
		boardService.increase(no, session);
		// 모델(데이터)+뷰(화면)를 함께 전달하는 객체
		ModelAndView mav = new ModelAndView();
		// 뷰의 이름
		mav.setViewName("board/view");
		// 뷰에 전달할 데이터
		// 댓글의 수 : 댓글이 존재하는 게시물의 삭제처리 방지하기 위해
		mav.addObject("count", replyService.count(no)); 
		mav.addObject("dto", boardService.view(no));
		mav.addObject("curPage", curPage);
		mav.addObject("option", option);
		mav.addObject("q", q);
		return mav;
	}
	
	// 4. 게시글 수정
	// 폼에서 입력한 내용들은 @ModelAttribute BoardVO vo로 전달됨
	@RequestMapping(value="update.do", method=RequestMethod.POST)
	public String update(@ModelAttribute BoardVO vo) throws Exception{
		boardService.update(vo);
		return "redirect:list.do";
	}
	
	// 5. 게시글 삭제
	@RequestMapping("delete.do")
	public String delete(@RequestParam int no) throws Exception{
		boardService.delete(no);
		return "redirect:list.do";
	}

}
