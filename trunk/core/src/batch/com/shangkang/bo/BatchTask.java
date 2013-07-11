/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-3-23 14:34:23
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class BatchTask extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	任务索引
	  */
	private java.lang.Long taskOid;

	/**
	  *	工作索引
	  */
	private java.lang.Long jobOid;

	/**
	  *	任务参数关键字
	  */
	private java.lang.String taskParameterKey;
	
	/**
	  *	工作执行类
	  */
	private java.lang.String jobExecuteClass;

	/**
	  *	工作执行方法
	  */
	private java.lang.String jobExecuteMethod;

	/**
	  *	任务参数
	  */
	private java.lang.String taskParameter;

	/**
	  *	A：待处理的任务
P：暂停的任务
R：运行中的工作
C：取消的任务
F：执行失败的任务
S：执行成功的任务


	  */
	private java.lang.String executeStatus;

	/**
	  *	执行开始时间
	  */
	private java.lang.String executeStartTime;

	/**
	  *	执行结束时间
	  */
	private java.lang.String executeEndTime;

	/**
	  *	执行总时间
	  */
	private java.lang.Integer executeTimes;

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
	  *	任务索引
	  */
	public java.lang.Long getTaskOid() 
	{
		return taskOid;
	}
	
	/**
	  *	任务索引
	  */
	public void setTaskOid(java.lang.Long taskOid) 
	{
		this.taskOid = taskOid;
	}
	
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
	  *	任务参数关键字
	  */
	public java.lang.String getTaskParameterKey() 
	{
		return taskParameterKey;
	}
	
	/**
	  *	任务参数关键字
	  */
	public void setTaskParameterKey(java.lang.String taskParameterKey) 
	{
		this.taskParameterKey = taskParameterKey;
	}
	
	/**
	 * @return the jobExecuteClass
	 */
	public java.lang.String getJobExecuteClass()
	{
		return jobExecuteClass;
	}

	/**
	 * @param jobExecuteClass the jobExecuteClass to set
	 */
	public void setJobExecuteClass(java.lang.String jobExecuteClass)
	{
		this.jobExecuteClass = jobExecuteClass;
	}

	/**
	 * @return the jobExecuteMethod
	 */
	public java.lang.String getJobExecuteMethod()
	{
		return jobExecuteMethod;
	}

	/**
	 * @param jobExecuteMethod the jobExecuteMethod to set
	 */
	public void setJobExecuteMethod(java.lang.String jobExecuteMethod)
	{
		this.jobExecuteMethod = jobExecuteMethod;
	}

	/**
	  *	任务参数
	  */
	public java.lang.String getTaskParameter() 
	{
		return taskParameter;
	}
	
	/**
	  *	任务参数
	  */
	public void setTaskParameter(java.lang.String taskParameter) 
	{
		this.taskParameter = taskParameter;
	}
	
	/**
	  *	A：待处理的任务
P：暂停的任务
R：运行中的工作
C：取消的任务
F：执行失败的任务
S：执行成功的任务


	  */
	public java.lang.String getExecuteStatus() 
	{
		return executeStatus;
	}
	
	/**
	  *	A：待处理的任务
P：暂停的任务
R：运行中的工作
C：取消的任务
F：执行失败的任务
S：执行成功的任务


	  */
	public void setExecuteStatus(java.lang.String executeStatus) 
	{
		this.executeStatus = executeStatus;
	}
	
	/**
	  *	执行开始时间
	  */
	public java.lang.String getExecuteStartTime() 
	{
		return executeStartTime;
	}
	
	/**
	  *	执行开始时间
	  */
	public void setExecuteStartTime(java.lang.String executeStartTime) 
	{
		this.executeStartTime = executeStartTime;
	}
	
	/**
	  *	执行结束时间
	  */
	public java.lang.String getExecuteEndTime() 
	{
		return executeEndTime;
	}
	
	/**
	  *	执行结束时间
	  */
	public void setExecuteEndTime(java.lang.String executeEndTime) 
	{
		this.executeEndTime = executeEndTime;
	}
	
	/**
	  *	执行总时间
	  */
	public java.lang.Integer getExecuteTimes() 
	{
		return executeTimes;
	}
	
	/**
	  *	执行总时间
	  */
	public void setExecuteTimes(java.lang.Integer executeTimes) 
	{
		this.executeTimes = executeTimes;
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
	
	public String toString()
	{
		return "BatchTask [" + 
					"taskOid=" + taskOid + 
					", jobOid=" + jobOid + 
					", taskParameterKey=" + taskParameterKey + 
					", taskParameter=" + taskParameter + 
					", executeStatus=" + executeStatus + 
					", executeStartTime=" + executeStartTime + 
					", executeEndTime=" + executeEndTime + 
					", executeTimes=" + executeTimes + 
					", createBy=" + createBy + 
					", createDate=" + createDate + 
					", updateBy=" + updateBy + 
					", updateDate=" + updateDate + 
				"]";
	}
}

