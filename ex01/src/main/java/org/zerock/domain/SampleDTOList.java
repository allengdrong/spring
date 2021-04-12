package org.zerock.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

//getter / setter / toString / 생성자 -> lombok이 해결해 준다.
@Data // setter & getter & toString() & 생성자 를 자동으로 만드는 어노테이션
public class SampleDTOList {
	
	private List<SampleDTO> list;
	
	public SampleDTOList() {
		list = new ArrayList<SampleDTO>();
	}

}
