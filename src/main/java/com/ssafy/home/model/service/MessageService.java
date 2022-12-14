package com.ssafy.home.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.home.model.dto.MessageDto;
import com.ssafy.home.model.dto.UserIdDto;

public interface MessageService {

	List<UserIdDto> getUsers(String userId);

	List<MessageDto> getMessageList(Map<String,Object> map);

	int insertMessage(MessageDto messageDto);
	int deleteMessageByUserId(String userId);
}
