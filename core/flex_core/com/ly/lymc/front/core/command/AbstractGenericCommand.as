package com.shangkang.front.core.command
{
	import com.shangkang.front.core.components.waitting.WaittingWindowTool;
	import com.shangkang.front.core.service.AbstractGenericService;
	import com.shangkang.front.core.service.DefaultGenericService;
	
	import mx.rpc.AsyncToken;
	
	public class AbstractGenericCommand
	{
		public function AbstractGenericCommand()
		{
		}
	
		protected function addCommand(token:AsyncToken, service:AbstractGenericService = null, showWaittingWindow :Boolean=true):void
		{
			if(showWaittingWindow)
			{
				WaittingWindowTool.showWaittingWindow();
			}
			
			if(service == null)
				service = DefaultGenericService.getInstance();
							
			token.addResponder(service);
		}
	}
}