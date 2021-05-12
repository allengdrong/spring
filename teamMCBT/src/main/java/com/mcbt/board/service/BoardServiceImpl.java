package com.mcbt.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcbt.board.mapper.BoardMapper;
import com.mcbt.board.vo.BoardVO;

// 자동 생성
@Service
// 타입이 같은 객체들이 많은 경우 선택할 수 있도록 지정
@Qualifier("bsi")
public class BoardServiceImpl implements BoardService {

	// mapper를 이용해서 DB처리해야한다.
	// 자동 DI 적용 - @Setter : lombok, @Autowired : spring, @Inject : java
	@Autowired
	private BoardMapper mapper;

	// 1. 게시글 목록
	@Override
	public List<BoardVO> list(String category, int start, int end, String option, String q) throws Exception {
		
		return mapper.list(category, start, end, option, q);
	}
	// 2. 게시글 작성
	@Transactional // 트랜잭션 처리 메서드로 설정
	@Override
	public void write(BoardVO vo) throws Exception {
		String title = vo.getTitle();
		String content = vo.getContent();
		String writer = vo.getWriter();
		// *태그문자 처리 (< ==> &lt; > ==> &gt;)
		// replace(A, B) A를 B로 변경
		title = title.replace("<", "&lt;");
		title = title.replace("<", "&gt;");
		writer = writer.replace("<", "&lt;");
		writer = writer.replace("<", "&gt;");
		// *공백문자 처리  
		title = title.replace("  ",	"&nbsp;&nbsp;");
		writer = writer.replace("  ",	"&nbsp;&nbsp;");
		// *줄바꿈 문자처리
		content = content.replace("\n", "<br>");
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		// 게시물 등록
		mapper.write(vo);
		// 게시물의 첨부파일 정보 등록
//		String[] files = vo.getFiles(); // 첨부파일 배열
//		if(files == null) return; // 첨부파일이 없으면 메서드 종료
//		// 첨부파일들의 정보를 tbl_attach 테이블에 insert
//		for(String name : files){ 
//			mapper.addAttach(name);
//		}
	}

	// 3. 게시글 보기
	@Override
	public BoardVO view(int no) throws Exception {
		return mapper.view(no);
	}
	
	// 3-1. 게시글 조회수 증가
	@Override
	public void increase(int no, HttpSession session) throws Exception {
		long update_time = 0;
		// 세션에 저장된 조회시간 검색
		// 최초로 조회할 경우 세션에 저장된 값이 없기 때문에 if문은 실행X
		if(session.getAttribute("update_time_"+no) != null){
								// 세션에서 읽어오기
			update_time = (Long) session.getAttribute("update_time_" + no);
		}
		// 시스템의 현재시간을 current_time에 저장
		long current_time = System.currentTimeMillis();
		// 일정시간이 경과 후 조회수 증가 처리 24*60*60*1000(24시간)
		 // 시스템현재시간 - 열람시간 > 일정시간(조회수 증가가 가능하도록 지정한 시간)
		if(current_time - update_time > 5*1000){
			mapper.increase(no);
			// 세션에 시간을 저장 : "update_time_"+bno는 다른변수와 중복되지 않게 명명한 것
			session.setAttribute("update_time_" + no, current_time);
			
		}
	}
	// 4. 게시글 수정
	@Transactional
	@Override
	public void update(BoardVO vo) throws Exception {
		mapper.update(vo);
//		// 첨부파일 정보 등록
//		String[] files = vo.getFiles(); // 첨부파일 배열
//		// 첨부파일이 없으면 종료
//		if(files == null) return;
//		// 첨부파일들의 정보를 tbl_attach 테이블에 insert
//		for(String name : files){
//			boardDao.updateAttach(name, vo.getBno());
//		}
	}
	// 5. 게시글 삭제
	@Override
	public void delete(int no) throws Exception {
		mapper.delete(no);
	}
	// 6. 게시글 레코드 갯수
	@Override
	public int countArticle(String option, String q) throws Exception {
		return mapper.countArticle(option, q);
	}
	
//	// 8. 게시글의 첨부파일 목록
//	@Override
//	public List<String> getAttach(int no) {
//		return mapper.getAttach(no);
//	}
//	// 9. 게시글의 첨부파일 삭제 처리
//	@Override
//	public void deleteFile(String fullname) {
//		mapper.deleteFile(fullname);
//	}

}
