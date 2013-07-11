/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-3-23 14:34:25
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.batch.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Component;

import com.core.batch.facade.BatchJobFacade;
import com.shangkang.bo.BatchJob;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.service.BatchJobService;

@Component("batchJobFacade")
@RemotingDestination(channels = { "my-amf", "my-secure-amf" })
public class BatchJobFacadeImpl implements BatchJobFacade {

	private BatchJobService batchJobService;
	
	@Autowired
	public void setBatchJobService(BatchJobService batchJobService)
	{
		this.batchJobService = batchJobService;
	}
	
	/**
	 * 通过主键查询实体对象
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public BatchJob getByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return batchJobService.getByPK(primaryKey);
	}

	/**
	 * 查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<BatchJob> list() throws ServiceException
	{
		return batchJobService.list();
	}

	/**
	 * 根据查询条件查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<BatchJob> listByProperty(BatchJob batchJob)
			throws ServiceException
	{
		return batchJobService.listByProperty(batchJob);
	}
	
	/**
	 * 根据查询条件查询分页记录
	 * @return
	 * @throws ServiceException
	 */
	public Pagination<BatchJob> listPaginationByProperty(Pagination<BatchJob> pagination, BatchJob batchJob)
			throws ServiceException
	{
		return batchJobService.listPaginationByProperty(pagination, batchJob);
	}

	/**
	 * 根据主键删除记录
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return batchJobService.deleteByPK(primaryKey);
	}
	
	/**
	 * 根据多个主键删除记录
	 * @param primaryKeys
	 * @throws ServiceException
	 */
	public void deleteByPKeys(List<java.lang.Long> primaryKeys) throws ServiceException
	{
		batchJobService.deleteByPKeys(primaryKeys);
	}
	
	/**
	 * 根据传入参数删除记录
	 * @param batchJob
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByProperty(BatchJob batchJob) throws ServiceException
	{
		return batchJobService.deleteByProperty(batchJob);
	}

	/**
	 * 保存记录
	 * @param batchJob
	 * @return
	 * @throws ServiceException
	 */
	public void save(BatchJob batchJob) throws ServiceException
	{
		batchJobService.save(batchJob);
	}

	/**
	 * 更新记录
	 * @param batchJob
	 * @return
	 * @throws ServiceException
	 */
	public int update(BatchJob batchJob) throws ServiceException
	{
		return batchJobService.update(batchJob);
	}

	/**
	 * 根据条件查询记录条数
	 * @param batchJob
	 * @return
	 * @throws ServiceException
	 */
	public int findByCount(BatchJob batchJob) throws ServiceException
	{
		return batchJobService.findByCount(batchJob);
	}
}
