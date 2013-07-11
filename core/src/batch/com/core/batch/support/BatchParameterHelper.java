/**
 * 
 */
package com.core.batch.support;

import com.core.batch.constant.BatchConstant;
import com.core.batch.facade.BatchParameterFacade;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.core.util.SpringBeanHelper;

/**
 * @author Liuzh
 * 
 */
public class BatchParameterHelper {
	private static BatchParameterFacade	facade	= (BatchParameterFacade) SpringBeanHelper
														.getBean("batchParameterFacade");

	public static String getParameter(String key) throws ServiceException
	{
		return facade.getParameter(key);
	}

	/**
	 * 得到最大线程数
	 * 
	 * @return
	 */
	public static int getMaxThreads() throws ServiceException
	{
		String maxThreads = getParameter(BatchConstant.SYS_PARAMETER_MAX_THREADS);
		return Integer.valueOf(maxThreads);
	}

	/**
	 * 得到缺省工作线程分配数
	 * 
	 * @return
	 */
	public static int getDefaultThreadAssignNum() throws ServiceException
	{
		String defaultThreadAssignNum = getParameter(BatchConstant.SYS_PARAMETER_THREAD_ASSIGN_NUM);
		return Integer.valueOf(defaultThreadAssignNum);
	}
}
