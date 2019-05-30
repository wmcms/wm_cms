package com.wilson.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.wilson.cms.mapper")
public class APP {

	public static void main(String[] args) {
		SpringApplication.run(APP.class, args);
		System.out.println("============================");
		System.out.println("==========启动成功==========");
		System.out.println("============================");
	}

}
