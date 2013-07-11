package com.shangkang.front.system
{
	import flash.net.SharedObject;
	
	import mx.collections.ArrayCollection;

	[Bindable]
    [RemoteClass(alias="com.shangkang.pmg.system.context.UserContext")]
	public class UserContext
	{
		private var _userId :Number;
		private var	_userCode :String;
		private var _userName :String;
		private var _appId :Number;
		private var _appName :String;
		private var _resources :ArrayCollection;
		private var _isLogin :Boolean = false;

		public function get userId():Number
		{
			return _userId;
		}

		public function set userId(value:Number):void
		{
			_userId = value;
		}

		public function get isLogin():Boolean
		{
			return this._isLogin;
		}

		public function set isLogin(_isLogin:Boolean):void
		{
			this._isLogin = _isLogin;
		}

		public function set userCode(_userCode:String):void
		{
			this._userCode = _userCode;
		}
		
		public function get userCode():String
		{
			return this._userCode;
		}
		
		public function set userName(_userName:String):void
		{
			this._userName = _userName;
		}
		
		public function get userName():String
		{
			return this._userName;
		}

		public function get appId():Number
		{
			return _appId;
		}

		public function set appId(value:Number):void
		{
			_appId = value;
		}

		public function get resources():ArrayCollection
		{
			return _resources;
		}

		public function set resources(value:ArrayCollection):void
		{
			_resources = value;
		}

		public function get appName():String
		{
			return _appName;
		}

		public function set appName(value:String):void
		{
			_appName = value;
		}

	}
}