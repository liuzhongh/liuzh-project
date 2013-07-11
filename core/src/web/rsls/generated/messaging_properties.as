package 
{

import mx.resources.ResourceBundle;

[ExcludeClass]

public class zh_CN$messaging_properties extends ResourceBundle
{

    public function zh_CN$messaging_properties()
    {
		 super("zh_CN", "messaging");
    }

    override protected function getContent():Object
    {
        var content:Object =
        {
            "httpRequestError.details": "错误：{0}",
            "errorReadingIExternalizable": "读取 IExternalizable 时遇到错误。{0}",
            "couldNotLoadCache": "无法将缓存加载到消息存储区中。",
            "cannotRemoveWhenConfigured": "无法从指向已配置的目标的 ChannelSet 中删除通道。",
            "deliveryInDoubt.details": "收到确认前通道已断开",
            "requestTimedOut": "请求超时",
            "securityError": "访问 URL 时遇到安全性错误",
            "invalidURL": "URL 无效",
            "messageQueueFailedInitialize": "消息存储区初始化失败。",
            "noErrorForMessage.details": "期望收到消息“{0}”，但收到的是“{1}”。",
            "couldNotSaveCache": "无法保存缓存。",
            "unknownChannelWithId": "配置中不存在通道“{0}”。",
            "producerConnectError": "制作程序连接错误",
            "noAckMessage.details": "期望收到 mx.messaging.messages.AcknowledgeMessage，但收到的是 {0}",
            "destinationNotSet": "必须设置 MessageAgent 的目标以便发送消息。",
            "requestTimedOut.details": "发送消息的请求超时，没有收到服务器的响应。",
            "couldNotRemoveMessageFromQueue": "在发送前无法从消息存储区中删除消息。",
            "unknownReference": "引用 {0} 未知",
            "noDestinationSpecified": "必须指定目标名称。",
            "wrongMessageQueueForProducerDetails": "消息不是来自与该制作程序相关联的消息存储区。",
            "emptySessionClientId": "会话 clientId 的长度必须为非零值。",
            "unknownTraitReference": "特征引用 {0} 未知",
            "producerSendErrorDetails": "没有连接制作程序，无法发送消息。",
            "connectTimedOut": "连接尝试超时。",
            "httpRequestError": "HTTP 请求错误",
            "noErrorForMessage": "没有收到错误消息",
            "pollingNotSupportedAMF": "StreamingAMFChannel 不支持轮询。 ",
            "pollingNotSupportedHTTP": "StreamingHTTPChannel 不支持轮询。 ",
            "noAvailableChannels": "没用可用的通道。",
            "ackFailed.details": "期望收到消息“{0}”，但收到的是“{1}”。",
            "couldNotLoadCacheIds": "无法加载缓存 ID 列表。",
            "noAMFXBody": "AMFX 包无效。无法找到消息正文",
            "couldNotClearCache": "无法清除缓存。",
            "unknownDestination": "目标“{0}”未知。",
            "invalidRequestMethod": "指定的方法无效。",
            "failedToSubscribe": "使用者无法订阅其目标。",
            "destinationWithInvalidMessageType": "目标“{0}”不可用于类型为“{1}”的消息。",
            "couldNotAddMessageToQueue": "消息存储区无法存储消息，也未连接制作程序。消息存储区发送的 FaultEvent 提供了其它信息。",
            "unsupportedAMFXVersion": "不受支持的 AMFX 版本：{0}",
            "producerSendError": "发送失败",
            "noURLSpecified": "没有为该通道指定 URL。",
            "cannotAddWhenConfigured": "无法向指向已配置的目标的 ChannelSet 添加通道。",
            "unknownChannelClass": "未找到指定的通道类“{0}”。",
            "pollingIntervalNonPositive": "通道 pollingInterval 只能设置为正值。",
            "cannotAddNullIdChannelWhenClustered": "当 ChannelSet 的 clustered 属性为 true 时，无法向其添加 ID 为空的通道。",
            "securityError.details": "目标：{0}",
            "referenceMissingId": "引用必须有 ID。",
            "sendFailed": "发送失败",
            "lsoStorageNotAllowed": "无法初始化消息存储区，因为不允许本地存储区。请确保已为 Flash Player 启用了本地存储区，并配置了足够的存储空间。",
            "ackFailed": "没有收到确认消息",
            "cannotSetClusteredWithdNullChannelIds": "当 ChannelSet 包含 ID 为空的通道时，无法将 ChannelSet 的 clustered 属性更改为 true。 ",
            "cannotConnectToDestination": "无法连接消息目标。",
            "reconnectIntervalNegative": "reconnectInterval 不能为负值。",
            "noURIAllowed": "DirectHTTPChannel 错误。没有可指定的 URI。",
            "messageQueueSendError": "发送失败",
            "noServiceForMessageType": "没有配置任何服务来处理类型为“{0}”的消息。",
            "AMFXTraitsNotFirst": "对象无效。单个特征组必须作为对象中的第一个条目提供。",
            "consumerSubscribeError": "使用者订阅错误",
            "noChannelForDestination": "目标“{0}”不存在，或者目标没有已定义的通道（并且应用程序没有定义任何默认通道）。",
            "emptyDestinationName": "“{0}”不是有效目标。",
            "failedToConnect": "制作程序无法连接其目标。",
            "noAMFXNode": "AMFX 包无效。内容必须以 <amfx> 节点开始",
            "resubscribeIntervalNegative": "resubscribeInterval 不能为负值。",
            "noAckMessage": "没有收到确认消息",
            "pollingRequestNotAllowed": "未启用轮询时，在“{0}”上发出轮询请求。",
            "queuedMessagesNotAllowedDetails": "该制作程序没有指定的消息队列，因此无法发送已排队的消息。",
            "unknownStringReference": "字符串引用 {0} 未知",
            "authenticationNotSupported": "DirectHTTPChannel（无代理）上不支持认证。",
            "deliveryInDoubt": "通道已断开",
            "messageQueueNotInitialized": "消息存储区尚未初始化。",
            "notImplementingIExternalizable": "类 {0} 必须执行 flash.util.IExternalizable。",
            "receivedNull": "收到空值。",
            "unknownDestinationForService": "ID 为“{0}”的服务的目标“{1}”未知。"
        };
        return content;
    }
}



}
