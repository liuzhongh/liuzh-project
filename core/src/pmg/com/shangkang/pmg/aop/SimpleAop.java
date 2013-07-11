/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Jan 25, 2011
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.pmg.aop;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class SimpleAop {

	/*@Before("execution(* com.shangkang.core.facade.*.*(..))")
	public void before(JoinPoint jp)
	{
		Object[] obj = jp.getArgs();
		
		obj[0] = "ttt";
		
		System.out.println(obj[0]);
				
		System.out.println(jp.getArgs());
		
	}*/
	
//	匹配多个方法@Around("execution(* com.shangkang.core.mapper.SimpleGenericDao.*(String, Object[])) or (execution(* com.shangkang.core.mapper.SimpleGenericDao.*(String, java.util.Map<String, Object>)))")
	@Around("execution(* com.shangkang.core.mapper.SimpleGenericDao.listAll(String, Object[])) and args(sql, parameters)")
	public Object around(ProceedingJoinPoint pjp, String sql, Object[] parameters) throws Throwable
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append(sql).append(" and module_id = ?");
		
		Object[] oldNames = parameters;
		parameters = new Object[oldNames.length + 1];
		parameters[oldNames.length] = 23;
		System.arraycopy(oldNames, 0, parameters, 0, oldNames.length);
		
//		Object value = pjp.proceed(new Object[]{sb.toString(), parameters});
		Object value = pjp.proceed();
		
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&***************************");
		
		return value;
	}
	
	@Around("execution(* com.shangkang.core.mapper.SimpleGenericDao.listAllByMap(String, java.util.Map<String, Object>)) and args(sql, parameters)")
	public Object around(ProceedingJoinPoint pjp, String sql, Map<String, Object> parameters) throws Throwable
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append(sql).append(" and module_id = :moduleId");
		
		parameters.put("moduleId", 3);
		
		Object value = pjp.proceed(new Object[]{sb.toString(), parameters});
		
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&3333333333333&&&&&&&&&&&&***************************");
		
		return value;
	}
	

	/*@Override
	@Before("execution(* com.shangkang.core.facade.*.*(..))")
	public void before(Method method, Object[] args, Object target)
			throws Throwable
	{
		args[0] = "test";
	}*/
	
}
