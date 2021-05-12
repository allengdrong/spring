package com.mcbt.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.mcbt.board.vo.BoardVO;

public interface BoardService {

	// 1. 게시글 전체 목록
	public List<BoardVO> list(String category, int start, int end, String option, String q) throws Exception;
	// 2. 게시글 상세보기
	public BoardVO view(int no) throws Exception;
	// 2-1. 게시글 조회
	public void increase(int no, HttpSession session) throws Exception;
	// 3. 게시판 글쓰기
	public void write(BoardVO vo) throws Exception;
	// 4. 게시글 수정
	public void update(BoardVO vo) throws Exception;
	// 5. 게시글 삭제
	public void delete(int no) throws Exception;
	// 6. 게시글 레코드 갯수
	public int countArticle(String option, String q) throws Exception;
	
//	// 7. 게시글 첨부파일 목록
//	public List<String> getAttach(int no);
//	// 8. 게시글 첨부파일 삭제
//	public void deleteFile(String fullname);
	
}
