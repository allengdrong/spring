package org.zerock.image.service;

import java.util.List;

import org.zerock.image.vo.ImageVO;

import com.webjjang.util.PageObject;

public interface ImageService {
	
	// 1. 이미지 갤러리 리스트
	public List<ImageVO> list(PageObject pageObject) throws Exception;
	
	// 2. 이미지 보기
	public ImageVO view(Long no) throws Exception;
}
