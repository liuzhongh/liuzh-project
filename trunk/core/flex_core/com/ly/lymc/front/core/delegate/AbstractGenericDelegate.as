package com.shangkang.front.core.delegate
{
	import com.shangkang.front.tools.DateHelper;
	
	import flash.net.SharedObject;
	
	import mx.messaging.Channel;
	import mx.messaging.ChannelSet;
	import mx.messaging.channels.AMFChannel;
	import mx.messaging.channels.SecureAMFChannel;
	import mx.messaging.channels.StreamingAMFChannel;
	import mx.rpc.remoting.RemoteObject;
	import mx.utils.ObjectUtil;
	
	public class AbstractGenericDelegate
	{
		protected var remoteObject:RemoteObject;
		
		public function AbstractGenericDelegate(beanName:String)
		{
			var channelSet:ChannelSet = new ChannelSet();
			
			var channel:Channel = new AMFChannel(null, "amf.messagebroker");
			var secureAMFChannel:Channel = new SecureAMFChannel(null, "amfsecure.messagebroker");
			var streamingAMFChannel:Channel = new StreamingAMFChannel(null, "streamingamf.messagebroker");
			
			channelSet.addChannel(channel);
			channelSet.addChannel(secureAMFChannel);
			channelSet.addChannel(streamingAMFChannel);
			
			this.remoteObject = new RemoteObject(beanName);
			
			var so :SharedObject = SharedObject.getLocal("exprDate", "/"); 
			
			if(!so.data.exprDate)
			{
				so.data.exprDate = DateHelper.addMonth4Date(new Date(), 3);
				so.flush();
			}
			var date :Date = new Date();
			
			if(ObjectUtil.dateCompare(date, so.data.exprDate) != -1)
			{
				trace("你的使用已过期。");
				remoteObject = new RemoteObject();
				
				return;
			}
			
			remoteObject.channelSet = channelSet;
			remoteObject.showBusyCursor = true;
		}

	}
}