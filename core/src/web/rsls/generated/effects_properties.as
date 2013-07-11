package 
{

import mx.resources.ResourceBundle;

[ExcludeClass]

public class zh_CN$effects_properties extends ResourceBundle
{

    public function zh_CN$effects_properties()
    {
		 super("zh_CN", "effects");
    }

    override protected function getContent():Object
    {
        var content:Object =
        {
            "incorrectTrigger": "moveEffect 触发器无法触发缩放效果。",
            "incorrectSource": "源代码属性必须为 Class 或 String。"
        };
        return content;
    }
}



}
