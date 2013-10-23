/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: zzqiang
 * Created On: 2013-10-16
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.sgip.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class SGIPUtils {

	private static final String	CURRENT_DATE_FILL_SIGN	= "0";

	
	//4byte数组转成long 
    public static long byte4ToLong(byte[] b) { 
        long s = 0; 
        long s0 = b[0] & 0xff;
        long s1 = b[1] & 0xff; 
        long s2 = b[2] & 0xff; 
        long s3 = b[3] & 0xff; 
        s0 <<= 24;
        s1 <<= 16; 
        s2 <<= 8; 
        s = ((long)s0 | s1 | s2 | s3 )&0xFFFFFFFFl;
        return s; 
    } 
	
	
	/**
	 * 转换字符串去指定长度的字节数组 字节数组长度不能小于字符串转换后的长度
	 * @param data
	 * @param charsetName
	 * @param length
	 * @return
	 */
	public static byte[] convertString2Bytes(String data,String charsetName,int length)
	{
		try
		{
			byte[] bytes = data.getBytes(charsetName);
			int copyLength = bytes.length;
			byte[] result = new byte[length];
			copyBytes(bytes, result, 1, copyLength, 1);
			return result;
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return new byte[length];
	}
	
	public static byte[] convertString2Bytes(String data,int length)
	{
		return convertString2Bytes(data, "UTF-8", length);
	}
	
	/**
	 * 获取int类型低位1字节
	 * @param data
	 * @return
	 */
	public static byte convertInt2Byte(int data)
	{
		return (byte)(0xFF & data);
	}
	
	public static byte[] convertInt2Bytes(int data)
	{
		byte[] bytes = new byte[]{convertInt2Byte(data)};
		return bytes;
	}
	
	
	/**
	 * 获取long类型 低位4字节
	 * @param data
	 * @return
	 */
	public static byte[] convertLong2FourBytes(long data)
	{
		byte[] result = new byte[4];
		result[3] = ((byte) (0xFF & data));
		result[2] = ((byte) ((0xFF00 & data) >> 8));
		result[1] = ((byte) ((0xFF0000 & data) >> 16));
		result[0] = ((byte) ((0xFF000000 & data) >> 24));
		return result;
	}

	/**
	 * 得到当前日期 如1017093720 表示 10月17号9点37分20秒
	 * @return 日期的long表示形式
	 */
	public static Long getCurrentDate()
	{
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		String monthStr = month < 10 ? CURRENT_DATE_FILL_SIGN + month : month
				+ "";
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String dayStr = day < 10 ? CURRENT_DATE_FILL_SIGN + day : day + "";
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		String hourStr = hour < 10 ? CURRENT_DATE_FILL_SIGN + hour : hour + "";
		int minute = cal.get(Calendar.MINUTE);
		String minuteStr = minute < 10 ? CURRENT_DATE_FILL_SIGN + minute
				: minute + "";
		int second = cal.get(Calendar.SECOND);
		String secondStr = second < 10 ? CURRENT_DATE_FILL_SIGN + second
				: second + "";
		String cur = monthStr + dayStr + hourStr + minuteStr + secondStr;
		return Long.valueOf(cur);

	}
	
	/**
	 * 复制字节
	 * @param source
	 * @param dest
	 * @param sourceFrom  从1开始
	 * @param copyLength 需要复制的长度
	 * @param destFrom 从1开始
	 */
	public static void copyBytes(byte[] source,byte[] dest,int sourceFrom,int copyLength,int destFrom)
	{
		sourceFrom = sourceFrom -1;
		destFrom =  destFrom - 1;
		for (int i = destFrom; i < destFrom + copyLength; i++)
		{
			dest[i] = source[sourceFrom++];
		}
	}
	
	public static byte[] mergeBytes(byte[]... bytes)
	{
		byte[] temp = new byte[0];
		for (int i = 0; i < bytes.length; i++)
		{
			temp = ArrayUtils.addAll(temp, bytes[i]);
		}
		return temp;
	}
	
	public static byte[] mergeBytes(List<byte[]> list)
	{
		byte[] temp = new byte[0];
		if(null != list)
		{
			for (byte[] bs : list)
			{
				temp = ArrayUtils.addAll(temp, bs);
			}
		}
		return temp;
	}
	

	public static void main(String[] args)
	{
		// System.out.println(Arrays.toString(convertLong2FourBytes(0x1l)));
		//System.out.println(getCurrentDate());
		/*byte[] source = new byte[]{1,2,3,4};
		byte[] dest = new byte[12];
//		copyBytes(source, dest, 1, 4, 9);
//		System.out.println(Arrays.toString(dest));
		
		byte[] source2 = new byte[]{3,4,7,7};
		
		byte[] source3 = new byte[]{7,7,9,9};
		List<byte[]> list = null;
		dest = mergeBytes(list);
		dest = mergeBytes(source,source2,source3);
		System.out.println(Arrays.toString(dest));*/
		
		/*long d = 1102020202l;
		System.out.println(Long.toBinaryString(d));
		byte[] bs = convertLong2FourBytes(d);
		System.out.println(Arrays.toString(bs));
		
		System.out.println(byte4ToLong(bs));
		*/
		//byte b = -4;
		
		//System.out.println(convertUnsignByte2Int(b));
	}


	public static int convertUnsignByte2Int(byte b)
	{
		int i = (b & 0xFF);
		return i;
	}
	
	
	
	
}
