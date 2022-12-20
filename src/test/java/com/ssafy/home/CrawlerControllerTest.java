package com.ssafy.home;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class CrawlerControllerTest {
	
	@Autowired
	WebApplicationContext ctx;
	
	private MockMvc mock;
	
	@BeforeEach
	public void setup() {
		mock = MockMvcBuilders.webAppContextSetup(ctx).addFilter(((request,response,chain) -> {
			response.setCharacterEncoding("UTF-8"); //인코딩 설정
			chain.doFilter(request, response);
		})).build();
	}
	
	@Test
	public void crawlerTest() throws Exception {
		mock.perform(get("/crawler/news"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("부동산")));
	}
}
