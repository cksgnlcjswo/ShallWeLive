package com.ssafy.home;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WhereIsMyHomeProjectApplicationTests {
	
	@Autowired
	SqlSessionFactory factory;
	
	@Autowired
	DataSource ds;
	
	@Test
	public void beanTest() throws Exception {
		assertNotNull(ds);
		
		Connection con = ds.getConnection();
		assertNotNull(con);
	}
	
	@Test
	public void factoryTest() {
		assertNotNull(factory);
	}
	
}
