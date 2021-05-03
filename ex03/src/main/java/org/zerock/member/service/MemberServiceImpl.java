package org.zerock.member.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zerock.member.mapper.MemberMapper;
import org.zerock.member.vo.LoginVO;

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

}
