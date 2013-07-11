package com.core.batch.manager;

import java.util.List;

import com.core.batch.constant.BatchConstant;
import com.core.batch.facade.BatchFacade;
import com.core.batch.support.BatchParameterHelper;
import com.shangkang.bo.BatchJob;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.core.util.SpringBeanHelper;
import com.shangkang.tools.UtilHelper;

/**
 * Jan 22, 2010
 * Liuzh
 */
public class CycleTimeManger {

	private static CycleTimeManger cycleTimeManger;
	
	private Long cycleTime;
	
	public static CycleTimeManger getInstance()
	{
		if(cycleTimeManger == null)
			cycleTimeManger = new CycleTimeManger();
		
		return cycleTimeManger;
	}
	
	public void startCycleTimeServer() throws ServiceException
	{
		CycleTimeThread cycleTimeThread = new CycleTimeThread();
		
		cycleTimeThread.start();
	}
	
	private class CycleTimeThread extends Thread
	{
		BatchFacade batchFacade = (BatchFacade) SpringBeanHelper.getBean("batchFacade");
		
		public void run()
		{
			try {
				String cycle = BatchParameterHelper.getParameter(BatchConstant.SYS_PARAMETER_THREAD_CYCLE_TIME);
				
				Long cycleTime = Long.valueOf(cycle);
				int counter = 0;
				
				for(; ;)
				{
					Thread.sleep(cycleTime);
					
					List<BatchJob> list = batchFacade.listJobByNotFinished();
					
					if(UtilHelper.isEmpty(list))
						counter ++;
					else 
						counter = 0;
					
					cycleTime = Long.valueOf(cycle) * ( (int) Math.ceil((double)(counter / 5)) + 1);
					
					CycleTimeManger.getInstance().setCycleTime(cycleTime);
				}
				
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Long getCycleTime() {
		return cycleTime;
	}

	private void setCycleTime(Long cycleTime) {
		this.cycleTime = cycleTime;
	}
}
