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

import com.core.batch.facade.BatchFacade;
import com.core.batch.facade.BatchTaskExceptionLogFacade;
import com.shangkang.bo.BatchTaskExceptionLog;
import com.shangkang.core.action.BaseAction;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;

public class BatchTaskExceptionLogAction extends BaseAction {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private List<java.lang.Long> primaryKeys;
	private BatchTaskExceptionLog batchTaskExceptionLog;
	private BatchTaskExceptionLogFacade batchTaskExceptionLogFacade;
	private BatchFacade batchFacade;
	private String stackMessage;
	private Long taskOid;

	public BatchTaskExceptionLogAction()
	{
		super();
		batchTaskExceptionLogFacade = (BatchTaskExceptionLogFacade) this.getBean("batchTaskExceptionLogFacade");
		batchFacade = (BatchFacade) this.getBean("batchFacade");
	}

	public Long getTaskOid()
	{
		return taskOid;
	}

	public void setTaskOid(Long taskOid)
	{
		this.taskOid = taskOid;
	}

	public String getStackMessage()
	{
		return stackMessage;
	}

	public void setStackMessage(String stackMessage)
	{
		this.stackMessage = stackMessage;
	}

	public BatchTaskExceptionLog getBatchTaskExceptionLog() {
		return batchTaskExceptionLog;
	}

	public void setBatchTaskExceptionLog(BatchTaskExceptionLog batchTaskExceptionLog) {
		this.batchTaskExceptionLog = batchTaskExceptionLog;
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
	public String listPgBatchTaskExceptionLog() throws ServiceException
	{
		Pagination<BatchTaskExceptionLog> pagination = new Pagination<BatchTaskExceptionLog>();
		
		pagination.setPaginationFlag(paginationFlag);
		pagination.setPageNo(pageNo);
		pagination.setPageSize(pageSize);
		
		Pagination<BatchTaskExceptionLog> lst = batchTaskExceptionLogFacade.listPaginationByProperty(pagination, batchTaskExceptionLog);
		
		total = lst.getTotal();
		rows = lst.getResultList();
		
		return "listPgBatchTaskExceptionLog";
	}
	
	/**
	 * 新增记录
	 * @return
	 * @throws ServiceException
	 */
	public String addBatchTaskExceptionLog() throws ServiceException
	{
		batchTaskExceptionLogFacade.save(batchTaskExceptionLog);
		
		return "addBatchTaskExceptionLog";
	}
	
	/**
	 * 根据多条主键值删除记录
	 * @return
	 * @throws ServiceException
	 */
	public String deleteBatchTaskExceptionLog() throws ServiceException
	{
		batchTaskExceptionLogFacade.deleteByPKeys(primaryKeys);
		
		return "deleteBatchTaskExceptionLog";
	}
	
	/**
	 * 修改记录
	 * @return
	 * @throws ServiceException
	 */
	public String updateBatchTaskExceptionLog() throws ServiceException
	{
		batchTaskExceptionLogFacade.update(batchTaskExceptionLog);
		
		return "updateBatchTaskExceptionLog";
	}
	
	public String findStackMessageByTaskOid()throws ServiceException {
		stackMessage = batchFacade.getStackMessageByTaskOid(taskOid);
		return SUCCESS;
	}
}
