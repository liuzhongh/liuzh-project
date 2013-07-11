package com.shangkang.front.service
{
	import com.shangkang.front.bo.Information;
	import com.shangkang.front.core.service.AbstractGenericService;
	import com.shangkang.front.resources.MessageResource;
	import com.shangkang.front.resources.MessageResourceHelper;
	import com.shangkang.front.tools.UtilHelper;
	
	import mx.collections.ArrayCollection;
	import mx.containers.Canvas;
	import mx.controls.Alert;
	import mx.controls.Button;
	import mx.controls.Label;
	import mx.controls.TextInput;
	import mx.rpc.events.ResultEvent;

	public class InforService extends AbstractGenericService
	{
		private static var instance:InforService; 
		
		private var infor:Information;
		
		public static function getInstance(processObjects:Object = null):InforService
		{
			if(!instance) { 
				instance = new InforService(processObjects); 
			} 
			
			return instance; 
		}
		
		public function InforService(processObjects:Object = null)
		{
			super(processObjects);
		}

		override public function resultHandler(event:ResultEvent):void
		{
			super.resultHandler(event);
			var ac:ArrayCollection=event.result as ArrayCollection;

			if (UtilHelper.isEmptyAC(ac))
			{
				Alert.show("数据不存在。");
				return;
			}
			else
			{
//				Alert.show("search = " + ac.getItemAt(0));

				var arr:Array=this.processObjects as Array;

				var lb:Label=arr[0] as Label;
				var cv:Canvas=Canvas(arr[1]);
				var btn:Button = Button(arr[2]);
				var fun:Function = arr[3] as Function;
				
//				fun.call(this, 202, lb, cv);
//				StyleManager.loadStyleDeclarations("rsls/ly-css.swf");
//				
//				btn.getStyle("btn");

				lb.text=MessageResourceHelper.getMessage(MessageResource.MESSAGE_TEST, [ac[0].name]);
				Alert.show(lb.text);
				for (var str:String in ac[0])
				{
					trace(str);
					this.setFiledValue(cv.getChildByName(str), ac[0][str]);
				}

			}
		}

		private function setFiledValue(filed:Object, value:String):void
		{
			if (filed is TextInput)
			{
				var ti:TextInput=filed as TextInput;

				ti.text=value;
			}
		}
	}
}