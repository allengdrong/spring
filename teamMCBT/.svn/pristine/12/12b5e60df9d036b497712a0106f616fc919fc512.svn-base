package com.mcbt.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mcbt.test.mapper.TestExMapper;
import com.mcbt.test.vo.TestExVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Service
@AllArgsConstructor
@Log4j
@Qualifier("texsi")

public class TestExServiceImpl implements TestExService {

	private TestExMapper mapper;
	
	@Override
	public List<TestExVO> list(Model model, Long no) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		
		map.put("model", model);
		map.put("no", no);
		
		return mapper.list(map);
	}

	

	@Override
	public int write(TestExVO vo) throws Exception {
		// TODO Auto-generated method stub
		log.info("write().vo : " + vo);
		
		return mapper.write(vo);
	}

	@Override
	public int update(TestExVO vo) throws Exception {
		// TODO Auto-generated method stub
		log.info("update().vo : " + vo);
		
		return mapper.update(vo);
	}

	@Override
	public int delete(Long no) throws Exception {
		// TODO Auto-generated method stub
		log.info("delete().no : " + no);
		
		return mapper.delete(no);
	}

}
