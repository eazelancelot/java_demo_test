package com.example.java_demo_test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Register;
import com.example.java_demo_test.respository.RegisterDao;
import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private RegisterDao registerDao;

	@Override
	public RegisterResponse register(String account, String pwd) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("Cannot empty!!");
		}
		if (registerDao.existsById(account)) {
			return new RegisterResponse("Account has existed!!");
		}
		try {
			registerDao.save(new Register(account, pwd));
		} catch (Exception e) {
			return new RegisterResponse("Failed!!");
		}		
		return new RegisterResponse("Successful!!");
	}

	@Override
	public RegisterResponse active(String account, String pwd) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(account)) {
			return new RegisterResponse("Cannot empty!!");
		}
		Register reg = registerDao.findByAccountAndPwd(account, pwd);
		if (reg == null) {
			return new RegisterResponse("Account not found!!");
		}
		reg.setActive(true);
		registerDao.save(reg);
		return new RegisterResponse("Successful!!");
	}

	@Override
	public RegisterResponse login(String account, String pwd) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("Cannot empty!!");
		}
		Register reg = registerDao.findByAccountAndPwd(account, pwd);
		if (reg == null) {
			return new RegisterResponse("Login failed!!");
		}
		if (!reg.isActive()) {
			return new RegisterResponse("Account inactive!!");
		}
		return new RegisterResponse("Login successful!!");
	}

	@Override
	public RegisterResponse getRegTime(String account, String pwd) {
		Register reg = registerDao.findByAccountAndPwdAndActive(account, pwd, true);
		if (reg == null) {
			return new RegisterResponse("Please login!!");
		}
		return new RegisterResponse(reg.getRegTime(), "Successful!!");
	}

	@Override
	public RegisterResponse getRegTime2(RegisterRequest request, String account, String pwd, Integer verifyCode) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("Please login!!");
		}
		if(verifyCode == null || verifyCode != request.getVerifyCode()) {
			return new RegisterResponse("Verify code incorrect!!");
		}
		Register reg = registerDao.findByAccountAndPwdAndActive(account, pwd, true);
		if (reg == null) {
			return new RegisterResponse("Please login!!");
		}
		return new RegisterResponse(reg.getRegTime(), "Successful!!");
	}

}
