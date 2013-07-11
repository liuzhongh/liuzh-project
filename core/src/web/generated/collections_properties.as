package 
{

import mx.resources.ResourceBundle;

[ExcludeClass]

public class zh_CN$collections_properties extends ResourceBundle
{

    public function zh_CN$collections_properties()
    {
		 super("zh_CN", "collections");
    }

    override protected function getContent():Object
    {
        var content:Object =
        {
            "findCondition": "查找条件必须包含“{0}”之前的所有排序域。",
            "noComparatorSortField": "无法确定名为“{0}”的 SortField 的比较条件。",
            "outOfBounds": "指定的索引“{0}”越界。",
            "nonUnique": "项目中的值不唯一。",
            "incorrectAddition": "试图添加的项目已存在于视图中。",
            "findRestriction": "查找条件必须至少包含一个排序域值。",
            "invalidType": "类型不正确。必须是 XML 类型，或包含一个 XML 对象的 XMLList。 ",
            "unknownMode": "查找模式未知。",
            "invalidIndex": "无效的索引：“{0}”。",
            "invalidRemove": "当前为 beforeFirst 或 afterLast 时，无法移动。",
            "unknownProperty": "未知的属性：“{0}”。",
            "invalidInsert": "当前为 beforeFirst 时，无法插入。",
            "itemNotFound": "视图未经过排序时无法执行查找。",
            "bookmarkInvalid": "书签不再有效。",
            "noComparator": "无法确定“{0}”的比较条件。",
            "invalidCursor": "光标不再有效。",
            "noItems": "没有要搜索的项目。",
            "bookmarkNotFound": "书签并非来自该视图。"
        };
        return content;
    }
}



}
