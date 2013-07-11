package com.shangkang.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.batch.constant.BatchConstant;
import com.core.batch.util.JobUtil;
import com.shangkang.bo.BatchJob;
import com.shangkang.bo.BatchTask;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.dto.BatchTaskDTO;
import com.shangkang.mapper.BatchJobMapper;
import com.shangkang.mapper.BatchSysParameterMapper;
import com.shangkang.mapper.BatchTaskExceptionLogMapper;
import com.shangkang.mapper.BatchTaskMapper;
import com.shangkang.mapper.SystemDateMapper;
import com.shangkang.tools.DateHelper;
import com.shangkang.tools.UtilHelper;

/**
 * 
 * @author Liuzh
 * 
 */
@Service("batchService")
public class BatchService {
	private	Log		logger				= LogFactory.getLog(getClass());
	
	private BatchJobMapper batchJobMapper; 
	
	private BatchLogger batchLogger;
	
	private BatchSysParameterMapper batchParameterMapper;
	
	private BatchTaskMapper batchTaskMapper;
	
	private BatchTaskExceptionLogMapper batchTaskExceptionLogMapper;
	
	private SystemDateMapper systemDateMapper;
	
	/**
	 * @param batchJobMapper the batchJobMapper to set
	 */
	@Autowired
	public void setBatchJobMapper(BatchJobMapper batchJobMapper)
	{
		this.batchJobMapper = batchJobMapper;
	}

	@Autowired
	public void setBatchLogger(BatchLogger batchLogger) {
		this.batchLogger = batchLogger;
	}
	@Autowired
	public void setSystemDateMapper(SystemDateMapper systemDateMapper)
	{
		this.systemDateMapper = systemDateMapper;
	}

	/**
	 * @param batchParameterMapper the batchParameterMapper to set
	 */
	@Autowired
	public void setBatchParameterMapper(BatchSysParameterMapper batchParameterMapper)
	{
		this.batchParameterMapper = batchParameterMapper;
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
	 * 暂停工作
	 * 
	 * @param jobOid
	 * @throws ServiceException
	 */
	public void processSuspendJob(Long jobOid) throws ServiceException {
		BatchJob batchJob = batchJobMapper.getByPK(jobOid);
		
		if (!BatchConstant.JOB_STATUS_RUNNING.equals(batchJob.getJobStatus())) {
			throw new ServiceException("", "只有工作状态为 [运行中] 的工作才可以进行暂停工作操作。");
		}
		Timestamp executeStartTime = DateHelper.nowTimestamp();
		
		BatchJob job = new BatchJob();
		
		job.setJobOid(jobOid);
		job.setJobStatus(BatchConstant.JOB_STATUS_PAUSE);
		
		batchJobMapper.update(job);

		Timestamp executeEndTime = DateHelper.nowTimestamp();
		batchLogger.createBatchJobLog(jobOid, executeEndTime, executeStartTime,
				BatchConstant.JOB_STATUS_PAUSE,
				BatchConstant.JOB_CHANGE_TYPE_STATUS);
	}

	/**
	 * 恢复工作
	 * 
	 * @param jobOid
	 * @throws ServiceException
	 */
	public void processResumeJob(Long jobOid) throws ServiceException {
		BatchJob batchJob = batchJobMapper.getByPK(jobOid);
		
		if (!BatchConstant.JOB_STATUS_PAUSE.equals(batchJob.getJobStatus())) {
			throw new ServiceException("", "只有工作状态为  [暂停]  的工作才可以进行恢复工作操作。");
		}

		Timestamp executeStartTime = DateHelper.nowTimestamp();
		
		BatchJob job = new BatchJob();
		
		job.setJobOid(jobOid);
		job.setJobStatus(BatchConstant.JOB_STATUS_RUNNING);
		
		batchJobMapper.update(job);

		Timestamp executeEndTime = DateHelper.nowTimestamp();
		batchLogger.createBatchJobLog(jobOid, executeEndTime, executeStartTime,
				BatchConstant.JOB_STATUS_RUNNING,
				BatchConstant.JOB_CHANGE_TYPE_STATUS);
	}

	/**
	 * 取消工作
	 * 
	 * @param jobOid
	 * @throws ServiceException
	 */
	public void processCancelJob(Long jobOid) throws ServiceException {
		BatchJob batchJob = batchJobMapper.getByPK(jobOid);
		
		if (!BatchConstant.JOB_STATUS_AWAIT.equals(batchJob.getJobStatus())) {
			throw new ServiceException("", "只有工作状态为 [待处理] 的工作才可以进行取消工作操作。");
		}
		
		Timestamp executeStartTime = DateHelper.nowTimestamp();
		
		BatchJob job = new BatchJob();
		
		job.setJobOid(jobOid);
		job.setJobStatus(BatchConstant.JOB_STATUS_CANCEL);
		
		batchJobMapper.update(job);
		
		Timestamp executeEndTime = DateHelper.nowTimestamp();
		
		batchLogger.createBatchJobLog(jobOid, executeEndTime, executeStartTime,
				BatchConstant.JOB_STATUS_CANCEL,
				BatchConstant.JOB_CHANGE_TYPE_STATUS);
	}


	/**
	 * 改变工作优先级
	 * 
	 * @param jobOid
	 *            工作Oid
	 * @param jobPriority
	 *            工作优先级
	 * @throws ServiceException
	 */
	public void processChangeJobPriority(Long jobOid, String jobPriority)
			throws ServiceException {
		BatchJob batchJob = batchJobMapper.getByPK(jobOid);
		
		if (!BatchConstant.JOB_STATUS_AWAIT.equals(batchJob.getJobStatus())
		  &&!BatchConstant.JOB_STATUS_PAUSE.equals(batchJob.getJobStatus())) {
			throw new ServiceException("", "只有工作状态为 [待处理]或[暂停] 的工作才可以进行更改优先级操作。");
		}

		Timestamp executeStartTime = DateHelper.nowTimestamp();
		
		BatchJob job = new BatchJob();
		
		job.setJobOid(jobOid);
		job.setJobPriority(jobPriority);
		
		batchJobMapper.update(job);

		Timestamp executeEndTime = DateHelper.nowTimestamp();
		batchLogger.createBatchJobLog(jobOid, executeEndTime, executeStartTime,
				jobPriority, BatchConstant.JOB_CHANGE_TYPE_PRIORITY);
	}

	/**
	 * 根据JOB_OID取得所有待处理和暂停的任务
	 * @param batchJobList
	 * @return
	 * @throws ServiceException 
	 */
	private List<BatchTaskDTO> getTasks() throws ServiceException
	{
		String maxThreads = batchParameterMapper.getParameterValueByParameter(BatchConstant.SYS_PARAMETER_MAX_THREADS);
		String taskRunMultiple = batchParameterMapper.getParameterValueByParameter(BatchConstant.SYS_PARAMETER_TASK_RUN_MULTIPLE);
		int taskNumber = Integer.valueOf(maxThreads) * Integer.valueOf(taskRunMultiple);
		
		List<BatchJob> batchJobList = processSortQueue();
		List<BatchTaskDTO> taskList = new ArrayList<BatchTaskDTO>();
		List<String> status = new ArrayList<String>();
		
		status.add(BatchConstant.TASK_EXECUTE_AWAIT);
		status.add(BatchConstant.TASK_EXECUTE_PAUSE);
		
		List<String> notNunStatus = new ArrayList<String>();
		notNunStatus.add(BatchConstant.TASK_EXECUTE_RUNNING);
		
		if(!UtilHelper.isEmpty(batchJobList))
		{
			String executeClass = null;
			String executeMethod = null;
			
			for(BatchJob batchJob :batchJobList)
			{
				Long jobOid = batchJob.getJobOid();
				List<BatchTask> list = new ArrayList<BatchTask>();
				//如果此工作是N次工作。直接取出其剔除运行中的taskList
				if(!BatchConstant.JOB_TYPE_EXE_N_COUNT.equalsIgnoreCase(batchJob.getJobType()))
				{
					list = batchTaskMapper.listBatchTaskByJobOidAndStatus(jobOid, status);
				}else
				{
					list = batchTaskMapper.listBatchTaskByJobOidAndExceptStatus(jobOid,notNunStatus);
				}
				for(BatchTask batchTask : list)
				{
					BatchTaskDTO batchTaskDTO = new BatchTaskDTO();
					
					executeClass = batchTask.getJobExecuteClass();
					executeMethod = batchTask.getJobExecuteMethod();
					
					batchTaskDTO.setJobOid(batchJob.getJobOid());
					batchTaskDTO.setPriority(batchJob.getJobPriority());
					batchTaskDTO.setJobExecuteClass(UtilHelper.isEmpty(executeClass) ? batchJob.getJobExecuteClass() : executeClass);
					batchTaskDTO.setJobExecuteMethod(UtilHelper.isEmpty(executeMethod) ? batchJob.getJobExecuteMethod() : executeMethod);
					
					batchTaskDTO.setCreateBy(batchTask.getCreateBy());
					batchTaskDTO.setCreateDate(batchTask.getCreateDate());
					batchTaskDTO.setExecuteEndTime(batchTask.getExecuteEndTime());
					batchTaskDTO.setExecuteStartTime(batchTask.getExecuteStartTime());
					batchTaskDTO.setExecuteStatus(batchTask.getExecuteStatus());
					batchTaskDTO.setExecuteTimes(batchTask.getExecuteTimes());
					batchTaskDTO.setTaskOid(batchTask.getTaskOid());
					batchTaskDTO.setTaskParameter(batchTask.getTaskParameter());
					batchTaskDTO.setTaskParameterKey(batchTask.getTaskParameterKey());
					
					taskList.add(batchTaskDTO);
				}
				if(taskList.size() > taskNumber)
				{
					taskList = taskList.subList(0, taskNumber);
					break;
				}
			}
		}
		
		return taskList;
	}
	
	/**
	 * 得到任务队列
	 * @return
	 * @throws ServiceException 
	 */
	public List<BatchTaskDTO> processTaskQueue() throws ServiceException
	{
		List<BatchTaskDTO> tasks = this.getTasks();
		
		if(!UtilHelper.isEmpty(tasks))
		{
			for(BatchTaskDTO task : tasks)
			{
				batchLogger.processStatusRun(task.getJobOid());
			}
		}
		
		return tasks;
	}
	
	/**
	 * 队列排序：将工作状态为待处理的工作队列按优先级由高到低排序
	 * 		   再将工作状态为暂停的进行队列按优先级由高到低排序
	 * @return
	 * @throws ServiceException 
	 */
	private List<BatchJob> processSortQueue() throws ServiceException
	{
		List<BatchJob> batchJobListAll = new ArrayList<BatchJob>();
		
		batchLogger.processStatusFinished();
		
		List<String> jobStatusList = new ArrayList<String>();
		
		jobStatusList.add(BatchConstant.JOB_STATUS_RUNNING);
		jobStatusList.add(BatchConstant.JOB_STATUS_AWAIT);
		jobStatusList.add(BatchConstant.JOB_STATUS_PAUSE);
		
		//得到运行和待处理及暂停的工作
		List<BatchJob> batchJobListA = batchJobMapper.listOnceJobByJobPriority(BatchConstant.JOB_TYPE_EXE_E_COUNT,jobStatusList);
		batchJobListA.addAll(batchJobMapper.listJobByJobPriorityAndJobType(BatchConstant.JOB_TYPE_EXE_N_COUNT));
		//查询完毕后 刷选时间符合要求的JOB
		List<BatchJob> batchJobListB = JobUtil.filterInValidJobList(batchJobListA,systemDateMapper.getSystemDate());
		
		if(!UtilHelper.isEmpty(batchJobListB))
		{
			batchJobListAll.addAll(batchJobListB);
		}
		return batchJobListAll;
	}

	public void processReExecute(Long jobOid,List<Long> taskOidList) throws ServiceException
	{
		//将任务对应的工作的状态改成R，
		BatchJob job = new BatchJob();
		
		job.setJobOid(jobOid);
		job.setJobStatus(BatchConstant.JOB_STATUS_RUNNING);
		
		batchJobMapper.update(job);
		
		//失败数减掉所选条数，
		int selectedNum = taskOidList.size();
		BatchJob batchJob = batchJobMapper.getByPK(jobOid);
		int taskFail = batchJob.getTaskFail();
		batchJob.setTaskFail(taskFail-selectedNum);
		batchJobMapper.update(batchJob);
		for(Long taskOid : taskOidList)
		{
			//将任务状态改成待处理，
			BatchTask task = new BatchTask();
			
			task.setTaskOid(taskOid);
			task.setExecuteStatus(BatchConstant.TASK_EXECUTE_AWAIT);
			batchTaskMapper.update(task);
			
			BatchJob btJob = new BatchJob();
			
			btJob.setJobOid(jobOid);
			btJob.setJobStatus(BatchConstant.TASK_EXECUTE_AWAIT);
			
			batchJobMapper.update(job);
			
			//删除任务异常日志
			batchTaskExceptionLogMapper.deleteByPK(taskOid);
		}
	}
}
