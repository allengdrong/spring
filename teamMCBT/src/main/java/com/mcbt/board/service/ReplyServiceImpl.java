package com.mcbt.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mcbt.board.mapper.ReplyMapper;
import com.mcbt.board.vo.ReplyVO;

import lombok.AllArgsConstructor;

// 자동생성 - @Controller, @Service, @Repository, @Component, @RestCotroller,
//   @~Advice : root-context.xml, servlet-context.xml 설정
@Service
// private 변수에 대한 자동 DI - 생성자
@AllArgsConstructor
@Qualifier("rsi")
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyMapper mapper;

	// 1. 댓글 목록
	@Override
	public List<ReplyVO> list(Integer no, int start, int end, HttpSession session) throws Exception {
		List<ReplyVO> items = mapper.list(no, start, end);
		// 세션에서 현재 사용자 id값 저장
		String id = (String) session.getAttribute("id");
		for(ReplyVO vo : items){
			// 댓글 목록중에 중에 비밀 댓글이 있을 경우
			if(vo.getShow().equals("y")){
				if(id== null){ // 비로그인 상태면 비밀 댓글로 처리
					vo.setReplycontent("비밀댓글입니다.");
				} else { // 로그인 상태일 경우
					String writer = vo.getWriter(); // 게시물 작성자 저장
					String replyer = vo.getReplyer(); // 댓글 작성자 저장
					// 로그인한 사용자가 게시물의 작성자X 댓글 작성자도 X 비밀댓글로 처리
					if(!id.equals(writer) && !id.equals(replyer)) {
						vo.setReplycontent("비밀댓글입니다.");
					}
				}
			}
		}
		return items;
	}
	// 2. 댓글 보기
	@Override
	public ReplyVO view(Integer rno) throws Exception {
		return mapper.view(rno);
	}
	
	// 3. 댓글 갯수
	@Override
	public int count(Integer no) throws Exception {
		return mapper.count(no);
	}

	// 4. 댓글 입력
	@Override
	public void write(ReplyVO vo) throws Exception {
		mapper.write(vo);
	}
	// 5. 댓글 수정
	@Override
	public void update(ReplyVO vo) throws Exception {
		mapper.update(vo);
	}
	// 6. 댓글 삭제
	@Override
	public void delete(Integer rno) throws Exception {
		mapper.delete(rno);
	}
}
