package com.example.java_demo_test.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterResponse2 {

	@JsonProperty("reg_time")
	private LocalDateTime regTime;

	private String account;

	private String code;

	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public RegisterResponse2() {
		super();
	}

	public RegisterResponse2(String message) {
		super();
		this.message = message;
	}

	public RegisterResponse2(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public RegisterResponse2(String account, String code, String message) {
		super();
		this.account = account;
		this.code = code;
		this.message = message;
	}

	public RegisterResponse2(LocalDateTime regTime, String message) {
		super();
		this.regTime = regTime;
		this.message = message;
	}

	public RegisterResponse2(LocalDateTime regTime, String code, String message) {
		super();
		this.regTime = regTime;
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getRegTime() {
		return regTime;
	}

	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
