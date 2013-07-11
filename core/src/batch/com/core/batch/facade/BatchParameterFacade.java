package com.core.batch.facade;

import com.shangkang.core.exception.ServiceException;

/**
 * 2010-01-15
 * liuzh
 */
public interface BatchParameterFacade {


	/**
	 * 获取参数的对应值
	 * @param key
	 * @return
	 */
	public String getParameter(String key)throws ServiceException;
}
