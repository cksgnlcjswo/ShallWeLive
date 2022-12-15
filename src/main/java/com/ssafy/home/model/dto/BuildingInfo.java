package com.ssafy.home.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingInfo {
	private int no;
	private String title;
	private String categoryCode;
	private String category;
	private String sidoCode;
	private String sido;
	private String sigunguCode;
	private String sigungu;
	private String hangjeongdongCode;
	private String hangjeongdong;
	private String dongCode;
	private String dong;
	private String jibunCode;
	private String bonbun;
	private String bubun;
	private String jibunaddress;
	private String doroCode;
	private String building;
	private String doroaddress;
	private String lng;
	private String lat;

}
