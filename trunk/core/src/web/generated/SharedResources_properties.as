package 
{

import mx.resources.ResourceBundle;

[ExcludeClass]

public class zh_CN$SharedResources_properties extends ResourceBundle
{

    public function zh_CN$SharedResources_properties()
    {
		 super("zh_CN", "SharedResources");
    }

    override protected function getContent():Object
    {
        var content:Object =
        {
            "dateFormat": "YYYY-M-D",
            "dayNames": "周一,周二,周三,周四,周五,周六,周日",
            "thousandsSeparatorFrom": ",",
            "monthNames": "一,二,三,四,五,六,七,八,九,十,十一,十二",
            "decimalSeparatorFrom": ".",
            "currencySymbol": "￥",
            "decimalSeparatorTo": ".",
            "thousandsSeparatorTo": ",",
            "monthSymbol": "月",
            "alignSymbol": "left"
        };
        return content;
    }
}



}
