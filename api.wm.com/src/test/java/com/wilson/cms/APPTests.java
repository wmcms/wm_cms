package com.wilson.cms;

import com.wilson.cms.service.UserService;
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


	}

}
