/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-3-23 14:34:22
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangkang.bo.BatchTaskExceptionLog;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.mapper.BatchTaskExceptionLogMapper;

@Service("batchTaskExceptionLogService")
public class BatchTaskExceptionLogService {

	private BatchTaskExceptionLogMapper	batchTaskExceptionLogMapper;

	@Autowired
	public void setBatchTaskExceptionLogMapper(BatchTaskExceptionLogMapper batchTaskExceptionLogMapper)
	{
		this.batchTaskExceptionLogMapper = batchTaskExceptionLogMapper;
	}
	
	/**
	 * 通过主键查询实体对象
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public BatchTaskExceptionLog getByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return batchTaskExceptionLogMapper.getByPK(primaryKey);
	}

	/**
	 * 查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<BatchTaskExceptionLog> list() throws ServiceException
	{
		return batchTaskExceptionLogMapper.list();
	}

	/**
	 * 根据查询条件查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<BatchTaskExceptionLog> listByProperty(BatchTaskExceptionLog batchTaskExceptionLog)
			throws ServiceException
	{
		return batchTaskExceptionLogMapper.listByProperty(batchTaskExceptionLog);
	}
	
	/**
	 * 根据查询条件查询分页记录
	 * @return
	 * @throws ServiceException
	 */
	public Pagination<BatchTaskExceptionLog> listPaginationByProperty(Pagination<BatchTaskExceptionLog> pagination, BatchTaskExceptionLog batchTaskExceptionLog)
			throws ServiceException
	{
		List<BatchTaskExceptionLog> list = batchTaskExceptionLogMapper.listPaginationByProperty(pagination, batchTaskExceptionLog);
		
		pagination.setResultList(list);
		
		return pagination;
	}

	/**
	 * 根据主键删除记录
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return batchTaskExceptionLogMapper.deleteByPK(primaryKey);
	}
	
	/**
	 * 根据多个主键删除记录
	 * @param primaryKeys
	 * @throws ServiceException
	 */
	public void deleteByPKeys(List<java.lang.Long> primaryKeys) throws ServiceException
	{
		batchTaskExceptionLogMapper.deleteByPKeys(primaryKeys);
	}
	
	/**
	 * 根据传入参数删除记录
	 * @param batchTaskExceptionLog
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByProperty(BatchTaskExceptionLog batchTaskExceptionLog) throws ServiceException
	{
		return batchTaskExceptionLogMapper.deleteByProperty(batchTaskExceptionLog);
	}

	/**
	 * 保存记录
	 * @param batchTaskExceptionLog
	 * @return
	 * @throws ServiceException
	 */
	public void save(BatchTaskExceptionLog batchTaskExceptionLog) throws ServiceException
	{
		batchTaskExceptionLogMapper.save(batchTaskExceptionLog);
	}

	/**
	 * 更新记录
	 * @param batchTaskExceptionLog
	 * @return
	 * @throws ServiceException
	 */
	public int update(BatchTaskExceptionLog batchTaskExceptionLog) throws ServiceException
	{
		return batchTaskExceptionLogMapper.update(batchTaskExceptionLog);
	}

	/**
	 * 根据条件查询记录条数
	 * @param batchTaskExceptionLog
	 * @return
	 * @throws ServiceException
	 */
	public int findByCount(BatchTaskExceptionLog batchTaskExceptionLog) throws ServiceException
	{
		return batchTaskExceptionLogMapper.findByCount(batchTaskExceptionLog);
	}
}