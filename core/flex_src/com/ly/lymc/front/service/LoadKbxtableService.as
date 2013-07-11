package com.shangkang.front.service
{
	import com.shangkang.front.components.mxml.ConditionArea;
	import com.shangkang.front.components.mxml.DataDisplayArea;
	import com.shangkang.front.components.utils.CreateInteractiveObject;
	import com.shangkang.front.components.utils.ObjAdvancedTools;
	import com.shangkang.front.constants.TableFieldConstant;
	import com.shangkang.front.core.service.AbstractGenericService;
	import com.shangkang.front.tools.UtilHelper;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Label;
	import mx.rpc.events.ResultEvent;
	
	import spark.components.Group;
	import spark.components.SkinnableContainer;
	
	public class LoadKbxtableService extends AbstractGenericService
	{
		public function LoadKbxtableService(processObjects:Object=null)
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
				
				
				// 分拣数据
				var sortResult:Array = sortingData(results,TableFieldConstant.LX, TableFieldConstant.VALUE_A);
				
				if (!UtilHelper.isEmptyObject(conditionArea) )
				{
					// 创建标题
					createCaption(sortResult[TableFieldConstant.VALUE_ZERO],conditionArea,dataDisplayArea.dispDGData);
					
					// 创建交互组件
					var interactiveObjArr:Array = new CreateInteractiveObject(sortResult[TableFieldConstant.VALUE_ONE],conditionArea,dataDisplayArea.dispDGData).orderMutualObject();
					
					// 动态组件添加效果
					new ObjAdvancedTools(interactiveObjArr, conditionArea, dataDisplayArea).objectAddAttribute();
					
				}
				
				if (!UtilHelper.isEmptyObject(nextFunc) && nextFunc is Function)
				{
					nextFunc(conditionArea,dataDisplayArea);
				}
				
			}
		}
		
		
		/**
		 * 根据param数据中的sortStr字段匹配matchStr将数据集分拣
		 * @param param 	待分拣的数据集
		 * @param sortStr 	数据集的key
		 * @param matchStr 	匹配的值
		 * @return array 	返回数组长度为2
		 * 					array[0]为matchStr匹配的数据，array[1]为其他数据
		 */		
		private function sortingData(param:ArrayCollection, 
									 sortStr:String, 
									 matchStr:String):Array
		{
			if(param.length < 1) return null;
			var tmpArray:Array = new Array();
			var child1Array:Array = new Array();
			var child2Array:Array = new Array();
			var i:int = 0; 
			for ( i; i < param.length; i++ )
			{
				if (param[i][sortStr] == matchStr) 
				{
					child1Array.push(param[i]);
				} else {
					child2Array.push(param[i]);
				}
			}
			tmpArray.push(child1Array);
			tmpArray.push(child2Array);
			return tmpArray;
		}
		
		/**
		 * 创建页面文字标题  
		 * @param param 标题数据
		 * @param upPanel 将upmx数据为 "Y" 生成的对象添加到此对象
		 * @param downPanel 将upmx数据为 "N" 生成的对象添加到此对象
		 */		 	
		private function createCaption(param:Array,
									   upPanel:ConditionArea,
									   downPanel:SkinnableContainer = null):void
		{
			if (param.length < 1) return;
			var i:int = 0;
			var label:Label;
			for ( i; i < param.length; i++ )
			{
				label = new Label();
				label.text = param[i][TableFieldConstant.LABEL_TITLE];
				label.x = param[i][TableFieldConstant.POS_X] ;
				label.y = param[i][TableFieldConstant.POS_Y];
				if ( param[i][TableFieldConstant.UPMX] == TableFieldConstant.VALUE_Y ) {
					upPanel.addElement( label );
				} else {
					downPanel.addElement( label );
				}
			}			
		}
		
	}
}