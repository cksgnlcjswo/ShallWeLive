package com.ssafy.home;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.home.model.dto.BoardDto;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(BoardRestController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class BoardControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@Test
	public void bookInsertTest() throws Exception {
		
		BoardDto board = BoardDto.builder()
								.userid("ssafy")
								.subject("test 제목")
								.content("test contet")
								.hit(0).build();
		
		//when(boardService.writeArticle(board)).thenReturn(true);
		
		mock.perform(post("/board")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(board)))
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
