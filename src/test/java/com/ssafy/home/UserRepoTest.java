package com.ssafy.home;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.home.model.dto.User;
import com.ssafy.home.model.repo.UserRepo;

@SpringBootTest
@Transactional
public class UserRepoTest {

	@Autowired
	UserRepo urepo;
	
	@Test
	public void loginTest() throws SQLException {
		// when
		
		User user = User.builder().userId("ssafy").userPass("1234").build();
				
		User result = urepo.login(user);
		// then
		assertNotNull(result);
	}
	
	@Test
	public void loginFailTest() throws SQLException {
		// when
		User user = User.builder().userId("ssafyffff").userPass("1234").build();
		
		User result = urepo.login(user);
		// then
		assertEquals(result, null);
	}
	
	@Test
	public void signupTest() throws SQLException {
		// given
		User user = User.builder().userId("test").userPass("1234").userName("test").email("test@naver.com").phone("test").build();
		// when
		int result = urepo.signup(user);
		// then
		assertEquals(result, 1);
	}
	
	@Test
	public void modifyUserTest() throws SQLException {
		// given
		User user = User.builder().userId("ssafy").userPass("1234").userName("test11").email("test@naver.com").phone("test").build();
		// when
		int result = urepo.updateUser(user);
		// then
		assertEquals(result, 1);
	}
	
}
