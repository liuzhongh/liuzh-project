package com.shangkang.front.view.vo
{
	/**
	 * 对应数据库当中的 zl_sel_fa表 
	 * @author Administrator
	 * 
	 */	
	public class ZlselfaVO
	{
		public function ZlselfaVO()
		{
			
		}

		private var _id:String;
		public function set id(param:String):void
		{
			_id = param;	
		}
		public function get id():String
		{
			return _id;
		}		


		private var _fangabh:String;
		public function set fangabh(param:String):void
		{
			_fangabh = param;	
		}
		public function get fangabh():String
		{
			return _fangabh;
		}		
		
		private var _fangamch:String;
		public function set fangamch(param:String):void
		{
			_fangamch = param;	
		}
		public function get fangamch():String
		{
			return _fangamch;
		}
		
		private var _dialog_cap:String;
		public function set dialog_cap(param:String):void
		{
			_dialog_cap = param;	
		}
		public function get dialog_cap():String
		{
			return _dialog_cap;
		}
		
		private var _dialog_hei:String;
		public function set dialog_hei(param:String):void
		{
			_dialog_hei = param;	
		}
		public function get dialog_hei():String
		{
			return _dialog_hei;
		}
		
		private var _dialog_wid:String;
		public function set dialog_wid(param:String):void
		{
			_dialog_wid = param;	
		}
		public function get dialog_wid():String
		{
			return _dialog_wid;
		}
		
		private var _displyflds:String;
		public function set displyflds(param:String):void
		{
			_displyflds = param;	
		}
		public function get displyflds():String
		{
			return _displyflds;
		}
		
		private var _undisplyflds:String;
		public function set undisplyflds(param:String):void
		{
			_undisplyflds = param;	
		}
		public function get undisplyflds():String
		{
			return _undisplyflds;
		}

		private var _zdysqls:String;
		public function set zdysqls(param:String):void
		{
			_zdysqls = param;	
		}
		public function get zdysqls():String
		{
			return _zdysqls;
		}
		private var _lastFangbh:String;
		public function get lastFangbh():String
		{
			return _lastFangbh;
		}
		public function set lastFangbh(param:String):void
		{
			_lastFangbh = param;
		}
		
		private var _is_pop:String;
		public function get is_pop():String
		{
			return _is_pop;
		}
		public function set is_pop(param:String):void
		{
			_is_pop = param;
		}
	}
}