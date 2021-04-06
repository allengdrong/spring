package org.zerock.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.board.service.BoardService;

import lombok.Setter;
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
	@Setter(onMethod_ = @Autowired )
	private BoardService service;
	
	// 실행할 메서드 - 리스트
	// 맵핑 - get 방식. list.do
	@GetMapping("/list.do")
	public String list() throws Exception {
		
		log.info("list() - 게시판 리스트 --------------");
		
		service.list();
		
		return MODULE + "/list";
	}
	
	// 실행할 메서드 - 글보기
	// 맵핑 - get 방식. view.do
	@GetMapping("/view.do")
	public String view() {
		
		log.info("view() - 게시판 글보기 --------------");
		
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
	public String write() {
		
		log.info("write() - 게시판 글쓰기 처리 --------------");
		
		return "redirect:list.do";
	}
	
	// 실행할 메서드 - 글수정 폼
	// 맵핑 - get 방식. write.do
	@GetMapping("/update.do")
	public String updateForm() {
		
		log.info("updateForm() - 게시판 글수정 폼 --------------");
		
		return MODULE + "/update";
	}
	
	// 실행할 메서드 - 글수정 처리
	// 맵핑 - post 방식. update.do
	@PostMapping("/update.do")
	public String update() {
		
		log.info("update() - 게시판 글수정 처리 --------------");
		
		return "redirect:view.do";
	}
	
	// 실행할 메서드 - 글삭제 처리
	// 맵핑 - get 방식. delete.do
	@GetMapping("/delete.do")
	public String delete() {
		
		log.info("delete() - 게시판 글삭제 처리 --------------");
		
		return "redirect:list.do";
	}
	
}
