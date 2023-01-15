package com.ssafy.home.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ssafy.home.model.dto.MessageDto;
import com.ssafy.home.model.dto.UserIdDto;
import com.ssafy.home.model.service.MessageService;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageController.class)
@AutoConfigureMybatis
public class MessageControllerTest {

	@MockBean
	private MessageService messageService;
	
	@Autowired
	private MockMvc mock;
	
	@DisplayName("채팅 상대 가져오기 테스트")
	@Test
	public void getUsersTest() throws Exception {
		
		List<UserIdDto> users = new ArrayList<>();
		
		users.add(new UserIdDto("test1"));
		users.add(new UserIdDto("test2"));
		
		when(messageService.getUsers(Mockito.<String>any())).thenReturn(users);
		
		mock.perform(get("/message/users").param("userId", "test"))
			.andExpect(status().isOk());
	}
	
	@DisplayName("상대방 채팅내역 가져오기 테스트")
	@Test
	public void getMessageListTest() throws Exception {
		
		List<MessageDto> mList = new ArrayList<>();
		
		mList.add(new MessageDto("test1","test2","hi","2022-01-02"));
		//mList.add(new MessageDto("test2","test1","hello","2022-01-02"));
	
		when(messageService.getMessageList(Mockito.<Map<String,Object>>any())).thenReturn(mList);
		
		mock.perform(get("/message").param("me", "test1").param("you", "test2"))
			.andExpect(status().isOk());
	}
}
