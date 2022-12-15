package com.ssafy.home.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsDto {
	
	private String title;
	private String originallink;
	private String link;
	private String description;
	private String pubDate;
}
