/**
 * 1、创建工作任务池
 * 2、
 */
package com.core.batch.manager;

import javax.servlet.ServletContext;

import com.core.batch.thread.TaskThreadAdapter;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.core.util.SpringBeanHelper;

/**
 * @author liuzh
 *
 */
public class BatchManager 
{
	public static void start(ServletContext servletContext) throws ServiceException
	{
		BatchManager BatchManager = new BatchManager();
		
		BatchManager.startServer(servletContext);
	}
	
	public void startServer(ServletContext servletContext) throws ServiceException
	{
		BatchManagerThread batchManagerThread = new BatchManagerThread(servletContext);
		
		batchManagerThread.start();
	}
	
	private class BatchManagerThread extends Thread
	{		
		private ServletContext servletContext;
		
		public BatchManagerThread(ServletContext servletContext)
		{
			this.servletContext = servletContext;
		}

		public void run()
		{
			TaskThreadAdapter taskThreadAdapter = (TaskThreadAdapter) SpringBeanHelper.getBean("taskThreadAdapter");
			
			try {
				taskThreadAdapter.processThreadTaskCycle(servletContext);
			} catch (ServiceException e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
