package com.mbct.solve.controller;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mbct.solve.service.SolveService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/test")
@Log4j
public class SolveController {

	private final String MODULE = "solve";
	
	@Autowired
	@Qualifier("ssi")
	private SolveService service;
	
	@GetMapping("/main.do")
	public String main() {
		log.info("main() 실행 중..............................");
		return MODULE + "/main";
	}
	
	// 문제 풀기
	@GetMapping("/solve.do")
	public String solve(Model model, String lev) throws Exception {
		log.info("solve() 실행 중.....................");
		
		Long cnt = service.getCnt(lev);
		log.info("lev : " + lev);
		
		int [] lotto = new int[10];
		  
		  // 중복되지 않는 7개의 숫자를 발생시켜서 저장
		  for(int i = 0 ; i < lotto.length; i++) {
		   // 1~45까지의 랜덤 숫자를 발생
		   int ran = (int)(Math.random()*cnt +1);
		   // 중복이 되는 지 결과를 저장하는 변수 - boolean
		   boolean isDuplicate = false;
		   // 중복이 되지는 검사를 한다.
		   for(int j = 0; j <= i; j++) {
		    // 데이터가 중복이 되는 경우
		    if(lotto[j]==ran) {
		     isDuplicate = true;
		     // 중복이 된 경우는 뒤의 데이터를 더이상 검사를 안해도 되므로
		     // 검사하는 for문을 빠져 나가면 된다.
		     break;
		    }
		   }
		   // 중복이 되지 않으면 저장, 중복이되면 i-- 시킨다.
		   if(isDuplicate)i--;
		   else lotto[i] = ran;
		  }
		  
		  // lotto Data 출력
		  System.out.println(Arrays.toString(lotto));
		  int i = 0;
		  for(i = 0; i < lotto.length; i++)
			  System.out.println(lotto[i]);
		  
		  int no = lotto[0];
		  System.out.println("no : " + no);
		  model.addAttribute("vo", service.solve(lev, no));
		  
		return MODULE + "/solve";
	}

}
