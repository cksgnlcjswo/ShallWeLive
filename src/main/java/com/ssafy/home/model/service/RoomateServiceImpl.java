package com.ssafy.home.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.model.dto.RoommateDto;
import com.ssafy.home.model.dto.SeekDto;
import com.ssafy.home.model.repo.RoommateRepo;

@Service
public class RoomateServiceImpl implements RoommateService {

	@Autowired
	RoommateRepo repo;
	
	@Override
	public List<RoommateDto> getSeeker(String aptName) {
		
		return repo.getSeeker(aptName);
	}

	@Override
	public int insertSeeker(SeekDto seekDto) {
		return repo.insertSeeker(seekDto);
	}

	@Override
	public int deleteSeeker(String userId) {
		return repo.deleteSeeker(userId);
	}
	
}
