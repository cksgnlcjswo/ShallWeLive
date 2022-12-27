package com.ssafy.home.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
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

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
@AutoConfigureMybatis
public class UserRestControllerTest {
	@Autowired
	private MockMvc mock;
	
	@MockBean
	UserService userService;
	
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
	
	
	
	private static String toJson(User user) {
		
		try {
			return new ObjectMapper().writeValueAsString(user);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
