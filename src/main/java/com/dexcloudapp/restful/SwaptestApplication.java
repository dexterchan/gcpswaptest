package com.dexcloudapp.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SwaptestApplication {
	
	public static ApplicationContext context=null;
	Logger logger = LoggerFactory.getLogger(SwaptestApplication.class);
	
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("SpringBeanConfig.xml");
		
		SpringApplication.run(SwaptestApplication.class, args);
	}
}
