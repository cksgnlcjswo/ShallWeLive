package com.ssafy.home.model.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DongCode {

	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String dongName;
}
