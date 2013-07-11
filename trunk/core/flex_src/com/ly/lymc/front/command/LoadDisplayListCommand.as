/**
 * Create [2011-01-13] by Daij
 * Description: 加载远程数据 command 类
 * 				数据用于创建单据模块显示对象  
 */
package com.shangkang.front.command
{
	import com.shangkang.front.components.ExtApplication;
	import com.shangkang.front.components.mxml.ConditionArea;
	import com.shangkang.front.components.mxml.DataDisplayArea;
	import com.shangkang.front.components.mxml.PagingDataGrid;
	import com.shangkang.front.constants.SqlConstant;
	import com.shangkang.front.constants.TableFieldConstant;
	import com.shangkang.front.core.command.AbstractGenericCommand;
	import com.shangkang.front.core.delegate.SimpleGenericDelegate;
	import com.shangkang.front.resources.*;
	import com.shangkang.front.service.LoadDjlxfaService;
	import com.shangkang.front.service.LoadKbxtableService;
	import com.shangkang.front.service.LoadLinetabService;
	import com.shangkang.front.service.LoadMxtableService;
	
	import mx.rpc.AsyncToken;
	
	import spark.components.Group;
	
	public class LoadDisplayListCommand extends AbstractGenericCommand
	{
		private var loadDisplayListDelegate:SimpleGenericDelegate;
		private var paramArr:Array; // SQL 参数列表
		private var token:AsyncToken;
		private var diplayArr:Array;
		
		public function LoadDisplayListCommand()
		{
			loadDisplayListDelegate = new SimpleGenericDelegate();
			
			paramArr = [ExtApplication.services[TableFieldConstant.DJLX]];
		}
		
		/**
		 * 查询 djlxfa 表，设置程序界面整体风格 
		 * @param app 
		 */		
		public function selectDjlxfa(conditionArea:ConditionArea,
									 dataDisplayArea:DataDisplayArea):void
		{
			var sql:String = MessageResourceHelper.getMessage4Sql(MessageResourceSql.MESSAGE_SQL_SELECT_DISPLAYTABLE,[SqlConstant.DJLXFA]);
			// selectKbxtable 为下一个执行函数
			diplayArr = [conditionArea,dataDisplayArea,selectKbxtable];
			
			var djlxfaService:LoadDjlxfaService = new LoadDjlxfaService(diplayArr);			
			token = loadDisplayListDelegate.listAllBySql(sql, paramArr);
			this.addCommand(token, djlxfaService);
		}
		
		/**
		 * 查询 kbxtable 表，动态创建除表格外的其他交互组件 
		 * @param app
		 */		
		public function selectKbxtable(conditionArea:ConditionArea,
									   dataDisplayArea:DataDisplayArea):void
		{
			var sql:String = MessageResourceHelper.getMessage4Sql(MessageResourceSql.MESSAGE_SQL_SELECT_DISPLAYTABLE,[SqlConstant.KBXTABLE]);
			// selectLinetab 为下一个执行函数
			diplayArr = [conditionArea,dataDisplayArea,selectLinetab];
			
			var kbxtableService:LoadKbxtableService = new LoadKbxtableService(diplayArr);
			token = loadDisplayListDelegate.listAllBySql(sql + " order by fieldtaborder", paramArr);
			this.addCommand(token, kbxtableService); 
		}
		
		/**
		 * 查询 linetab 表，动态创建线条 
		 * @param app
		 */		
		public function selectLinetab(conditionArea:ConditionArea,
									  dataDisplayArea:DataDisplayArea):void
		{
			var sql:String = MessageResourceHelper.getMessage4Sql(MessageResourceSql.MESSAGE_SQL_SELECT_DISPLAYTABLE,[SqlConstant.LINETAB]);
			// selectLinetab 为下一个执行函数
			diplayArr = [conditionArea,dataDisplayArea,selectMxtable];
			
			var linetabService:LoadLinetabService = new LoadLinetabService(diplayArr);
			token = loadDisplayListDelegate.listAllBySql(sql, paramArr);
			this.addCommand(token, linetabService);
		}
		
		/**
		 * 查询 mxtable 表，创建表格表头 
		 * @param app
		 */		
		public function selectMxtable(conditionArea:ConditionArea,
									  dataDisplayArea:DataDisplayArea):void
		{
			var sql:String = MessageResourceHelper.getMessage4Sql(MessageResourceSql.MESSAGE_SQL_SELECT_DISPLAYTABLE,[SqlConstant.MXTABLE]);
			diplayArr = [conditionArea,dataDisplayArea];
			
			var mxtableService:LoadMxtableService = new LoadMxtableService(diplayArr);
			token = loadDisplayListDelegate.listAllBySql(sql, paramArr);
			this.addCommand(token, mxtableService);
		}
	}
}