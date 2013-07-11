package com.shangkang.dto;

import com.core.batch.constant.BatchConstant;

public class JobDetailDTO {

	/**
	 * 工作优先级-紧急的
	 */
	public static final String JOB_PRIORITY_EXPEDITE = BatchConstant.JOB_PRIORITY_EXPEDITE;
	
	/**
	 * 工作优先级-高的
	 */
	public static final String JOB_PRIORITY_HEIGHT = BatchConstant.JOB_PRIORITY_HEIGHT;
	
	/**
	 * 工作优先级-普通的
	 */
	public static final String JOB_PRIORITY_GENERAL = BatchConstant.JOB_PRIORITY_GENERAL;
	
	/**
	 * 工作优先级-低的
	 */
	public static final String JOB_PRIORITY_LOW = BatchConstant.JOB_PRIORITY_LOW;
	
	private String jobName;// 工作名称
	private String userId;// 工作发起人
	private String jobExecuteClass;// 工作执行类名
	private String jobExecuteMethod;// 工作执行方法
	private String jobPriority = JOB_PRIORITY_GENERAL;// 工作执行优先级(9：紧急的 8:高优先级 7:普通的 6:低优先级;默认为 7 普通级)
	private int threadAssignNum;// 工作启动时线程分配的最大数
	private String description;// 描述
	private String jobModule;// 工作模块
	
	/**
	  *	计划执行时间
	  */
	private java.lang.String planExecuteTime;

	private String jobType;
	
	public String getDescription() {
		return description;
	}

	/**
	 * 设置工作描述
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobExecuteClass() {
		return jobExecuteClass;
	}

	/**
	 * 设置工作执行类在spring配置文件中的名称
	 * @param jobExecuteClass
	 */
	public void setJobExecuteClass(String jobExecuteClass) {
		this.jobExecuteClass = jobExecuteClass;
	}

	public String getJobExecuteMethod() {
		return jobExecuteMethod;
	}

	/**
	 * 设置工作执行方法
	 * @param jobExecuteMethod
	 */
	public void setJobExecuteMethod(String jobExecuteMethod) {
		this.jobExecuteMethod = jobExecuteMethod;
	}

	public String getJobName() {
		return jobName;
	}

	/**
	 * 设置工作名称
	 * @param jobName
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobPriority() {
		return jobPriority;
	}

	/**
	 * 设置工作优先级，（可不设，默认为普通）
	 * <br>优先级有以下几种：
	 * <br>紧急的 9: JobDetailDTO.JOB_PRIORITY_EXPEDITE
	 * <br>高优先级 8: JobDetailDTO.JOB_PRIORITY_HEIGHT
	 * <br>普通的 7: JobDetailDTO.JOB_PRIORITY_GENERAL
	 * <br>低优先级 6: JobDetailDTO.JOB_PRIORITY_LOW
	 * @param jobPriority
	 */
	public void setJobPriority(String jobPriority) {
		this.jobPriority = jobPriority;
	}

	public int getThreadAssignNum() {
		return threadAssignNum;
	}

	/**
	 * 设置给工作分配的线程数（可不设）
	 * @param threadAssignNum
	 */
	public void setThreadAssignNum(int threadAssignNum) {
		this.threadAssignNum = threadAssignNum;
	}

	public String getUserId() {
		return userId;
	}
	
	/**
	 * 设置工作创建人（不能为空）
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getJobModule() {
		return jobModule;
	}

	/**
	 * 设置工作模块，由调用者定义，以后可根据模块进行工作查询
	 * @param jobModule
	 */
	public void setJobModule(String jobModule) {
		this.jobModule = jobModule;
	}

	/**
	 * @return the planExecuteTime
	 */
	public java.lang.String getPlanExecuteTime()
	{
		return planExecuteTime;
	}

	/**
	 * @param planExecuteTime the planExecuteTime to set
	 */
	public void setPlanExecuteTime(java.lang.String planExecuteTime)
	{
		this.planExecuteTime = planExecuteTime;
	}

	/**
	 * 
	 * @return the jobType
	 */
	public String getJobType()
	{
		return jobType;
	}

	/**
	 * 
	 * @param jobType  the jobType to set
	 */
	public void setJobType(String jobType)
	{
		this.jobType = jobType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "JobDetailDTO [jobName=" + jobName + ", userId=" + userId
				+ ", jobExecuteClass=" + jobExecuteClass
				+ ", jobExecuteMethod=" + jobExecuteMethod + ", jobPriority="
				+ jobPriority + ", threadAssignNum=" + threadAssignNum
				+ ", description=" + description + ", jobModule=" + jobModule
				+ ", planExecuteTime=" + planExecuteTime 
				+ ", jobType=" + jobType + "]";
	}

}
