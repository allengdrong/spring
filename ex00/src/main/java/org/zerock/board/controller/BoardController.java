package org.zerock.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.board.service.BoardService;
import org.zerock.board.vo.BoardVO;

import lombok.extern.log4j.Log4j;

//자동 생성하게하는 어노테이션
//@Controller, @Service, @Repository, @Component, @RestController, @Advice
///WEB-INF/spring/appServlet/servlet-context.xml 설정이 되어 있어야 한다. component-scan
@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {

	private final String MODULE = "board";
	
	//@Setter - lombok 사용, @Autowired - Spring 사용
	// 대신 사용 가능한 어노테이션 : @Autowired - Spring, @Inject - java
	// DI 적용 시 BoardService 타입 : 1. BoardService 인터페이스 2. BoardServiceImpl 클래스
	// @Setter(onMethod_ = @Autowired )
	@Autowired
	// BoardService 상속 받아서 타입이 같은것이 있으면 어떤 것을 넣어 줄지 결정이 안되서 오류 - 해결
	@Qualifier("bsi")
	private BoardService service;
	
	// 실행할 메서드 - 리스트
	// 맵핑 - get 방식. list.do
	@GetMapping("/list.do")
	public String list(Model model) throws Exception {
		
		log.info("list() - 게시판 리스트 --------------");
		
		// model에 데이터를 담으면 model 안에 있는 request에 데이터가 담긴다.
		model.addAttribute("list", service.list());
		
		return MODULE + "/list";
	}
	
	// 실행할 메서드 - 글보기
	// 맵핑 - get 방식. view.do
	@GetMapping("/view.do")
	public String view(Model model, Long no) throws Exception {
		
		log.info("view().no : "+ no +" - 게시판 글보기 --------------");
		
		model.addAttribute("vo", service.view(no));
		
		//WEB-INF/views/ + board/view + .jsp -> servret-context
		return MODULE + "/view";
	}
	
	// 실행할 메서드 - 글쓰기 폼
	// 맵핑 - get 방식. write.do
	@GetMapping("/write.do")
	public String writeForm() {
		
		log.info("writeForm() - 게시판 글쓰기 폼 --------------");
		
		return MODULE + "/write";
	}
	
	// 실행할 메서드 - 글쓰기 처리
	// 맵핑 - post 방식. write.do
	@PostMapping("/write.do")
	public String write(BoardVO vo) throws Exception {
		
		log.info("write().vo : " + vo + " - 게시판 글쓰기 처리 --------------");
		
		service.write(vo);
		
		return "redirect:list.do";
	}
	
	// 실행할 메서드 - 글수정 폼
	// 맵핑 - get 방식. update.do
	@GetMapping("/update.do")
	public String updateForm(Model model, Long no) throws Exception {
		
		log.info("updateForm() - 게시판 글수정 폼 --------------");
		
		model.addAttribute("vo", service.view(no));
		
		return MODULE + "/update";
	}
	
	// 실행할 메서드 - 글수정 처리
	// 맵핑 - post 방식. update.do
	@PostMapping("/update.do")
	public String update(BoardVO vo) throws Exception {
		
		log.info("update().vo : " + vo + " - 게시판 글수정 처리 --------------");
		
		service.update(vo);
		
		return "redirect:view.do?no=" + vo.getNo();
	}
	
	// 실행할 메서드 - 글삭제 처리
	// 맵핑 - get 방식. delete.do
	@GetMapping("/delete.do")
	public String delete(Long no) throws Exception{
		
		log.info("delete().no : " + no + " - 게시판 글삭제 처리 --------------");
		
		service.delete(no);
		
		return "redirect:list.do";
	}
	
}
