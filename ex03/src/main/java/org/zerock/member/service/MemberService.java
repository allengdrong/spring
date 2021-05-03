package org.zerock.member.service;

import org.zerock.member.vo.LoginVO;

public interface MemberService {

	public LoginVO login(LoginVO vo) throws Exception;
	
}
