package com.core.batch.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.batch.facade.BatchParameterFacade;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.service.BatchSysParameterService;

/**
 * 2010-01-15
 * Liuzh
 */
@Component("batchParameterFacade")
public class BatchParameterFacadeImpl implements BatchParameterFacade{

	private BatchSysParameterService batchParameterService;
	
	@Autowired
	public void setBatchParameterService(BatchSysParameterService batchParameterService) {
		this.batchParameterService = batchParameterService;
	}

	/**
	 * 获取参数的对应值
	 * @param key
	 * @return
	 */
	public String getParameter(String key)throws ServiceException
	{
		return batchParameterService.getParameterValueByParameter(key);
	}
}
