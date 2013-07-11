/**
 * 提供对外接口，供业务调用查询
 */
package com.core.batch;

import java.util.List;

import com.core.batch.facade.BatchFacade;
import com.shangkang.bo.BatchTaskExceptionLog;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.core.util.SpringBeanHelper;
import com.shangkang.dto.JobDetailDTO;
import com.shangkang.dto.TaskDetailDTO;

/**
 * 
 * @author liuzh
 * 
 */
public class BatchJobAware {
	private static BatchFacade	batchFacade	= (BatchFacade) SpringBeanHelper
													.getBean("batchFacade");

	/**
	 * 添加工作  如果添加N次执行的工作已存在,将不会添加
	 * @param jobDetailDTO   
	 * 	如果jobDetailDTO中jobType没有设置将默认为E(执行一次的任务)
	 * 	如果jobDetailDTO中jobType不等于N planExecuteTime没有设置 将默认设置planExecuteTime为当前时间的CronExpression
	 * 	如果jobDetailDTO中jobType等于N planExecuteTime没有设置 此工作将不会添加
	 *  
	 * @param taskDetailDTOList
	 * @throws ServiceException
	 */
	public static void addJob(JobDetailDTO jobDetailDTO,
			List<TaskDetailDTO> taskDetailDTOList) throws ServiceException
	{
		batchFacade.insertJobs(jobDetailDTO, taskDetailDTOList);
	}

	/**
	 * 暂停工作
	 * 
	 * @param jobOid
	 * @throws ServiceException
	 */
	public static void suspendJob(Long jobOid) throws ServiceException
	{
		batchFacade.executeSuspendJob(jobOid);
	}

	/**
	 * 恢复工作
	 * 
	 * @param jobOid
	 * @throws ServiceException
	 */
	public static void resumeJob(Long jobOid) throws ServiceException
	{
		batchFacade.executeResumeJob(jobOid);
	}

	/**
	 * 取消工作
	 * 
	 * @param jobOid
	 * @throws ServiceException
	 */
	public static void cancelJob(Long jobOid) throws ServiceException
	{
		batchFacade.executeCancelJob(jobOid);
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
	public static void changeJobPriority(Long jobOid, String jobPriority)
			throws ServiceException
	{
		batchFacade.executeChangeJobPriority(jobOid, jobPriority);
	}

	/**
	 * 获取异常信息
	 * 
	 * @param taskOid
	 * @return
	 * @throws ServiceException
	 */
	public static String getTaskErrorMessages(Long taskOid)
			throws ServiceException
	{
		BatchTaskExceptionLog exceptionLog = batchFacade
				.getExceptionLogByTaskOid(taskOid);
		if (null == exceptionLog)
		{
			return null;
		}
		return exceptionLog.getExceptionMessage();
	}

	/**
	 * 获取异常堆栈信息
	 * 
	 * @param taskOid
	 * @return
	 * @throws ServiceException
	 */
	public static String getTaskExceptionStrace(Long taskOid)
			throws ServiceException
	{
		return batchFacade.getStackMessageByTaskOid(taskOid);
	}

}
