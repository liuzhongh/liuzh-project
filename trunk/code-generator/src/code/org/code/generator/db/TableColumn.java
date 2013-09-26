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

public class TableColumn {

	private boolean pKey = false;
	
	private boolean nullable;
	
	private int jdbcType;
	
	private String jdbcTypeName;

	private String columnName;

	private int length;

	private int scale;

	private String remarks;

	private String defaultValue;
	
	private String userDefaultValue;
	
	private String columnType;

	/**
	 * @return the pKey
	 */
	public boolean ispKey()
	{
		return pKey;
	}

	/**
	 * @param pKey the pKey to set
	 */
	public void setpKey(boolean pKey)
	{
		this.pKey = pKey;
	}

	/**
	 * @return the nullable
	 */
	public boolean isNullable()
	{
		return nullable;
	}

	/**
	 * @param nullable the nullable to set
	 */
	public void setNullable(boolean nullable)
	{
		this.nullable = nullable;
	}

	public int getJdbcType()
	{
		return jdbcType;
	}

	public void setJdbcType(int jdbcType)
	{
		this.jdbcType = jdbcType;
	}

	public String getJdbcTypeName()
	{
		return jdbcTypeName;
	}

	public void setJdbcTypeName(String jdbcTypeName)
	{
		this.jdbcTypeName = jdbcTypeName;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public int getScale()
	{
		return scale;
	}

	public void setScale(int scale)
	{
		this.scale = scale;
	}

	public String getRemarks()
	{
		return trim(remarks);
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getDefaultValue()
	{
		return trim(defaultValue);
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}
	
	public String getUserDefaultValue()
	{
		return userDefaultValue;
	}

	public void setUserDefaultValue(String userDefaultValue)
	{
		this.userDefaultValue = userDefaultValue;
	}

	public String getColumnType()
	{
		return columnType;
	}

	public void setColumnType(String columnType)
	{
		this.columnType = columnType;
	}

	private String trim(String value)
	{
		if(null == value || "".equals(value.trim()))
			return "";
		
		return value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TableColumn [pKey=" + pKey + ", nullable=" + nullable
				+ ", jdbcType=" + jdbcType + ", jdbcTypeName=" + jdbcTypeName
				+ ", columnName=" + columnName + ", length=" + length
				+ ", scale=" + scale + ", remarks=" + remarks
				+ ", defaultValue=" + defaultValue + ", userDefaultValue="
				+ userDefaultValue + ", columnType=" + columnType + "]";
	}
	
	
}
