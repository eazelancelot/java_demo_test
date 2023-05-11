package com.example.java_demo_test.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login {
	
	@Id
	@Column(name = "account")
	private String account;
	
	@Column(name = "password")
	private String pwd;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "city")
	private String city;
	
	/*
	 * private Date regTime;
	 *        產生 Date ==> new Date();
	 *        
	 * private LocalDateTime regTime;
	 *        產生 LocalDateTime ==> LocalDateTime.now();
	 */
	@Column(name = "register_time")
	private LocalDateTime regTime;
	
	@Column(name = "active")
	private boolean isActive;
	
	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
