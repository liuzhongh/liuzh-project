/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-3-26
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.batch.support;

import java.lang.reflect.Method;

import com.google.gson.Gson;
import com.shangkang.tools.UtilHelper;

public class ParameterProcess {

	/**
	 * 根据方法名得到类中此方法的参数类型数组
	 * @param object
	 * @param methodName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Class[] getMehtodParameterClass(Object object, String methodName)
	{
		if(object == null || UtilHelper.isEmpty(methodName)) return null;
		
		Class ownerClass = object.getClass();		
		Method[] methods = ownerClass.getMethods();
		
		for(int i = 0; i < methods.length; i ++)
		{
			if(methodName.equalsIgnoreCase(methods[i].getName()))
			{
				Class[] cls = methods[i].getParameterTypes();	
				
				return cls;
			}
		}
		
		return null;
	}
	
	/**
	 * 将字符串按分隔符和类型转换成相对应的对象
	 * @param string
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object[] string2Object(String string, Class[] clazz)
	{
		if(UtilHelper.isEmpty(string) || clazz == null) return null;
		
		Gson gson = new Gson();
		Object[] strArray = gson.fromJson(string, Object[].class);
				
		return stringArray2Object(strArray, clazz);
	}
	
	/**
	 * 将字符串数组按类型转换成相对应的对象（字符串数组与类型数组长度必须一致）
	 * @param stringArray
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object[] stringArray2Object(Object[] stringArray, Class[] clazz)
	{
		if(stringArray == null || clazz == null) return null;
		
		Object[] obj = new Object[clazz.length];
		
		Gson gson = new Gson();
		
		for(int i = 0; i < clazz.length; i ++)
		{
			obj[i] = gson.fromJson(String.valueOf(stringArray[i]), clazz[i]);
		}
		
		return obj;
	}
	
	/**
	 * 将对象数组值转换成Json字符串
	 * @param object
	 * @return
	 */
	public static String object2String(Object[] object)
	{
		if(object == null) return null;
		
		Gson gson = new Gson();
		
		return gson.toJson(object);
	}
}
