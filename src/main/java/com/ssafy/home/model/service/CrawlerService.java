package com.ssafy.home.model.service;

import java.util.Map;

public interface CrawlerService {

	String getNews(String apiURL, Map<String, String> requestHeaders);
}
