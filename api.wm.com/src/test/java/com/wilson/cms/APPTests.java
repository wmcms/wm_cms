package com.wilson.cms;

import  java.sql.Timestamp;
import java.time.LocalDateTime;

import com.wilson.cms.po.TUser;
import com.wilson.cms.service.UserService;
import org.apache.ibatis.type.JdbcType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class APPTests {

	@Autowired
	UserService userService;
	@Test
	public void contextLoads() {

		TUser user = new TUser();
		System.out.println(user);
		System.out.println(Timestamp.valueOf("2019-05-01"));
		TUser tUser = userService.getById(1l);
		//JdbcType.DATETIMEOFFSET
		System.out.println(tUser);
	}

}
