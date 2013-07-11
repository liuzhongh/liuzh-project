/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-3-23 14:34:24
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class BatchJobLog extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	工作日志索引
	  */
	private java.lang.Long jobLogOid;

	/**
	  *	工作索引
	  */
	private java.lang.Long jobOid;

	/**
	  *	P：优先级更改
S：工作状态更改
	  */
	private java.lang.String type;

	/**
	  *	更改状态
	  */
	private java.lang.String status;

	/**
	  *	工作状态更改人
	  */
	private java.lang.String userId;

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
	  *	工作日志索引
	  */
	public java.lang.Long getJobLogOid() 
	{
		return jobLogOid;
	}
	
	/**
	  *	工作日志索引
	  */
	public void setJobLogOid(java.lang.Long jobLogOid) 
	{
		this.jobLogOid = jobLogOid;
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
	  *	P：优先级更改
S：工作状态更改
	  */
	public java.lang.String getType() 
	{
		return type;
	}
	
	/**
	  *	P：优先级更改
S：工作状态更改
	  */
	public void setType(java.lang.String type) 
	{
		this.type = type;
	}
	
	/**
	  *	更改状态
	  */
	public java.lang.String getStatus() 
	{
		return status;
	}
	
	/**
	  *	更改状态
	  */
	public void setStatus(java.lang.String status) 
	{
		this.status = status;
	}
	
	/**
	  *	工作状态更改人
	  */
	public java.lang.String getUserId() 
	{
		return userId;
	}
	
	/**
	  *	工作状态更改人
	  */
	public void setUserId(java.lang.String userId) 
	{
		this.userId = userId;
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
		return "BatchJobLog [" + 
					"jobLogOid=" + jobLogOid + 
					", jobOid=" + jobOid + 
					", type=" + type + 
					", status=" + status + 
					", userId=" + userId + 
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

