package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;

public class PersonInfoResponse {
	
	private List<PersonInfo> resPersonInfo;

	private String message;

	public PersonInfoResponse() {
		super();
	}

	public PersonInfoResponse(String message) {
		super();
		this.message = message;
	}

	public PersonInfoResponse(List<PersonInfo> resPersonInfo, String message) {
		super();
		this.resPersonInfo = resPersonInfo;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<PersonInfo> getResPersonInfo() {
		return resPersonInfo;
	}

	public void setResPersonInfo(List<PersonInfo> resPersonInfo) {
		this.resPersonInfo = resPersonInfo;
	}

}
