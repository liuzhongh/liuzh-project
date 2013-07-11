package com.core.batch.facade.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.batch.facade.BatchLoggerFacade;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.service.BatchLogger;

/**
 * 
 * @author liuzh
 *
 */
@Component("batchLoggerFacade")
public class BatchLoggerFacadeImpl implements BatchLoggerFacade {

	private BatchLogger batchLogger;
	
	@Autowired
	public void setBatchLogger(BatchLogger batchLogger) {
		this.batchLogger = batchLogger;
	}

	public void createBatchJobLog(Long jobOid,
								  Timestamp executeEndTime,
								  Timestamp executeStartTime,
								  String status, String type)throws ServiceException
	{
		batchLogger.createBatchJobLog(jobOid, executeEndTime, executeStartTime, status, type);
	}

	public void executeStatusFinished()throws ServiceException {
		
		batchLogger.processStatusFinished();

	}

	public void processBatchStatusRun(Long taskOid) throws ServiceException{
		batchLogger.processBatchStatusRun(taskOid);

	}

	public void processBatchTaskFinishedLog( Long taskOid, 
										boolean isFailed,
										Timestamp timestamp,
										Exception exception) throws ServiceException
	{
		batchLogger.processBatchTaskFinishedLog(taskOid, isFailed, timestamp, exception);

	}

}
