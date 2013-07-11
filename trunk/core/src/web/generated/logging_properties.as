package 
{

import mx.resources.ResourceBundle;

[ExcludeClass]

public class zh_CN$logging_properties extends ResourceBundle
{

    public function zh_CN$logging_properties()
    {
		 super("zh_CN", "logging");
    }

    override protected function getContent():Object
    {
        var content:Object =
        {
            "invalidTarget": "指定的目标无效。",
            "charsInvalid": "过滤器“{0}”错误：以下字符无效：[]~$^&amp;/(){}<>+=_-`!@#%?,:;'\"。",
            "charPlacement": "过滤器“{0}”错误：“*”必须是最右侧的字符。",
            "levelLimit": "记录级别无法设置为 LogEventLevel.ALL。",
            "invalidChars": "类别不能包含以下任何字符：[]`~,!@#$%*^&amp;()]{}+=|';?><./\"。",
            "invalidLen": "类别长度必须至少为一个字符。"
        };
        return content;
    }
}



}
