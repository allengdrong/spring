package com.mcbt.tq.mapper;

import java.util.List;
import java.util.Map;

import com.mcbt.tq.vo.TqExVO;

public interface TqExMapper {
	
	public List<TqExVO> list (Map<String, Object> map) throws Exception;
	
	public Long getTotalRow(Long no) throws Exception;
	
	public int write(TqExVO vo) throws Exception;
	
	public int update(TqExVO vo) throws Exception;
	
	public int delete(Long no) throws Exception;

}
