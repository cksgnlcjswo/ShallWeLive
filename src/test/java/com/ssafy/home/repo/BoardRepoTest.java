package com.ssafy.home.repo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.home.model.dto.BoardDto;
import com.ssafy.home.model.repo.BoardRepo;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
public class BoardRepoTest {
	
	@Autowired
	BoardRepo brepo;
	
	@Test
	public void writeArticleTest() throws Exception {
		BoardDto boarDto = BoardDto.builder()
							.userid("ssafy")
							.content("test")
							.hit(0).build();
		
		int result = brepo.writeArticle(boarDto);
		assertEquals(result, 1);
	}
	
	@Test
	public void ListArticleTest() throws Exception {
		BoardDto boarDto = BoardDto.builder()
							.userid("ssafy")
							.content("test")
							.hit(0).build();
		
		BoardDto boarDto2 = BoardDto.builder()
				.userid("ssafy")
				.content("test2")
				.hit(0).build();
		
		brepo.writeArticle(boarDto);
		brepo.writeArticle(boarDto2);
		
		List<BoardDto> result = brepo.listArticle();
		
		assertNotNull(result);
	}
	
	@Test
	public void getArticleTest() throws Exception {
		BoardDto boarDto = BoardDto.builder()
							.userid("ssafy")
							.content("test")
							.hit(0).build();
		
		brepo.writeArticle(boarDto);
		List<BoardDto> result = brepo.listArticle();
		
		int articleno = result.get(0).getArticleno();
		
		BoardDto board = brepo.getArticle(articleno);
		
		assertEquals(board.getArticleno(),articleno);
	}
	
	@Test
	public void deleteArticleTest() throws Exception {
		BoardDto boarDto = BoardDto.builder()
				.userid("ssafy")
				.content("test")
				.hit(0).build();

		brepo.writeArticle(boarDto);
		
		List<BoardDto> result = brepo.listArticle();
		
		int articleno = result.get(0).getArticleno();
		
		int res = brepo.deleteArticle(articleno);
		
		assertEquals(res,1);
	}
}
