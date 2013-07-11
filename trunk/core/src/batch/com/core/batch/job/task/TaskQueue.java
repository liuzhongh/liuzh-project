package com.core.batch.job.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.batch.facade.BatchFacade;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.dto.BatchTaskDTO;

/**
 * Dec 24, 2009
 * Liuzh
 */
@Component("taskQueue")
public class TaskQueue {

	private BatchFacade batchFacade;

	@Autowired
	public void setBatchFacade(BatchFacade batchFacade) {
		this.batchFacade = batchFacade;
	}
	/**
	 * 得到任务队列
	 * @return
	 * @throws ServiceException 
	 */
	public List<BatchTaskDTO> processTaskQueue() throws ServiceException
	{
		return batchFacade.executeTaskQueue();
	}
}
