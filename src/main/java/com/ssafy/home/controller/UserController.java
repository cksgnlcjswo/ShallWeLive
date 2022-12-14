package com.ssafy.home.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.home.model.dto.TokenResponse;
import com.ssafy.home.model.dto.User;
import com.ssafy.home.model.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UserController {
	
	private static final String ENDPOINT = "https://accounts.google.com/o/oauth2/v2/auth";
    private static final String CLIENT_ID = "610123170272-c3i1hroiufq8dpjr5u6k492e96duk3me.apps.googleusercontent.com";
    private static final String REDIRECT_URI = "http://localhost:9999/user/login/oauth2/code/google";
    private static final String RESPONSE_TYPE = "code";
    private static final String SCOPE = "https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";
	
	@Autowired
	UserService service;
	
	@GetMapping("/google/login")
    public String login() {
		log.info("/google/login called!!");
        return "redirect:" + ENDPOINT + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=" + RESPONSE_TYPE + "&scope=" + SCOPE;
    }
	
	@GetMapping("/login")
	public String loginView() {
		return "user/login";
	}
	
	@GetMapping("/signup")
	public String signupView() {
		return "user/signup";
	}
	
	@GetMapping("/modify")
	public String modifyView() {
		return "user/modify";
	}
	
}
