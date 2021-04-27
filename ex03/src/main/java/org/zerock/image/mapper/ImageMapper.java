package org.zerock.image.mapper;

import java.util.List;

import org.zerock.image.vo.ImageVO;

import com.webjjang.util.PageObject;

public interface ImageMapper {
	
	// 1. 이미지 갤러리 리스트
	public List<ImageVO> list(PageObject pageObject);
	
	// 1-1. 전체 데이터의 갯수 - 화면 표시용 - 페이지네이션
	public Long getTotalRow(PageObject pageObject);
	
	// 2. 이미지 보기
	public ImageVO view(Long no);
	
}
