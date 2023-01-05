package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.home.model.dto.DongCode;
import com.ssafy.home.model.dto.User;
import com.ssafy.home.model.repo.BoardRepo;
import com.ssafy.home.model.repo.MessageRepo;
import com.ssafy.home.model.repo.RoommateRepo;
import com.ssafy.home.model.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	BoardRepo brepo;

	@Autowired
	RoommateRepo rRepo;
	
	@Autowired
	MessageRepo mRepo;
	
	@Autowired
	JwtService jwtService;
	
	@Override
	public User login(User user) throws SQLException {
		
		//아이디가 null이거나 비밀번호가 null인경우 return
		if(user.getUserId() == null || user.getUserPass() == null) return null;	
		return repo.login(user);
	}

	@Override
	@Transactional
	public int signup(User user) throws SQLException {
		return repo.signup(user);
	}

	@Override
	public int updateUser(User user) throws SQLException {
		return repo.updateUser(user);
	}
	
	@Transactional
	@Override
	public int deleteUser(String userId) throws SQLException {
		brepo.deleteArticleByUserId(userId);
		rRepo.deleteSeeker(userId);
		mRepo.deleteMessageByUserId(userId);
		return repo.deleteUser(userId);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		repo.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		
		return repo.getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		repo.deleteRefreshToken(map);
	}

	@Override
	public User userInfo(String userid) {
		
		return repo.userInfo(userid);
	}

	@Override
	public User userInfoByEmail(String email) {
		return repo.userInfoByEmail(email);
	}
	
	
}
