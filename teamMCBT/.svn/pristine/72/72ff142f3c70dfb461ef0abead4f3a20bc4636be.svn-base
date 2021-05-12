package com.mcbt.board.mapper;

import java.util.List;

import com.mcbt.board.vo.BoardVO;

public interface BoardMapper {

	// 1. 게시글 전체 목록 ==> 검색옵션, 키워드 매개변수 추가
	public List<BoardVO> list(String category, int start, int end, String option, String q);
	// 2. 게시글 보기
	public BoardVO view(int no);
	// 2-1. 게시글 조회 증가
	public int increase(int no);
	// 3. 게시글 작성
	public int write(BoardVO vo);
	// 4. 게시글 수정
	public int update(BoardVO vo);
	// 5. 게시글 삭제
	public int delete(int no);
	// 6. 게시글 레코드 갯수 메서드 추가
	public int countArticle(String option, String q);
	
//	// 7. 게시물 첨부파일 추가
//	public void addAttach(String fullName);
//	// 8. 게시물 첨부파일 목록
//	public List<String> getAttach(int bno);
//	// 9. 게시글 첨부파일 삭제처리
//	public void deleteFile(String fullname);
//	// 10. 게시글 첨부파일 업데이트처리
//	public void updateAttach(String fullName, int bno);
	
}
