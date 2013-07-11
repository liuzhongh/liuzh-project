/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Dec 21, 2010
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.tools;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class UtilHelper {
	
	/**
	 * 将字符串去空且转成小写，如果为null则返回null
	 * @param value
	 * @return
	 */
	public static String toTrimLowerCase(String value)
	{
		if(value == null)
			return null;
		
		return value.trim().toLowerCase();
	}
	
	/**
	 * 将字符串去空且转成大写，如果为null则返回null
	 * @param value
	 * @return
	 */
	public static String toTrimUpperCase(String value)
	{
		if(value == null)
			return null;
		
		return value.trim().toUpperCase();
	}
	
	/**
	 * 将字符串去空，如果为null则返回null
	 * @param value
	 * @return
	 */
	public static String trim(String value)
	{
		if(value == null)
			return null;
		
		return value.trim();
	}

	/**
	 * 除去字符中除数字字母外的所有字符
	 * @param str
	 * @return
	 */
	public static String eliminateSpecialCharacter(String str)
	{
		if(str == null) return null;
		
		return str.replaceAll("[^A-Za-z0-9]", "");
	}
	
	/**
	 * 判断对象是否为空
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object)
	{
		return (object == null);
	}
	
	/**
	 * 判断数组是否为空
	 * @param objects
	 * @return
	 */
	public static boolean isEmpty(Object[] objects)
	{
		return (objects == null || objects.length == 0);
	}
	
	/**
	 * 判断字符串是否为空
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return (s == null || "".equals(s.trim()));
	}
	
	/**
	 * 判断集合是否为空
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Collection<?> o) {
		return (o == null || o.isEmpty());
	}
	
	/**
	 * 判断Map对象是否为空
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> o) {
		return (o == null || o.isEmpty());
	}
	
	/**
	 * 将一维数组转化为List<String>对象
	 * 
	 * @param strArray
	 * @return List<String>
	 */
	public static List<String> getList(String[] strArray) {		
		
		return Arrays.asList(strArray);
	}
	
	public static String toChineseDigits(Double value)
	{
		if(value == 0) return null;
		
		StringBuffer returnValue = new StringBuffer();
		final String[] chineseDigits = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
		String[] chineseUnits = new String[]{"圆", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", 
				"佰", "仟", "万", "拾", "佰", "仟"};
		
		BigDecimal bd = new BigDecimal(value);
		String tmp = bd.toString();
		 
		String[] valueStrs = tmp.split("\\.");
		//整数部分
		String integerValue = isEmpty(valueStrs) ? tmp : valueStrs[0];
		//小数部分
		String decimalStr = (valueStrs.length > 1) ? valueStrs[1] : null;
		System.out.println("decimalStr = " + decimalStr);
		if(integerValue != null)
		{
			int[] integers = new int[integerValue.length()];
			
			for(int i = 0; i < integerValue.length(); i ++)
			{
				integers[i] = Integer.parseInt(integerValue.substring(i, i + 1));
			}
			
			int length = integers.length; 
			for(int i = 0; i < length; i ++)
			{
				returnValue.append(chineseDigits[integers[i]]).append(chineseUnits[length - 1 - i]);
			}
		}
		
		return returnValue.toString();
	}
	
	public static void main(String[] str)
	{
		System.out.println(toChineseDigits(212143898.89909089898989));
	}
}
