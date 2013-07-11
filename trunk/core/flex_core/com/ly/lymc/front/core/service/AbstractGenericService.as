package com.shangkang.front.core.service
{
	import com.shangkang.front.core.components.waitting.WaittingWindowTool;
	
	import mx.rpc.events.ResultEvent;
	
	public class AbstractGenericService extends BaseGenericService
	{
		protected var processObjects:Object;
		
		public function AbstractGenericService(processObjects:Object=null)
		{
			super();
			this.processObjects = processObjects;
		}
		
		public function setProcessObjects(processObjects:Object):void
		{
			this.processObjects = processObjects;
		}
		
		override public function resultHandler(event:ResultEvent):void{}
		
		protected function hideWaittingWindow():void
		{
			WaittingWindowTool.hideWaittingWindow();
		}

	}
}