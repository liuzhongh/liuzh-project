package 
{

import mx.resources.ResourceBundle;

[ExcludeClass]

public class zh_CN$message_resource_sql_properties extends ResourceBundle
{

    public function zh_CN$message_resource_sql_properties()
    {
		 super("zh_CN", "message_resource_sql");
    }

    override protected function getContent():Object
    {
        var content:Object =
        {
            "message.sql.selectInformation": "select * from information where id = ?",
            "message.sql.selectJbpm4IdUser": "select * from jbpm4_id_user where dbid_ = ?;"
        };
        return content;
    }
}



}
