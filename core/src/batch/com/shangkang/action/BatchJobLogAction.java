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
package com.shangkang.action;

import java.util.List;

import com.core.batch.facade.BatchJobLogFacade;
import com.shangkang.bo.BatchJobLog;
import com.shangkang.core.action.BaseAction;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;

public class BatchJobLogAction extends BaseAction {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private List<java.lang.Long> primaryKeys;
	private BatchJobLog batchJobLog;
	private BatchJobLogFacade batchJobLogFacade;

	public BatchJobLogAction()
	{
		super();
		batchJobLogFacade = (BatchJobLogFacade) this.getBean("batchJobLogFacade");
	}

	public BatchJobLog getBatchJobLog() {
		return batchJobLog;
	}

	public void setBatchJobLog(BatchJobLog batchJobLog) {
		this.batchJobLog = batchJobLog;
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
	public String listPgBatchJobLog() throws ServiceException
	{
		Pagination<BatchJobLog> pagination = new Pagination<BatchJobLog>();
		
		pagination.setPaginationFlag(paginationFlag);
		pagination.setPageNo(pageNo);
		pagination.setPageSize(pageSize);
		
		Pagination<BatchJobLog> lst = batchJobLogFacade.listPaginationByProperty(pagination, batchJobLog);
		
		total = lst.getTotal();
		rows = lst.getResultList();
		
		return "listPgBatchJobLog";
	}
	
	/**
	 * 新增记录
	 * @return
	 * @throws ServiceException
	 */
	public String addBatchJobLog() throws ServiceException
	{
		batchJobLogFacade.save(batchJobLog);
		
		return "addBatchJobLog";
	}
	
	/**
	 * 根据多条主键值删除记录
	 * @return
	 * @throws ServiceException
	 */
	public String deleteBatchJobLog() throws ServiceException
	{
		batchJobLogFacade.deleteByPKeys(primaryKeys);
		
		return "deleteBatchJobLog";
	}
	
	/**
	 * 修改记录
	 * @return
	 * @throws ServiceException
	 */
	public String updateBatchJobLog() throws ServiceException
	{
		batchJobLogFacade.update(batchJobLog);
		
		return "updateBatchJobLog";
	}
}
