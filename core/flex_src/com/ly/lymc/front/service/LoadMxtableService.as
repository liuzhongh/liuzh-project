package com.shangkang.front.service
{
	import com.shangkang.front.components.ExtAdvancedDataGrid;
	import com.shangkang.front.components.ExtTextInput;
	import com.shangkang.front.components.mxml.ConditionArea;
	import com.shangkang.front.components.mxml.DataDisplayArea;
	import com.shangkang.front.components.utils.CreateDataGridItem;
	import com.shangkang.front.constants.TableFieldConstant;
	import com.shangkang.front.core.service.AbstractGenericService;
	import com.shangkang.front.tools.UtilHelper;
	
	import mx.collections.ArrayCollection;
	import mx.core.IVisualElement;
	import mx.rpc.events.ResultEvent;
	
	public class LoadMxtableService extends AbstractGenericService
	{
		public function LoadMxtableService(processObjects:Object=null)
		{
			super(processObjects);
		}
		
		private var conditionArea:ConditionArea;
		
		override public function resultHandler(event:ResultEvent):void
		{
			var results:ArrayCollection = event.result as ArrayCollection;
			
			var dataDisplayArea:DataDisplayArea;
			var nextFunc:Function;
			
			if (!UtilHelper.isEmptyAC(results))
			{
				var dispObjArr:Array = this.processObjects as Array;
				conditionArea = dispObjArr[TableFieldConstant.VALUE_ZERO];
				dataDisplayArea = dispObjArr[TableFieldConstant.VALUE_ONE];
				nextFunc = dispObjArr[TableFieldConstant.VALUE_TWO];
				
				var returnArr:Array = [];
				if (!UtilHelper.isEmptyObject(dataDisplayArea))
				{
					var dataGrid:ExtAdvancedDataGrid = dataDisplayArea.pagingDG.dgrid;
					
					returnArr = new CreateDataGridItem(dataGrid, results).createDataGridColumns();
				}
				
				var dp:ArrayCollection = defaultDp(returnArr[TableFieldConstant.VALUE_ZERO]);
				dataGrid.dataProvider = dp;
				setDefaultFocus();
			}
		}
		
		/**
		 * 拼接表格默认值 
		 * @param param
		 * @return 
		 */		
		private function defaultDp(param:Object):ArrayCollection
		{
			if (UtilHelper.isEmptyObject(param))
			{
				return new ArrayCollection();
			}
			var rs:ArrayCollection = new ArrayCollection();
			var vDefault:String;
			var fldDec:Number;
			var fldType:String; 
			
			var tmpObj:Object = new Object();
			for (var str:String in param)
			{
				vDefault = param[str][TableFieldConstant.VALUE_ONE];
				if (!UtilHelper.isEmpty(vDefault))
				{
					tmpObj[str] = vDefault;
					continue;
				}
				fldType = param[str][TableFieldConstant.VALUE_ZERO];
				fldDec = param[str][TableFieldConstant.VALUE_TWO];
				if (fldType == TableFieldConstant.REAL_NUM)
				{
					tmpObj[str] = addZeroToNum(fldDec);
				}
				if (fldType == TableFieldConstant.INTEGER_NUM)
				{
					tmpObj[str] = "0";
				}
			}
			rs.addItem(tmpObj);
			return rs;
		}
		
		/**
		 * 尾部加 "0" 
		 * @param param 位数
		 * @return 
		 */		
		private function addZeroToNum(param:Number):String
		{
			var result:String = "0.";
			if (!UtilHelper.isEmpty("" + param))
			{
				for (var i:int=0;i<param;i++)
				{
					result += "0";
				}
			} 
			return result; 
		}
		
		/**
		 * 设置程序焦点初始化位置 
		 */		
		private function setDefaultFocus():void
		{
			if (UtilHelper.isEmptyObject(conditionArea)) 
			{
				return;	
			}
			
			var num:int = conditionArea.numElements;
			
			var objectArr:Array = []
			var obj:IVisualElement;
			var tmpObj:IVisualElement;
			
			for (var i:int = 0; i< num; i++)
			{
				tmpObj = conditionArea.getElementAt(i);
				
				if (tmpObj is ExtTextInput && (tmpObj as ExtTextInput).tabIndex > 0)
				{
					var txtInput:ExtTextInput = tmpObj as ExtTextInput; 
					if (UtilHelper.isEmptyObject(obj))
					{
						obj = txtInput;
						continue;
					}
					if (txtInput.tabIndex < (obj as ExtTextInput).tabIndex)
					{
						obj = txtInput;	
					}
				}
			}
			
			(obj as ExtTextInput).setFocus();
		}
	}
}