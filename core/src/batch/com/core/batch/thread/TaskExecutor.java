package com.core.batch.thread;

import javax.servlet.ServletContext;

/**
 * Jan 13, 2010
 * Liuzh
 */
public interface TaskExecutor {

	/**
	 * 执行业务逻辑
	 * @param owner
	 * @param methodName
	 * @param taskParameter
	 * @param servletContext 
	 * @return
	 * @throws Exception
	 */
	public Object processBatchBusinessTask(Object owner, String methodName,
			String taskParameter, ServletContext servletContext) throws Exception;

}