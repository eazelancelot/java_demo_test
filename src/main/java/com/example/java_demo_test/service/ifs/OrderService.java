package com.example.java_demo_test.service.ifs;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

public interface OrderService {
	
	public GetMenuResponse getMenuByName(String name);//name = 餐點名稱
	
	public OrderResponse getAllMenus();
	
	public OrderResponse addMenus(List<Menu> menuList);
	
	public OrderResponse order(Map<String, Integer> orderMap);
	
	/*
	 * 1. 只能修改已存在的菜單價格(價格不得為負數)
	 *    a. 不存在的菜單不能新增
	 * 2. 返回修改後的菜單名稱和新價格
	 */
	public OrderResponse updateMenuPrice(List<Menu> menuList);

}
