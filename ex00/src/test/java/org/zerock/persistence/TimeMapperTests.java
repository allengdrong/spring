package org.zerock.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {

	// 처리해서 테스트해야 할 객체
	@Setter(onMethod_ = {@Autowired})
	private TimeMapper timeMapper;
	
	// 테스트할 메서드
	@Test
	public void testGetTime() {
		log.info("Mapper Test - getTime() --------------------------");
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime());
	}
}
