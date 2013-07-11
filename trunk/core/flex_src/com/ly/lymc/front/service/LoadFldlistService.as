package com.shangkang.front.service
{
	import com.shangkang.front.components.ExtAdvancedDataGrid;
	import com.shangkang.front.components.utils.CreateDataGridItem;
	import com.shangkang.front.constants.TableFieldConstant;
	import com.shangkang.front.core.service.AbstractGenericService;
	import com.shangkang.front.tools.UtilHelper;
	
	import mx.collections.ArrayCollection;
	import mx.core.IFlexDisplayObject;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	public class LoadFldlistService extends AbstractGenericService
	{
		public function LoadFldlistService(processObjects:Object=null)
		{
			super(processObjects);
		}
		
		override public function resultHandler(event:ResultEvent):void
		{
			var result:ArrayCollection = event.result as ArrayCollection;
			var headerOrder:Array;
			var dg:ExtAdvancedDataGrid;
			var nextFunc:Function;
			
			if (!UtilHelper.isEmptyAC(result))
			{
				var objArr:Array = this.processObjects as Array;
				
				headerOrder = objArr[TableFieldConstant.VALUE_ZERO];
				dg = objArr[TableFieldConstant.VALUE_ONE];
				nextFunc = objArr[TableFieldConstant.VALUE_TWO];
				
				if (!UtilHelper.isEmptyObject(dg) && dg is ExtAdvancedDataGrid)
				{
					var orderedRes:Array = formatHeader(result.toArray(),headerOrder);
					
					if (!UtilHelper.isEmptyArray(orderedRes))
					{
						new CreateDataGridItem(dg,result).createDataGridColumns();
					}
					
				}
				
				if (!UtilHelper.isEmptyObject(nextFunc) && nextFunc is Function)
				{
					nextFunc();
				}
				
			}
		}
		
		/**
		 * 格式化数据， 按orderList数组顺序格式化
		 * @param header
		 * @param orderList
		 * @return 
		 */		
		private function formatHeader(data:Array,orderList:Array):Array
		{
			if (UtilHelper.isEmptyArray(orderList))
			{
				return data; 
			}
			var result:Array = new Array();
			for each(var item:String in orderList)
			{
				for each(var hItem:Object in data)
				{
					if(item.toLowerCase() == StringUtil.trim(String(hItem[TableFieldConstant.FIELD_NAME]).toLowerCase()))
					{
						result.push(hItem);
						break;						
					}
				}
			}
			return result;
		}
	}
}