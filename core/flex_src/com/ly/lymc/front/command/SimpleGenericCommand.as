package com.shangkang.front.command
{
	import com.shangkang.front.bo.Information;
	import com.shangkang.front.core.command.AbstractGenericCommand;
	import com.shangkang.front.core.delegate.SimpleGenericDelegate;
	import com.shangkang.front.resources.MessageResourceHelper;
	import com.shangkang.front.resources.MessageResourceSql;
	import com.shangkang.front.service.BusinessService;
	import com.shangkang.front.service.InforService;
	import com.shangkang.front.tools.CurrencyHelper;
	
	import mx.containers.Canvas;
	import mx.controls.Alert;
	import mx.controls.Button;
	import mx.controls.Label;
	import mx.rpc.AsyncToken;
	
	import org.as3commons.lang.StringUtils;
	
	public class SimpleGenericCommand extends AbstractGenericCommand
	{
		private static var instance:SimpleGenericCommand; 
		
		public static function getInstance():SimpleGenericCommand
		{
			if(!instance) { 
				instance = new SimpleGenericCommand(); 
			} 
			
			return instance; 
		}
		
		public function insertInfo(infor:Information):void
		{
			var object:Object = new Object();
			
			if(StringUtils.isEmpty(infor.name))  infor.name = null;
			
			var token:AsyncToken = SimpleGenericDelegate.getInstance().insertRecord("information", infor);
			
			this.addCommand(token);
		}
		
		public function getUserById(id:Number, lb:Label, cv:Canvas):void
		{
			var sql:String = MessageResourceHelper.getMessage4Sql(MessageResourceSql.MESSAGE_SQL_SELECT_INFORMATION);
			var array:Array = new Array();
			trace(sql);
			array.push(id);
			
			var token:AsyncToken = SimpleGenericDelegate.getInstance().listAllBySql(sql, array);
			
			trace("result == " + token.result);
			
			var bs:BusinessService = BusinessService.getInstance(lb);
//			bs.setProcessObjects(lb);
			
			this.addCommand(token, bs);
		}
		
		public function getInforById(id:Number, lb:Label, cv:Canvas, btn:Button):void
		{
			var sql:String = MessageResourceHelper.getMessage4Sql(MessageResourceSql.MESSAGE_SQL_SELECT_INFORMATION);
			var array:Array = new Array();
			trace(sql);
			array.push(id);
			
			trace(CurrencyHelper.toChineseDigits(id));
			var obj:Array = new Array();
			
			obj.push(lb);
			obj.push(cv);
			obj.push(btn);
			obj.push(getUserById);
			var token:AsyncToken = SimpleGenericDelegate.getInstance().listAllBySql(sql, array);
			
			var ifs:InforService = InforService.getInstance(obj);
//			ifs.setProcessObjects(obj);
			
			this.addCommand(token, ifs);
		}
		
		public function funtest(result:Object):void
		{
			var o:Object = result;
			Alert.show("it is ");
		}
		
	}
}