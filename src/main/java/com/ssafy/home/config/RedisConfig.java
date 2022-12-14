package com.ssafy.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {
	
	@Value("${spring.redis.port}")
    public int port;
 
    @Value("${spring.redis.host}")
    public String host;
 
    @Autowired
    public ObjectMapper objectMapper;
 
    @Bean //요친구가 실제로 Template 역할 Key Serializer, Value Serializer를 통해서 실제 데이터를 변환하는 과정이 필요하다. 
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()); //이친구 덕에 객체가 json형태로 변환되는 것으로 생각된다. 
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    } 
 
    @Bean //이친구는 Connect와 관련된 객체이다. 보면 HostName Port등을 지정하고 Lettuce까지 지정.
    public RedisConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        return connectionFactory;
    }}