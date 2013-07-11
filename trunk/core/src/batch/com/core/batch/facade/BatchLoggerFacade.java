package com.core.batch.facade;

import java.sql.Timestamp;

import com.shangkang.core.exception.ServiceException;

/**
 * 
 * @author Liuzh
 *
 */
public interface BatchLoggerFacade {

	/**
	 * 处理运行时的状态和日志
	 * @param taskOid	
	 * @throws ServiceException 
	 */
	public void processBatchStatusRun(Long taskOid) throws ServiceException;
	
	/**
	 * 处理工作结束的状态和日志
	 * @param batchJob
	 */
	public void executeStatusFinished()throws ServiceException;
	
	/**
	 * 处理任务运行结束状态和日志
	 * @param taskOid
	 * @param isFailed
	 * @param timestamp
	 * @param exception
	 * @throws ServiceException 
	 */
	public void processBatchTaskFinishedLog(Long taskOid, boolean isFailed, Timestamp timestamp, Exception exception) throws ServiceException;

	/**
	 * 创建工作日志
	 * @param jobOid 工作Oid
	 * @param executeEndTime 执行结束时间
	 * @param executeStartTime 执行开始时间
	 * @param status 更改状态
	 * @param type 更改类型
	 */
	public void createBatchJobLog(Long jobOid,
								  Timestamp executeEndTime,
								  Timestamp executeStartTime,
								  String status,
								  String type)throws ServiceException;
}
