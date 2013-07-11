package com.shangkang.front.tools
{
	import org.as3commons.lang.StringBuffer;
	
	public class CurrencyHelper
	{
		public static const chineseDigits:Array = ["零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"];
		
		public static const dUnits:Array = ["角", "分", "厘", "毫"];
		
		public static function toChineseDigits(number:Number):String
		{
			if(number == 0)return null;
			
			var result:StringBuffer = new StringBuffer();
			
			var tmp:String = number.toString();
			var array:Array = tmp.split(".");
			var integerStr:String = UtilHelper.isEmptyArray(array) ? tmp : array[0];
			var decimalStr:String =  (array.length > 1) ? array[1] : null;
			
			result.append(toIntegerChineseDigits(integerStr))
				.append(toDecmalChineseDigits(decimalStr));
			
			return result.toString();
		}
		
		public static function toDecmalChineseDigits(decimalStr:String):String
		{
			if(UtilHelper.isEmpty(decimalStr)) return "";
			
			var result:StringBuffer = new StringBuffer();
			var decimals:Array = decimalStr.split("");
			var length:int = decimals.length;
			var isZero:Boolean = false;
			
			for(var j:int = 0; j < length; j++)
			{
				var currentValue:int = decimals[j];
				
				if(currentValue == 0)
				{
					isZero = true;
				}else
				{
					if(isZero)
						result.append(chineseDigits[0]);
						
					result.append(chineseDigits[currentValue]).append(dUnits[j]);
					isZero = false;
				}
			}
			
			return result.toString();
		}
		
		public static function toIntegerChineseDigits(integerStr:String):String
		{
			if(UtilHelper.isEmpty(integerStr) || Number(integerStr) == 0) return "";
			
			var result:StringBuffer = new StringBuffer();
			var integers:Array = UtilHelper.splitString2Array(integerStr, 4);
			
			var length:int = integers.length;
			var chineseUnit1:Array = ["", "拾", "佰", "仟"];
			var chineseUnit2:Array = ["", "万", "亿", "兆", "京"];
			
			for(var i:int = 0; i < length; i++)
			{
				var tmpStr:String = integers[i];
				
				if(Number(tmpStr) == 0) continue;
				
				var val:Array = tmpStr.split("");
				var len:int = val.length;
				var isZero:Boolean = false;
				
				for(var j:int = 0; j < len; j ++)
				{
					var currentV:int = val[j];
					
					if(currentV == 0)
					{
						isZero = true;
					}else
					{
						if(isZero)
							result.append(chineseDigits[0]);
							
						result.append(chineseDigits[currentV]).append(chineseUnit1[len - (j + 1)]);
						isZero = false;
					}
				}
				result.append(chineseUnit2[length - (i + 1)]);
			}
			result.append("圆");
			
			return result.toString();
		}
		
	}
}