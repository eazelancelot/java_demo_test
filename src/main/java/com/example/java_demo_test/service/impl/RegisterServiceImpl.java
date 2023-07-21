package com.example.java_demo_test.service.impl;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.entity.Register;
import com.example.java_demo_test.respository.RegisterDao;
import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;
import com.example.java_demo_test.vo.RegisterResponse2;

//@EnableScheduling
@Service
public class RegisterServiceImpl implements RegisterService {
	
	private Logger logger = LoggerFactory.getLogger(getClass()); // slf4j
	
	@Autowired
	private RegisterDao registerDao;

//	@Transactional(readOnly = true)
	@Override
	public RegisterResponse register(String account, String pwd) throws Exception {
//		logger.info("register service");
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			logger.error("register error!!");
			throw new Exception(RtnCode.CANNOT_EMPTY.getCode() + RtnCode.CANNOT_EMPTY.getMessage());
//			logger.error("cannot empty!!");
//			return new RegisterResponse(RtnCode.CANNOT_EMPTY.getCode(), RtnCode.CANNOT_EMPTY.getMessage());
		}
		if (registerDao.existsById(account)) {
			return new RegisterResponse(RtnCode.ACCOUNT_EXISTED.getCode(), RtnCode.ACCOUNT_EXISTED.getMessage());
		}
		try {
			registerDao.save(new Register(account, pwd));
		} catch (Exception e) {
			return new RegisterResponse(RtnCode.FAILED.getCode(), RtnCode.FAILED.getMessage());
		}		
		return new RegisterResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public RegisterResponse active(String account, String pwd) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse(RtnCode.CANNOT_EMPTY.getCode(), RtnCode.CANNOT_EMPTY.getMessage());
		}
		Register res = registerDao.findByAccountAndPwd(account, pwd);
		if (res == null) {
			return new RegisterResponse(RtnCode.NOT_FOUND.getCode(), RtnCode.NOT_FOUND.getMessage());
		}
		if (res.isActive()) {
			return new RegisterResponse(RtnCode.ACCOUNT_ACTIVED.getCode(), RtnCode.ACCOUNT_ACTIVED.getMessage());
		}
		res.setActive(true);
		try {
			registerDao.save(res);
		} catch (Exception e) {
			return new RegisterResponse(RtnCode.FAILED.getCode(), RtnCode.FAILED.getMessage());
		}		
		return new RegisterResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	@Cacheable(value = "account", key = "#account")
	@Override
	public RegisterResponse login(String account, String pwd) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse(RtnCode.CANNOT_EMPTY.getCode(), RtnCode.CANNOT_EMPTY.getMessage());
		}
		Register res = registerDao.findByAccountAndPwd(account, pwd);
		if (res == null) {
			return new RegisterResponse(RtnCode.NOT_FOUND.getCode(), RtnCode.NOT_FOUND.getMessage());
		}
		if (!res.isActive()) {
			return new RegisterResponse(RtnCode.ACCOUNT_NOT_ACTIVED.getCode(), RtnCode.ACCOUNT_NOT_ACTIVED.getMessage());
		}
		return new RegisterResponse(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	//@Cacheable(cacheNames = "account", key = "#account")
	@Cacheable(value = "account", key = "#account")
	@Override
	public RegisterResponse getRegTime(String account, String pwd) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse(RtnCode.PLEASE_LOGIN.getCode(), RtnCode.PLEASE_LOGIN.getMessage());
		}
		Register res = registerDao.findByAccountAndPwd(account, pwd);
		if (res == null) {
			return new RegisterResponse(RtnCode.NOT_FOUND.getCode(), RtnCode.NOT_FOUND.getMessage());
		}
		if (!res.isActive()) {
			return new RegisterResponse(RtnCode.ACCOUNT_NOT_ACTIVED.getCode(), RtnCode.ACCOUNT_NOT_ACTIVED.getMessage());
		}
		return new RegisterResponse(res.getRegTime(), RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	@CacheEvict(value = "account", key = "#account")
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
	
	//================================================================
	
	@Cacheable(value = "account", key = "#account")
	@Override
	public RegisterResponse2 login3(String account, String pwd) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse2(RtnCode.CANNOT_EMPTY.getCode(), RtnCode.CANNOT_EMPTY.getMessage());
		}
		Register res = registerDao.findByAccountAndPwd(account, pwd);
		if (res == null) {
			return new RegisterResponse2(RtnCode.NOT_FOUND.getCode(), RtnCode.NOT_FOUND.getMessage());
		}
		if (!res.isActive()) {
			return new RegisterResponse2(RtnCode.ACCOUNT_NOT_ACTIVED.getCode(), RtnCode.ACCOUNT_NOT_ACTIVED.getMessage());
		}
		return new RegisterResponse2(account, RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}
	
	@Cacheable(value = "get_reg_time", key = "#account")
	@Override
	public RegisterResponse getRegTime3(String account, String pwd) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse(RtnCode.PLEASE_LOGIN.getCode(), RtnCode.PLEASE_LOGIN.getMessage());
		}
		Register res = registerDao.findByAccountAndPwd(account, pwd);
		if (res == null) {
			return new RegisterResponse(RtnCode.NOT_FOUND.getCode(), RtnCode.NOT_FOUND.getMessage());
		}
		if (!res.isActive()) {
			return new RegisterResponse(RtnCode.ACCOUNT_NOT_ACTIVED.getCode(), RtnCode.ACCOUNT_NOT_ACTIVED.getMessage());
		}
		return new RegisterResponse(res.getRegTime(), RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}
	
	@Scheduled(cron = "0 * 14-16 * * *")
	public void scheduleTest() {
		System.out.println("now: " + LocalTime.now());
	}

}
