package com.example.java_demo_test.vo;

public class BookSearchRequest {

	private String name;

	private String isbn;

	private String author;
	
	private boolean isConsumer;// 1= consumer, 0= supplier

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

	public boolean isConsumer() {
		return isConsumer;
	}

	public void setConsumer(boolean isConsumer) {
		this.isConsumer = isConsumer;
	}

}
