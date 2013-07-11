package 
{

import mx.resources.ResourceBundle;

[ExcludeClass]

public class zh_CN$containers_properties extends ResourceBundle
{

    public function zh_CN$containers_properties()
    {
		 super("zh_CN", "containers");
    }

    override protected function getContent():Object
    {
        var content:Object =
        {
            "noColumnsFound": "未找到任何 ConstraintColumn。",
            "noRowsFound": "未找到任何 ConstraintRow。",
            "rowNotFound": "未找到 ConstraintRow“{0}”。",
            "columnNotFound": "未找到 ConstraintColumn“{0}”。"
        };
        return content;
    }
}



}
