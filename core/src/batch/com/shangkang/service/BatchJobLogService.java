/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-3-23 14:34:24
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

import com.shangkang.bo.BatchJobLog;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.mapper.BatchJobLogMapper;

@Service("batchJobLogService")
public class BatchJobLogService {

	private BatchJobLogMapper	batchJobLogMapper;

	@Autowired
	public void setBatchJobLogMapper(BatchJobLogMapper batchJobLogMapper)
	{
		this.batchJobLogMapper = batchJobLogMapper;
	}
	
	/**
	 * 通过主键查询实体对象
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public BatchJobLog getByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return batchJobLogMapper.getByPK(primaryKey);
	}

	/**
	 * 查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<BatchJobLog> list() throws ServiceException
	{
		return batchJobLogMapper.list();
	}

	/**
	 * 根据查询条件查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<BatchJobLog> listByProperty(BatchJobLog batchJobLog)
			throws ServiceException
	{
		return batchJobLogMapper.listByProperty(batchJobLog);
	}
	
	/**
	 * 根据查询条件查询分页记录
	 * @return
	 * @throws ServiceException
	 */
	public Pagination<BatchJobLog> listPaginationByProperty(Pagination<BatchJobLog> pagination, BatchJobLog batchJobLog)
			throws ServiceException
	{
		List<BatchJobLog> list = batchJobLogMapper.listPaginationByProperty(pagination, batchJobLog);
		
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
		return batchJobLogMapper.deleteByPK(primaryKey);
	}
	
	/**
	 * 根据多个主键删除记录
	 * @param primaryKeys
	 * @throws ServiceException
	 */
	public void deleteByPKeys(List<java.lang.Long> primaryKeys) throws ServiceException
	{
		batchJobLogMapper.deleteByPKeys(primaryKeys);
	}
	
	/**
	 * 根据传入参数删除记录
	 * @param batchJobLog
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByProperty(BatchJobLog batchJobLog) throws ServiceException
	{
		return batchJobLogMapper.deleteByProperty(batchJobLog);
	}

	/**
	 * 保存记录
	 * @param batchJobLog
	 * @return
	 * @throws ServiceException
	 */
	public void save(BatchJobLog batchJobLog) throws ServiceException
	{
		batchJobLogMapper.save(batchJobLog);
	}

	/**
	 * 更新记录
	 * @param batchJobLog
	 * @return
	 * @throws ServiceException
	 */
	public int update(BatchJobLog batchJobLog) throws ServiceException
	{
		return batchJobLogMapper.update(batchJobLog);
	}

	/**
	 * 根据条件查询记录条数
	 * @param batchJobLog
	 * @return
	 * @throws ServiceException
	 */
	public int findByCount(BatchJobLog batchJobLog) throws ServiceException
	{
		return batchJobLogMapper.findByCount(batchJobLog);
	}
}