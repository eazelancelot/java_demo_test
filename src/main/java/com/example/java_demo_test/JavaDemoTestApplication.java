package com.example.java_demo_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JavaDemoTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaDemoTestApplication.class, args);
	}

}
