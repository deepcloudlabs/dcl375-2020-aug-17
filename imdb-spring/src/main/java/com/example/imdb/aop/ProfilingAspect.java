package com.example.imdb.aop;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
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
	@Around("classProfilerAnnotated() || methodProfilerAnnotated()") // invasive
	public Object audit(ProceedingJoinPoint pjp) throws Throwable {
		var start = System.nanoTime();
		var clazz = pjp.getTarget().getClass();
		TimeUnit tu = TimeUnit.NANOSECONDS;
		if (clazz.isAnnotationPresent(Profiler.class)) {
			tu = clazz.getAnnotation(Profiler.class).unit();
		}
		var signature = (MethodSignature) pjp.getSignature();
		var method = clazz.getDeclaredMethod(signature.getName(), signature.getParameterTypes());
		if (method.isAnnotationPresent(Profiler.class)) {
			tu = method.getAnnotation(Profiler.class).unit();
		}
		var result = pjp.proceed();
		System.err.println("ProfilingAspect::Target is " + pjp.getTarget().getClass());
		var stop = System.nanoTime();
		System.err.println(
				method.getName() + " runs " + tu.convert((stop - start), TimeUnit.NANOSECONDS) + " " + tu.name().toLowerCase());
		return result;
	}

	@Pointcut("@annotation(com.example.imdb.aop.Profiler)")
	public void classProfilerAnnotated() {
	}

	@Pointcut("within(@com.example.imdb.aop.Profiler *)")
	public void methodProfilerAnnotated() {
	}

}
