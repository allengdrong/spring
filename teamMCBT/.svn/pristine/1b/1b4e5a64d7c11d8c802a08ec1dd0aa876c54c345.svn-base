package com.mbct.solve.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mbct.solve.mapper.SolveMapper;
import com.mcbt.test.vo.TestVO;

import lombok.extern.log4j.Log4j;

@Service
@Qualifier("ssi")
@Log4j
public class SolveServiceImpl implements SolveService {

	@Inject
	private SolveMapper mapper;
	
	@Override
	public TestVO solve(String lev, int no) throws Exception {
		// TODO Auto-generated method stub		
		log.info("solve.lev: " + lev);
		
//		mapper.quizLev(lev);
		
		return mapper.solve(no); 
	}

	public long getCnt(String lev) throws Exception {
		// TODO Auto-generated method stub
		log.info("getCnt.lev : " + lev);
		return mapper.getCnt(lev);
	}
	
	

}
