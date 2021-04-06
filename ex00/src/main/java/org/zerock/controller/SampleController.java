package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

// 자동 생성하게 하는 어노테이션
//@Controller, @Service, @Repository, @Component, @Restcontroller, @Advice
///WEB-INF/spring/appServlet/root-context.xml 설정이 되어 있어야 한다. component-scan
@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {

	// 실행할 메서드
	@RequestMapping("")
	public void basic() {
		log.info("basic-------------------------");
	}
	
}
