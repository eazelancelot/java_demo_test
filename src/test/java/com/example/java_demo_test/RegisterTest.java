package com.example.java_demo_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.respository.RegisterDao;
import com.example.java_demo_test.service.ifs.RegisterService;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class RegisterTest {
	
	@Autowired
	private RegisterDao registerDao;
	
	@Autowired
	private RegisterService service;

}
