package com.meteor.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class Test_Aop {
	
	
	//@Pointcut("execution (public * com.meteor.aop.Test_Aop.*())")
	@Pointcut("execution(* com.meteor.dao.Jedis_Dao.print_test())")
	public void pointcut(){
		//System.out.println("포인트 컷");
	}
	
	@Pointcut("execution(* com.meteor.dao.Jedis_Dao.print_test())")
	public void pointcut2(){
		System.out.println("포인트 컷");
	} 
	
	
	@Before("pointcut()")
	public void before(){
		System.out.println("before");
	}
	@Before("execution(* com.meteor.dao.Jedis_Dao.print_test())")
	public void after(){
		System.out.println("before");
	}
	
}
