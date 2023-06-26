package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.entity.Users;
import com.example.java_demo_test.respository.UsersDao;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class UsersTest {
	
	@Autowired
	private UsersDao usersDao;
	
	@Test
	public void findMaxAutoIncrementId() {		
		for(int i = 0; i < 10; i++) {
			System.out.println(i);
			addUsers();
		}
	}
	
	private void addUsers() {
		Integer maxId = usersDao.selectLastInsertId();
		System.out.println(maxId);
		if (maxId == null) {
			maxId = 0;
		}
		String str = "name_";
		if (maxId < 10) {
			str = str + "0" + maxId;
		} else {
			str = str + maxId;
		}
		usersDao.save(new Users(str));
		System.out.println(str);
	}

}
