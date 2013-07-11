package com.shangkang.front.service
{
	import com.shangkang.front.components.supports.IModuleGetProp;
	import com.shangkang.front.constants.TableFieldConstant;
	import com.shangkang.front.core.service.AbstractGenericService;
	import com.shangkang.front.tools.UtilHelper;
	import com.shangkang.front.view.vo.ZlselfaVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	public class LoadZlselfaService extends AbstractGenericService
	{
		private var parentObject:MainApp;
		public function LoadZlselfaService(processObjects:Object=null)
		{
			super(processObjects);
		}
		
		override public function resultHandler(event:ResultEvent):void
		{
			var result:ArrayCollection = event.result as ArrayCollection;
			
			if (!UtilHelper.isEmptyAC(result))
			{
				var dispObjArr:Array = this.processObjects as Array;
				var dgTitleWin:IModuleGetProp = dispObjArr[TableFieldConstant.VALUE_ZERO] as IModuleGetProp;
				var nextFunc:Function = dispObjArr[TableFieldConstant.VALUE_ONE];
				
				if (!UtilHelper.isEmptyObject(dgTitleWin))
				{
					var vo:ZlselfaVO = dgTitleWin.zlselfaVo;
					var sValue:Object = result.getItemAt(0);
					
					vo.dialog_cap = sValue[TableFieldConstant.DIALOG_CAP];
					vo.dialog_wid = sValue[TableFieldConstant.DIALOG_WID];
					vo.dialog_hei = sValue[TableFieldConstant.DIALOG_HEI];
					vo.displyflds = sValue[TableFieldConstant.DISPLAY_FLDS];
					vo.zdysqls = sValue[TableFieldConstant.ZDY_SQL];
					vo.lastFangbh = sValue[TableFieldConstant.MXFANGABH];
					
				}
				
				if (!UtilHelper.isEmptyObject(nextFunc))
				{
					nextFunc();
				}
				
			}
		}
		
	}
}