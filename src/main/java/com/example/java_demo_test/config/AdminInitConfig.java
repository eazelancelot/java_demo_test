//package com.example.java_demo_test.config;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.example.java_demo_test.entity.Register;
//import com.example.java_demo_test.respository.RegisterDao;
//
//@Component
//public class AdminInitConfig {
//	
//	@Autowired
//	private RegisterDao registerDao;
//	
//	@PostConstruct
//	public void init() {
//		Register register = new Register("Q_Q", "QQ123");
//		registerDao.save(register);
//	}
//	
//	
//
//}
