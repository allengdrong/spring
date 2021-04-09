package org.zerock.board.service;

import java.util.List;

import org.zerock.board.vo.BoardVO;

public interface BoardService {

	// 1. 리스트
	public List<BoardVO> list() throws Exception;
	
	// 2. 글보기
	public BoardVO view(Long no) throws Exception;
	
	// 3. 글쓰기
	public int write(BoardVO vo) throws Exception;
	
	// 4. 글수정
	public int update(BoardVO vo) throws Exception;
	
	// 5. 글삭제
	public int delete(Long no) throws Exception;
	
}
