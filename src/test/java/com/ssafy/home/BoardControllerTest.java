package com.ssafy.home;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.home.controller.BoardRestController;
import com.ssafy.home.model.dto.BoardDto;
import com.ssafy.home.model.service.BoardService;

@RunWith(SpringRunner.class)
@WebMvcTest(BoardRestController.class)
@AutoConfigureMybatis
public class BoardControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	BoardService boardService;
	
	//book inserttest
	@Test
	public void bookInsertTest() throws Exception {
		
		BoardDto board = BoardDto.builder()
								.userid("ssafy")
								.subject("test 제목")
								.content("test contet")
								.hit(0).build();
		
		when(boardService.writeArticle(board)).thenReturn(true);
		
		mock.perform(post("/board")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(board)))
				.andExpect(status().isOk());
	}
	
	//listArticle test
	@Test
	public void bookListTest() throws Exception {
		List<BoardDto> arr = new ArrayList<>();
		when(boardService.listArticle()).thenReturn(arr);
		
		mock.perform(get("/board"))
			.andExpect(status().isOk());
	}
	
	private static String toJson(BoardDto board) {
		
		try {
			return new ObjectMapper().writeValueAsString(board);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
