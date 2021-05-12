package com.mcbt.member.service;


import com.mcbt.member.vo.LoginVO;
import com.mcbt.member.vo.WriteVO;

public interface MemberService {

	//로그인
	public LoginVO login(LoginVO vo) throws Exception;

	//회원가입
	//3. 게시판 글쓰기
	public int write(WriteVO vo) throws Exception;

}
