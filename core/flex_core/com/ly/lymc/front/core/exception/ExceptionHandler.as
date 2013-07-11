package com.shangkang.front.core.exception
{
	import com.shangkang.front.core.components.waitting.WaittingWindowTool;
	import com.shangkang.front.system.UserContextHolder;
	
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	
	import mx.controls.Alert;
	import mx.events.CloseEvent;
	import mx.rpc.events.FaultEvent;
	
	public class ExceptionHandler
	{
		public function handleException(event:FaultEvent):void
		{
			//隐藏等待进度条
			WaittingWindowTool.hideWaittingWindow();
			
			if(event.fault.rootCause is ServiceException)
			{
				var msg:ServiceException = ServiceException(event.fault.rootCause);
				Alert.show(msg.errorMessage, "错误信息：");
			}
			else if(event.fault.rootCause is DataAccessFailureException)
			{
				var e:DataAccessFailureException = DataAccessFailureException(event.fault.rootCause);
				Alert.show(e.errorMessage, "错误信息：");
			}
			else if(event.fault.rootCause is SecurityException)
			{
				var se:SecurityException = SecurityException(event.fault.rootCause);
				Alert.show(se.errorMessage, "权限错误：");
			}
			else if(event.fault.rootCause is LoginSecurityException)
			{
				var le:LoginSecurityException = LoginSecurityException(event.fault.rootCause);
				Alert.show(le.errorMessage, "权限错误：", Alert.OK, null, loginSecurityColoseHandler);
			}
			else
			{
				Alert.show(event.fault.faultString, "错误信息：");
			}
		}
		
		private function loginSecurityColoseHandler(event :CloseEvent):void
		{
			var url:URLRequest = new URLRequest("index.jsp");

			UserContextHolder.getSingleton().clearUserContext();
			
			flash.net.navigateToURL(url, "_top");
		}
		
	}
}