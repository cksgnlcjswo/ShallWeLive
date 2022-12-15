package com.ssafy.home.model.repo;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BuildingInfoRepo {
	List<BuildingInfoRepo> select(String dongCode, String categoryCode) throws SQLException;
}
