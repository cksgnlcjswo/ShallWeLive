package com.ssafy.home.model.repo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.model.dto.MessageDto;

@Mapper
public interface MessageRepo {

	List<MessageDto> getMessageList(Map<String,Object> map);

	int insertMessage(MessageDto messageDto);

	int deleteMessageByUserId(String userId);
}
