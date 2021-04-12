package org.zerock.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {

	// 메인 표시 화면
	@GetMapping("/main")
	public void main() {
		
	}
	
	// 로그인 처리 메서드
	@GetMapping("/login")
	public String login(HttpSession session) {
		// session에 id가 있으면 로그인 한것 - 강제 로그인
		session.setAttribute("id", "test");
		log.info("로그인 처리 됨.");
		return "redirect:main";
	}
	
	// 로그아웃 처리 메서드
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// session에 id가 없으면 로그아웃
		// invalidate() - session 완전 삭제, removeAttibute() - session의 속성값 삭제
		session.removeAttribute("id");
		log.info("로그아웃 처리 됨.");
		return "redirect:main";
	}
	
	
}
