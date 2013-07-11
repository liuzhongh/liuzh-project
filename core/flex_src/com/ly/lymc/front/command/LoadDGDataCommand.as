package com.shangkang.front.command
{
	import com.shangkang.front.core.command.AbstractGenericCommand;
	import com.shangkang.front.core.delegate.SimpleGenericDelegate;
	import com.shangkang.front.service.LoadDGDataService;
	import com.shangkang.front.service.LoadFldlistService;
	import com.shangkang.front.tools.UtilHelper;
	
	import mx.rpc.AsyncToken;
	
	public class LoadDGDataCommand extends AbstractGenericCommand
	{
		private var loadDGDataDelegate:SimpleGenericDelegate;
		
		public function LoadDGDataCommand()
		{
			super();
			
			loadDGDataDelegate = new SimpleGenericDelegate();
		}
		
		public function selectFldlist(sql:String, param:Object):void
		{
			if (sql != null)
			{
				var loadFldlistService:LoadFldlistService = new LoadFldlistService(param);
				var token:AsyncToken = loadDGDataDelegate.listAllBySql(sql, new Array());
				
				this.addCommand(token, loadFldlistService);
			}
		}
		
		public function selectDGDataProvider(sql:String, param:Object):void
		{
			if (!UtilHelper.isEmpty(sql))
			{
				var loadDGDataService:LoadDGDataService = new LoadDGDataService(param);
				var token:AsyncToken = loadDGDataDelegate.listAllBySql(sql, new Array());
				
				this.addCommand(token, loadDGDataService);
			}
		}
	}
}