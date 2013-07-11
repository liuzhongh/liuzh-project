package com.shangkang.front.service
{
	import com.shangkang.front.components.constants.CSSStyleConstant;
	import com.shangkang.front.components.mxml.ConditionArea;
	import com.shangkang.front.components.mxml.DataDisplayArea;
	import com.shangkang.front.components.mxml.PagingDataGrid;
	import com.shangkang.front.constants.TableFieldConstant;
	import com.shangkang.front.core.service.AbstractGenericService;
	import com.shangkang.front.tools.UtilHelper;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	import spark.components.Group;
	import spark.components.SkinnableContainer;
	
	public class LoadDjlxfaService extends AbstractGenericService
	{
		public function LoadDjlxfaService(processObjects:Object=null)
		{
			super(processObjects);
		}
		
		override public function resultHandler(event:ResultEvent):void
		{
			var results:ArrayCollection = event.result as ArrayCollection;
			var conditionArea:ConditionArea;
			var dataDisplayArea:DataDisplayArea;
			var nextFunc:Function;
			
			if (!UtilHelper.isEmptyAC(results))
			{
				var displayArr:Array = this.processObjects as Array;
				conditionArea = displayArr[TableFieldConstant.VALUE_ZERO];
				dataDisplayArea = displayArr[TableFieldConstant.VALUE_ONE];
				nextFunc = displayArr[TableFieldConstant.VALUE_TWO];
				
				var obj:Object = results.getItemAt(TableFieldConstant.VALUE_ZERO);
				var dataDisplayHeight:Number = Number(obj[TableFieldConstant.QT_HEIGHT]) - 20;
				conditionArea.height = obj[TableFieldConstant.TD_HEIGHT]; 
				conditionArea.setStyle(CSSStyleConstant.BGCOLOR,UtilHelper.intToHex(obj[TableFieldConstant.UPMXCOLOR]));
				
				if (!UtilHelper.isEmptyObject(dataDisplayArea))
				{
					var topHeight:Number = Number(obj[TableFieldConstant.TD_HEIGHT])+ 36;
					dataDisplayArea.top = topHeight;
					
					var dispDGData:SkinnableContainer = dataDisplayArea.dispDGData;
					
					if (dataDisplayHeight != TableFieldConstant.VALUE_ZERO)
					{
						dispDGData.height = dataDisplayHeight;
						dispDGData.setStyle(CSSStyleConstant.BGCOLOR,UtilHelper.intToHex(obj[TableFieldConstant.DOWNMXCOLOR]));
					} else {
						dispDGData.height = 0;
						dispDGData.visible = false;
					}
					
					var pagingDG:PagingDataGrid = dataDisplayArea.pagingDG;
					if ( obj[TableFieldConstant.MXACTIVE] != TableFieldConstant.VALUE_Y) 
					{
						pagingDG.height = 0;
						pagingDG.visible = false;
					}
				}
			
				if (!UtilHelper.isEmptyObject(nextFunc) && nextFunc is Function )
				{
					nextFunc(conditionArea,dataDisplayArea);
				}
			}
		}
		
	}
}