package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.ssafy.home.model.dto.AptInfo;
import com.ssafy.home.model.dto.HouseHistory;
import com.ssafy.home.model.dto.HouseInfo;
import com.ssafy.home.model.dto.SidoGugunDongCodeDto;
import com.ssafy.home.model.repo.DongCodeRepo;

@Service
public class AptServiceImpl implements AptService {
	
	@Autowired
	DongCodeRepo mapper;
	
	@Override
	public Page<AptInfo> searchByDongAndPrice(Map<String,Object> map) throws SQLException {
		
		return mapper.searchByDongAndPrice(map);
	}

	@Override
	public List<SidoGugunDongCodeDto> getSido() {
		
		return mapper.getSido();
	}

	@Override
	public List<SidoGugunDongCodeDto> getGugunInSido(String sido) {
		return mapper.getGugunInSido(sido);
	}

	@Override
	public List<SidoGugunDongCodeDto> getDongInGugun(String gugun) {
		return mapper.getDongInGugun(gugun);
	}

	@Override
	public HouseInfo getHouse(String aptName) {
		return mapper.getHouse(aptName);
	}

	@Override
	public List<HouseHistory> getHouseHistory(String aptName) {
		return mapper.getHouseHistory(aptName);
	}
}
