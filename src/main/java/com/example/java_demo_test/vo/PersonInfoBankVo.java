package com.example.java_demo_test.vo;

public class PersonInfoBankVo {

	private String id;

	private String name;

	private int amount;

	public PersonInfoBankVo() {
		super();
	}

	public PersonInfoBankVo(String id, String name, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
