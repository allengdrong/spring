package org.zerock.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {

	private final String MODULE = "board";

	// 실행할 메서드 - 리스트
	// 맵핑 - get 방식
	@GetMapping("/list.do")
	public String list() {

		log.info("list() - 게시판 리스트 ------------------------");

		return MODULE + "/list";
	}

	// 실행할 메서드 - 리스트
	// 맵핑 - get 방식
	@GetMapping("/view.do")
	public String view() {

		log.info("view() - 게시판 글보기 ------------------------");

		return MODULE + "/view";
	}

	// 실행할 메서드 - 글쓰기 폼
	// 맵핑 - get 방식
	@GetMapping("/write.do")
	public String writeForm() {

		log.info("writeForm() - 게시판 글쓰기 ------------------------");
		return MODULE + "/write";
	}

	// 실행할 메서드 - 글쓰기 처리
	// 맵핑 - post 방식 .write.do
	@PostMapping("/write.do")
	public String write() {

		log.info("write() - 게시판 글쓰기 처리 ------------------------");
		return "redirect:list.do";
	}

	// 실행할 메서드 - 글수정 폼
	// 맵핑 - get 방식
	@GetMapping("/update.do")
	public String updateForm() {

		log.info("updateForm() - 게시판 글수정 ------------------------");
		return MODULE + "/update";
	}

	// 실행할 메서드 - 글수정 처리
	// 맵핑 - post 방식 .write.do
	@PostMapping("/update.do")
	public String update() {

		log.info("update() - 게시판 글수정 처리 ------------------------");
		return "redirect:view.do";
	}

	// 실행할 메서드 - 글삭제 처리
	// 맵핑 - post 방식 .delete.do
	@GetMapping("/delete.do")
	public String delete() {

		log.info("delete() - 게시판 글삭제 처리 ------------------------");
		return "redirect:list.do";
	}

}
