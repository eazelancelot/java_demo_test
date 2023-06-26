package com.example.java_demo_test.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExecuteTimeLogAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass()); // slf4j

	@Pointcut("execution (public * com.example.java_demo_test.service.impl.*.*(..))")
	public void pointcut() {

	}
	
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
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("===== around before advice ======");
		// 取得方法名稱
		Signature signature = pjp.getSignature();
		System.out.println("signature_name: " + signature.getName());
		MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
		System.out.println("method_signature_name: " + methodSignature.getName());
		long startTime = System.currentTimeMillis();
		// 呼叫 proceed() 才會開始執行原方法
		Object res = pjp.proceed();
		long endTime = System.currentTimeMillis();
		long spendTime = endTime - startTime;
		System.out.println("spendTime: " + spendTime);
		System.out.println("===== around after advice ======");
		return res;
	}
	
//	@AfterReturning("pointcut()")
	public void afterReturning() {
		System.out.println("===== after returning advice ======");
	}
	
	@AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
	public void afterThrowing(Throwable throwable) {
		// 取得錯誤訊息
		System.out.println("錯誤訊息: " + throwable.getMessage());
		System.out.println("===== after throwing advice =====");
	}

}
