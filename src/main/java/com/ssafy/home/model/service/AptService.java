package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.ssafy.home.model.dto.AptInfo;
import com.ssafy.home.model.dto.HouseHistory;
import com.ssafy.home.model.dto.HouseInfo;
import com.ssafy.home.model.dto.SidoGugunDongCodeDto;

public interface AptService {
	public Page<AptInfo> searchByDongAndPrice(Map<String,Object> map) throws SQLException;

	public List<SidoGugunDongCodeDto> getSido();

	public List<SidoGugunDongCodeDto> getGugunInSido(String sido);

	public List<SidoGugunDongCodeDto> getDongInGugun(String gugun);

	public HouseInfo getHouse(String aptName);

	public List<HouseHistory> getHouseHistory(String aptName);
}
