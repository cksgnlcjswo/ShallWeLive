package com.ssafy.home.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.home.model.dto.User;
import com.ssafy.home.model.service.JwtService;
import com.ssafy.home.model.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
@AutoConfigureMybatis
@Slf4j
public class UserRestControllerTest {
	@Autowired
	private MockMvc mock;
	
	@MockBean
	UserService userService;
	
	//테스트용 secret SALT값
	private static final String SALT = "ssafySecret";
	private static final int ACCESS_TOKEN_EXPIRE_MINUTES = 1; // 분단위
	private static final int REFRESH_TOKEN_EXPIRE_MINUTES = 2; // 주단위

	@MockBean
	JwtService jwtService;
	
	@DisplayName("user 회원가입 테스트")
	@Test
	public void signUpTest() throws Exception {
		
		User user = User.builder()
						.userId("testId")
						.userPass("1234")
						.userName("testuser")
						.email("11@namver.com")
						.phone("010-1234-5678")
						.gender("G")
						.info("hihi").build();
		
		when(userService.signup(user)).thenReturn(1);
	
		mock.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(user)))
				.andExpect(status().isOk());
	}
	
	@DisplayName("user 회원가입 실패테스트")
	@Test
	public void signUpFailTest() throws Exception {
		
		User user = User.builder()
						.userId("testId")
						.userPass("1234")
						.userName("testuser")
						.email("11@namver.com")
						.phone("010-1234-5678")
						.gender("G")
						.info("hihi").build();
		
		when(userService.signup(user)).thenReturn(0);
	
		mock.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(user)))
				.andExpect(status().is5xxServerError());
	}
	
	@DisplayName("회원 수정 테스트")
	@Test
	public void userUpdateTest() throws Exception {
		User user = User.builder()
				.userId("testId")
				.userPass("1234")
				.userName("testuser")
				.email("11@namver.com")
				.phone("010-1234-5678")
				.gender("G")
				.info("hihi").build();
		
		when(userService.updateUser(user)).thenReturn(1);
		
		mock.perform(put("/user")
					.contentType(MediaType.APPLICATION_JSON)
					.content(toJson(user)))
					.andExpect(status().isOk());
	}
	
	@DisplayName("아이디 삭제 테스트")
	@Test
	public void userDeleteTest() throws Exception {
		
		String userId = "ssafy";
		
		when(userService.deleteUser(userId)).thenReturn(1);
		
		mock.perform(delete("/user/{userId}",userId))
			.andExpect(status().isOk());
	}
	
	@DisplayName("로그인 성공테스트")
	@Test
	public void loginSuccessTest() throws Exception {
		
		User user = User.builder()
				.userId("testId")
				.userPass("1234")
				.userName("testuser")
				.email("11@namver.com")
				.phone("010-1234-5678")
				.gender("G")
				.info("hihi").build();
		
		when(userService.login(user)).thenReturn(user);
		when(jwtService.createAccessToken("userid", user.getUserId())).thenReturn(create("userid", user.getUserId(), "access-token", 1000 * 60 * ACCESS_TOKEN_EXPIRE_MINUTES));
		when(jwtService.createRefreshToken("userid", user.getUserId())).thenReturn(create("userid", user.getUserId(), "refresh-token", 1000 * 60 * 60 * 24 * 7 * REFRESH_TOKEN_EXPIRE_MINUTES));
	
		mock.perform(post("/user/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(toJson(user)))
			.andExpect(status().isAccepted())
			.andExpect(jsonPath("$.access-token").exists())
			.andExpect(jsonPath("$.refresh-token").exists());
	}
	
	@DisplayName("로그인 실패 테스트")
	@Test
	public void loginFailTest() throws Exception {
		User user = User.builder()
				.userId("testId")
				.userPass("1234")
				.userName("testuser")
				.email("11@namver.com")
				.phone("010-1234-5678")
				.gender("G")
				.info("hihi").build();
		
		when(userService.login(user)).thenReturn(null);
		when(jwtService.createAccessToken("userid", user.getUserId())).thenReturn(create("userid", user.getUserId(), "access-token", 1000 * 60 * ACCESS_TOKEN_EXPIRE_MINUTES));
		when(jwtService.createRefreshToken("userid", user.getUserId())).thenReturn(create("userid", user.getUserId(), "refresh-token", 1000 * 60 * 60 * 24 * 7 * REFRESH_TOKEN_EXPIRE_MINUTES));
		
		mock.perform(post("/user/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(toJson(user)))
			.andExpect(jsonPath("$.message",containsString("fail")));
	}
	
	@DisplayName("회원정보 반환 테스트")
	@Test
	public void getUserInfoTest() throws Exception {
		
		User user = User.builder()
				.userId("testId")
				.userPass("1234")
				.userName("testuser")
				.email("11@namver.com")
				.phone("010-1234-5678")
				.gender("G")
				.info("hihi").build();
		
		when(userService.userInfo("testId")).thenReturn(user);
		when(jwtService.checkToken(Mockito.<String>any())).thenReturn(true);
		
		mock.perform(get("/user/info/{userid}","testId")
			.header("access-token", create("userid", user.getUserId(), "access-token", 1000 * 60 * ACCESS_TOKEN_EXPIRE_MINUTES)))
			.andExpect(status().isAccepted());
	}
 	
	@DisplayName("로그아웃 테스트")
	@Test
	public void logoutTest() throws Exception {
		
		mock.perform(get("/user/logout/{userid}","ssafy"))
				.andExpect(status().isAccepted());
	}
	
	@DisplayName("액세스 토큰 재발급 테스트")
	@Test
	public void getRefreshToken() throws Exception {
		//리프레쉬 토큰 생성
		User user = User.builder()
				.userId("testId")
				.userPass("1234")
				.userName("testuser")
				.email("11@namver.com")
				.phone("010-1234-5678")
				.gender("G")
				.info("hihi").build();
		String token = create("userid",user.getUserId(), "refresh-token", 1000 * 60 * REFRESH_TOKEN_EXPIRE_MINUTES);
		
		when(jwtService.checkToken(Mockito.<String>any())).thenReturn(true);
		when(userService.getRefreshToken(Mockito.<String>any())).thenReturn(token);
		when(jwtService.createAccessToken("userid", user.getUserId())).thenReturn(create("userid", user.getUserId(), "access-token", 1000 * 60 * ACCESS_TOKEN_EXPIRE_MINUTES));
		
		mock.perform(post("/user/refresh")
					.contentType(MediaType.APPLICATION_JSON)
					.content(toJson(user))
					.header("refresh-token", token))
					.andExpect(status().isAccepted())
					.andExpect(jsonPath("$.access-token").exists());
	}
	
	//jwt 생성함수
	public <T> String create(String key, T data, String subject, long expire) {
		String jwt = Jwts.builder()
				// Header 설정 : 토큰의 타입, 해쉬 알고리즘 정보 세팅.
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("regDate", System.currentTimeMillis()) // 생성 시간
				// Payload 설정 : 유효기간(Expiration), 토큰 제목 (Subject), 데이터 (Claim) 등 정보 세팅.
				.setExpiration(new Date(System.currentTimeMillis() + expire)) // 토큰 유효기간
				.setSubject(subject) // 토큰 제목 설정 ex) access-token, refresh-token
				.claim(key, data) // 저장할 데이터
				// Signature 설정 : secret key를 활용한 암호화.
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact(); // 직렬화 처리.
		return jwt;
	}
	// Signature 설정에 들어갈 key 생성.
	private byte[] generateKey() {
		byte[] key = null;
		try {
			// charset 설정 안하면 사용자 플랫폼의 기본 인코딩 설정으로 인코딩 됨.
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (log.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				log.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}

		return key;
	}
		
	private static String toJson(User user) {
		
		try {
			return new ObjectMapper().writeValueAsString(user);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
