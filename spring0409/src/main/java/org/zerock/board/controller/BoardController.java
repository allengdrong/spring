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

@Controller // 자동생성과 역할
@RequestMapping("/board")
@Log4j
public class BoardController {

	private final String MODULE = "board";
	
	@Autowired
	@Qualifier("bsi")
	private BoardService service;
	
	// 1. 게시판 리스트
	@GetMapping("/list.do")
	public String list(Model model) throws Exception {
		
		log.info("list() - 게시판 리스트 -----------------");
		
		model.addAttribute("list", service.list());
		
		return MODULE + "/list";
	}
	
	// 2. 게시판 글보기
	@GetMapping("/view.do")
	public String view(Model model, Long no) throws Exception {
		
		log.info("view()no : " + no + " - 게시판 글보기 -----------");
		
		model.addAttribute("vo", service.view(no));
		
		return MODULE + "/view";
	}
	
	// 3-1. 게시판 글쓰기 폼
	@GetMapping("/write.do")
	public String writeForm() {
		
		log.info("writeForm() - 게시판 글쓰기 폼 -----------");
		
		return MODULE + "/write";
		
	}
	
	// 3-2. 게시판 글쓰기 처리
	@PostMapping("/write.do")
	public String wrtie(BoardVO vo) throws Exception {
		
		log.info("write().vo : " + vo + " - 게시판 글쓰기 처리 --------------");
	
		service.write(vo);
		
		return "redirect:list.do";
		
	}
	
	// 4-1. 게시판 글수정 폼
	@GetMapping("/update.do")
	public String updateForm(Model model, Long no) throws Exception {
		
		log.info("updateForm() - 게시판 글수정 폼 ---------");
		
		model.addAttribute("vo", service.view(no));
		
		return MODULE + "/update";
	}
	
	// 4-2. 게시판 글수정 처리
	@PostMapping("/update.do")
	public String update(BoardVO vo) throws Exception {
		
	log.info("update().vo : " + vo + " - 게시판 글수정 처리 ---------");
	
	service.update(vo);
	
	return "redirect:view.do?no=" + vo.getNo();
	}
	// 5. 게시판 글삭제 처리
	@GetMapping("/delete.do")
	public String delete(Long no) throws Exception{
		
		log.info("delete().no : " + no + " - 게시판 글삭제 처리 --------------");
		
		service.delete(no);
		
		return "redirect:list.do";
	}
	
}
