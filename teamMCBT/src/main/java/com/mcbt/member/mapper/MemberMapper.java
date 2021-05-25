package com.mcbt.member.mapper;

import java.util.List;

import com.mcbt.member.vo.LoginVO;
import com.mcbt.member.vo.MemberVO;
import com.mcbt.member.vo.WriteVO;

public interface MemberMapper {

	//리스트
	public List<MemberVO> list() throws Exception;
	
	public LoginVO login(LoginVO vo);
	// 3. 게시판 글쓰기
	public int write(WriteVO vo) throws Exception;
}
