package com.core.batch.facade;

import java.util.List;

import com.shangkang.bo.BatchJob;
import com.shangkang.bo.BatchTaskExceptionLog;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.dto.BatchTaskDTO;
import com.shangkang.dto.JobDetailDTO;
import com.shangkang.dto.TaskDetailDTO;

/**
 * 
 * @author liuzh
 *
 */
public interface BatchFacade {

	/**
	 * 添加工作和任务
	 * @param jobDetailDTO
	 * @param taskDetailDTOList
	 * @return
	 */
	public Long insertJobs(JobDetailDTO jobDetailDTO, List<TaskDetailDTO> taskDetailDTOList)throws ServiceException;
	
	/**
	 * 取得所有运行的工作
	 * 
	 * @return
	 */
	public List<BatchJob> listRunJobs()throws ServiceException;
	
	/**
	 * 获取异常日志
	 * @param taskOid
	 * @return
	 */
	public BatchTaskExceptionLog getExceptionLogByTaskOid(Long taskOid)throws ServiceException;

	/**
	 * 获取异常堆栈信息
	 * @param taskOid
	 * @return
	 */
	public String getStackMessageByTaskOid(Long taskOid)throws ServiceException;
	
	/**
	 * 暂停工作
	 * 
	 * @param jobOid
	 * @throws ServiceException
	 */
	public void executeSuspendJob(Long jobOid) throws ServiceException;

	/**
	 * 恢复工作
	 * 
	 * @param jobOid
	 * @throws ServiceException
	 */
	public void executeResumeJob(Long jobOid) throws ServiceException;

	/**
	 * 取消工作
	 * 
	 * @param jobOid
	 * @throws ServiceException
	 */
	public void executeCancelJob(Long jobOid) throws ServiceException;


	/**
	 * 改变工作优先级
	 * 
	 * @param jobOid
	 *            工作Oid
	 * @param jobPriority
	 *            工作优先级
	 * @throws ServiceException
	 */
	public void executeChangeJobPriority(Long jobOid, String jobPriority)throws ServiceException;
	
	/**
	 * 得到任务队列
	 * @return
	 * @throws ServiceException 
	 */
	public List<BatchTaskDTO> executeTaskQueue() throws ServiceException;
	
	/**
	 * 重新执行失败的任务
	 * @param jobOid
	 * @param taskOidList
	 */
	public void executeReExecute(Long jobOid,List<Long> taskOidList)throws ServiceException;
	
	/**
     * 列出工作状态不是 完成状态且不是取消状态 的工作
     * @param jobStatus 工作状态
     * @return 排序后的工作队列
     */
    public List<BatchJob> listJobByNotFinished() throws ServiceException;
}
