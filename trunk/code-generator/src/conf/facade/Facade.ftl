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
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;

public interface ${tableField}Facade {

	/**
	 * 通过主键查询实体对象
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public ${tableField} getByPK(${result.getKeyType()} primaryKey) throws ServiceException;

	/**
	 * 查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<${tableField}> list() throws ServiceException;

	/**
	 * 根据查询条件查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<${tableField}> listByProperty(${tableField} ${tableLowercaseField})
			throws ServiceException;

	/**
	 * 根据主键删除记录
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByPK(${result.getKeyType()} primaryKey) throws ServiceException;
	
	/**
	 * 根据多个主键删除记录
	 * @param primaryKeys
	 * @throws ServiceException
	 */
	public void deleteByPKeys(List<${result.getKeyType()}> primaryKeys) throws ServiceException;

	/**
	 * 根据传入参数删除记录
	 * @param ${tableLowercaseField}
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByProperty(${tableField} ${tableLowercaseField}) throws ServiceException;

	/**
	 * 保存记录
	 * @param ${tableLowercaseField}
	 * @return
	 * @throws ServiceException
	 */
	public void save(${tableField} ${tableLowercaseField}) throws ServiceException;

	/**
	 * 更新记录
	 * @param ${tableLowercaseField}
	 * @return
	 * @throws ServiceException
	 */
	public int update(${tableField} ${tableLowercaseField}) throws ServiceException;

	/**
	 * 根据条件查询记录条数
	 * @param ${tableLowercaseField}
	 * @return
	 * @throws ServiceException
	 */
	public int findByCount(${tableField} ${tableLowercaseField}) throws ServiceException;
	
	/**
	 * 根据查询条件查询分页记录
	 * @return
	 * @throws ServiceException
	 */
	public Pagination<${tableField}> listPaginationByProperty(Pagination<${tableField}> pagination, ${tableField} ${tableLowercaseField})
			throws ServiceException;

}
