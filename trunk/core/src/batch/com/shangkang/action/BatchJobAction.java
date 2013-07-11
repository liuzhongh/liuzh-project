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
package com.shangkang.action;

import java.util.List;

import com.core.batch.facade.BatchJobFacade;
import com.shangkang.bo.BatchJob;
import com.shangkang.core.action.BaseAction;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;

public class BatchJobAction extends BaseAction {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private List<java.lang.Long> primaryKeys;
	private BatchJob batchJob;
	private BatchJobFacade batchJobFacade;

	public BatchJobAction()
	{
		super();
		batchJobFacade = (BatchJobFacade) this.getBean("batchJobFacade");
	}

	public BatchJob getBatchJob() {
		return batchJob;
	}

	public void setBatchJob(BatchJob batchJob) {
		this.batchJob = batchJob;
	}
	
	/**
	 * @param primaryKeys the primaryKeys to set
	 */
	public void setPrimaryKeys(List<java.lang.Long> primaryKeys)
	{
		this.primaryKeys = primaryKeys;
	}
	
	/**
	 * 分页查询记录
	 * @return
	 * @throws ServiceException
	 */
	public String listPgBatchJob() throws ServiceException
	{
		Pagination<BatchJob> pagination = new Pagination<BatchJob>();
		
		pagination.setPaginationFlag(paginationFlag);
		pagination.setPageNo(pageNo);
		pagination.setPageSize(pageSize);
		
		Pagination<BatchJob> lst = batchJobFacade.listPaginationByProperty(pagination, batchJob);
		
		total = lst.getTotal();
		rows = lst.getResultList();
		
		return "listPgBatchJob";
	}
	
	/**
	 * 新增记录
	 * @return
	 * @throws ServiceException
	 */
	public String addBatchJob() throws ServiceException
	{
		batchJobFacade.save(batchJob);
		
		return "addBatchJob";
	}
	
	/**
	 * 根据多条主键值删除记录
	 * @return
	 * @throws ServiceException
	 */
	public String deleteBatchJob() throws ServiceException
	{
		batchJobFacade.deleteByPKeys(primaryKeys);
		
		return "deleteBatchJob";
	}
	
	/**
	 * 修改记录
	 * @return
	 * @throws ServiceException
	 */
	public String updateBatchJob() throws ServiceException
	{
		batchJobFacade.update(batchJob);
		
		return "updateBatchJob";
	}
}
