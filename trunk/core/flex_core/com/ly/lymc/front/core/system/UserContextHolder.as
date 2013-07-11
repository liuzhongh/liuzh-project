package com.shangkang.front.system
{
	import flash.net.SharedObject;

	public class UserContextHolder
	{
		public static const USER_CONTEXT_SHARE_OBJECT:String = "user_context_share_object";

		private var context :SharedObject; 
		
		private static var singleton :UserContextHolder;
		
		public function UserContextHolder()
		{
			context = SharedObject.getLocal(USER_CONTEXT_SHARE_OBJECT, "/"); 
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
			return context.data.userContext;
		}
		
		public function hasLogin():Boolean
		{
			var uc :UserContext = getUserContext();
			
			if(uc != null && uc.isLogin)
				return true;
			
			return false;
		}
		
		public function setUserContext(uc:UserContext):void
		{
			context.clear();
			context.data.userContext = uc;
			context.flush();
		}
		
		public function clearUserContext():void
		{
			context.clear();
		}
	}
}