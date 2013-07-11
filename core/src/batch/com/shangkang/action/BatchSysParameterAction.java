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

import com.core.batch.facade.BatchSysParameterFacade;
import com.shangkang.bo.BatchSysParameter;
import com.shangkang.core.action.BaseAction;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;

public class BatchSysParameterAction extends BaseAction {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private List<java.lang.Long> primaryKeys;
	private BatchSysParameter batchSysParameter;
	private BatchSysParameterFacade batchSysParameterFacade;

	public BatchSysParameterAction()
	{
		super();
		batchSysParameterFacade = (BatchSysParameterFacade) this.getBean("batchSysParameterFacade");
	}

	public BatchSysParameter getBatchSysParameter() {
		return batchSysParameter;
	}

	public void setBatchSysParameter(BatchSysParameter batchSysParameter) {
		this.batchSysParameter = batchSysParameter;
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
	public String listPgBatchSysParameter() throws ServiceException
	{
		Pagination<BatchSysParameter> pagination = new Pagination<BatchSysParameter>();
		
		pagination.setPaginationFlag(paginationFlag);
		pagination.setPageNo(pageNo);
		pagination.setPageSize(pageSize);
		
		Pagination<BatchSysParameter> lst = batchSysParameterFacade.listPaginationByProperty(pagination, batchSysParameter);
		
		total = lst.getTotal();
		rows = lst.getResultList();
		
		return "listPgBatchSysParameter";
	}
	
	/**
	 * 新增记录
	 * @return
	 * @throws ServiceException
	 */
	public String addBatchSysParameter() throws ServiceException
	{
		batchSysParameterFacade.save(batchSysParameter);
		
		return "addBatchSysParameter";
	}
	
	/**
	 * 根据多条主键值删除记录
	 * @return
	 * @throws ServiceException
	 */
	public String deleteBatchSysParameter() throws ServiceException
	{
		batchSysParameterFacade.deleteByPKeys(primaryKeys);
		
		return "deleteBatchSysParameter";
	}
	
	/**
	 * 修改记录
	 * @return
	 * @throws ServiceException
	 */
	public String updateBatchSysParameter() throws ServiceException
	{
		batchSysParameterFacade.update(batchSysParameter);
		
		return "updateBatchSysParameter";
	}
}
