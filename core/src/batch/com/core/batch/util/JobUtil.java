package com.core.batch.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;

import com.core.batch.constant.BatchConstant;
import com.shangkang.bo.BatchJob;
import com.shangkang.tools.DateHelper;
import com.shangkang.tools.UtilHelper;

public class JobUtil {

	private static Logger log = Logger.getLogger(JobUtil.class);
	
	/**
	 * 过滤掉计划执行时间不符合要求的工作
	 * @param batchJobList
	 * @return
	 */
	public static List<BatchJob> filterInValidJobList(List<BatchJob> batchJobList,String systemDate)
	{
		if (UtilHelper.isEmpty(batchJobList))
		{
			return null;
		}
		String planTime = null;
		Date now = DateHelper.parseTime(systemDate);
		log.debug("now time : " + now);
		long nowMillils = now.getTime();
		
		CronExpression cronExpression = null;
		Long jobOId = null;
		Date afterNowMatch = null;
		Date afterFinishMatch = null;
		
		List<BatchJob> batchJobAll = new ArrayList<BatchJob>();
		for (BatchJob batchJob : batchJobList)
		{
			String jobType = batchJob.getJobType();
			jobOId = batchJob.getJobOid();
			planTime = batchJob.getPlanExecuteTime();
			if (UtilHelper.isEmpty(planTime))
			{
				log.error("job:	" + jobOId + " 's plan execute time cron expression is empty!");
				continue;
			}
			planTime = planTime.trim();
			try
			{
				cronExpression = new CronExpression(planTime);
			} catch (ParseException e)
			{
				log.error("job:	" + jobOId + " 's plan execute time cron expression is invalid! detail info :	" + e.getMessage());
				continue;
			}
			String finishTime = batchJob.getJobFinishedTime();
			
			log.debug("job:	" + jobOId + "'s plan execute time cron expression : " + planTime);
			log.debug("job:	" + jobOId + "'s finishTime : " + finishTime);
			
			if (!UtilHelper.isEmpty(finishTime))
			{
				afterFinishMatch = cronExpression.getTimeAfter(DateHelper.parseTime(finishTime));
			}else
			{
				afterFinishMatch = null;
			}
			afterNowMatch = cronExpression.getTimeAfter(now);
			
			log.debug("job:	" + jobOId + "'s afterFinishMatch : " + afterFinishMatch);
			log.debug("job:	" + jobOId + "'s afterNowMatch : " + afterNowMatch);
			if (cronExpression.isSatisfiedBy(new Date()))         //如果当前时间满足 计划执行时间Cron表达式
			{
				batchJobAll.add(batchJob);
			} else if (UtilHelper.isEmpty(finishTime) 
					&& BatchConstant.JOB_TYPE_EXE_N_COUNT.equalsIgnoreCase(jobType))
			{
				batchJobAll.add(batchJob);
			} else if (BatchConstant.JOB_TYPE_EXE_N_COUNT.equalsIgnoreCase(jobType) 
					&& afterFinishMatch != null 
					&& nowMillils >= afterFinishMatch.getTime()) 
			{
				batchJobAll.add(batchJob);
			}else if(BatchConstant.JOB_TYPE_EXE_E_COUNT.equalsIgnoreCase(jobType)
					&& UtilHelper.isEmpty(afterNowMatch))
			{
				batchJobAll.add(batchJob);
			}
		}
		return batchJobAll;
	}
	
	/**
	 * 获取某个日期 所对应的 Cron Expression 
	 * @param date
	 * @return
	 */
	public static String getCronExpressionByDate(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);

		StringBuffer sb = new StringBuffer();
		sb.append(cal.get(Calendar.SECOND) + " ")
				.append(cal.get(Calendar.MINUTE) + " ")
				.append(cal.get(Calendar.HOUR_OF_DAY) + " ")
				.append(cal.get(Calendar.DAY_OF_MONTH) + " ")
				.append(cal.get(Calendar.MONTH)+1 + " ").append("? ").append(cal.get(Calendar.YEAR));
		/*
		 * 
		 *1. Seconds 秒 
		 *2. Minutes 分钟
		 *3. Hours 小时 
		 *4. Day-of-Month 月中的天
		 *5. Month月 
		 *6. Day-of-Week 周中的天 
		 *7. Year (optional field) 年（可选的域）
		 */
		return sb.toString();
	}
}
