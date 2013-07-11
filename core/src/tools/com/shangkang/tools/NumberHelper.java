/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: 2011-6-12
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.tools;

import java.math.BigDecimal;

public class NumberHelper {

	public static final BigDecimal BLANK_DECIMAL = new BigDecimal(-99999);
	
	/**
     * 根据给定的scale进行四舍五入
     * 
     * @param   aValue		- The value
     * @param   aScale	 	- The maximum decimal place 
     * 
     * @return  BigDecimal   	- The round value
     * 
     */
	public static BigDecimal round(BigDecimal aValue, int aScale)
	{
		if(aValue == null)
			return null;
		
		aValue = aValue.setScale(aScale, BigDecimal.ROUND_HALF_UP);
		
		return aValue;
	}
	
	public static BigDecimal getBigDecimal(String aInput)
	{
		return getBigDecimal(aInput, BLANK_DECIMAL);
	}

	public static BigDecimal getBigDecimal(String aInput, BigDecimal aDefaultValue)
	{
		try
		{
			return new BigDecimal(aInput);
		}
		catch (Exception e)
		{
			return aDefaultValue;
		}
	}
	

	/**
     * Add(加) the two value
     * 
     * @param   aValue1		- The value 1
     * @param   aValue2	 	- The value 2
     * 
     * @return  BigDecimal   	- The sum of two values
     * 
     */
	public static BigDecimal add(BigDecimal aValue1, BigDecimal aValue2)
	{
		if(aValue1 == null || aValue2 == null)
			return null;
		
		return aValue1.add(aValue2);
	}
	
	/**
     * Add(加) the two value
     * 
     * @param   aValue1		- The value 1
     * @param   aValue2	 	- The value 2
     * 
     * @return  BigDecimal   	- The sum of two values
     * 
     */
	public static BigDecimal add2Abs(BigDecimal aValue1, BigDecimal aValue2)
	{
		if(aValue1 == null || aValue2 == null)
			return null;
		
		return aValue1.add(aValue2).abs();
	}

	/**
     * Minus(减) the two value
     * 
     * @param   aValue1		- The value 1
     * @param   aValue2	 	- The value 2
     * 
     * @return  BigDecimal   	- The different between two values
     * 
     */
	public static BigDecimal subtract(BigDecimal aValue1, BigDecimal aValue2)
	{
		if(aValue1 == null || aValue2 == null)
			return null;
		
		return aValue1.subtract(aValue2);
	}
	
	/**
     * Minus(减) the two value
     * 
     * @param   aValue1		- The value 1
     * @param   aValue2	 	- The value 2
     * 
     * @return  BigDecimal   	- The different between two values
     * 
     */
	public static BigDecimal subtract2Abs(BigDecimal aValue1, BigDecimal aValue2)
	{
		if(aValue1 == null || aValue2 == null)
			return null;
		
		return aValue1.subtract(aValue2).abs();
	}

	/**
     * Multiply(乘) the two value
     * 
     * @param   aValue1		- The value 1
     * @param   aValue2	 	- The value 2
     * 
     * @return  BigDecimal   	- The multiply of two values
     * 
     */
	public static BigDecimal multiply(BigDecimal aValue1, BigDecimal aValue2)
	{
		if(aValue1 == null || aValue2 == null)
			return null;
		
		return aValue1.multiply(aValue2);
	}
	
	/**
     * Multiply(乘) the two value
     * 
     * @param   aValue1		- The value 1
     * @param   aValue2	 	- The value 2
     * @param   sacle	 	- 返回小数位数
     * @param   roundingMode	 	- 四舍五入模式
     * @return  BigDecimal   	- The multiply of two values
     * 
     */
	public static BigDecimal multiply(BigDecimal aValue1, BigDecimal aValue2, int sacle, int roundingMode)
	{
		if(aValue1 == null || aValue2 == null)
			return null;
		
		return aValue1.multiply(aValue2).setScale(sacle, roundingMode);
	}

	/**
     * Divide(除) the two value
     * 
     * @param   aValue1		- The value 1
     * @param   aValue2	 	- The value 2
     * 
     * @return  BigDecimal   	- The division of two values
     * 
     */
	public static BigDecimal divide(BigDecimal aValue1, BigDecimal aValue2, int scale)
	{
		if(aValue1 == null || aValue2 == null)
			return null;
		
		return aValue1.divide(aValue2, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
     * Remainder(%) the two value
     * 
     * @param   aValue1		- The value 1
     * @param   aValue2	 	- The value 2
     * 
     * @return  BigDecimal   	- The remainder of two values
     * 
     */
	public static BigDecimal remainder(BigDecimal aValue1, BigDecimal aValue2)
	{
		if(aValue1 == null || aValue2 == null)
			return null;
		
		return aValue1.remainder(aValue2);
	}
}
