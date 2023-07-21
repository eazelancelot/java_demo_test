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
		//���o requestUrl
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		String requestUrl = Objects.requireNonNull(attributes).getRequest().getRequestURI();
		System.out.println(requestUrl);
		if(requestUrl.equals("/get_person_info_by_id")) {
			Object[] params = pjp.getArgs();
			GetPersonInfoRequest request = (GetPersonInfoRequest)pjp.getArgs()[0];
			
		} else if(requestUrl.equals("/get_person_info_by_age")) {
			System.out.println("++++++++++");
		}
		// �I�s proceed() �~�|�}�l������k
		Object res = pjp.proceed();
		return res;
	}
	
//	@AfterReturning("pointcut()")
	public void afterReturning() {
		System.out.println("===== after returning advice ======");
	}
	
//	@AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
	public void afterThrowing(Throwable throwable) {
		// ���o���~�T��
		System.out.println("���~�T��: " + throwable.getMessage());
		System.out.println("===== after throwing advice =====");
	}

}
