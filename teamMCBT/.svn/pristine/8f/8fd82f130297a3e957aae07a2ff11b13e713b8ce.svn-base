package com.mcbt.board.mapper;

import java.util.List;

import com.mcbt.board.vo.ReplyVO;

public interface ReplyMapper {
	
	// 1. 댓글 목록
	public List<ReplyVO> list(int start, int end, Integer no) throws Exception;
	// 2. 댓글 상세보기
	public ReplyVO view(Integer rno) throws Exception;
	// 3. 댓글 갯수
	public int count(Integer no) throws Exception;
	// 4. 댓글 입력
	public void write(ReplyVO vo) throws Exception;
	// 5. 댓글 수정
	public void update(ReplyVO vo) throws Exception;
	// 6. 댓글 삭제
	public void delete(Integer rno) throws Exception;
	
}
