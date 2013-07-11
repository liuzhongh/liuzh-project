package 
{

import mx.resources.ResourceBundle;

[ExcludeClass]

public class zh_CN$core_properties extends ResourceBundle
{

    public function zh_CN$core_properties()
    {
		 super("zh_CN", "core");
    }

    override protected function getContent():Object
    {
        var content:Object =
        {
            "multipleChildSets_ClassAndInstance": "已经为该组件（组件定义和组件实例）指定了多个视觉子项组。",
            "truncationIndicator": "...",
            "notExecuting": "重复器未处于执行状态。",
            "nullParameter": "参数 {0} 必须为非 null 值。",
            "versionAlreadyRead": "已读取了兼容性版本。",
            "multipleChildSets_ClassAndSubclass": "已经为该组件（基本组件定义和派生组件定义）指定了多个视觉子项组。",
            "fontIncompatible": "警告: 为 {1} 指定的嵌入字体“{0}”不兼容。此组件要求使用 embedAsCff={2} 声明嵌入字体。",
            "badParameter": "参数 {0} 必须为可接受的值之一。",
            "notImplementedInFTETextField": "“{0}”不是在 FTETextField 中实现的。",
            "viewSource": "查看源代码",
            "unsupportedTypeInFTETextField": "FTETextField 不支持将类型设置为“input”。",
            "badFile": "文件不存在。",
            "stateUndefined": "状态“{0}”未定义。",
            "badIndex": "提供的索引超出限制。",
            "versionAlreadySet": "已设置了兼容性版本。"
        };
        return content;
    }
}



}
