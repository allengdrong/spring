package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
// 자동 생성 - @Controller, @Service, @Repository, @Component, @RestController, @~Advice
public class LogAdvice {
	
//	@Before("execution(* org.zerock.service.SampleService*.*(..))")
//	public void logBefore() {
//		log.info("==============================================================");
//	}
	
//	@Around("execution(* org.zerock.service.SampleService*.*(..))")
	// Server를 실행해서 동작이 되도록 만들어야 하므로 .servlet-context.xml에 AOP 설정이 되어 있어야 한다. - Project
	@Around("execution(* org.zerock.*.service.*.*(..))")
	// 실행 시간 알아내기
	public Object logTime(ProceedingJoinPoint pjp) {
		log.info("<<<<<<<<<<<<<<<==============================================================>>>>>>>>>>>>>>>>>");
	
	// 시작 시간은 저장
	long start = System.currentTimeMillis();
	
	// 실행 객체의 정보를 출력
	// 실행 객체 출력
	log.info("Target : " + pjp.getTarget());
	// 전달 데이터 출력
	log.info("param : " + Arrays.toString(pjp.getArgs()));
	
	// 실행
	
	// 실행 결과를 저장할 변수 선언
	Object result = null;
	
	// 실행
	try {
		result = pjp.proceed();
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// 끝 시간을 저장
	long end = System.currentTimeMillis();
	
	// 끝 - 시작 시간 - 실행할때 걸린 시간
	log.info("Time : " + (end - start));
	log.info("<<<<<<<<<<<<<<<==============================================================>>>>>>>>>>>>>>>>>");

	// 실행 결과 리턴
	return result;

}

}
