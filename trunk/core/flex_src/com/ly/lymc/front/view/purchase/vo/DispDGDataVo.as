package com.shangkang.front.view.purchase.vo
{
	[Bindable]
	public class DispDGDataVo
	{
		public function DispDGDataVo()
		{
		}
		
		private var _spmch:String;
		
		public function get spmch():String
		{
			return _spmch;
		}
		
		public function set spmch(value:String):void
		{
			this._spmch = value;
		}
		
		private var _dw:String;
		
		public function get dw():String
		{
			return _dw;
		}
		
		public function set dw(value:String):void
		{
			_dw = value;
		}
		
		private var _shpgg:String;
		
		public function get shpgg():String
		{
			return _shpgg;
		}
		
		public function set shpgg(value:String):void
		{
			_shpgg = value;
		}
		
		private var _shl:String;
		
		public function get shl():String
		{
			return _shl;		
		}
		
		public function set shl(value:String):void
		{
			_shl = value;
		}
		
		private var _shpchd:String;
		
		public function get shpchd():String
		{
			return _shpchd;
		}
		
		public function set shpchd(value:String):void
		{
			_shpchd = value;
		}
	}
}