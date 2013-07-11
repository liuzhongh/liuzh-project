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
package com.shangkang.action;

import java.util.List;

import com.core.batch.facade.BatchTaskFacade;
import com.shangkang.bo.BatchTask;
import com.shangkang.core.action.BaseAction;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;

public class BatchTaskAction extends BaseAction {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private List<java.lang.Long> primaryKeys;
	private BatchTask batchTask;
	private BatchTaskFacade batchTaskFacade;

	public BatchTaskAction()
	{
		super();
		batchTaskFacade = (BatchTaskFacade) this.getBean("batchTaskFacade");
	}

	public BatchTask getBatchTask() {
		return batchTask;
	}

	public void setBatchTask(BatchTask batchTask) {
		this.batchTask = batchTask;
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
	public String listPgBatchTask() throws ServiceException
	{
		Pagination<BatchTask> pagination = new Pagination<BatchTask>();
		
		pagination.setPaginationFlag(paginationFlag);
		pagination.setPageNo(pageNo);
		pagination.setPageSize(pageSize);
		
		Pagination<BatchTask> lst = batchTaskFacade.listPaginationByProperty(pagination, batchTask);
		
		total = lst.getTotal();
		rows = lst.getResultList();
		
		return "listPgBatchTask";
	}
	
	/**
	 * 新增记录
	 * @return
	 * @throws ServiceException
	 */
	public String addBatchTask() throws ServiceException
	{
		batchTaskFacade.save(batchTask);
		
		return "addBatchTask";
	}
	
	/**
	 * 根据多条主键值删除记录
	 * @return
	 * @throws ServiceException
	 */
	public String deleteBatchTask() throws ServiceException
	{
		batchTaskFacade.deleteByPKeys(primaryKeys);
		
		return "deleteBatchTask";
	}
	
	/**
	 * 修改记录
	 * @return
	 * @throws ServiceException
	 */
	public String updateBatchTask() throws ServiceException
	{
		batchTaskFacade.update(batchTask);
		
		return "updateBatchTask";
	}
}
