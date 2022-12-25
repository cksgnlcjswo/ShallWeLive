package com.ssafy.home.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
	
	@DisplayName("게시글 삽입 TEST")
	@Test
	public void boardInsertTest() throws Exception {
		
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
	
	@DisplayName("게시글 리스트 테스트")
	@Test
	public void boardkListTest() throws Exception {
		List<BoardDto> arr = new ArrayList<>();
		when(boardService.listArticle()).thenReturn(arr);
		
		mock.perform(get("/board"))
			.andExpect(status().isOk());
	}
	
	@DisplayName("특정 게시글 테스트")
	@Test
	public void getArticleTest() throws Exception {
		
		int aNo = 99;
		
		BoardDto board = BoardDto.builder()
				.articleno(aNo)
				.userid("ssafy")
				.subject("test 제목")
				.content("test contet")
				.hit(0).build();
		
		when(boardService.getArticle(aNo)).thenReturn(board);
	
		mock.perform(get("/board/{aNo}",aNo))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.articleno",is(aNo)));	
	}
	
	@DisplayName("게시글 수정 테스트")
	@Test
	public void updateArticle() throws Exception {
		BoardDto boardDto = BoardDto.builder()
				.articleno(1)
				.userid("ssafy")
				.subject("updated title")
				.content("updated contents")
				.hit(0).build();
		
		when(boardService.modifyArticle(any())).thenReturn(true);
	
		mock.perform(put("/board")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(boardDto)))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("success")));
	}
	
	@DisplayName("게시글 삭제 테스트")
	@Test
	public void boardDeleteTest() throws Exception {
		
		int articleno = 1;
		
		when(boardService.deleteArticle(articleno)).thenReturn(true);
		
		mock.perform(delete("/board/delete/{articleno}",articleno))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("success")));
	}
	
	@DisplayName("특정 유저가쓴 글 모두 삭제 테스트")
	@Test
	public void boardDeleteByUserIdTesT() throws Exception {
		
		String userId = "ssafy";
		
		when(boardService.deleteArticleByUserId(userId)).thenReturn(true);
		
		mock.perform(delete("/board/{userId}",userId))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("success")));
	}
	
	
	private static String toJson(BoardDto board) {
		
		try {
			return new ObjectMapper().writeValueAsString(board);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
