package com.shangkang.front.core.command
{
	import com.shangkang.front.core.components.waitting.WaittingWindowTool;
	import com.shangkang.front.core.service.ISimpleBaseGenericeService;
	import com.shangkang.front.core.service.SimpleBaseGenericService;
	
	import mx.rpc.AsyncToken;

	public class AbstractGenericCommand2
	{
		public function AbstractGenericCommand2()
		{
		}
		
		/**
		 * @param token:AsyncToken
		 * @param service:ISimpleBaseGenericeService接口的具体实现类
		 * @param showSelfMsg:是否显示操作成功提示信息窗口，默认为false，系统将自动显示操作成功信息窗口
		 * @param hideWaittingWindow:是否显示等待提示窗口，默认为false，系统将自动隐藏等待提示窗口
		 * @param showWaittingWindow:是否显示等待提示窗口,默认为true,系统将在调用此方法时自动弹出行等待提示窗口
		 **/
		protected function addCommand(token:AsyncToken, service:ISimpleBaseGenericeService=null, showSelfMsg :Boolean=false, hideWaittingWindow :Boolean=false, showWaittingWindow :Boolean=true):void
		{
			if(showWaittingWindow)
			{
				WaittingWindowTool.showWaittingWindow();
			}
			
			var simpleService :SimpleBaseGenericService = null;
			try
			{
				simpleService = new SimpleBaseGenericService(service, showSelfMsg, hideWaittingWindow);
				
				token.addResponder(simpleService);
			}
			finally
			{
				service = null;
				simpleService = null;
				token = null;
			}
		}
	}
}