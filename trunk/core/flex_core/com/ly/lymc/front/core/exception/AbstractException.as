package com.shangkang.front.core.exception
{
	public class AbstractException
	{
		private var _errorMessage :String;
		private var _message :String;
		private var _localizedMessage :String;
		private var _cause :Object;
		
		public function get errorMessage():String
		{
			return _errorMessage;
		}

		public function set errorMessage(value:String):void
		{
			_errorMessage = value;
		}

		public function get message():String
		{
			return _message;
		}

		public function set message(value:String):void
		{
			_message = value;
		}

		public function get localizedMessage():String
		{
			return _localizedMessage;
		}

		public function set localizedMessage(value:String):void
		{
			_localizedMessage = value;
		}

		public function get cause():Object
		{
			return _cause;
		}

		public function set cause(value:Object):void
		{
			_cause = value;
		}


	}
}