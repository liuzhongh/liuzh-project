package com.shangkang.front.service
{
	import com.shangkang.front.bo.Information;
	import com.shangkang.front.bo.User;
	import com.shangkang.front.core.service.AbstractGenericService;
	import com.shangkang.front.tools.UtilHelper;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.controls.Label;
	import mx.rpc.events.ResultEvent;
	
	public class BusinessService extends AbstractGenericService
	{
		private var user:User;
		private var infor:Information;
		private static var instance:BusinessService; 
		
		public static function getInstance(processObjects:Object = null):BusinessService
		{
			if(!instance) { 
				instance = new BusinessService(processObjects); 
			} 
			
			return instance; 
		}
		
		public function BusinessService(processObjects:Object = null)
		{
			super(processObjects);	
		}
		
		override public function resultHandler(event:ResultEvent):void
		{
			Alert.show("" + event.result);
			var ac:ArrayCollection = event.result as ArrayCollection;
			
			if(!UtilHelper.isEmptyAC(ac))
			{
				var lb:Label = Label(this.processObjects);
				
				lb.text = ac[0].name;
			}
		}
		
	}
}