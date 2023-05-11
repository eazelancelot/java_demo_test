package com.example.java_demo_test.vo;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;

public class OrderResponse {

	private List<Menu> menuList;
	
	private Map<String, Integer> orderMap;
	
	private int totalPrice;

	private String message;

	public OrderResponse() {
		super();
	}

	public OrderResponse(String message) {
		super();
		this.message = message;
	}

	public OrderResponse(List<Menu> menuList, String message) {
		super();
		this.menuList = menuList;
		this.message = message;
	}

	public OrderResponse(Map<String, Integer> orderMap, int totalPrice, String message) {
		super();
		this.orderMap = orderMap;
		this.totalPrice = totalPrice;
		this.message = message;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Map<String, Integer> getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(Map<String, Integer> orderMap) {
		this.orderMap = orderMap;
	}

}
