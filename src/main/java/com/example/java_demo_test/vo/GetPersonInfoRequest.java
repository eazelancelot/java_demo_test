package com.example.java_demo_test.vo;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetPersonInfoRequest {

	private String id;

	private int age;
	
	@JsonProperty("search_conditions")
	private Set<String> searchConditions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<String> getSearchConditions() {
		return searchConditions;
	}

	public void setSearchConditions(Set<String> searchConditions) {
		this.searchConditions = searchConditions;
	}

}
