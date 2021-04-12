package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// NoticeDTO = NoticeVO
@Data
public class NoticeDTO {
	
	private Long no;
	private String title;
	private String content;
	// 날짜 입력할 때만 해당된다.
	// 화면의 날짜 형식이 String이므로 날짜 형식에 맞지 않아서 형식을 맞춰서 입력을 받아야 오류가 나지 않는다.
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date writeDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;

}
