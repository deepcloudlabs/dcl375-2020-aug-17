package com.example.imdb.aop;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class AuditingAspect {

//	@Before
//	@AfterThrowing
//	@AfterReturning
//	@After
	@Around("execution(* *.*(..))") // invasive
	public Object audit(ProceedingJoinPoint pjp) throws Throwable {
		System.err.println("AuditingAspect::Target is "+pjp.getTarget().getClass());
		Date now = new Date();
		String method = pjp.getSignature().getName();
		System.err.println(method + " is called at "+now);
		System.err.println("parameters are "+Arrays.toString(pjp.getArgs()));
		var result = pjp.proceed();
		System.err.println("result is "+result);
		return result;
	}
}
