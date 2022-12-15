package com.ssafy.home.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.model.dto.DongCode;
import com.ssafy.home.model.dto.User;
import com.ssafy.home.model.dto.UserIdDto;

@Mapper
public interface UserRepo {
	User login(User user) throws SQLException;
	
	int signup(User user) throws SQLException;
	
	int updateUser(User user) throws SQLException;
	
	int deleteUser(String userid) throws SQLException;
	
	List<DongCode> interestArea(String id) throws SQLException;
	
	int addArea(String dongCode, String userId) throws SQLException;
	
	boolean isExistId(String str) throws SQLException;
	
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	User userInfo(String userid);
	User userInfoByEmail(String email);
	void signUp(Map<String,Object> map);

	List<UserIdDto> getUsers(String userId);
	
}