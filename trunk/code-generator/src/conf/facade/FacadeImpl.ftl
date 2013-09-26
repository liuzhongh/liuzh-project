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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Component;

<#assign tableField="${result.getFirstCharacterUppercase(result.getTableName())}">
<#assign tableLowercaseField="${result.getFirstCharacterLowercase(result.getTableName())}">
import ${boPackage}.${tableField};
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;
import ${interfacePackage}.${tableField}Facade;
import ${servicePackage}.${tableField}Service;

@Component("${tableLowercaseField}Facade")
@RemotingDestination(channels = { "my-amf", "my-secure-amf" })
public class ${tableField}FacadeImpl implements ${tableField}Facade {

	private ${tableField}Service ${tableLowercaseField}Service;
	
	@Autowired
	public void set${tableField}Service(${tableField}Service ${tableLowercaseField}Service)
	{
		this.${tableLowercaseField}Service = ${tableLowercaseField}Service;
	}
	
	/**
	 * 通过主键查询实体对象
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public ${tableField} getByPK(${result.getKeyType()} primaryKey) throws ServiceException
	{
		return ${tableLowercaseField}Service.getByPK(primaryKey);
	}

	/**
	 * 查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<${tableField}> list() throws ServiceException
	{
		return ${tableLowercaseField}Service.list();
	}

	/**
	 * 根据查询条件查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<${tableField}> listByProperty(${tableField} ${tableLowercaseField})
			throws ServiceException
	{
		return ${tableLowercaseField}Service.listByProperty(${tableLowercaseField});
	}
	
	/**
	 * 根据查询条件查询分页记录
	 * @return
	 * @throws ServiceException
	 */
	public Pagination<${tableField}> listPaginationByProperty(Pagination<${tableField}> pagination, ${tableField} ${tableLowercaseField})
			throws ServiceException
	{
		return ${tableLowercaseField}Service.listPaginationByProperty(pagination, ${tableLowercaseField});
	}

	/**
	 * 根据主键删除记录
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByPK(${result.getKeyType()} primaryKey) throws ServiceException
	{
		return ${tableLowercaseField}Service.deleteByPK(primaryKey);
	}
	
	/**
	 * 根据多个主键删除记录
	 * @param primaryKeys
	 * @throws ServiceException
	 */
	public void deleteByPKeys(List<${result.getKeyType()}> primaryKeys) throws ServiceException
	{
		${tableLowercaseField}Service.deleteByPKeys(primaryKeys);
	}
	
	/**
	 * 根据传入参数删除记录
	 * @param ${tableLowercaseField}
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByProperty(${tableField} ${tableLowercaseField}) throws ServiceException
	{
		return ${tableLowercaseField}Service.deleteByProperty(${tableLowercaseField});
	}

	/**
	 * 保存记录
	 * @param ${tableLowercaseField}
	 * @return
	 * @throws ServiceException
	 */
	public void save(${tableField} ${tableLowercaseField}) throws ServiceException
	{
		${tableLowercaseField}Service.save(${tableLowercaseField});
	}

	/**
	 * 更新记录
	 * @param ${tableLowercaseField}
	 * @return
	 * @throws ServiceException
	 */
	public int update(${tableField} ${tableLowercaseField}) throws ServiceException
	{
		return ${tableLowercaseField}Service.update(${tableLowercaseField});
	}

	/**
	 * 根据条件查询记录条数
	 * @param ${tableLowercaseField}
	 * @return
	 * @throws ServiceException
	 */
	public int findByCount(${tableField} ${tableLowercaseField}) throws ServiceException
	{
		return ${tableLowercaseField}Service.findByCount(${tableLowercaseField});
	}
}
