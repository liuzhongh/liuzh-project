package com.shangkang.front.tools
{
	import flash.utils.ByteArray;
	
	import mx.collections.ArrayCollection;
	import mx.utils.ObjectUtil;
	
	public class UtilHelper
	{
		/***
		 * 判断字符串是否为空
		 * 
		 * */
		public static function isEmpty(value:String):Boolean
		{
			if(!value || value == "")
				return true;
			
			return false;
		}
		
		/***
		 * 判断对象是否为空
		 * */
		public static function isEmptyObject(value:Object):Boolean
		{
			if(!value)
				return true;
			
			return false;
		}
		
		/***
		 * 判断ArrayCollection是否为空
		 * */
		public static function isEmptyAC(ac:ArrayCollection):Boolean
		{
			if(!ac || ac.length <= 0)
				return true;
			
			return false;
		}
		
		/***
		 * 判断数组是否为空
		 * */
		public static function isEmptyArray(array:Array):Boolean
		{
			if(!array || array.length <= 0)
				return true;
				
			return false;
		}
		
		/**
		 * int32颜色值转换成16位颜色值
		 * @param integ 32位颜色值  
		 * @return 16位颜色值，如：0x00ff00
		 */		
		public static function intToHex(intNum:String):String{
			var num:int = parseInt(intNum);
			var str:String = num.toString(16);
			var colorB:String = str.substring(0,2);
			var colorG:String = str.substring(2,4);
			var colorR:String = str.substring(4,str.length);
			var hexColor:String = colorR + colorG + colorB;
			var tempc:int =parseInt(hexColor,16);
			var result:String = tempc.toString(10);
			return result;
		}
		
		/***
		 * 将字符串分隔成指定长度的字符串的数组,以结尾为主。
		 * 如将"1234567"分隔成每段长度为3的字符串数组的结果为["1", "234", "567"].
		 * */
		public static function splitString2Array(value:String, size:int = 0):Array
		{
			if(isEmpty(value)) return null;
			if(size <= 0) return [value];
			
			var result:Array = [];
			var length:int = value.length;
			var tmp:int = Math.ceil(length / size);
			var start:int = 0;
			
			for(var i:int = 0; i < tmp; i ++)
			{
				var last:int = length - size * (tmp - (i + 1));
				
				result.push(value.substring(start, last));
				
				start = last;
			}
			
			return result;
		}
		
		/**
		 * 将对像进行深度拷贝
		 * */
		public static function cloneObject(obj:Object):*
		{
			var byteArray:ByteArray = new ByteArray();
			
			byteArray.writeObject(obj);
			byteArray.position = 0;
			
			return byteArray.readObject();
		}
		
		/**
		 * 将一个对像的属性值拷贝到另一个对象中
		 * destObject：目标对象
		 * sourceObject：源对象
		 * */
		public static function copyProperty(destObject:Object, sourceObject:Object):void
		{  
			if(destObject == null || sourceObject == null) return;  
			
			var clazzInfo:Object = ObjectUtil.getClassInfo(destObject);           
			var props:Array = clazzInfo["properties"];  
			for each(var q:QName in props){  
				try{          
					if(sourceObject.hasOwnProperty(q.localName)){  
						destObject[q.localName] = sourceObject[q.localName];  
					}  
				}catch(err:Error){}  
			}                  
		}  
		
	}
}