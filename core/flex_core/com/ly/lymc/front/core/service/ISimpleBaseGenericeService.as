package com.shangkang.front.core.service
{
	import mx.rpc.events.ResultEvent;

	public interface ISimpleBaseGenericeService
	{
		/**
		 * @param event后台返回的结果事件
		 **/
		function resultHandler(event:ResultEvent):void;
			
		/**
		 * 清除所有引用对象
		 **/
		function clearup():void;
	}
}