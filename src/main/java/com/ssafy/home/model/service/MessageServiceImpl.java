package com.ssafy.home.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.model.dto.MessageDto;
import com.ssafy.home.model.dto.UserIdDto;
import com.ssafy.home.model.repo.MessageRepo;
import com.ssafy.home.model.repo.UserRepo;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	UserRepo uRepo;
	
	@Autowired
	MessageRepo mRepo;
	
	@Override
	public List<UserIdDto> getUsers(String userId) {
		
		return uRepo.getUsers(userId);
	}

	@Override
	public List<MessageDto> getMessageList(Map<String,Object> map) {
		
		return mRepo.getMessageList(map);
	}

	@Override
	public int insertMessage(MessageDto messageDto) {
		return mRepo.insertMessage(messageDto);
	}

	@Override
	public int deleteMessageByUserId(String userId) {
		
		return mRepo.deleteMessageByUserId(userId);
	}

}
