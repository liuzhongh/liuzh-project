package org.code.generator.config;

import static org.code.generator.util.StringUtility.stringHasValue;

import org.code.generator.dom.Attribute;
import org.code.generator.dom.XmlElement;
import org.code.generator.util.StringUtility;

public class TableConfiguration extends PropertyHolder {

    private String catalog;
    private String schema;
    private boolean allTable;
    private String tablesName;
    private String tableType;
    private String tablePrefix = "";

    public TableConfiguration(Context context) {
        super();
        
        allTable = true;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public boolean isAllTable()
	{
    	if(StringUtility.stringHasValue(tablesName))
    		return false;
    	
		return allTable;
	}

	public void setAllTable(boolean allTable)
	{
		this.allTable = allTable;
	}

	public String getTablesName()
	{
		return tablesName;
	}

	public void setTablesName(String tablesName)
	{
		this.tablesName = tablesName;
	}

	public String getTableType()
	{
		return tableType;
	}

	public void setTableType(String tableType)
	{
		this.tableType = tableType;
	}

	
	public String getTablePrefix()
	{
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix)
	{
		this.tablePrefix = tablePrefix;
	}

	public XmlElement toXmlElement() {
        XmlElement xmlElement = new XmlElement("table"); //$NON-NLS-1$
        xmlElement.addAttribute(new Attribute("tablesName", tablesName)); //$NON-NLS-1$

        if (stringHasValue(catalog)) {
            xmlElement.addAttribute(new Attribute("catalog", catalog)); //$NON-NLS-1$
        }

        if (stringHasValue(schema)) {
            xmlElement.addAttribute(new Attribute("schema", schema)); //$NON-NLS-1$
        }
        
        if (stringHasValue(tableType)) {
            xmlElement.addAttribute(new Attribute("tableType", tableType)); //$NON-NLS-1$
        }
        
        if (stringHasValue(tablePrefix)) {
            xmlElement.addAttribute(new Attribute("tablePrefix", tablePrefix)); //$NON-NLS-1$
        }

        if (!allTable) {
            xmlElement.addAttribute(new Attribute("allTable", "false")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        addPropertyXmlElements(xmlElement);

        return xmlElement;
    }

}
