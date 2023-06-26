package com.example.java_demo_test.constants;

public enum RtnCode {
	
	SUCCESSFUL("200", "successful!!"),
	CANNOT_EMPTY("400", "cannot empty!!"),
	NOT_FOUND("404", "not found!!"),
	ACCOUNT_EXISTED("400", "account existed!!"),
	FAILED("400", "failed!!"),
	ACCOUNT_ACTIVED("400", "account actived!!"),
	ACCOUNT_NOT_ACTIVED("400", "account not actived"),
	PLEASE_LOGIN("400", "please login!!");

	private String code;

	private String message;

	private RtnCode(String code, String message) {
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
