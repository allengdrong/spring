package com.mcbt.tq.controller;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mcbt.tq.service.TqService;
import com.mcbt.tq.vo.TqVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

//자동 생성 - @Controller, @Service, @Repository, @Component, @RestController,
//@~~Advice -> component-scan 설정 : servlet-context.xml, root-context.xml

@Controller
@RequestMapping("/tq")
@Log4j
public class TqController {
	
	private final String MODULE = "tq";
	
	@Autowired
	@Qualifier("tsi")
	private TqService service;
	
	// 1. 문제 출제 게시판 리스트
	@GetMapping("/list.do")
	public String list(Model model, @ModelAttribute PageObject pageObject) throws Exception {
		log.info("list().pageObject ...... " + pageObject );
		model.addAttribute("list", service.list(pageObject));
		return MODULE + "/list";
	}
	
	// 2. 문제 출제 게시판 글보기 / view.do - get
	@GetMapping("/view.do")
	// Model 객체 - 처리된 데이터를 JSP에 전달
	// no, inc - 숫자 타입 : 원래는 String으로 데이터 전달. 없으면 null이 된다.
	// null을 숫자로 변환하는 과정에서 오류가 난다.
	public String view(Model model, Long no, @ModelAttribute PageObject pageObject) throws Exception {
		
		log.info("view().no : "+ no +" - 게시판 글보기 --------------");
		
		model.addAttribute("vo", service.view(no));
		
		return MODULE + "/view";
	}
	
	// 3. 문제 출제 게시판 등록 폼 / write.do - get
	@GetMapping("/write.do")
	public String writeForm() {
		return MODULE + "/write";
	}
	
	// 3-1. 문제 출제 게시판 등록 처리 / write.do - post
	@PostMapping("/write.do")
	public String write(TqVO vo, int perPageNum, RedirectAttributes rttr) throws Exception {
		log.info("write().vo : " + vo);
		
		// DB에 데이터 저장하기
		service.write(vo);
		
		rttr.addFlashAttribute("msg", "게시판 글등록이 성공적으로 되었습니다.");
		
		return "redirect:list.do?perPageNum=" + perPageNum;
	}
	
	// 4. 문제 출제 게시판 글수정 폼 / update.do - get
	@GetMapping("/update.do")
	public String updateForm(Model model, Long no) throws Exception {
		
		log.info("updateForm().no : " + no);
		
		// jsp에 수정할 데이터를 보내야 한다. 데이터가 DB에 있다. view()
		
		model.addAttribute("vo", service.view(no));
		
		return MODULE + "/update";
	}
	
	// 4-1. 문제 출제 게시판 글수정 처리 / update.do - post
	@PostMapping("/update.do")
	public String update(TqVO vo, RedirectAttributes rttr, PageObject pageObject) throws Exception {
		
		log.info("update().vo : " + vo);
		
		int result = service.update(vo);
		
		if(result == 0) throw new Exception("게시판 수정 실패 - 정보를 확인해 주세요");
		
		log.info("update().result : " + result);
		
		rttr.addFlashAttribute("msg", "게시판 글수정이 성공적으로 되었습니다.");
		
		return "redirect:view.do?no=" + vo.getNo()
			+ "&page=" + pageObject.getPage() 
			+ "&perPageNum=" + pageObject.getPerPageNum()
			+ "&key=" + pageObject.getKey()
			// url로 요청되는 경우 서버의 한글이 적용되므로 utf-8로 encode 시켜서 보낸다.
			+ "&word=" + URLEncoder.encode(pageObject.getWord(), "utf-8")
			;
	}
	
	// 5. 문제 출제 게시판 글삭제 / delete.do - get
	@PostMapping("/delete.do")
	public String delete(TqVO vo, int perPageNum, RedirectAttributes rttr) throws Exception {
		
		log.info("delete().vo : " + vo);
		
		int result = service.delete(vo);
		// result 가 0이면 비밀번호가 틀림.
		if(result == 0) throw new Exception("게시판 삭제 실패 - 정보를 확인해 주세요.");
		
		rttr.addFlashAttribute("msg", "게시판 글삭제가 성공적으로 되었습니다.");
		
		return "redirect:list.do?perPageNum=" + perPageNum;
	}

}
