package org.zerock.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zerock.notice.mapper.NoticeMapper;
import org.zerock.notice.vo.NoticeVO;

import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Service
@Qualifier("nsi")
@Log4j
public class NoticeServiceImpl implements NoticeService {
	
	@Inject
	private NoticeMapper mapper;

	@Override
	public List<NoticeVO> list(PageObject pageObject) throws Exception {
		// TODO Auto-generated method stub
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		log.info("pageObject" + pageObject);
		return mapper.list(pageObject);
	}

	@Override
	public NoticeVO view(Long no) throws Exception {
		// TODO Auto-generated method stub
		return mapper.view(no);
	}

	@Override
	public int write(NoticeVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.write(vo);
	}

	@Override
	public int update(NoticeVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(NoticeVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.delete(vo);
	}

}
