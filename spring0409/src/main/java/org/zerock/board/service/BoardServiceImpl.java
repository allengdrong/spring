package org.zerock.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zerock.board.mapper.BoardMapper;
import org.zerock.board.vo.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@Qualifier("bsi")
public class BoardServiceImpl implements BoardService{

	@Setter(onMethod_ = @Autowired )
	private BoardMapper mapper;
	
	// 1. 리스트
	@Override
	public List<BoardVO> list() throws Exception {
		// TODO Auto-generated method stub
		
		log.info("list() 실행-------------------");
		
		return mapper.list();
	}
	
	// 2. 글보기
	@Override
	public BoardVO view(Long no) throws Exception {
		log.info("view() 실행-------------------");
		
		return mapper.view(no);
	}
	
	// 3. 글쓰기
	@Override
	public int write(BoardVO vo) throws Exception {
		
		return mapper.write(vo);
	}
	
	// 4. 글수정
	@Override
	public int update(BoardVO vo) throws Exception {
		
		return mapper.update(vo);
	}
	
	// 5. 글삭제
	@Override
	public int delete(Long no) throws Exception {
		// TODO Auto-generated method stub
		return mapper.delete(no);
	}

}
