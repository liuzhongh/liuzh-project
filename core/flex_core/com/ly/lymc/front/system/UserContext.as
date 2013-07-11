package com.shangkang.front.system
{
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
		private var _empId :Number;
		private var _empName :String;
		private var _orgId :Number;
		private var _orgCode :String;
		private var _orgName :String;
		private var _bizOrgId :Number;
		private var _bizOrgCode :String;
		private var _bizOrgName :String;
		private var _parentBizOrgId :Number;
		private var _parentBizOrgCode :String;
		private var _parentBizOrgName :String;
		private var _resources :ArrayCollection;
		private var _isLogin :Boolean = false;
		private var _appInitPageUrl :String;

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

		public function get orgId():Number
		{
			return _orgId;
		}

		public function set orgId(value:Number):void
		{
			_orgId = value;
		}

		public function get orgName():String
		{
			return _orgName;
		}

		public function set orgName(value:String):void
		{
			_orgName = value;
		}

		public function get bizOrgId():Number
		{
			return _bizOrgId;
		}

		public function set bizOrgId(value:Number):void
		{
			_bizOrgId = value;
		}

		public function get bizOrgName():String
		{
			return _bizOrgName;
		}

		public function set bizOrgName(value:String):void
		{
			_bizOrgName = value;
		}

		public function get empName():String
		{
			return _empName;
		}

		public function set empName(value:String):void
		{
			_empName = value;
		}

		public function get empId():Number
		{
			return _empId;
		}

		public function set empId(value:Number):void
		{
			_empId = value;
		}

		public function get appInitPageUrl():String
		{
			return _appInitPageUrl;
		}

		public function set appInitPageUrl(value:String):void
		{
			_appInitPageUrl = value;
		}

		public function get orgCode():String
		{
			return _orgCode;
		}

		public function set orgCode(value:String):void
		{
			_orgCode = value;
		}

		public function get bizOrgCode():String
		{
			return _bizOrgCode;
		}

		public function set bizOrgCode(value:String):void
		{
			_bizOrgCode = value;
		}

		public function get parentBizOrgId():Number
		{
			return _parentBizOrgId;
		}

		public function set parentBizOrgId(value:Number):void
		{
			_parentBizOrgId = value;
		}

		public function get parentBizOrgCode():String
		{
			return _parentBizOrgCode;
		}

		public function set parentBizOrgCode(value:String):void
		{
			_parentBizOrgCode = value;
		}

		public function get parentBizOrgName():String
		{
			return _parentBizOrgName;
		}

		public function set parentBizOrgName(value:String):void
		{
			_parentBizOrgName = value;
		}


	}
}