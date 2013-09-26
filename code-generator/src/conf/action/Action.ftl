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

import java.util.List;

<#assign tableField="${result.getFirstCharacterUppercase(result.getTableName())}">
<#assign tableLowercaseField="${result.getFirstCharacterLowercase(result.getTableName())}">
import ${boPackage}.${tableField};
import com.shangkang.core.action.BaseAction;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;
import ${facadePackage}.${tableField}Facade;

public class ${tableField}Action extends BaseAction {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private List<${result.getKeyType()}> primaryKeys;
	private ${tableField} ${tableLowercaseField};
	private ${tableField}Facade ${tableLowercaseField}Facade;

	public ${tableField}Action()
	{
		super();
		${tableLowercaseField}Facade = (${tableField}Facade) this.getBean("${tableLowercaseField}Facade");
	}

	public ${tableField} get${tableField}() {
		return ${tableLowercaseField};
	}

	public void set${tableField}(${tableField} ${tableLowercaseField}) {
		this.${tableLowercaseField} = ${tableLowercaseField};
	}
	
	/**
	 * @param primaryKeys the primaryKeys to set
	 */
	public void setPrimaryKeys(List<${result.getKeyType()}> primaryKeys)
	{
		this.primaryKeys = primaryKeys;
	}
	
	/**
	 * 分页查询记录
	 * @return
	 * @throws ServiceException
	 */
	public String listPg${tableField}() throws ServiceException
	{
		Pagination<${tableField}> pagination = new Pagination<${tableField}>();
		
		pagination.setPaginationFlag(paginationFlag);
		pagination.setPageNo(pageNo);
		pagination.setPageSize(pageSize);
		
		Pagination<${tableField}> lst = ${tableLowercaseField}Facade.listPaginationByProperty(pagination, ${tableLowercaseField});
		
		total = lst.getTotal();
		rows = lst.getResultList();
		
		return "listPg${tableField}";
	}
	
	/**
	 * 新增记录
	 * @return
	 * @throws ServiceException
	 */
	public String add${tableField}() throws ServiceException
	{
		${tableLowercaseField}Facade.save(${tableLowercaseField});
		
		return "add${tableField}";
	}
	
	/**
	 * 根据多条主键值删除记录
	 * @return
	 * @throws ServiceException
	 */
	public String delete${tableField}() throws ServiceException
	{
		${tableLowercaseField}Facade.deleteByPKeys(primaryKeys);
		
		return "delete${tableField}";
	}
	
	/**
	 * 修改记录
	 * @return
	 * @throws ServiceException
	 */
	public String update${tableField}() throws ServiceException
	{
		${tableLowercaseField}Facade.update(${tableLowercaseField});
		
		return "update${tableField}";
	}
}
