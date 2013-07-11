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
package com.shangkang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangkang.bo.BatchSysParameter;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.mapper.BatchSysParameterMapper;

@Service("batchSysParameterService")
public class BatchSysParameterService {

	private BatchSysParameterMapper	batchSysParameterMapper;

	@Autowired
	public void setBatchSysParameterMapper(BatchSysParameterMapper batchSysParameterMapper)
	{
		this.batchSysParameterMapper = batchSysParameterMapper;
	}
	
	/**
	 * 通过主键查询实体对象
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public BatchSysParameter getByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return batchSysParameterMapper.getByPK(primaryKey);
	}

	/**
	 * 查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<BatchSysParameter> list() throws ServiceException
	{
		return batchSysParameterMapper.list();
	}

	/**
	 * 根据查询条件查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<BatchSysParameter> listByProperty(BatchSysParameter batchSysParameter)
			throws ServiceException
	{
		return batchSysParameterMapper.listByProperty(batchSysParameter);
	}
	
	/**
	 * 根据查询条件查询分页记录
	 * @return
	 * @throws ServiceException
	 */
	public Pagination<BatchSysParameter> listPaginationByProperty(Pagination<BatchSysParameter> pagination, BatchSysParameter batchSysParameter)
			throws ServiceException
	{
		List<BatchSysParameter> list = batchSysParameterMapper.listPaginationByProperty(pagination, batchSysParameter);
		
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
		return batchSysParameterMapper.deleteByPK(primaryKey);
	}
	
	/**
	 * 根据多个主键删除记录
	 * @param primaryKeys
	 * @throws ServiceException
	 */
	public void deleteByPKeys(List<java.lang.Long> primaryKeys) throws ServiceException
	{
		batchSysParameterMapper.deleteByPKeys(primaryKeys);
	}
	
	/**
	 * 根据传入参数删除记录
	 * @param batchSysParameter
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByProperty(BatchSysParameter batchSysParameter) throws ServiceException
	{
		return batchSysParameterMapper.deleteByProperty(batchSysParameter);
	}

	/**
	 * 保存记录
	 * @param batchSysParameter
	 * @return
	 * @throws ServiceException
	 */
	public void save(BatchSysParameter batchSysParameter) throws ServiceException
	{
		batchSysParameterMapper.save(batchSysParameter);
	}

	/**
	 * 更新记录
	 * @param batchSysParameter
	 * @return
	 * @throws ServiceException
	 */
	public int update(BatchSysParameter batchSysParameter) throws ServiceException
	{
		return batchSysParameterMapper.update(batchSysParameter);
	}

	/**
	 * 根据条件查询记录条数
	 * @param batchSysParameter
	 * @return
	 * @throws ServiceException
	 */
	public int findByCount(BatchSysParameter batchSysParameter) throws ServiceException
	{
		return batchSysParameterMapper.findByCount(batchSysParameter);
	}
	
	public String getParameterValueByParameter(String parameter)
	{
		return batchSysParameterMapper.getParameterValueByParameter(parameter);
	}
}