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
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class BatchJob extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	工作索引
	  */
	private java.lang.Long jobOid;

	/**
	  *	工作名称
	  */
	private java.lang.String jobName;

	/**
	  *	工作模块
	  */
	private java.lang.String jobModule;

	/**
	  *	工作请求人ID
	  */
	private java.lang.String userId;

	/**
	  *	6：待处理的工作
4：暂停的工作
8：运行中的工作
0：取消执行的工作
1：已执行完成的工作
	  */
	private java.lang.String jobStatus;

	/**
	  *	9：紧急的
8：高优先级
7：普通的
6：低优先级
	  */
	private java.lang.String jobPriority;

	/**
	  *	工作执行类
	  */
	private java.lang.String jobExecuteClass;

	/**
	  *	工作执行方法
	  */
	private java.lang.String jobExecuteMethod;

	/**
	  *	线程分配数
	  */
	private java.lang.Integer threadAssignNum;

	/**
	  *	任务总数
	  */
	private java.lang.Integer taskTotal;

	/**
	  *	任务成功数
	  */
	private java.lang.Integer taskSuccess;

	/**
	  *	任务失败数
	  */
	private java.lang.Integer taskFail;

	/**
	  *	工作添加时间
	  */
	private java.lang.String jobAddTime;
	
	/**
	  *	计划执行时间
	  */
	private java.lang.String planExecuteTime;

	/**
	  *	工作完成时间
	  */
	private java.lang.String jobFinishedTime;

	/**
	  *	描述
	  */
	private java.lang.String description;

	/**
	  *	创建人
	  */
	private java.lang.String createBy;

	/**
	  *	创建时间
	  */
	private java.lang.String createDate;

	/**
	  *	修改人
	  */
	private java.lang.String updateBy;

	/**
	  *	修改时间
	  */
	private java.lang.String updateDate;

	/**
	 * 工作类型
	 */
	private java.lang.String jobType;
	
	/**
	  *	工作索引
	  */
	public java.lang.Long getJobOid() 
	{
		return jobOid;
	}
	
	/**
	  *	工作索引
	  */
	public void setJobOid(java.lang.Long jobOid) 
	{
		this.jobOid = jobOid;
	}
	
	/**
	  *	工作名称
	  */
	public java.lang.String getJobName() 
	{
		return jobName;
	}
	
	/**
	  *	工作名称
	  */
	public void setJobName(java.lang.String jobName) 
	{
		this.jobName = jobName;
	}
	
	/**
	  *	工作模块
	  */
	public java.lang.String getJobModule() 
	{
		return jobModule;
	}
	
	/**
	  *	工作模块
	  */
	public void setJobModule(java.lang.String jobModule) 
	{
		this.jobModule = jobModule;
	}
	
	/**
	  *	工作请求人ID
	  */
	public java.lang.String getUserId() 
	{
		return userId;
	}
	
	/**
	  *	工作请求人ID
	  */
	public void setUserId(java.lang.String userId) 
	{
		this.userId = userId;
	}
	
	/**
	  *	6：待处理的工作
4：暂停的工作
8：运行中的工作
0：取消执行的工作
1：已执行完成的工作
	  */
	public java.lang.String getJobStatus() 
	{
		return jobStatus;
	}
	
	/**
	  *	6：待处理的工作
4：暂停的工作
8：运行中的工作
0：取消执行的工作
1：已执行完成的工作
	  */
	public void setJobStatus(java.lang.String jobStatus) 
	{
		this.jobStatus = jobStatus;
	}
	
	/**
	  *	9：紧急的
8：高优先级
7：普通的
6：低优先级
	  */
	public java.lang.String getJobPriority() 
	{
		return jobPriority;
	}
	
	/**
	  *	9：紧急的
8：高优先级
7：普通的
6：低优先级
	  */
	public void setJobPriority(java.lang.String jobPriority) 
	{
		this.jobPriority = jobPriority;
	}
	
	/**
	  *	工作执行类
	  */
	public java.lang.String getJobExecuteClass() 
	{
		return jobExecuteClass;
	}
	
	/**
	  *	工作执行类
	  */
	public void setJobExecuteClass(java.lang.String jobExecuteClass) 
	{
		this.jobExecuteClass = jobExecuteClass;
	}
	
	/**
	  *	工作执行方法
	  */
	public java.lang.String getJobExecuteMethod() 
	{
		return jobExecuteMethod;
	}
	
	/**
	  *	工作执行方法
	  */
	public void setJobExecuteMethod(java.lang.String jobExecuteMethod) 
	{
		this.jobExecuteMethod = jobExecuteMethod;
	}
	
	/**
	  *	线程分配数
	  */
	public java.lang.Integer getThreadAssignNum() 
	{
		return threadAssignNum;
	}
	
	/**
	  *	线程分配数
	  */
	public void setThreadAssignNum(java.lang.Integer threadAssignNum) 
	{
		this.threadAssignNum = threadAssignNum;
	}
	
	/**
	  *	任务总数
	  */
	public java.lang.Integer getTaskTotal() 
	{
		return taskTotal;
	}
	
	/**
	  *	任务总数
	  */
	public void setTaskTotal(java.lang.Integer taskTotal) 
	{
		this.taskTotal = taskTotal;
	}
	
	/**
	  *	任务成功数
	  */
	public java.lang.Integer getTaskSuccess() 
	{
		return taskSuccess;
	}
	
	/**
	  *	任务成功数
	  */
	public void setTaskSuccess(java.lang.Integer taskSuccess) 
	{
		this.taskSuccess = taskSuccess;
	}
	
	/**
	  *	任务失败数
	  */
	public java.lang.Integer getTaskFail() 
	{
		return taskFail;
	}
	
	/**
	  *	任务失败数
	  */
	public void setTaskFail(java.lang.Integer taskFail) 
	{
		this.taskFail = taskFail;
	}
	
	/**
	  *	工作添加时间
	  */
	public java.lang.String getJobAddTime() 
	{
		return jobAddTime;
	}
	
	/**
	  *	工作添加时间
	  */
	public void setJobAddTime(java.lang.String jobAddTime) 
	{
		this.jobAddTime = jobAddTime;
	}
	
	/**
	 * @param planExecuteTime the planExecuteTime to set
	 */
	public void setPlanExecuteTime(java.lang.String planExecuteTime)
	{
		this.planExecuteTime = planExecuteTime;
	}

	/**
	 * @return the planExecuteTime
	 */
	public java.lang.String getPlanExecuteTime()
	{
		return planExecuteTime;
	}

	/**
	  *	工作完成时间
	  */
	public java.lang.String getJobFinishedTime() 
	{
		return jobFinishedTime;
	}
	
	/**
	  *	工作完成时间
	  */
	public void setJobFinishedTime(java.lang.String jobFinishedTime) 
	{
		this.jobFinishedTime = jobFinishedTime;
	}
	
	/**
	  *	描述
	  */
	public java.lang.String getDescription() 
	{
		return description;
	}
	
	/**
	  *	描述
	  */
	public void setDescription(java.lang.String description) 
	{
		this.description = description;
	}
	
	/**
	  *	创建人
	  */
	public java.lang.String getCreateBy() 
	{
		return createBy;
	}
	
	/**
	  *	创建人
	  */
	public void setCreateBy(java.lang.String createBy) 
	{
		this.createBy = createBy;
	}
	
	/**
	  *	创建时间
	  */
	public java.lang.String getCreateDate() 
	{
		return createDate;
	}
	
	/**
	  *	创建时间
	  */
	public void setCreateDate(java.lang.String createDate) 
	{
		this.createDate = createDate;
	}
	
	/**
	  *	修改人
	  */
	public java.lang.String getUpdateBy() 
	{
		return updateBy;
	}
	
	/**
	  *	修改人
	  */
	public void setUpdateBy(java.lang.String updateBy) 
	{
		this.updateBy = updateBy;
	}
	
	/**
	  *	修改时间
	  */
	public java.lang.String getUpdateDate() 
	{
		return updateDate;
	}
	
	/**
	  *	修改时间
	  */
	public void setUpdateDate(java.lang.String updateDate) 
	{
		this.updateDate = updateDate;
	}
	
	/**
	 * 工作类型
	 * @return
	 */
	public java.lang.String getJobType()
	{
		return jobType;
	}

	/**
	 * 工作类型
	 * @return
	 */
	public void setJobType(java.lang.String jobType)
	{
		this.jobType = jobType;
	}

	public String toString()
	{
		return "BatchJob [" + 
					"jobOid=" + jobOid + 
					", jobName=" + jobName + 
					", jobModule=" + jobModule + 
					", userId=" + userId + 
					", jobStatus=" + jobStatus + 
					", jobPriority=" + jobPriority + 
					", jobExecuteClass=" + jobExecuteClass + 
					", jobExecuteMethod=" + jobExecuteMethod + 
					", threadAssignNum=" + threadAssignNum + 
					", taskTotal=" + taskTotal + 
					", taskSuccess=" + taskSuccess + 
					", taskFail=" + taskFail + 
					", jobAddTime=" + jobAddTime + 
					", jobFinishedTime=" + jobFinishedTime + 
					", planExecuteTime=" + planExecuteTime + 
					", description=" + description + 
					", createBy=" + createBy + 
					", createDate=" + createDate + 
					", updateBy=" + updateBy + 
					", updateDate=" + updateDate + 
					", jobType=" + jobType + 
				"]";
	}
}

