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

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

	public static final String DATE_DIVISION = "-";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
    public static final String TIME_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd
     */
    public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";
    /**
     * yyyyMMdd
     */
    public static final String DATA_PATTERN_YYYYMMDD = "yyyyMMdd";
    /**
     * HH:mm:ss
     */
    public static final String TIME_PATTERN_HHMMSS = "HH:mm:ss";
    
    public static final int  SECOND = 1000;
    public static final int  MINUTE = 60 * SECOND;
    public static final int  HOUR   = 60 * MINUTE;
    public static final long DAY    = 24l * HOUR;
    
    /**
     * Return the current date
     * 
     * @return － DATE<br>
     */
    public static Date now()
    {
    	Calendar cal = Calendar.getInstance();
    	Date currDate = cal.getTime();
    	
    	return currDate;
    }
    
    public static Timestamp nowTimestamp()
    {
    	Calendar cal = Calendar.getInstance();
    	return new Timestamp(cal.getTimeInMillis());
    }
    
    /**
     * Return the current date string
     * 
     * @return － 产生的日期字符串<br>
     */
    public static String nowString()
    {
    	Calendar cal = Calendar.getInstance();
    	Date currDate = cal.getTime();
    	
    	return formatDate(currDate);
    }

    /**
	 * Return the current date in the specified format
	 * 
	 * @param strFormat
	 * @return
	 */
	public static String nowString(String pattern)
	{
    	Calendar cal = Calendar.getInstance();
    	Date currDate = cal.getTime();
    	
    	return format(currDate, pattern);
	}
	
	 /**
     * Parse a string and return a date value
     * 
     * @param dateValue
     * @return
     * @throws Exception
     */
    public static Date parseDate(String dateValue)
    {
    	return parse(dateValue,DATE_PATTERN_DEFAULT);
    }
    
    /**
     * Parse a strign and return a datetime value
     * 
     * @param dateValue
     * @return
     */
    public static Date parseTime(String dateValue)
    {
    	return parse(dateValue,TIME_PATTERN_DEFAULT);
    }
	
	/**
	 * Parse a string and return the date value in the specified format
	 * 
	 * @param strFormat
	 * @param dateValue
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 */
	public static Date parse(String dateValue, String pattern)
	{
        if (UtilHelper.isEmpty(dateValue) || UtilHelper.isEmpty(pattern))
            return null;
        
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        
		try	{
			return dateFormat.parse(dateValue);
		} catch (ParseException pe)	{
			return null;
		}
	}

	
    /**
     * 将Timestamp类型的日期转换为系统参数定义的格式的字符串。
     * @param aTs_Datetime 需要转换的日期。
     * @return 转换后符合给定格式的日期字符串
     */
    public static String formatDate(Date d)
    {
      return format(d, DATE_PATTERN_DEFAULT);
    }
    
    /**
     * 将Timestamp类型的日期转换为系统参数定义的格式的字符串。
     * @param aTs_Datetime 需要转换的日期。
     * @return 转换后符合给定格式的日期字符串
     */
    public static String formatTime(Date t)
    {
      return format(t, TIME_PATTERN_DEFAULT);
    }

    /**
     * 将Date类型的日期转换为系统参数定义的格式的字符串。
     * @param aTs_Datetime
     * @param as_Pattern
     * @return
     */
    public static String format(Date d, String pattern)
    {
      if (d == null)
        return null;

      SimpleDateFormat dateFromat = new SimpleDateFormat(pattern);
      return dateFromat.format(d);
    }
    
    /**
	 * 根据当前日期及月份间隔获得新日期 n为负值所得日期为当前日期的前n月日期 n为正值所得日期为当前日期的后n月日期
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date dateAddMonth(Date date, int month) {
		if (date == null)
			return now();
		
		Calendar c = Calendar.getInstance();

		c.setTime(date);
		c.add(Calendar.MONTH, month);

		return c.getTime();
	}
	
	/**
	 * 根据当前日期及年份间隔获得新日期 n为负值所得日期为当前日期的前n年日期 n为正值所得日期为当前日期的后n年日期
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date dateAddYear(Date date, int year) {
		if (date == null)
			return now();
		
		Calendar c = Calendar.getInstance();

		c.setTime(date);
		c.add(Calendar.YEAR, year);

		return c.getTime();
	}
	
	/**
	 * 根据当前日期及天数间隔获得新日期 n为负值所得日期为当前日期的前n天日期 n为正值所得日期为当前日期的后n天日期
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date dateAddDay(Date date, int day) {
		if (date == null)
			return now();
		
		Calendar c = Calendar.getInstance();

		c.setTime(date);
		c.add(Calendar.DATE, day);
		
		return c.getTime();
	}
	
	/**
	 * 根据当前日期及小时间隔获得新日期 <br/>
	 * n为负值所得日期为当前日期的前n小时日期<br/> 
	 * n为正值所得日期为当前日期的后n小时日期<br/>
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date dateAddHour(Date date, int hour) {
		if (date == null)
			return now();
		
		Calendar c = Calendar.getInstance();

		c.setTime(date);
		c.add(Calendar.HOUR, hour);
		
		return c.getTime();
	}
	
	/**
	 * 将日期转换成毫秒数
	 * @param date
	 * @return
	 */
	public static long getMillis(Date date) 
	{
		if(date == null)
			return 0;
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		
		return calendar.getTimeInMillis();
	}
	
	/**
	 * 得到两个日期之间相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differenceOnDays(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		long l1 = getMillis(date1);
		long l2 = getMillis(date2);

		return Integer.parseInt(Long.toString((l2 - l1) / DAY));
	}
	
	
	/**
	 * 得到两个日期之间相差的月份数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differenceOnMonth(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		int difYear = 0;
		int difMonth = 0;

		int year1 = getDateYear(date1);
		int year2 = getDateYear(date2);
		int month1 = getMonthOnDate(date1);
		int month2 = getMonthOnDate(date2);

		difYear = (year2 - year1) * 12;
		difMonth = month2 - month1;

		return Math.abs(difYear + difMonth);
	}
	
	/**
	 * 获得所在日期的月份
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthOnDate(Date date) {
		if (date == null)
			return 0;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.MONTH);
	}
	
	/**
	 * 得到两个日期之间相差的年份数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differenceOnYear(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return 0;
		}
		int year1 = getDateYear(date1);
		int year2 = getDateYear(date2);

		return Math.abs(year1 - year2);
	}
	
	/**
	 * 获得所在日期的年份
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDateYear(Date date) {
		if (date == null)
			return 0;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	
	/**
	 * 获得所在月份的日
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		if (date == null)
			return 0;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DATE);
	}
	
	/**
	 * 对两个日期进行比较，如日期1小于日期2(或两个日期中有为null值的日期)则返回-1，相等则返回0，大于则返回1
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(Date date1, Date date2)
	{
		if(date1 == null || date2 == null) return -1;
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal1.setTime(date1);		
		cal2.setTime(date2);
		
		return cal1.compareTo(cal2);
	}
	
	
}
