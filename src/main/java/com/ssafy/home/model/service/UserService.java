package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.home.model.dto.DongCode;
import com.ssafy.home.model.dto.GoogleUser;
import com.ssafy.home.model.dto.OAuthToken;
import com.ssafy.home.model.dto.User;

public interface UserService {
	User login(User user) throws SQLException;
	int signup(User user) throws SQLException;
	int updateUser(User user) throws SQLException;
	int deleteUser(String userId) throws SQLException;
	List<DongCode> interestArea(String id) throws SQLException;
	int addArea(String dongCode, String userId) throws SQLException;
	boolean isExistId(String str) throws SQLException;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	User userInfo(String userid);
	public User oauthLogin(String code);
	void signUp(GoogleUser googleUser, OAuthToken oAuthToken);
	User userInfoByEmail(String email);
}
