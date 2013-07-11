package com.shangkang.front.core.service
{
	import com.shangkang.front.core.exception.ExceptionHandler;
	
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	public class BaseGenericService extends Responder
	{
		public function BaseGenericService()
		{
			super(null, null);
		}
		
		override public function result(data:Object):void
		{
			resultHandler(ResultEvent(data));
		}
		
		override public function fault(info:Object):void
		{
			faultHandler(info);
		}
		
		private function faultHandler(info:Object):void
		{
			var eh:ExceptionHandler = new ExceptionHandler();
			
			eh.handleException(FaultEvent(info));
		}
		
		public function resultHandler(event:ResultEvent):void{};
	}
}