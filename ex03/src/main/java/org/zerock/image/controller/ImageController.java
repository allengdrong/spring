package org.zerock.image.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.image.service.ImageService;

import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/image")
@Log4j
public class ImageController {

	private final String MODULE = "image";
	
	@Autowired
	@Qualifier("isi")
	private ImageService service;
	
	// 1. 이미지 갤러리 리스트
	@GetMapping("/list.do")
	public String list(@ModelAttribute PageObject pageObject, Model model) throws Exception {
		
		if(pageObject.getPerPageNum() == 10)
			pageObject.setPerPageNum(8);
		
		log.info("list().pageObject : " + pageObject);
		
		model.addAttribute("list", service.list(pageObject));
		
		
		// jsp의 정보를 리턴한다.
		return MODULE + "/list";
		
	}
	
	// 2. 이미지 보기
	@GetMapping("/view.do")
	public String view(Long no, @ModelAttribute PageObject pageObject,
			Model model) throws Exception {
		log.info("view().no : " + no);
		
		model.addAttribute("vo", service.view(no));
		
		// jsp의 정보를 리턴한다.
		return MODULE + "/view";
		
	}
	
}
