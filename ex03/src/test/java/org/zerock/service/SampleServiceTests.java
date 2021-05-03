package org.zerock.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class SampleServiceTests {
	
	// @Setter(onMethod_ = @Autowired)
	@Inject
	@Qualifier("ssi")
	private SampleService service;
	
	// 실행되는 객체에 대한 정보
	@Test
	public void TestClass() {
		log.info(service);
		log.info(service.getClass().getName());
	}
	
	// 값을 대입해서 결과를 출력하는 테스트 진행 - aop 테스트
	@Test
	public void testAdd() throws Exception {
		log.info(service.doAdd("123","456"));
	}

}
