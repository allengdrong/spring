package com.mbct.solve.service;

import com.mcbt.test.vo.TestVO;

public interface SolveService {

	public TestVO solve(String lev, int no) throws Exception;
	
	// 랜덤함수
	public long getCnt(String lev) throws Exception;
}
