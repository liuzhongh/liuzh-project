/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: ${user}
 * Created On: ${date}
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package ${package};

import com.shangkang.core.bo.Model;
<#assign tableField="${result.getFirstCharacterUppercase(result.getTableName())}">
<#assign tableLowercaseField="${result.getFirstCharacterLowercase(result.getTableName())}">

public class ${tableField} extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
<#foreach prop in result.getColumns()>
	/**
	  *	${prop.remarks}
	  */
	private ${prop.columnType} ${result.getFirstCharacterLowercase(prop.columnName)};

</#foreach>
<#foreach prop in result.getColumns()>
	/**
	  *	${prop.remarks}
	  */
	public ${prop.columnType} get${result.getFirstCharacterUppercase(prop.columnName)}() 
	{
		return ${result.getFirstCharacterLowercase(prop.columnName)};
	}
	
	/**
	  *	${prop.remarks}
	  */
	public void set${result.getFirstCharacterUppercase(prop.columnName)}(${prop.columnType} ${result.getFirstCharacterLowercase(prop.columnName)}) 
	{
		this.${result.getFirstCharacterLowercase(prop.columnName)} = ${result.getFirstCharacterLowercase(prop.columnName)};
	}
	
</#foreach>
	public String toString()
	{
		return "${tableField} [" + 
				<#assign tmp="">
				<#foreach prop in result.getColumns()>
					"${tmp}${result.getFirstCharacterLowercase(prop.columnName)}=" + ${result.getFirstCharacterLowercase(prop.columnName)} + 
					<#assign tmp=", ">
				</#foreach>
				"]";
	}
}

