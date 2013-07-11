package 
{

import mx.resources.ResourceBundle;

[ExcludeClass]

public class zh_CN$rpc_properties extends ResourceBundle
{

    public function zh_CN$rpc_properties()
    {
		 super("zh_CN", "rpc");
    }

    override protected function getContent():Object
    {
        var content:Object =
        {
            "noBaseSchemaAddress": "没有完全限定的基本地址，无法解析相对架构导入。",
            "unrecognizedPortTypeName": "WSDL 解析程序无法在命名空间“{1}”中找到名为“{0}”的 portType",
            "noListenerForHeader": "表头 {0} 没有事件侦听器",
            "cannotConnectToDestination": "无法建立到“{0}”的连接",
            "unexpectedException": "运行时异常 {0}",
            "noServiceElement.details": "在 WSDL 中的 {0} 上没有找到 <wsdl:service> 元素。",
            "unableToLoadWSDL": "无法加载 WSDL。如果当前联机，请验证 URI 和/或 WSDL 的格式 ({0})",
            "errorWhileLoadingFromParent": "从父位置加载导入的架构时出现错误：{0}",
            "unexpectedSchemaException": "导入架构时出现错误：{0}",
            "unrecognizedBindingName": "WSDL 解析程序无法在命名空间“{1}”中找到名为“{0}”的绑定",
            "cannotResetService": "无法复位操作的服务",
            "mustSpecifyWSDLLocation": "必须指定 WSDL 位置，其中 useProxy 设置为 false。",
            "urlNotSpecified": "必须指定 useProxy 设置为 false 的 URL。",
            "unexpectedInputParameter": "输入自变量中存在意外参数“{0}”。",
            "noBaseWSDLAddress": "没有完全限定的基本地址，无法解析相对 WSDL 导入。",
            "noListenerForEvent": "收到没有定义侦听器的事件。请添加事件侦听器。{0}",
            "unknownSchemaVersion": "架构版本未知",
            "missingInputParameter": "输入自变量数组在位置 {0} 上没有包含必需的参数",
            "unrecognizedNamespace": "WSDL 解析程序没有命名空间“{0}”的注册文档",
            "multiplePortsFound": "未指定有效端口。无法选择默认端口，因为 WSDL 文件中存在多个端口。",
            "wsdlDefinitionsNotFirst": "定义必须是 WSDL 文档中的第一个元素",
            "xmlEncodeReturnNoXMLNode": "xmlEncode 没有返回 XMLNode",
            "xmlDecodeReturnNull": "xmlDecode 返回空值",
            "tooFewInputParameters": "参数过少，应为至少 {0} 个，但只找到 {1} 个",
            "noPortsInWSDL": "WSDL 文件中没有 {0} 服务的有效端口。",
            "invalidResultFormat": "resultFormat“{0}”无效。有效的格式为 [{1}、{2}、{3}、{4}、{5}]",
            "unrecognizedMessageName": "WSDL 解析程序无法在命名空间“{1}”中找到名为“{0}”的消息",
            "operationsNotAllowedInService": "无法向 RPC 服务 ({0}) 指定操作",
            "badSchemaNode": "架构节点错误",
            "noSuchServiceInWSDL": "在 WSDL 文件中没有找到请求的服务“{0}”。",
            "destinationOrWSDLNotSpecified": "必须指定目标和/或 WSDL。",
            "noBaseWSDLAddress.details": "请指定 WebService 的 WSDL 文档的位置。",
            "missingInputParameterWithName": "在输入自变量中未找到必需的参数“{0}”。",
            "badElement": "无法解析元素 {0}:{1}",
            "overloadedOperation": "WSDL 包含过载的操作 ({0})，目前不支持这种用法。",
            "defaultDecoderFailed": "默认解码器无法对结果进行解码",
            "faultyWSDLFormat": "WSDL 格式不正确",
            "soapVersionMismatch": "请求执行版本：{0}，响应执行版本 {1}",
            "badType": "无法解析类型 {0}",
            "noSuchService": "无法找到服务“{0}”",
            "cannotResetOperationName": "无法复位操作的名称",
            "unknownSchemaElement": "未知的元素：{0}",
            "pendingCallExists": "试图在另一个调用处于挂起状态时进行调用。请更改并发性选项或避免多个调用。",
            "noServiceAndPort": "无法找到匹配的端口（服务 =“{0}”，端口 =“{1}”）",
            "noServices": "WSDL 文件中没有有效的服务。",
            "unknownProtocol": "协议“{0}”未知",
            "unknownSchemaType": "架构类型系统未知",
            "invalidSoapResultFormat": "resultFormat“{0}”无效。有效的格式为“object”、“xml”和“e4x”",
            "xmlEncodeReturnNull": "xmlEncode 返回空值",
            "cannotFindType": "无法找到其类型：{0}",
            "noServiceElement": "无法加载 WSDL"
        };
        return content;
    }
}



}
