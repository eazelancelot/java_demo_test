package com.example.java_demo_test.aspect;

import java.util.Objects;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.java_demo_test.vo.GetPersonInfoRequest;

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
	
	@Before("execution (public * com.example.java_demo_test.controller.*.*(..))"
			+ "&& args(requestObj, ..)")
	public void controllerBefore(Object requestObj) {
		System.out.println(requestObj instanceof GetPersonInfoRequest);
		// 要轉型為 ServletRequestAttributes；
		// 因為 RequestContextHolder.getRequestAttributes() 的型別是 RequestAttributes；
		// 但 RequestAttributes 沒有 getRequest() 此方法可以呼叫
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		Objects.requireNonNull(attributes);
		String requestUrl = Objects.requireNonNull(attributes).getRequest().getRequestURI();
		if (attributes != null) {
			String uri = attributes.getRequest().getRequestURI();
			System.out.println(uri);
		}
		
		
	}
	
//	@After("pointcut()")
	public void after() {
		logger.info("===== after advice ======");
		System.out.println("===== after advice ======");
	}

//	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("===== around before advice ======");
		// 呼叫 proceed() 才會開始執行原方法
		Object res = pjp.proceed();
		System.out.println("===== around after advice ======");
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
