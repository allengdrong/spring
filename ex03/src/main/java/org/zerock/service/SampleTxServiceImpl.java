package org.zerock.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.SampleMapper;

@Service
@Qualifier("stsi")
public class SampleTxServiceImpl implements SampleTxService {

	@Inject
	private SampleMapper mapper;
	
	// 만약에 메서드 안의 DB 처리가 한개라도 오류가 나면 rollback 시킨다.
	@Transactional
	@Override
	public int addData() {
		// TODO Auto-generated method stub

		// 정상 입력 처리 - 위에 Transactional 처리를 하면 처리 단위 블록안에 하나라도 오류가 있으면 rollback 시킨다.
		mapper.insertCol();
		// PK 5가 중복 오류가 발생
		mapper.insertCol();
		
		return 1;
	}

}
