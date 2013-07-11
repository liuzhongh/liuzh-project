package 
{

import mx.resources.ResourceBundle;

[ExcludeClass]

public class zh_CN$message_resource_properties extends ResourceBundle
{

    public function zh_CN$message_resource_properties()
    {
		 super("zh_CN", "message_resource");
    }

    override protected function getContent():Object
    {
        var content:Object =
        {
            "message_test": "这是一个测试({0})。"
        };
        return content;
    }
}



}
