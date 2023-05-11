package com.example.java_demo_test.service.ifs;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

public interface OrderService {
	
	public GetMenuResponse getMenuByName(String name);//name = �\�I�W��
	
	public OrderResponse getAllMenus();
	
	public OrderResponse addMenus(List<Menu> menuList);
	
	public OrderResponse order(Map<String, Integer> orderMap);
	
	/*
	 * 1. �u��ק�w�s�b��������(���椣�o���t��)
	 *    a. ���s�b����椣��s�W
	 * 2. ��^�ק�᪺���W�٩M�s����
	 */
	public OrderResponse updateMenuPrice(List<Menu> menuList);

}
