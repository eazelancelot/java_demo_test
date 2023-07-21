package com.example.java_demo_test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;
import com.example.java_demo_test.vo.RegisterResponse2;

@RestController
public class RegisterController {
	
	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private RegisterService service;

	@PostMapping(value = "api/register")
	public RegisterResponse register(@RequestBody RegisterRequest request) throws Exception {
		return service.register(request.getAccount(), request.getPwd());
	}

	@PostMapping(value = "api/active")
	public RegisterResponse active(@RequestBody RegisterRequest request) {
		return service.active(request.getAccount(), request.getPwd());
	}

	@PostMapping(value = "api/login")
	public RegisterResponse login(@RequestBody RegisterRequest request, HttpSession session) {
		
		/*
		 * 設定 Session 存活時間
		 * 1. 預設存活時間: 30 分
		 * 2. 單位: 秒
		 * 3. 0 或負數: 表示 Session 不會過期
		 */
		session.setMaxInactiveInterval(180);
		
		RegisterResponse res = service.login(request.getAccount(), request.getPwd());
		if (res.getCode().equals("200")) {
			session.setAttribute("account", request.getAccount());
			session.setAttribute("pwd", request.getPwd());
//			session.setMaxInactiveInterval(30);// 單位:秒
		}
		return res;
	}
	
	@PostMapping(value = "api/logout")
	public RegisterResponse logout(HttpSession session) {
//		session.removeAttribute("account");
//		session.removeAttribute("pwd");
		
		session.invalidate();
		
		return new RegisterResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	@PostMapping(value = "api/get_reg_time")
	public RegisterResponse getRegTime(HttpSession session) {
		String account = (String)session.getAttribute("account");
		String pwd = (String)session.getAttribute("pwd");
		return service.getRegTime(account, pwd);
	}
	
	@PostMapping(value = "api/get_reg_time1")
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
	
	//==========================================================
	@PostMapping(value = "api/login3")
	public RegisterResponse2 login3(@RequestBody RegisterRequest request) {
//		ValueWrapper value = cacheManager.getCache("account").get(request.getAccount());
//		if (value != null) {
//			return new RegisterResponse2(RtnCode.PLEASE_LOGIN.toString());
//		}
		RegisterResponse2 res = service.login3(request.getAccount(), request.getPwd());
		if (!res.getCode().equals("200")) {
			logout3();
		}
		return res;
	}
	
	@CacheEvict(value = "account", key = "#account")
	@PostMapping(value = "api/logout3")
	public RegisterResponse logout3() {
		System.out.println("========= log out =========");
		return new RegisterResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}
	
//	@Cacheable(value = "get_reg_time", key = "#request.account")
	@PostMapping(value = "api/get_reg_time3")
	public RegisterResponse getRegTime3(@RequestBody RegisterRequest request) {
		//判斷是否有先 login
		ValueWrapper value = cacheManager.getCache("account").get(request.getAccount());
		if (value == null) {
			return new RegisterResponse(RtnCode.PLEASE_LOGIN.toString());
		}
		return service.getRegTime3(request.getAccount(), request.getPwd());
	}
}
