/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-3-23 14:34:23
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.batch.facade;

import java.util.List;

import com.shangkang.bo.BatchTask;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;

public interface BatchTaskFacade {

	/**
	 * 通过主键查询实体对象
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public BatchTask getByPK(java.lang.Long primaryKey) throws ServiceException;

	/**
	 * 查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<BatchTask> list() throws ServiceException;

	/**
	 * 根据查询条件查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<BatchTask> listByProperty(BatchTask batchTask)
			throws ServiceException;

	/**
	 * 根据主键删除记录
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByPK(java.lang.Long primaryKey) throws ServiceException;
	
	/**
	 * 根据多个主键删除记录
	 * @param primaryKeys
	 * @throws ServiceException
	 */
	public void deleteByPKeys(List<java.lang.Long> primaryKeys) throws ServiceException;

	/**
	 * 根据传入参数删除记录
	 * @param batchTask
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByProperty(BatchTask batchTask) throws ServiceException;

	/**
	 * 保存记录
	 * @param batchTask
	 * @return
	 * @throws ServiceException
	 */
	public void save(BatchTask batchTask) throws ServiceException;

	/**
	 * 更新记录
	 * @param batchTask
	 * @return
	 * @throws ServiceException
	 */
	public int update(BatchTask batchTask) throws ServiceException;

	/**
	 * 根据条件查询记录条数
	 * @param batchTask
	 * @return
	 * @throws ServiceException
	 */
	public int findByCount(BatchTask batchTask) throws ServiceException;
	
	/**
	 * 根据查询条件查询分页记录
	 * @return
	 * @throws ServiceException
	 */
	public Pagination<BatchTask> listPaginationByProperty(Pagination<BatchTask> pagination, BatchTask batchTask)
			throws ServiceException;

}
