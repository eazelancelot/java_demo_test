package com.example.java_demo_test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.respository.NewMenuDao;

@SpringBootTest(classes = JavaDemoTestApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewMenuTest {
	
	@Autowired
	private NewMenuDao newMenuDao;
	
	@Test
	public void addDataTest() {
//		NewMenu nm1 = new NewMenu("fish", "·Î", 100, UUID.randomUUID());
//		NewMenu nm1 = new NewMenu();
//		NewMenu nm2 = new NewMenu("fish", "¯N", 110);
//		newMenuDao.saveAll(Arrays.asList(nm2));
//		newMenuDao.delete(nm1);
		System.out.println("AAAAAAAAAAA");
	}
	
	@Test
	public void addData2Test() {
		System.out.println("BBBBBBBBBB");
	}
	
	@BeforeEach
	public void beforeEach() {
		System.out.println("===== before_each =====");
	}
	
	@AfterEach
	public void afterEach() {
		System.out.println("===== after_each =====");
	}
	
	@AfterAll
	public void afterAll() {
		System.out.println("===== after_all =====");
	}
	
	@BeforeAll
	public void beforeAll() {
		System.out.println("===== before_all =====");
	}

}
