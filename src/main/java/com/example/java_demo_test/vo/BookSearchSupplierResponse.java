package com.example.java_demo_test.vo;

public class BookSearchSupplierResponse extends BookSearchBaseResponse {

	private int inventory;

	private int sales;

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}
}
