package com.shangkang.front.core.service
{
	import com.shangkang.front.core.components.waitting.WaittingWindowTool;
	
	import mx.controls.Alert;
	import mx.rpc.events.ResultEvent;

	public class SimpleBaseGenericService extends BaseGenericService
	{
		private var service :ISimpleBaseGenericeService;
		private var showSelfMsg :Boolean;
		private var hideWaittingWindow :Boolean;
		
		/**
		 * @param service:ISimpleBaseGenericeService接口的具体实现类
		 * @param showSelfMsg:是否显示操作成功提示信息窗口，默认为false，系统将自动显示操作成功信息窗口
		 * @param hideWaittingWindow:是否显示等待提示窗口，默认为false，系统将自动隐藏等待提示窗口
		 **/
		public function SimpleBaseGenericService(service :ISimpleBaseGenericeService=null, showSelfMsg :Boolean=false, hideWaittingWindow :Boolean=false)
		{
			super();
			
			this.service = service;
			this.showSelfMsg = showSelfMsg;
			this.hideWaittingWindow = hideWaittingWindow;
		}
		
		override public function resultHandler(event:ResultEvent):void
		{
			try
			{
				if(!hideWaittingWindow)
					WaittingWindowTool.hideWaittingWindow();
				
				if(service)
				{
					service.resultHandler(event);
				}
				
				if(!showSelfMsg)
					Alert.show("业务操作成功！");
				
			}finally
			{
				if(service)
				{
					service.clearup();
					service = null;
				}
			}
		}
	}
}