package com.ssafy.home;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.home.model.dto.AptInfo;
import com.ssafy.home.model.service.AptService;
import com.ssafy.home.util.PageNavigationForPageHelper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class PagingTest {
	
	 @Autowired
	 AptService service;
	    
	 @Value("${paging.perpage}")
	 int per;
	    
    @Test
    public void pagingTest() throws Exception {
        PageHelper.startPage(1, per);
        
        Map<String,Object> map = new HashMap<>();
		
		map.put("dong", "1111017400");
		map.put("year",2019);
		map.put("month",12);
        
        Page<AptInfo> info = service.searchByDongAndPrice(map);
        PageNavigationForPageHelper helper = new PageNavigationForPageHelper(info, "/test");
        String navigationText = helper.getNavigation();
        log.debug("navigation text: {}", navigationText);
        List<AptInfo> list = (List<AptInfo>) helper.getPage().getResult();
        for(AptInfo apt: list) {
            log.info("book: {}", apt);
        }
    }
}
