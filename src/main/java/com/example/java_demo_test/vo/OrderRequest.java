package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.Menu;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequest {

	@JsonProperty("menu_list")
	private List<Menu> menuList;
	
	private String name;
	
	private String id;
	
	private String city;

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
