package 
{

import mx.resources.ResourceBundle;

[ExcludeClass]

public class zh_CN$layout_properties extends ResourceBundle
{

    public function zh_CN$layout_properties()
    {
		 super("zh_CN", "layout");
    }

    override protected function getContent():Object
    {
        var content:Object =
        {
            "basicLayoutNotVirtualized": "BasicLayout 不支持虚拟化。",
            "invalidIndex": "invalidIndex"
        };
        return content;
    }
}



}
