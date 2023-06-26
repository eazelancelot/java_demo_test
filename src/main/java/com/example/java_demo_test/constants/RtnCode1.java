package com.example.java_demo_test.constants;

public enum RtnCode1 {
	
	SUCCESSFUL("200", "Successful!!"),
	CANNOT_EMPTY("400", "Account or password is empty!!"),
	DATA_ERROR("400", "Account or password is error!!"),
	NOT_FOUND("404", "Not found!!");
	
	private String code;
	
	private String message;

	private RtnCode1(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
