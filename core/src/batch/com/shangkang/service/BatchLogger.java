package com.shangkang.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.batch.constant.BatchConstant;
import com.shangkang.bo.BatchJob;
import com.shangkang.bo.BatchJobLog;
import com.shangkang.bo.BatchTask;
import com.shangkang.bo.BatchTaskExceptionLog;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.mapper.BatchJobLogMapper;
import com.shangkang.mapper.BatchTaskExceptionLogMapper;
import com.shangkang.mapper.BatchTaskMapper;
import com.shangkang.mapper.SystemDateMapper;
import com.shangkang.tools.DateHelper;
import com.shangkang.tools.UtilHelper;

/**
 * Jan 6, 2010
 * Liuzh
 */
@Service
public class BatchLogger {

	private BatchJobService batchJobService;
	
	private BatchTaskMapper batchTaskMapper;
	
	private BatchTaskExceptionLogMapper batchTaskExceptionLogMapper;
	
	private BatchJobLogMapper batchJobLogMapper;
	
	private SystemDateMapper systemDateMapper;
	
	/**
	 * @param batchJobService the batchJobService to set
	 */
	@Autowired
	public void setBatchJobService(BatchJobService batchJobService)
	{
		this.batchJobService = batchJobService;
	}

	/**
	 * @param batchTaskMapper the batchTaskMapper to set
	 */
	@Autowired
	public void setBatchTaskMapper(BatchTaskMapper batchTaskMapper)
	{
		this.batchTaskMapper = batchTaskMapper;
	}

	/**
	 * @param batchTaskExceptionLogMapper the batchTaskExceptionLogMapper to set
	 */
	@Autowired
	public void setBatchTaskExceptionLogMapper(
			BatchTaskExceptionLogMapper batchTaskExceptionLogMapper)
	{
		this.batchTaskExceptionLogMapper = batchTaskExceptionLogMapper;
	}

	/**
	 * @param batchJobLogMapper the batchJobLogMapper to set
	 */
	@Autowired
	public void setBatchJobLogMapper(BatchJobLogMapper batchJobLogMapper)
	{
		this.batchJobLogMapper = batchJobLogMapper;
	}
	@Autowired
	public void setSystemDateMapper(SystemDateMapper systemDateMapper)
	{
		this.systemDateMapper = systemDateMapper;
	}

	/**
	 * 处理运行时的任务状态和日志	 
	 * @param taskOid	
	 * @throws ServiceException 
	 */
	public void processBatchStatusRun(Long taskOid) throws ServiceException {
		String userId = "system";
		
		BatchTask batchTask = batchTaskMapper.getByPK(taskOid);
		
		batchTask.setExecuteStatus(BatchConstant.TASK_EXECUTE_RUNNING);
		batchTask.setUpdateBy(userId);
		
		batchTaskMapper.update(batchTask);
	}
	
	/**
	 * 处理运行时工作的状态和日志
	 * @param jobOid
	 * @throws ServiceException 
	 */
	public void processStatusRun(Long jobOid) throws ServiceException
	{
		String userId = "system"; 
		
		BatchJob batchJob = batchJobService.getByPK(jobOid);
		
		if(!BatchConstant.JOB_STATUS_RUNNING.equals(batchJob.getJobStatus()))
		{			
			batchJob.setUpdateBy(userId);
			batchJob.setJobStatus(BatchConstant.JOB_STATUS_RUNNING);
			
			batchJobService.update(batchJob);
			
			createBatchJobLog(jobOid, DateHelper.nowTimestamp(), DateHelper.nowTimestamp(), BatchConstant.JOB_STATUS_RUNNING, BatchConstant.JOB_CHANGE_TYPE_STATUS);
		}
	}
	
	/**
	 * 处理工作结束的状态和日志
	 * @param batchJob
	 * @throws ServiceException 
	 */
	public void processStatusFinished() throws ServiceException
	{		
		List<BatchJob> list = batchJobService.listJobByNotFinished();
		
		if(UtilHelper.isEmpty(list)) return;
		
		for(BatchJob job : list)
		{
			long key = job.getJobOid();

			BatchJob batchJob = batchJobService.getByPK(key);
			
			BatchTask successTask = new BatchTask();
			
			successTask.setJobOid(batchJob.getJobOid());
			successTask.setExecuteStatus(BatchConstant.TASK_EXECUTE_SUCCESS);
			
			BatchTask failTask = new BatchTask();
			
			failTask.setJobOid(batchJob.getJobOid());
			failTask.setExecuteStatus(BatchConstant.TASK_EXECUTE_FAILURE);
			
			int successNum = batchTaskMapper.findByCount(successTask);
			int failNum = batchTaskMapper.findByCount(failTask);
			
			batchJob.setTaskSuccess(successNum);
			batchJob.setTaskFail(failNum);
			
			if(batchJob.getTaskTotal().intValue() == successNum + failNum)
			{
				batchJob.setJobFinishedTime(systemDateMapper.getSystemDate());
				batchJob.setJobStatus(BatchConstant.JOB_STATUS_FINISH);
				
				this.createBatchJobLog(batchJob.getJobOid(), DateHelper.nowTimestamp(), DateHelper.nowTimestamp(), BatchConstant.JOB_STATUS_FINISH, BatchConstant.JOB_CHANGE_TYPE_STATUS);
			}
			
			batchJobService.update(batchJob);
		}
	}
	
	/**
	 * 处理任务运行结束状态和日志
	 * @param taskOid
	 * @param isFailed
	 * @param timestamp
	 * @param exception
	 * @throws ServiceException 
	 */
	public void processBatchTaskFinishedLog(Long taskOid, boolean isFailed, Timestamp timestamp, Exception exception) throws ServiceException {
		String userId = "system";
		
		String taskExecuteStatus = BatchConstant.TASK_EXECUTE_SUCCESS;
		
		if(isFailed)
		{
			taskExecuteStatus = BatchConstant.TASK_EXECUTE_FAILURE;
			saveExceptionLog(taskOid, userId, exception);	
		}
		
		BatchTask batchTask = batchTaskMapper.getByPK(taskOid);
		
		batchTask.setExecuteStartTime(DateHelper.formatTime(timestamp));
		batchTask.setExecuteStatus(taskExecuteStatus);
		batchTask.setExecuteTimes(DateHelper.compareDate(DateHelper.nowTimestamp(), timestamp));
		batchTask.setUpdateBy(userId);
		
		batchTaskMapper.update(batchTask);
	}

	/**
	 * 记录任务异常日志
	 * @param taskOid
	 * @param e
	 * @throws ServiceException 
	 */
	private void saveExceptionLog(Long taskOid, String userId, Exception e) throws ServiceException {
		
		StringWriter sw = new StringWriter();					
		PrintWriter pw = new PrintWriter(sw, true);
		String errorMessage = null;
									
		if(e.getCause() != null)
		{		
			errorMessage = e.getCause().getMessage();
			e.getCause().printStackTrace(pw);	
		}
		else
		{
			errorMessage = e.getMessage();
			e.printStackTrace(pw);
		}
		
		BatchTaskExceptionLog exceptionLog = new BatchTaskExceptionLog();
		
		exceptionLog.setTaskOid(taskOid);
		exceptionLog.setExceptionMessage(errorMessage);
		exceptionLog.setExceptionStackMessage(sw.toString());
		exceptionLog.setCreateBy(userId);
		exceptionLog.setUpdateBy(userId);
		
		batchTaskExceptionLogMapper.save(exceptionLog);
		
		e.printStackTrace();
	}
	
	/**
	 * 创建工作日志
	 * @param jobOid 工作Oid
	 * @param executeEndTime 执行结束时间
	 * @param executeStartTime 执行开始时间
	 * @param status 更改状态
	 * @param type 更改类型
	 * @throws ServiceException 
	 */
	public void createBatchJobLog( Long jobOid,
									Timestamp executeEndTime,
									Timestamp executeStartTime,
									String status,
									String type) throws ServiceException
	{
		String userId = "system";
		
		BatchJobLog batchJobLog = new BatchJobLog();
		
		batchJobLog.setJobOid(jobOid);
		batchJobLog.setCreateBy(userId);
		batchJobLog.setExecuteEndTime(DateHelper.formatTime(executeEndTime));
		batchJobLog.setExecuteStartTime(DateHelper.formatTime(executeStartTime));
		batchJobLog.setExecuteTimes(DateHelper.compareDate(executeEndTime, executeStartTime));
		batchJobLog.setStatus(status);
		batchJobLog.setType(type);
		batchJobLog.setUpdateBy(userId);
		batchJobLog.setUserId(userId);
		
		batchJobLogMapper.save(batchJobLog);
	}
}
