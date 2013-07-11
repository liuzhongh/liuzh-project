package 
{

import mx.resources.ResourceBundle;

[ExcludeClass]

public class zh_CN$skins_properties extends ResourceBundle
{

    public function zh_CN$skins_properties()
    {
		 super("zh_CN", "skins");
    }

    override protected function getContent():Object
    {
        var content:Object =
        {
            "notLoaded": "无法加载“{0}”。"
        };
        return content;
    }
}



}
