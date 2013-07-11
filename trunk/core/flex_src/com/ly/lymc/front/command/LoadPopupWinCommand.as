package com.shangkang.front.command
{
	import com.shangkang.front.constants.SqlConstant;
	import com.shangkang.front.core.command.AbstractGenericCommand;
	import com.shangkang.front.core.delegate.SimpleGenericDelegate;
	import com.shangkang.front.resources.MessageResourceHelper;
	import com.shangkang.front.resources.MessageResourceSql;
	import com.shangkang.front.service.LoadZlselfaService;
	
	import mx.core.IFlexDisplayObject;
	import mx.rpc.AsyncToken;
	
	public class LoadPopupWinCommand extends AbstractGenericCommand
	{
		private var simpleGenericDelegate:SimpleGenericDelegate;
		private var token:AsyncToken;
		
		public function LoadPopupWinCommand()
		{
			super();
			
			simpleGenericDelegate = new SimpleGenericDelegate();
		}
		
		/**
		 * 根据方案编号(fangabh)查询弹出窗口属性数据 
		 * @param fangabh 方案编号
		 * @param obj 处理对象
		 */		
		public function selelctZlselfa(fangabh:String, 
									   obj:Object):void
		{
			var paramArr:Array = [fangabh];
			var sql:String = SqlConstant.SELECT_ZL_SEL_FA;
			
			var loadZlselfaService:LoadZlselfaService = new LoadZlselfaService(obj);
			token = simpleGenericDelegate.listAllBySql(sql,paramArr);
			this.addCommand(token,loadZlselfaService);
			
		}
			
	}
}