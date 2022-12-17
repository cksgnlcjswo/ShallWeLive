package com.ssafy.home.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.model.service.CrawlerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/crawler")
@Api("부동산 관련 뉴스  API V1")
@Slf4j
public class CrawlerRestController {
	
	@Autowired
	private CrawlerService crawlerService;
	
	@Value("${naver.news.clientId}")
	private String clientId; 
    
	@Value("${naver.news.clientSecret}")
	private String clientSecret;
    
	@ApiOperation(value="부동상 관련 뉴스정보",notes="부동산 뉴스 크롤링",response=String.class)
    @GetMapping("/news")
    public ResponseEntity<?> getNews() {
		log.info("crawler getnews - 호출");
    	 String word = null;
    	 Map<String,Object> result = new HashMap<>();
    	 
         try {
        	 word = URLEncoder.encode("부동산", "UTF-8");
         } catch (UnsupportedEncodingException e) {
             throw new RuntimeException("검색어 인코딩 실패",e);
         }
         
         String apiURL = "https://openapi.naver.com/v1/search/news?query=" + word;    // JSON 결과

         Map<String, String> requestHeaders = new HashMap<>();
         requestHeaders.put("X-Naver-Client-Id", clientId);
         requestHeaders.put("X-Naver-Client-Secret", clientSecret);
         String responseBody = crawlerService.getNews(apiURL,requestHeaders);
         
         responseBody.replaceAll("<\\/b>","");
         
         JSONParser parser = new JSONParser();
        
         //json response에 있는 items 객체 가져오기
         try {
        	 JSONObject jObj = (JSONObject)parser.parse(responseBody);
        	 JSONArray jarr = (JSONArray)jObj.get("items");
        	 
        	 //여러 내용중 첫번째만 가져오겠다.
        	 JSONObject obj = (JSONObject)jarr.get(0);
        	 
        	 //해당 기사의 링크정보, 설명, 발행 일자 가져오기
        	 result.put("link",obj.get("originallink"));
        	 result.put("desc",obj.get("description"));
        	 result.put("pubDate",obj.get("pubDate"));
			
         } catch (ParseException e) {
			
         	e.printStackTrace();
         }
        
         return new ResponseEntity< Map<String,Object> >(result,HttpStatus.OK);
    }
    
}
