package com.ssafy.home.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoommateDto {
	int no;
	private String userId;
	private String content;
	private String userName;
	private String email;
	private String phone;
	private String info;
}
