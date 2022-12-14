package com.ssafy.home.model.service;

import java.util.List;

import com.ssafy.home.model.dto.RoommateDto;
import com.ssafy.home.model.dto.SeekDto;

public interface RoommateService {

	List<RoommateDto> getSeeker(String aptName);

	int insertSeeker(SeekDto seekDto);

	int deleteSeeker(String userId);
}
