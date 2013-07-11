package com.shangkang.front.resources
{
	import mx.resources.ResourceManager;
	
	public class MessageResourceHelper
	{
		[ResourceBundle("message_resource")] 
		[ResourceBundle("message_resource_sql")] 
		
		/***
		 * 根据key、parameters得到对应的信息提示
		 * */
		public static function getMessage(key:String, parameters:Array=null):String
		{  
			return getMessageByResource("message_resource", key, parameters);
		}
		
		/***
		 * 根据key、parameters得到对应的sql语句
		 * */
		public static function getMessage4Sql(key:String, parameters:Array=null):String
		{  
			return getMessageByResource("message_resource_sql", key, parameters);
		}
		
		private static function getMessageByResource(resourceName:String, key:String, parameters:Array):String
		{
			var message:String = ResourceManager.getInstance().getString(resourceName, key, parameters);
			
			return message;
		}

	}
}