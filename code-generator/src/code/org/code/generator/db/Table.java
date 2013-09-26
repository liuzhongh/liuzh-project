/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-4-23
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.code.generator.db;

import java.util.ArrayList;
import java.util.List;

import org.code.generator.util.StringUtility;

import com.shangkang.tools.UtilHelper;

public class Table {

	private String tableName;
	
	private String tableRemarks;
	
	private List<TableColumn> keyColumns = new ArrayList<TableColumn>();
	
	private List<TableColumn> columns = new ArrayList<TableColumn>();
	
	private String key;
	
	private String keyType;
	
	private String tablePrefix;

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getTableRemarks()
	{
		return tableRemarks;
	}

	public void setTableRemarks(String tableRemarks)
	{
		this.tableRemarks = tableRemarks;
	}

	public List<TableColumn> getKeyColumns()
	{
		return keyColumns;
	}

	public void setKeyColumns(List<TableColumn> keyColumns)
	{
		this.keyColumns = keyColumns;
	}

	public List<TableColumn> getColumns()
	{
		return columns;
	}

	public void setColumns(List<TableColumn> columns)
	{
		this.columns = columns;
	}
	
	public String getKey()
	{
		if(!UtilHelper.isEmpty(keyColumns) && keyColumns.size() == 1)
			return keyColumns.get(0).getColumnName();
		
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getKeyType()
	{
		if(!UtilHelper.isEmpty(keyColumns) && keyColumns.size() == 1)
		{
			if(!UtilHelper.isEmpty(columns))
			{
				for(TableColumn col : columns)
				{
					if(getKey().equals(col.getColumnName()))
						return UtilHelper.trim(col.getColumnType());
				}
			}
		}
		
		return UtilHelper.trim(keyType);
	}

	public void setKeyType(String keyType)
	{
		this.keyType = keyType;
	}

	public String getTablePrefix()
	{
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix)
	{
		this.tablePrefix = tablePrefix;
	}

	public String getFirstCharacterUppercase(String inputString)
	{
		return StringUtility.getCamelCaseString(inputString, true);
	}
	
	public String getFirstCharacterLowercase(String inputString)
	{
		return StringUtility.getCamelCaseString(inputString, false);
	}

}
