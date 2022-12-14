package com.ssafy.home.model.repo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.model.dto.RoommateDto;
import com.ssafy.home.model.dto.SeekDto;

@Mapper
public interface RoommateRepo {

	List<RoommateDto> getSeeker(String aptName);

	int insertSeeker(SeekDto seekDto);

	int deleteSeeker(String userId);
}
