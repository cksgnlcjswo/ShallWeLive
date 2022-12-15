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
import com.ssafy.home.model.dto.GoogleUser;
import com.ssafy.home.model.dto.OAuthToken;
import com.ssafy.home.model.dto.User;
import com.ssafy.home.model.repo.BoardRepo;
import com.ssafy.home.model.repo.MessageRepo;
import com.ssafy.home.model.repo.RoommateRepo;
import com.ssafy.home.model.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
	
	@Autowired
	OAuthService oauthService;
	
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
	public List<DongCode> interestArea(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addArea(String dongCode, String userId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isExistId(String str) throws SQLException {
		// TODO Auto-generated method stub
		return false;
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
	public User oauthLogin(String code) {
		ResponseEntity<String> accessTokenResponse = oauthService.createPostRequest(code);
        OAuthToken oAuthToken = oauthService.getAccessToken(accessTokenResponse);
        log.info("Access Token: {}", oAuthToken.getAccessToken());

        ResponseEntity<String> userInfoResponse = oauthService.createGetRequest(oAuthToken);
        GoogleUser googleUser = oauthService.getUserInfo(userInfoResponse);
        log.info("Google User Name: {}", googleUser.getName());

        //user가 등록이 되있지 않다면 등록
        if (repo.userInfoByEmail(googleUser.getEmail())==null) {
        	log.info("signup!! email {}",googleUser.getEmail());
            signUp(googleUser, oAuthToken);
        }
        
        return repo.userInfoByEmail(googleUser.getEmail());
//        
//        return jwtService.createAccessToken("userid", user.getEmail());
	}

	@Override
	public void signUp(GoogleUser googleUser, OAuthToken oAuthToken) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("email", googleUser.getEmail());
		map.put("token", oAuthToken.getAccessToken());
		map.put("id", googleUser.getId());
		map.put("name",googleUser.getName());
		repo.signUp(map);
	}

	@Override
	public User userInfoByEmail(String email) {
		return repo.userInfoByEmail(email);
	}
	
	
}
