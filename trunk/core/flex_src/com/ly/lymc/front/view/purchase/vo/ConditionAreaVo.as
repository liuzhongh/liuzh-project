package com.shangkang.front.view.purchase.vo
{
	[Bindable]
	public class ConditionAreaVo
	{
		public function ConditionAreaVo()
		{
			
		}
		
		private var _bm:String;
		
		public function set bm(value:String):void
		{
			this._bm = value;
		}
		
		public function get bm():String
		{
			return this._bm;
		}
		
		private var _ywy:String;
		
		public function set ywy(value:String):void
		{
			this._ywy = value;
		}
		
		public function get ywy():String
		{
			return _ywy;
		}
	}
}