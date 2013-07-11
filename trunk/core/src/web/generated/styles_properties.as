package 
{

import mx.resources.ResourceBundle;

[ExcludeClass]

public class zh_CN$styles_properties extends ResourceBundle
{

    public function zh_CN$styles_properties()
    {
		 super("zh_CN", "styles");
    }

    override protected function getContent():Object
    {
        var content:Object =
        {
            "unableToLoad": "无法加载样式（{0}）：{1}。"
        };
        return content;
    }
}



}
