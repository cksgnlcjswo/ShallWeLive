package com.ssafy.home.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseInfo {
	private int buildYear;
	private String roadName;
	private Long aptCode;
	private String apartmentName;
	private String jibun;
	private String sidoName;
	private String dongName;
	private String area;
	private int dealAmount;
}
