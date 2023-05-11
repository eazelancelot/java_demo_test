package com.example.java_demo_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.respository.OrderDao;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class OrderTest {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDao orderDao;

	@Test
	public void addMenusTest() {
		List<Menu> list = new ArrayList<>(Arrays.asList(new Menu("beef", 120), new Menu("fish", 100)));
		OrderResponse response = orderService.addMenus(list);
		List<Menu> responseList = response.getMenuList();
		Assert.isTrue(responseList != null, "·s¼WÀ\ÂI¿ù»~!!");
	}

	@Test
	public void getMenuByName() {
		GetMenuResponse response = orderService.getMenuByName("beef");
		System.out.println(response.getMessage());
	}

	@Test
	public void updateMenuPriceTest() {
		List<Menu> list = new ArrayList<>(
				Arrays.asList(new Menu("beef", 150), new Menu("fish", 130), new Menu("AAA", 100)));
		OrderResponse response = orderService.updateMenuPrice(new ArrayList<>());
		System.out.println(response.getMessage());
	}

	@Test
	public void forEachTest() {
		String str = "beef, prok, fish";
		System.out.println(str.contains("bee"));
		String[] array = { "a", "b", "c" };
		for (String item : array) {
			System.out.println(item);
			item = "d";
			System.out.println(item);
		}
		for (int i = 0, j = 0; i < array.length; i++) {
			array[i] = "d";
		}
		for (String item : array) {
			System.out.println(item);
		}

		List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
		for (String item : list) {
			item = "D";
		}
		for (String item : list) {
			System.out.println(item);
		}

		int num = 10;
		while (num++ <= 10) {
			System.out.println(num);
			num++;
			System.out.println(num);
		}
		System.out.println(num);
	}

	@Test
	public void test() {
		int[] ary1 = { 1, 2, 3, 4 };
		int[] ary2 = new int[2];
		ary2 = ary1;
		for (int item : ary2) {
			System.out.println(item);
		}
	}

}
