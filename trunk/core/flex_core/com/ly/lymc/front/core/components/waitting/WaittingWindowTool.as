package com.shangkang.front.core.components.waitting
{
	import com.shangkang.front.core.components.helper.PopUpManagerHelper;
	
	import mx.core.IFlexDisplayObject;
	
	public class WaittingWindowTool
	{
		public function WaittingWindowTool()
		{
		}
		
		/** 通信画面 */
		private static var _waittingWindow :IFlexDisplayObject = null;
		
		public static function get waittingWindow():IFlexDisplayObject {
			return _waittingWindow;
		}
		
		/**
		 * 显示通信画面。
		 */
		public static function showWaittingWindow():void {
			if(hasWaittingWindow()) return;
			
			_waittingWindow = PopUpManagerHelper.createPopUp(WaittingWindow, true);

			PopUpManagerHelper.centerPopUp(_waittingWindow);
		}
		
		/**
		 * 关闭通信画面。
		 */
		public static function hideWaittingWindow():void {
			if (_waittingWindow != null) 
			{
				PopUpManagerHelper.removePopUp(_waittingWindow);
				
				_waittingWindow = null;
			}
		}
		/**
		 * 通信画面是否存在。
		 * @return boolean
		 */
		public static function hasWaittingWindow():Boolean {
			if (null != _waittingWindow) 
			{
				return true;
			} else 
			{
				return false;
			}
		}
	}
}