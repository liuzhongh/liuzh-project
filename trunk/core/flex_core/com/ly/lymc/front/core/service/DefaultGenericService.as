package com.shangkang.front.core.service
{
	import com.shangkang.front.core.components.waitting.WaittingWindowTool;
	
	import mx.controls.Alert;
	import mx.rpc.events.ResultEvent;

	public class DefaultGenericService extends AbstractGenericService
	{
		private static var instance:DefaultGenericService; 

		public function DefaultGenericService(fun :Function=null)
		{
			this.processObjects = fun;
		}
		
		public static function getInstance():DefaultGenericService
		{
			if(!instance) { 
				instance = new DefaultGenericService(); 
			} 
			
			return instance; 
		}
			
		override public function resultHandler(result:ResultEvent):void
		{
			if(processObjects is Function)
				processObjects.call();
			
			super.hideWaittingWindow();
			
			Alert.show("业务操作成功!", "信息提示:");
		}
		
	}
}