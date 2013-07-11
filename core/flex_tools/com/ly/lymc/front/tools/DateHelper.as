package com.shangkang.front.tools
{
	import mx.controls.DateField;
	import mx.formatters.DateFormatter;

	public class DateHelper
	{
		/**
		 * 获取格式化后的日期 
		 * @param formatStr 格式样式 如："YYYY-MM-DD"
		 * @param dt 日期对象
		 * @return String
		 */		
		public static function getFormattedDate(formatStr:String, dt:Date = null):String
		{
			var tmpDate:Date;
			if (dt != null)
			{
				tmpDate = dt;	
			} else {
				tmpDate = new Date();
			}
			
			var dateFormatter:DateFormatter = new DateFormatter();
			dateFormatter.formatString = formatStr;
			var result:String = dateFormatter.format( tmpDate );
			return result;
		}
		
		/**
		 * 给日期增加年份，如日期为2010-10-1,所加的年份为2,则结果为2012-10-1
		 */
		public static function addYear4Date(date :Date, year :Number) :Date
		{
			if(!date) return null;
			if(isNaN(year)) return date;
			
			date.setFullYear(date.getFullYear() + year);
			
			return date;
		}
		
		/**
		 * 给日期增加月份，如日期为2010-10-1,所加的月份为2,则结果为2010-12-1
		 */
		public static function addMonth4Date(date :Date, month :Number) :Date
		{
			if(!date) return null;
			if(isNaN(month)) return date;
			
			date.setMonth(date.getMonth() + month);
			
			return date;
		}
		
		/**
		 * 给日期增加天数，如日期为2010-10-1,所加的天数为10,则结果为2010-10-11
		 */
		public static function addDays4Date(date :Date, days :Number) :Date
		{
			if(!date) return null;
			if(isNaN(days)) return date;
			
			date.setDate(date.getDate() + days);
			
			return date;
		}
	}
}