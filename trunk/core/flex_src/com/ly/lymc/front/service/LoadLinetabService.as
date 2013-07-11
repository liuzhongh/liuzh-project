package com.shangkang.front.service
{
	import com.shangkang.front.components.constants.CSSStyleConstant;
	import com.shangkang.front.components.mxml.ConditionArea;
	import com.shangkang.front.components.mxml.DataDisplayArea;
	import com.shangkang.front.components.utils.CreateInteractiveObject;
	import com.shangkang.front.constants.TableFieldConstant;
	import com.shangkang.front.core.service.AbstractGenericService;
	import com.shangkang.front.tools.UtilHelper;
	
	import mx.collections.ArrayCollection;
	import mx.containers.Panel;
	import mx.controls.HRule;
	import mx.rpc.events.ResultEvent;
	
	import spark.components.SkinnableContainer;
	
	public class LoadLinetabService extends AbstractGenericService
	{
		public function LoadLinetabService(processObjects:Object=null)
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
				var displayObjArr:Array = this.processObjects as Array;
				conditionArea = displayObjArr[TableFieldConstant.VALUE_ZERO];
				dataDisplayArea = displayObjArr[TableFieldConstant.VALUE_ONE];
				nextFunc = displayObjArr[TableFieldConstant.VALUE_TWO];
				
				createHorizontalLine(results.toArray(),conditionArea, dataDisplayArea.dispDGData);
				
				if (!UtilHelper.isEmptyObject(nextFunc) && nextFunc is Function)
				{
					nextFunc(conditionArea,dataDisplayArea);
				}
			}
		}
		
		/**
		 * 创建单独的一条横线条 
		 * @param param
		 */		
		private function createHorizontalLine(objectData:Array,
											  upContainer:ConditionArea, 
											  downContainer:SkinnableContainer):void
		{
			for (var i:int = 0; i < objectData.length; i++)
			{
				var hLine:HRule = new HRule();
				hLine.x = objectData[i][TableFieldConstant.LINE_POS_X];
				hLine.y = objectData[i][TableFieldConstant.LINE_POS_Y];
				hLine.width = objectData[i][TableFieldConstant.LINE_WIDTH];
				
				if (objectData[i][TableFieldConstant.IS_ACTIVE] == TableFieldConstant.VALUE_N)
				{
					hLine.visible = false;
				}
				
				hLine.setStyle(CSSStyleConstant.STROKECOLOR,TableFieldConstant.COLOR_BLACK);
				if ( objectData[i][TableFieldConstant.UPMX] == TableFieldConstant.VALUE_Y ) 
				{
					upContainer.addElement( hLine );
				} else {
					downContainer.addElement(hLine);
				}
				
			}
		}
	}
}