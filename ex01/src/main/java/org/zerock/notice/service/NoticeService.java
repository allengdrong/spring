package org.zerock.notice.service;

import java.util.List;

import org.zerock.notice.vo.NoticeVO;

import com.webjjang.util.PageObject;

public interface NoticeService {
	
	// 1. 공지 리스트
	public List<NoticeVO> list(PageObject pageObject) throws Exception;
	
	// 2. 공지 글보기
	public NoticeVO view(Long no) throws Exception;
	
	// 3. 게시판 글쓰기
	public int write(NoticeVO vo) throws Exception;
		
	// 4. 게시판 글수정
	public int update(NoticeVO vo) throws Exception;
		
	// 5. 게시판 글삭제
	public int delete(NoticeVO vo) throws Exception;

}
