package com.alrealor.springboot.web.firstspringbootwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.alrealor.springboot.web"})
public class FirstSpringBootWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringBootWebAppApplication.class, args);
	}

}
