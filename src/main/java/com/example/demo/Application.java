package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * srpingboot 入口
 */
@SpringBootApplication
//添加MapperScan注解自动扫描mapper类
@MapperScan(value = "com.example.demo.*.dao")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("*****************************");
		System.out.println("*****************************");
		System.out.println("************启动成功**********");
		System.out.println("*****************************");
		System.out.println("*****************************");
	}

}
