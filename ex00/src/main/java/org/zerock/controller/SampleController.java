package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

// �ڵ� �����ϰ� �ϴ� ������̼�
//@Controller, @Service, @Repository, @Component, @Restcontroller, @Advice
///WEB-INF/spring/appServlet/root-context.xml ������ �Ǿ� �־�� �Ѵ�. component-scan
@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {

	// ������ �޼���
	@RequestMapping("")
	public void basic() {
		log.info("basic-------------------------");
	}
	
}
