package com.mcbt.tq.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mcbt.tq.service.TqExService;
import com.mcbt.tq.service.TqReplyService;
import com.mcbt.tq.vo.TqExVO;
import com.mcbt.tq.vo.TqReplyVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/exs")
@Log4j
public class ExController {
	
	private final String MODULE = "exs";

	// 자동 DI
	@Autowired
	@Qualifier("tqexsi")
	private TqExService service;
	
	// 1. 게시판 보기 리스트 - 검색 / list.do - get -> list.json 불가능 : xml만 서비스
	@GetMapping("/list.do")
	public TqExVO list(Model model, long no)
		throws Exception {
			return MODULE + "/list";
	}
	
	//2. 게시판 댓글보기 생략 - 리스트에 내용이 다 나온다.
	
	//3. 게시판 댓글 등록 폼 / /board/view.do 포함.
	
	//3-1. 게시판 등록 처리 / write.do - post
	@PostMapping(value = "/write.do", 
//			consumes = "aplication/json",
			consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
			produces = {"application/text; charset=utf-8"})
//			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> write(@RequestBody TqExVO vo) throws Exception {
		log.info("write().vo : " + vo);
		
		// db에 데이터 저장하기
		service.write(vo);
		
		return new ResponseEntity<String>
		("댓글이 등록되었습니다.",HttpStatus.OK);
//		(URLEncoder.encode("댓글이 등록되었습니다.","utf-8"),HttpStatus.OK);
	}
	
	//4. 게시판 글수정 폼 / /board/view.do 포함.
	
	//4. 게시판 글수정 처리 / update.do - patch
	@PatchMapping(value = "/update.do",
			consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
			produces = {"application/text; charset=utf-8"})
	public ResponseEntity<String> update(@RequestBody TqExVO vo) throws Exception {
		
		log.info("update().vo : " + vo);
		
		int result = service.update(vo);
		
		// 전달되는 데이터의 선언
		String msg = "게시판 글수정이 성공적으로 되었습니다.";
		HttpStatus status = HttpStatus.OK;
		
		if(result == 0) {
			msg = "게시판 수정 실패 - 정보를 확인해 주세요.";
			status = HttpStatus.NOT_MODIFIED;
		}
		
		log.info("update().msg : " + msg);
		
		return new ResponseEntity<String>(msg, status);
	}
	
	//5. 게시판 글삭제 / delete.do - delete 방식
	@DeleteMapping(value = "/delete.do",
			consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
			produces = {"application/text; charset=utf-8"})
	public ResponseEntity<String> delete(@RequestBody TqExVO vo)
			throws Exception {
		
		log.info("delete().vo : " + vo);
		
		int result = service.delete(vo);

		// 전달되는 데이터의 선언
		String msg = "게시판 삭제가 성공적으로 되었습니다.";
		HttpStatus status = HttpStatus.OK;
		
		if(result == 0) {
			msg = "게시판 삭제 실패 - 정보를 확인해 주세요.";
			status = HttpStatus.NOT_MODIFIED;
		}
		
		log.info("update().msg : " + msg);
		
		return new ResponseEntity<String>(msg, status);
	}
	

}
