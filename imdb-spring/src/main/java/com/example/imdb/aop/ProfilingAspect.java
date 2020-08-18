package com.example.imdb.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(Integer.MAX_VALUE)
//AuditingAspect::Target is class com.example.imdb.service.business.SequenceServiceImpl
//nextId is called at Tue Aug 18 16:43:44 EET 2020
//parameters are [movies, 256]
//ProfilingAspect::Target is class com.example.imdb.service.business.SequenceServiceImpl
//nextId runs 199200 ns.
//result is 256
public class ProfilingAspect {

//	@Before
//	@AfterThrowing
//	@AfterReturning
//	@After
	@Around("within(com.example.imdb.service.business..*Impl)") // invasive
	public Object audit(ProceedingJoinPoint pjp) throws Throwable {
		var start = System.nanoTime();
		String method = pjp.getSignature().getName();
		var result = pjp.proceed();
		System.err.println("ProfilingAspect::Target is "+pjp.getTarget().getClass());
		var stop = System.nanoTime();
		System.err.println(method + " runs "+(stop-start)+" ns.");
		return result;
	}
}
