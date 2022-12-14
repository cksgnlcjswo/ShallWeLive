package com.ssafy.home.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.model.dto.MessageDto;
import com.ssafy.home.model.dto.UserIdDto;
import com.ssafy.home.model.service.MessageService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/message")
@Api("message api")
@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
public class MessageController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private MessageService messageService;
	
	//파라미터로 넘어온 사용자와 채팅한 사용자 목록 반환
	@GetMapping("/users")
	public ResponseEntity<?> getUsers(@RequestParam String userId) throws Exception {
        
		List<UserIdDto> result = messageService.getUsers(userId);
        
        return new ResponseEntity<List<UserIdDto> >(result, HttpStatus.OK);
    } 
	
	//상대방과 채팅한 내역
	@GetMapping
	public ResponseEntity<?> getMessageList(@RequestParam String me, @RequestParam String you) throws Exception {
        
		Map<String,Object> map = new HashMap<>();
		map.put("me",me);
		map.put("you",you);
		
		List<MessageDto> result = messageService.getMessageList(map);
        
        return new ResponseEntity<List<MessageDto> >(result, HttpStatus.OK);
    }
	
	//상대방과 채팅한 내역
	@PostMapping
	public ResponseEntity<?> insertMessage(@RequestBody MessageDto messageDto) throws Exception {
		
		int result = messageService.insertMessage(messageDto);
        
		if(result > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
    }
}
