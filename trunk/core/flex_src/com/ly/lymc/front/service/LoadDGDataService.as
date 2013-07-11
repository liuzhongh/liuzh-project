package com.shangkang.front.service
{
	import com.shangkang.front.components.ExtAdvancedDataGrid;
	import com.shangkang.front.components.supports.ISetResObjList;
	import com.shangkang.front.constants.TableFieldConstant;
	import com.shangkang.front.core.service.AbstractGenericService;
	import com.shangkang.front.tools.UtilHelper;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	public class LoadDGDataService extends AbstractGenericService
	{
		public function LoadDGDataService(processObjects:Object=null)
		{
			super(processObjects);
		}
		
		override public function resultHandler(event:ResultEvent):void
		{
			var result:ArrayCollection = event.result as ArrayCollection;
			
			if (!UtilHelper.isEmptyAC(result))
			{
				var dispObjArr:Array = this.processObjects as Array;
				var model:ISetResObjList = dispObjArr[TableFieldConstant.VALUE_ZERO] as ISetResObjList;
				var dg:ExtAdvancedDataGrid = dispObjArr[TableFieldConstant.VALUE_ONE] as ExtAdvancedDataGrid;
				
				if (!UtilHelper.isEmptyObject(model) )
				{
					model.resObjList = result;
					dg.selectedIndex = TableFieldConstant.VALUE_ZERO;
					dg.setFocus();
				}
			}
		}
	}
}