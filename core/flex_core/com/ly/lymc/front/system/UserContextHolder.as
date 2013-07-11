package com.shangkang.front.system
{
	import flash.net.SharedObject;

	public class UserContextHolder
	{
		public static const USER_CONTEXT_SHARE_OBJECT:String = "user_context_share_object";

		private static var context :UserContext; 
		
		private static var singleton :UserContextHolder;
		
		public function UserContextHolder()
		{
//			context = SharedObject.getLocal(USER_CONTEXT_SHARE_OBJECT); 
		}
		
		public static function getSingleton():UserContextHolder
		{
			if(singleton == null)
			{
				singleton = new UserContextHolder();
			}
			
			return singleton;
		}
		
		public function getUserContext():UserContext
		{
			return context;
		}
		
		public function hasLogin():Boolean
		{
			if(context != null && context.isLogin)
				return true;
			
			return false;
		}
		
		public function setUserContext(uc:UserContext):void
		{
			context = uc;
		}
		
		public function clearUserContext():void
		{
			context = null;
		}
	}
}