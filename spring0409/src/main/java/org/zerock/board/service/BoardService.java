package org.zerock.board.service;

import java.util.List;

import org.zerock.board.vo.BoardVO;

public interface BoardService {
	
	// 1. 게시판 리스트
	public List<BoardVO> list() throws Exception;

	// 2. 게시판 글보기
	public BoardVO view(Long no) throws Exception;
	
	// 3-2. 게시판 글쓰기 처리
	public int write(BoardVO vo) throws Exception;
	
	// 4-2. 게시판 글수정 처리
	public int update(BoardVO vo) throws Exception;

	// 5. 게시판 글삭제 처리
	public int delete(Long no) throws Exception;

}
