package com.example.java_demo_test.vo;

public class BookSearchBaseResponse {

	private String name;

	private String isbn;

	private String author;

	private int price;

	private String message;

	public BookSearchBaseResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookSearchBaseResponse(String message) {
		super();
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
