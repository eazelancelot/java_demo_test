package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;
import com.example.java_demo_test.vo.RegisterResponse2;

public interface RegisterService {
	
	public RegisterResponse register(String account, String pwd) throws Exception;
	
	public RegisterResponse active(String account, String pwd);
	
	public RegisterResponse login(String account, String pwd);
	
	public RegisterResponse2 login3(String account, String pwd);
	
	public RegisterResponse getRegTime(String account, String pwd);
	
	public RegisterResponse getRegTime2(RegisterRequest request, String account, String pwd, Integer verifyCode);
	
	public RegisterResponse getRegTime3(String account, String pwd);

}
