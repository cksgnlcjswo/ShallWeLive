package com.ssafy.home;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ssafy.home.model.repo.UserRepo;

@SpringBootApplication
@MapperScan(basePackageClasses = UserRepo.class)
public class WhereIsMyHomeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereIsMyHomeProjectApplication.class, args);
	}

}
