package com.ssafy.home.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.home.model.dto.User;
import com.ssafy.home.model.repo.BoardRepo;
import com.ssafy.home.model.repo.MessageRepo;
import com.ssafy.home.model.repo.UserRepo;
import com.ssafy.home.model.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class userServiceTest {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepo uRepo;
	
	@MockBean
	private BoardRepo bRepo;
	
	@MockBean
	private MessageRepo mRepo;
	
	@DisplayName("login service 성공 테스트")
	@Test
	public void loginTest() throws Exception {
		
		User user = User.builder()
				.userId("testId")
				.userPass("1234")
				.userName("testuser")
				.email("11@namver.com")
				.phone("010-1234-5678")
				.gender("G")
				.info("hihi").build();
		
		when(uRepo.login(Mockito.<User>any())).thenReturn(user);
		
		User result = userService.login(user);
		assertNotNull(result);
	}
	
	@DisplayName("signup 서비스 test")
	@Test
	public void signupTest() throws Exception {
		User user = User.builder()
				.userId("testId")
				.userPass("1234")
				.userName("testuser")
				.email("11@namver.com")
				.phone("010-1234-5678")
				.gender("G")
				.info("hihi").build();
		
		when(uRepo.signup(Mockito.<User>any())).thenReturn(1);
		int result = userService.signup(user);
		
		assertEquals(result, 1);
	}
	
	@DisplayName("유저 업데이트 서비스 테스트")
	@Test
	public void updateUserTest() throws Exception {
		
		User user = User.builder()
				.userId("testId")
				.userPass("1234")
				.userName("testuser")
				.email("11@namver.com")
				.phone("010-1234-5678")
				.gender("G")
				.info("hihi").build();
		
		when(uRepo.updateUser(Mockito.<User>any())).thenReturn(1);
		int result = userService.updateUser(user);
		
		assertEquals(result,1);
	}
	
	@DisplayName("user 삭제 테스트")
	@Test
	public void deleteUser() throws Exception {
		String id = "ssafy";
		
		when(uRepo.deleteUser(Mockito.<String>any())).thenReturn(1);
		int result = userService.deleteUser(id);
		
		assertEquals(result,1);
	}
}
