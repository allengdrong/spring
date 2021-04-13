package org.zerock.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;

import lombok.extern.log4j.Log4j;

// 자동 생성 - @Controller, @Service, @Repository, @RestController, @Component, @~advice
// servlet-context.xml에 component-scan으로 설정되어 있어야 한다.
@Controller // url과 실행 mapping
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	@RequestMapping("")
	// return type이 void이면 jsp 정보가 없으므로 url 자체가 jsp 정보가 된다.
	// 예 : /localhost/board/list -> return을 void로 지정할 수 있다.
	public void basic() {
		
		log.info("basic..........");
		
	}
	
	// get 방식으로 넘어오는 url 받기
	// /sample/basic
	@RequestMapping(value = "/basic", method = {RequestMethod.GET})
	public void basicGet() {
		
		log.info("basic get..........");
		
		
	}
	
	// get 방식으로 넘어오는 url 받기 - @GetMapping
	// /sample/basicGet
	@GetMapping("/basicGet")
	public void basicGet2() {

		log.info("basic get 2..........");
		
	}
	
	// get 방식으로 넘어오는 url 받기 - /sample/ex02
	// get 방식으로 넘어오는 데이터 받기 - 이름, 나이
	// url localhost/sample/ex02?name=lee&age=30
	@GetMapping("/ex02")
	public String ex02(
			@RequestParam("name") String name,
			@RequestParam("age") int age
			) {
		
		log.info("name : " + name);
		log.info("age : " + age);
		// /WEB-INF/views/ + ex02 + .jsp
		return "ex02";
	}
	
	@GetMapping("/ex03")
	public String ex03(String name, int age) {
		
		log.info("ex03()------------");
		log.info("name : " + name);
		log.info("age : " + age);
		// /WEB-INF/views/ + ex03 + .jsp
		return "ex03";
	}
	
	// ArrayList로 데이터 받기
	// /sample/ex02List?ids=111&ids=222&ids=333
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids : " + ids);
		return "ex02List";
	}
	
	// ArrayList로 데이터 받기
	// /sample/ex02List1?ids=111&ids=222&ids=333
	@GetMapping("/ex02List1")
	// ArrayList 인 경우 @RequestParam("ids") 없으면 받아지지 않는다.
	// String[] 인 경우 변수 이름만 맞으면 된다. @RequestParam("ids") 없어도 된다.
	public String ex02List1(@RequestParam("ids") ArrayList<String> ids) {
//	public String ex02List1(String[] ids) {
		for(String s : ids)
			log.info("s : " + s);
//		log.info("ids : " + ids);
		return "ex02List1";
	}
	
	// DTO : Data Transfer Object = VO : Value Object
	// BoardDTO = BoardVO = Board : private 변수 선언, getter & setter, toString()
	// /sample/ex02DTO?name=test
	@GetMapping("/ex02DTO")
	public String ex02DTO(SampleDTO dto) {
		
		log.info("dto : " + dto);
		return "ex02DTO";
	}
	
	// List - DTOs
	// /sample/ex02Bean?list[0].name=aaa&list[1].name=bbb
	// [, ] - URL로 사용 못하는 문자열 -> 오류 / unicode로 작성해 넘겨준다. [ : %5B, ] : %5D
	// /sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		
		log.info("list : " + list);
		return "ex02Bean";
	}
	
	// @ModelAttribute test
	// WEB-INF/views/ + sample/ex04 + .jsp
	// @ModelAttribute("page") int page -> 넘어오는 page 데이터를 받아서 바로 Model에 담는다.
	// JSP로 page 정보를 바로 넘겨 줄 수 있다.
	@GetMapping("/ex04")
	public void ex04(@ModelAttribute("dto")SampleDTO dto, @ModelAttribute("page") int page) {
		
		log.info("dto : " + dto);
		log.info("page : " + page);
		
	}
	
	// 객체 타입의 데이터를 순수 데이터로 전송 -> JSON 데이터 활용
	// 순수한 데이터를 전달하는 메서드만 모아서 @RestController를 만든다.
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("ex06... DTO data return ");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
	}
	
	// 처리된 상태코드와 함께 보내는 ResponseEntity
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		log.info("ex07... ResponseEntity return ");
		
		String msg =  "{name:'홍길동'}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	// file upload Form
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload.....");
	}
	
	// file upload 처리
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		// 반복문(for) 람다식 표시
		files.forEach(file -> {
			if(!file.getOriginalFilename().equals("")) {
				log.info("[upload File List] ----------------- ");
				log.info("name : " + file.getOriginalFilename());
				log.info("size : " + file.getSize());
			}
		});
		for(MultipartFile file : files) {
			log.info("[upload File List] ----------------- ");
			log.info("name : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
		}
	}

}
