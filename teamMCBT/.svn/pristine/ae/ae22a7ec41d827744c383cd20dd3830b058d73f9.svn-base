package com.mcbt.member.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.mcbt.member.mapper.MemberMapper;
import com.mcbt.member.vo.LoginVO;

import com.mcbt.member.vo.WriteVO;

// 자동 생성
@Service
@Qualifier("msi")
public class MemberServiceImpl implements MemberService {

	// 자동 DI- Mapper
	@Inject
	private MemberMapper mapper;
	
	@Override
	public LoginVO login(LoginVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.login(vo);
	}
	@Override
	public int write(WriteVO vo) throws Exception{
		return mapper.write(vo);
	}

}
