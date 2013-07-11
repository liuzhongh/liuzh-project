package com.core.batch.job.job;

import com.core.batch.facade.BatchFacade;
import com.shangkang.core.exception.ServiceException;

/**
 * 工作监控器
 * 
 * @author liuzh
 * 
 */
public class JobStateMonitor {
	
	private BatchFacade batchFacade;

	public void setBatchFacade(BatchFacade batchFacade) {
		this.batchFacade = batchFacade;
	}

	/**
	 * 暂停工作
	 * 
	 * @param jobOid
	 * @throws ServiceException
	 */
	public void processSuspendJob(Long jobOid) throws ServiceException {
		batchFacade.executeSuspendJob(jobOid);
	}

	/**
	 * 恢复工作
	 * 
	 * @param jobOid
	 * @throws ServiceException
	 */
	public void processResumeJob(Long jobOid) throws ServiceException {
		batchFacade.executeResumeJob(jobOid);
	}

	/**
	 * 取消工作
	 * 
	 * @param jobOid
	 * @throws ServiceException
	 */
	public void processCancelJob(Long jobOid) throws ServiceException {
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
	public void processChangeJobPriority(Long jobOid, String jobPriority)
			throws ServiceException {
		batchFacade.executeChangeJobPriority(jobOid, jobPriority);
	}

}
