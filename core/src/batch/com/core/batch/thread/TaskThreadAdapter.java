package com.core.batch.thread;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.batch.constant.BatchConstant;
import com.core.batch.job.task.TaskQueue;
import com.core.batch.manager.ThreadPoolManager;
import com.core.batch.support.BatchParameterHelper;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.core.util.SpringBeanHelper;
import com.shangkang.dto.BatchTaskDTO;
import com.shangkang.tools.UtilHelper;

/**
 * Dec 22, 2009 Liuzh
 */
@Component("taskThreadAdapter")
public class TaskThreadAdapter {

	private TaskQueue taskQueue;
	private ServletContext servletContext;
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	public void setTaskQueue(TaskQueue taskQueue) {
		this.taskQueue = taskQueue;
	}

	/**
	 * 往任务队列中添加任务
	 * @param batchJobList
	 * @throws ServiceException 
	 */
	private void doAddQueue() throws ServiceException
	{
		List<BatchTaskDTO> tasks = taskQueue.processTaskQueue();

		this.doAddQueue(tasks);
	}
	
	/**
	 * 往任务队列中添加任务
	 * @param batchJobList
	 */
	private void doAddQueue(List<BatchTaskDTO> tasks)
	{
		if (UtilHelper.isEmpty(tasks))
			return;

		for (BatchTaskDTO task : tasks) {
			TaskThreadExecutor taskThreadExecutor = (TaskThreadExecutor) SpringBeanHelper
					.getBean("taskThreadExecutor");

			taskThreadExecutor.setServletContext(servletContext);
			taskThreadExecutor.setJobOid(task.getJobOid());
			taskThreadExecutor.setPriority(task.getPriority());
			taskThreadExecutor.setTaskOid(task.getTaskOid());
			taskThreadExecutor.setExecuteClassName(task.getJobExecuteClass());
			taskThreadExecutor.setExecuteMethod(task.getJobExecuteMethod());
			taskThreadExecutor.setTaskParameter(task.getTaskParameter());

			ThreadPoolManager.getInstance().addTask(taskThreadExecutor);
		}
	}
	
	/**
	 * 按周期轮循线程池
	 * @param servletContext 
	 * @throws ServiceException 
	 * 
	 */
	public void processThreadTaskCycle(ServletContext servletContext) throws ServiceException
	{
		String cycle = BatchParameterHelper.getParameter(BatchConstant.SYS_PARAMETER_THREAD_CYCLE_TIME);
		
		long cycleTime = Long.valueOf(cycle);
		
		this.startThreadPool();//启动线程池
		
		int counter = 0;
		
		this.servletContext = servletContext;
		
		for(;;)
		{			
			try {
				log.debug("当前线程轮循周期为：" + cycleTime);
				Thread.sleep(cycleTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if(ThreadPoolManager.getInstance().isEmpty())
			{
				List<BatchTaskDTO> tasks = taskQueue.processTaskQueue();
				
				if(UtilHelper.isEmpty(tasks))
					counter ++;
				else
				{
					this.doAddQueue(tasks);
					counter = 0;
				}
				
				cycleTime = Long.valueOf(cycle) * ( (int) Math.ceil((double)(counter / 5)) + 1);
			}
		}
	}

	/**
	 * 启动线程池
	 * @param batchJobList
	 * @throws ServiceException 
	 */
	private void startThreadPool() throws ServiceException {					
		ThreadPoolManager.getInstance(BatchParameterHelper.getMaxThreads());//初始化线程池
		
		doAddQueue();//添加任务
	}
	
}
