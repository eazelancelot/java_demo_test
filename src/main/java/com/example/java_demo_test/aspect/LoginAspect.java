package com.example.java_demo_test.aspect;

import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.java_demo_test.vo.AddPersonInfoRequest;
import com.example.java_demo_test.vo.GetPersonInfoRequest;

//@Component
//@Aspect
public class LoginAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass()); // slf4j
	
//	@Before("pointcut()")
	public void before() {
		logger.info("===== before advice ======");
		System.out.println("===== before advice ======");
	}
	
//	@After("pointcut()")
	public void after() {
		logger.info("===== after advice ======");
		System.out.println("===== after advice ======");
	}

//	@Around("pointcut()")
	@Around("execution (public * com.example.java_demo_test.controller.*.*(..))"
			+ "&& args(requestObj, ..)")
	public Object around(ProceedingJoinPoint pjp, Object requestObj) throws Throwable {
		if (requestObj instanceof GetPersonInfoRequest) {
			System.out.println("AAAAAA");
		} else if (requestObj instanceof AddPersonInfoRequest) {
			System.out.println("BBBBBB");
		}
		//取得 requestUrl
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		String requestUrl = Objects.requireNonNull(attributes).getRequest().getRequestURI();
		System.out.println(requestUrl);
		if(requestUrl.equals("/get_person_info_by_id")) {
			Object[] params = pjp.getArgs();
			GetPersonInfoRequest request = (GetPersonInfoRequest)pjp.getArgs()[0];
			
		} else if(requestUrl.equals("/get_person_info_by_age")) {
			System.out.println("++++++++++");
		}
		// 呼叫 proceed() 才會開始執行原方法
		Object res = pjp.proceed();
		return res;
	}
	
//	@AfterReturning("pointcut()")
	public void afterReturning() {
		System.out.println("===== after returning advice ======");
	}
	
//	@AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
	public void afterThrowing(Throwable throwable) {
		// 取得錯誤訊息
		System.out.println("錯誤訊息: " + throwable.getMessage());
		System.out.println("===== after throwing advice =====");
	}

}
