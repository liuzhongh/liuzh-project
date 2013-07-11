package com.core.batch.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.batch.facade.BatchFacade;
import com.shangkang.bo.BatchJob;
import com.shangkang.bo.BatchTaskExceptionLog;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.dto.BatchTaskDTO;
import com.shangkang.dto.JobDetailDTO;
import com.shangkang.dto.TaskDetailDTO;
import com.shangkang.service.BatchJobService;
import com.shangkang.service.BatchService;

@Component("batchFacade")
public class BatchFacadeImpl implements BatchFacade {

	private BatchJobService batchJobService;
	private BatchService batchService;
	
	@Autowired
	public void setBatchJobService(BatchJobService batchJobService) {
		this.batchJobService = batchJobService;
	}
	
	@Autowired
	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
	}
	
	public Long insertJobs(JobDetailDTO jobDetailDTO,List<TaskDetailDTO> taskDetailDTOList) throws ServiceException {
		return batchJobService.addJobs(jobDetailDTO, taskDetailDTOList);
	}

	public BatchTaskExceptionLog getExceptionLogByTaskOid(Long taskOid)throws ServiceException {
		return batchJobService.getExceptionLogByTaskOid(taskOid);
	}

	public String getStackMessageByTaskOid(Long taskOid)throws ServiceException {
		return batchJobService.getStackMessageByTaskOid(taskOid);
	}

	public List<BatchJob> listRunJobs()throws ServiceException {
		return batchJobService.listJobsByJobStatus();
	}

	public void executeCancelJob(Long jobOid) throws ServiceException {
		batchService.processCancelJob(jobOid);
		
	}

	public void executeChangeJobPriority(Long jobOid, String jobPriority) throws ServiceException {
		batchService.processChangeJobPriority(jobOid, jobPriority);
		
	}

	public void executeResumeJob(Long jobOid) throws ServiceException {
		batchService.processResumeJob(jobOid);
		
	}

	public void executeSuspendJob(Long jobOid) throws ServiceException {
		batchService.processSuspendJob(jobOid);
	}
	
	/**
	 * 得到任务队列
	 * @return
	 * @throws ServiceException 
	 */
	public List<BatchTaskDTO> executeTaskQueue() throws ServiceException
	{
		return batchService.processTaskQueue();
	}

	/* (non-Javadoc)
	 * @see jade.framework.batch.core.facade.BatchFacade#processReExecute(java.lang.Long, java.lang.Long[])
	 */
	public void executeReExecute(Long jobOid, List<Long> taskOidList)throws ServiceException {
		
		batchService.processReExecute(jobOid, taskOidList);
	}
	
	/**
     * 列出工作状态不是 完成状态且不是取消状态 的工作
     * @param jobStatus 工作状态
     * @return 排序后的工作队列
     */
    public List<BatchJob> listJobByNotFinished() throws ServiceException
    {
    	return batchJobService.listJobByNotFinished();
    }
}
