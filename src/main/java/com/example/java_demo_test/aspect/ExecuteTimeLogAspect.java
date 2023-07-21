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
		// �n�૬�� ServletRequestAttributes�F
		// �]�� RequestContextHolder.getRequestAttributes() �����O�O RequestAttributes�F
		// �� RequestAttributes �S�� getRequest() ����k�i�H�I�s
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
		// �I�s proceed() �~�|�}�l������k
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
		// ���o���~�T��
		System.out.println("���~�T��: " + throwable.getMessage());
		System.out.println("===== after throwing advice =====");
	}

}
