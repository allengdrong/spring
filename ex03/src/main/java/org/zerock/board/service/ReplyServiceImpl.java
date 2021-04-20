package org.zerock.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zerock.board.mapper.ReplyMapper;
import org.zerock.board.vo.ReplyVO;

import com.webjjang.util.PageObject;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// 자동생성 - @Controller, @Service, @Repository, @Component, @RestController
@Service
// private 변수에 대한 자동 DI - 생성자
@AllArgsConstructor
@Qualifier("rsi")
@Log4j
public class ReplyServiceImpl implements ReplyService {
	
	private ReplyMapper mapper;

	@Override
	public List<ReplyVO> list(PageObject pageObject, Long no) throws Exception {
		// TODO Auto-generated method stub
		log.info("list().PageObject : " + pageObject + ", no : " + no);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageObject", pageObject);
		map.put("no", no);
		return mapper.list(map);
	}

	@Override
	public int write(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		log.info("write().vo : " + vo);
		return mapper.write(vo);
	}
	
	@Override
	public int update(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		log.info("update().vo : " + vo);
		return mapper.write(vo);
	}
	
	@Override
	public int delete(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		log.info("delete().vo : " + vo);
		return mapper.write(vo);
	}

	@Override
	public Long getTotalRow() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
