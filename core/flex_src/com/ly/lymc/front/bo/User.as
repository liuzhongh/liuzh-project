package com.shangkang.front.bo
{
	[Bindable]
	[RemoteClass(alias="com.shangkang.bo.User")]
	public class User
	{
		public var dbid:Number;
		public var dbversion:int;
		public var id:String;
		public var password:String;
		public var givenName:String;
		public var familyName:String;
		public var businessEmail:String;

	}
}