package com.ssafy.home.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
	
	private int articleno;
	private String userid;
	private String subject;
	private String content;
	private int hit;
	private String regtime;
}