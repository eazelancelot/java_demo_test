package com.example.java_demo_test.entity;

import java.io.Serializable;

public class NewMenu2Id implements Serializable{

	private static final long serialVersionUID = 1L;

	private String category;

	private String item;

	public NewMenu2Id() {
		super();
	}

	public NewMenu2Id(String category, String item) {
		super();
		this.category = category;
		this.item = item;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}
