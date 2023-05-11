package com.example.java_demo_test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

@RestController
public class RegisterController {

	@Autowired
	private RegisterService service;

	@PostMapping(value = "register")
	public RegisterResponse register(@RequestBody RegisterRequest request) {
		return service.register(request.getAccount(), request.getPwd());
	}

	@PostMapping(value = "active")
	public RegisterResponse active(@RequestBody RegisterRequest request) {
		return service.active(request.getAccount(), request.getPwd());
	}

	@PostMapping(value = "login")
	public RegisterResponse login(@RequestBody RegisterRequest request, HttpSession session) {
		RegisterResponse res = service.login(request.getAccount(), request.getPwd());
		if(res.getMessage().equalsIgnoreCase("Login successful!!")) {
			double random = Math.random()*10000;
			int verifyCode = (int)Math.round(random);
			session.setAttribute("verifyCode", verifyCode);
			session.setAttribute("account", request.getAccount());
			session.setAttribute("pwd", request.getPwd());
			session.setMaxInactiveInterval(60);//³æ¦ì:¬í
			res.setSessionId(session.getId());
			res.setVerifyCode(verifyCode);
		}
		return res;
	}

	@PostMapping(value = "get_reg_time")
	public RegisterResponse getRegTime(@RequestBody RegisterRequest request) {
		return service.getRegTime(request.getAccount(), request.getPwd());
	}
	
	@PostMapping(value = "get_reg_time1")
	public RegisterResponse getRegTime1(@RequestBody RegisterRequest request, HttpSession session) {
		String account = (String)session.getAttribute("account");
		String pwd = (String)session.getAttribute("pwd");
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("Please login!!");
		}
		Integer verifyCode = (Integer)session.getAttribute("verifyCode");
		if(verifyCode == null || verifyCode != request.getVerifyCode()) {
			return new RegisterResponse("Verify code incorrect!!");
		}
		return service.getRegTime(account, pwd);
	}
	
	@PostMapping(value = "get_reg_time2")
	public RegisterResponse getRegTime2(@RequestBody RegisterRequest request, HttpSession session) {
		String account = (String)session.getAttribute("account");
		String pwd = (String)session.getAttribute("pwd");		
		Integer verifyCode = (Integer)session.getAttribute("verifyCode");		
		return service.getRegTime2(request, account, pwd, verifyCode);
	}
}
