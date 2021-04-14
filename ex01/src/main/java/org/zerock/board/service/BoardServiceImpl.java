package org.zerock.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zerock.board.mapper.BoardMapper;
import org.zerock.board.vo.BoardVO;

// 자동 생성
@Service
// 타입이 같은 객체들이 많은경우
//
@Qualifier("bsi")
public class BoardServiceImpl implements BoardService {

	// mapper를 이용해서 DB처리해야한다.
	// 자동 DI 적용 - @Setter : lombok, @Autowired : spring, @Inject : java
	@Inject
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> list() throws Exception {
		// TODO Auto-generated method stub
		// 페이지 처리를 위한 전체 데이터 가져오기 - 추후
		return mapper.list();
	}

	@Override
	public BoardVO view(Long no, int inc) throws Exception {
		// TODO Auto-generated method stub
		// list -> view : inc = 1 - 조회수 1증가
		if(inc == 1)
			mapper.increase(no);
		return mapper.view(no);
	}

	@Override
	public int write(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.write(vo);
	}

	@Override
	public int update(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.delete(vo);
	}

}
