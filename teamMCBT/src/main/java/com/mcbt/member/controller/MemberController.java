package com.mcbt.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.mcbt.member.service.MemberService;
import com.mcbt.member.vo.LoginVO;
import com.mcbt.member.vo.WriteVO;
import com.webjjang.util.PageObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {
	
	private final String MODULE = "member";
	
	// 자동 DI
	@Autowired
	@Qualifier("msi")
	private MemberService service;
	
	//리스트
	@GetMapping("/list.do")
	// @ModelAttribute - 전달 받은 변수의 값을 model에 담아서 JSP까지 보내준다. 변수 이름으로 사용
	public String list(Model model) throws Exception{
		log.info("리스트 ㅇㅇㅇㅇㅇㅇㅇ");
		
		model.addAttribute("list",service.list());
		return MODULE + "/list";
	}

	
	// 로그인 폼
	@GetMapping("/loginForm.do")
	public String loginForm() {
		
		log.info("loginForm() - 로그인 폼 이동");
		
		return MODULE + "/login";
	}
	
	
	// 로그인 처리
	// 클라이언트에서 넘겨주는 id, pw를 받아야 한다.(LoginVO), session을 받아야 한다.
	@PostMapping("/login.do")
	public String login(LoginVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		// 넘어온 데이터 확인
		log.info("login().vo - " + vo);
		
		// 아이디와 비밀번호가 DB정보와 맞으면 로그인 처리한다(session에 loginVO객체를 넣어준다.).
		session.setAttribute("login", service.login(vo));
		
		session.getAttribute("login");
		
		rttr.addFlashAttribute("msg", "정상적으로 로그인이 되었습니다.");
		
		// 로그인이 끝나면 이동할 url
		return "redirect:/tq/list.do";
	}
	
	
	// 로그 아웃 처리 메서드 - session을 그대로 두고 session안에 login 정보가 지운다.
	@GetMapping("/logout.do")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		
		log.info("logout() - 로그아웃 처리 ");
		
		// 로그아웃 처리
		// 1. session을 완전히 지운다.
		// session.invalidate();
		// 2. session을 그대로 두고 session안에 login 정보가 지운다.
		session.removeAttribute("login");
		
		// 메시지 보내기 - session이 사라지면 오류가 난다. session.invalidate() 사용 금지
		rttr.addFlashAttribute("msg", "정상적으로 로그아웃이 되었습니다.");
		
		return "redirect:/board/list.do";
	}
	

	//3. 게시판 등록 폼 / write.do - get
	@GetMapping("/writeForm.do")
	public String writeForm() {
		return MODULE + "/write";
	}
	
	//3-1. 게시판 등록 처리 / write.do - post
	@PostMapping("/write.do")
	public String write(WriteVO vo,
			RedirectAttributes rttr) throws Exception {
		log.info("write().vo : " + vo);
			
		// db에 데이터 저장하기	
		service.write(vo);
		
		// addFlashAttribute() - 단 일회만 사용 가능한 정보 저장
		rttr.addFlashAttribute("msg", "회원가입 완료.");
		
		return MODULE + "/login";
	}
} 
