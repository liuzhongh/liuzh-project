/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-3-23 14:34:25
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.batch.constant.BatchConstant;
import com.core.batch.support.ParameterProcess;
import com.core.batch.util.JobUtil;
import com.shangkang.bo.BatchJob;
import com.shangkang.bo.BatchTask;
import com.shangkang.bo.BatchTaskExceptionLog;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.dto.JobDetailDTO;
import com.shangkang.dto.TaskDetailDTO;
import com.shangkang.mapper.BatchJobMapper;
import com.shangkang.mapper.BatchTaskExceptionLogMapper;
import com.shangkang.mapper.BatchTaskMapper;
import com.shangkang.mapper.SystemDateMapper;
import com.shangkang.tools.DateHelper;
import com.shangkang.tools.UtilHelper;

@Service("batchJobService")
public class BatchJobService {

	private BatchJobMapper				batchJobMapper;

	private BatchTaskMapper				batchTaskMapper;

	private BatchTaskExceptionLogMapper	batchTaskExceptionLogMapper;
	
	private SystemDateMapper			systemDateMapper;

	@Autowired
	public void setSystemDateMapper(SystemDateMapper systemDateMapper)
	{
		this.systemDateMapper = systemDateMapper;
	}

	@Autowired
	public void setBatchJobMapper(BatchJobMapper batchJobMapper)
	{
		this.batchJobMapper = batchJobMapper;
	}

	/**
	 * @param batchTaskMapper
	 *            the batchTaskMapper to set
	 */
	@Autowired
	public void setBatchTaskMapper(BatchTaskMapper batchTaskMapper)
	{
		this.batchTaskMapper = batchTaskMapper;
	}

	/**
	 * @param batchTaskExceptionLogMapper
	 *            the batchTaskExceptionLogMapper to set
	 */
	@Autowired
	public void setBatchTaskExceptionLogMapper(
			BatchTaskExceptionLogMapper batchTaskExceptionLogMapper)
	{
		this.batchTaskExceptionLogMapper = batchTaskExceptionLogMapper;
	}

	/**
	 * 通过主键查询实体对象
	 * 
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public BatchJob getByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return batchJobMapper.getByPK(primaryKey);
	}

	/**
	 * 查询所有记录
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<BatchJob> list() throws ServiceException
	{
		return batchJobMapper.list();
	}

	/**
	 * 根据查询条件查询所有记录
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<BatchJob> listByProperty(BatchJob batchJob)
			throws ServiceException
	{
		return batchJobMapper.listByProperty(batchJob);
	}

	/**
	 * 根据查询条件查询分页记录
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public Pagination<BatchJob> listPaginationByProperty(
			Pagination<BatchJob> pagination, BatchJob batchJob)
			throws ServiceException
	{
		List<BatchJob> list = batchJobMapper.listPaginationByProperty(
				pagination, batchJob);

		pagination.setResultList(list);

		return pagination;
	}

	/**
	 * 根据主键删除记录
	 * 
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return batchJobMapper.deleteByPK(primaryKey);
	}

	/**
	 * 根据多个主键删除记录
	 * 
	 * @param primaryKeys
	 * @throws ServiceException
	 */
	public void deleteByPKeys(List<java.lang.Long> primaryKeys)
			throws ServiceException
	{
		batchJobMapper.deleteByPKeys(primaryKeys);
	}

	/**
	 * 根据传入参数删除记录
	 * 
	 * @param batchJob
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByProperty(BatchJob batchJob) throws ServiceException
	{
		return batchJobMapper.deleteByProperty(batchJob);
	}

	/**
	 * 保存记录
	 * 
	 * @param batchJob
	 * @return
	 * @throws ServiceException
	 */
	public void save(BatchJob batchJob) throws ServiceException
	{
		batchJobMapper.save(batchJob);
	}

	/**
	 * 添加工作和任务
	 * 
	 * @param jobDetailDTO
	 * @param taskDetailDTOList
	 * @return
	 * @throws ServiceException
	 */
	public Long addJobs(JobDetailDTO jobDetailDTO,
			List<TaskDetailDTO> taskDetailDTOList) throws ServiceException
	{
		BatchJob batchJob = new BatchJob();

		String executeMethod = jobDetailDTO.getJobExecuteMethod();
		String executeClass = jobDetailDTO.getJobExecuteClass();
		batchJob.setJobExecuteMethod(executeMethod);
		batchJob.setJobExecuteClass(executeClass);
		String planExecuteTime = jobDetailDTO.getPlanExecuteTime();
		//如果添加的执行N次的任务
		if(BatchConstant.JOB_TYPE_EXE_N_COUNT.equalsIgnoreCase(jobDetailDTO.getJobType()))
		{
			batchJob.setJobType(BatchConstant.JOB_TYPE_EXE_N_COUNT);
			if(UtilHelper.isEmpty(planExecuteTime))
			{
				return null;
			}
			batchJob.setPlanExecuteTime(planExecuteTime);
			//如果已经有此执行N次的工作则不加入
			List<BatchJob>  batchJobList = batchJobMapper.listByProperty(batchJob);
			if(!UtilHelper.isEmpty(batchJobList))
			{
				return null;
			}
		}
		
		if(UtilHelper.isEmpty(jobDetailDTO.getJobType()))
		{
			batchJob.setJobType(BatchConstant.JOB_TYPE_EXE_E_COUNT);
		}
		
		if(UtilHelper.isEmpty(planExecuteTime))
		{
			planExecuteTime = JobUtil.getCronExpressionByDate(DateHelper.parseTime(systemDateMapper.getSystemDate()));
		}
		
		String userId = jobDetailDTO.getUserId();

		batchJob.setUserId(userId);
		batchJob.setUpdateBy(userId);
		batchJob.setThreadAssignNum(jobDetailDTO.getThreadAssignNum());
		batchJob.setTaskTotal(taskDetailDTOList.size());
		batchJob.setJobStatus(BatchConstant.JOB_STATUS_AWAIT);
		batchJob.setJobPriority(jobDetailDTO.getJobPriority());
		batchJob.setJobName(jobDetailDTO.getJobName());
		batchJob.setJobModule(jobDetailDTO.getJobModule());
		batchJob.setPlanExecuteTime(planExecuteTime);
		batchJob.setDescription(jobDetailDTO.getDescription());
		batchJob.setCreateBy(userId);

		batchJobMapper.save(batchJob);

		Long jobOid = batchJob.getJobOid();

		for (TaskDetailDTO taskDetailDTO : taskDetailDTOList)
		{
			BatchTask batchTask = new BatchTask();

			batchTask.setJobOid(jobOid);
			batchTask.setCreateBy(userId);
			batchTask.setExecuteStatus(BatchConstant.TASK_EXECUTE_AWAIT);
			batchTask.setTaskParameter(ParameterProcess.object2String(
					taskDetailDTO.getExecuteMethodParameter()));
			batchTask.setTaskParameterKey(taskDetailDTO.getKey());
			batchTask.setJobExecuteClass(taskDetailDTO.getJobExecuteClass());
			batchTask.setJobExecuteMethod(taskDetailDTO.getJobExecuteMethod());
			batchTask.setUpdateBy(userId);

			batchTaskMapper.save(batchTask);
		}

		return jobOid;
	}

	public BatchTaskExceptionLog getExceptionLogByTaskOid(Long taskOid)
			throws ServiceException
	{
		BatchTaskExceptionLog log = new BatchTaskExceptionLog();

		log.setTaskOid(taskOid);

		return batchTaskExceptionLogMapper.listByProperty(log).get(0);
	}

	public String getStackMessageByTaskOid(Long taskOid)
			throws ServiceException
	{
		return getExceptionLogByTaskOid(taskOid).getExceptionStackMessage();
	}

	public List<BatchJob> listJobsByJobStatus() throws ServiceException
	{
		BatchJob job = new BatchJob();

		job.setJobStatus(BatchConstant.JOB_STATUS_RUNNING);

		return batchJobMapper.listByProperty(job);
	}

	/**
	 * 更新记录
	 * 
	 * @param batchJob
	 * @return
	 * @throws ServiceException
	 */
	public int update(BatchJob batchJob) throws ServiceException
	{
		return batchJobMapper.update(batchJob);
	}

	/**
	 * 根据条件查询记录条数
	 * 
	 * @param batchJob
	 * @return
	 * @throws ServiceException
	 */
	public int findByCount(BatchJob batchJob) throws ServiceException
	{
		return batchJobMapper.findByCount(batchJob);
	}
	
	/**
     * 列出工作状态不是 完成状态且不是取消状态 的工作
     * @param jobStatus 工作状态
     * @return 排序后的工作队列
     */
    public List<BatchJob> listJobByNotFinished()throws ServiceException
    {
    	List<String> status = new ArrayList<String>();
		
		status.add(BatchConstant.JOB_STATUS_FINISH);
		status.add(BatchConstant.JOB_STATUS_CANCEL);
		
	    return batchJobMapper.listJobByNotFinished(status);
    }
}