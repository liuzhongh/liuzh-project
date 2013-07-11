/*
 * Created at 2009-12-22
 */
package com.shangkang.dto;

public class BatchTaskDTO
{
	private Long taskOid;

	private Long jobOid;
	
	private String taskParameterKey;

	private String taskParameter;

	private String executeStatus;
	
	/**
	  *	计划执行时间
	  */
	private java.lang.String planExecuteTime;

	private String executeStartTime;

	private String executeEndTime;

	private Integer executeTimes;

	private String createBy;

	private String createDate;

	private String updateBy;

	private String updateDate;

	private String exceptionMessage;
	
	private String exceptionStackMessage;
	
	private String Priority;
	private String JobExecuteClass;
	private String JobExecuteMethod;
	private Long taskExceptionOid;
	
	public Long getTaskExceptionOid() {
		return taskExceptionOid;
	}

	public void setTaskExceptionOid(Long taskExceptionOid) {
		this.taskExceptionOid = taskExceptionOid;
	}

	public String getJobExecuteClass() {
		return JobExecuteClass;
	}

	public void setJobExecuteClass(String jobExecuteClass) {
		JobExecuteClass = jobExecuteClass;
	}

	public String getJobExecuteMethod() {
		return JobExecuteMethod;
	}

	public void setJobExecuteMethod(String jobExecuteMethod) {
		JobExecuteMethod = jobExecuteMethod;
	}

	public String getPriority() {
		return Priority;
	}

	public void setPriority(String priority) {
		Priority = priority;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public void setTaskOid(Long taskOid)
	{
		this.taskOid=taskOid;
	}

	public Long getTaskOid()
	{
		return this.taskOid;
	}

	public void setTaskParameterKey(String taskParameterKey)
	{
		this.taskParameterKey=taskParameterKey;
	}

	public String getTaskParameterKey()
	{
		return this.taskParameterKey;
	}

	public void setTaskParameter(String taskParameter)
	{
		this.taskParameter=taskParameter;
	}

	public String getTaskParameter()
	{
		return this.taskParameter;
	}

	public void setExecuteStatus(String executeStatus)
	{
		this.executeStatus=executeStatus;
	}

	public String getExecuteStatus()
	{
		return this.executeStatus;
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

	public String getExecuteEndTime() {
		return executeEndTime;
	}

	public void setExecuteEndTime(String executeEndTime) {
		this.executeEndTime = executeEndTime;
	}

	public String getExecuteStartTime() {
		return executeStartTime;
	}

	public void setExecuteStartTime(String executeStartTime) {
		this.executeStartTime = executeStartTime;
	}

	public void setExecuteTimes(Integer executeTimes)
	{
		this.executeTimes = executeTimes;
	}

	public Integer getExecuteTimes()
	{
		return this.executeTimes;
	}

	public void setCreateBy(String createBy)
	{
		this.createBy=createBy;
	}

	public String getCreateBy()
	{
		return this.createBy;
	}

	public void setCreateDate(String createDate)
	{
		this.createDate=createDate;
	}

	public String getCreateDate()
	{
		return this.createDate;
	}

	public void setUpdateBy(String updateBy)
	{
		this.updateBy=updateBy;
	}

	public String getUpdateBy()
	{
		return this.updateBy;
	}

	public void setUpdateDate(String updateDate)
	{
		this.updateDate=updateDate;
	}

	public String getUpdateDate()
	{
		return this.updateDate;
	}

	public BatchTaskDTO(Long taskOid, 
						Long jobOid,
						String executeStatus, 
						Integer executeTimes, 
						String taskParameterKey,
						String taskParameter,
						String createBy, 
						String createDate, 
						String updateBy, 
						String updateDate)
	{
		super();
		this.taskOid = taskOid;
		this.jobOid=jobOid;
		this.executeStatus = executeStatus;
		this.executeTimes = executeTimes;
		this.taskParameterKey=taskParameterKey;
		this.taskParameter=taskParameter;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
	}

	public Long getJobOid() {
		return jobOid;
	}

	public void setJobOid(Long jobOid) {
		this.jobOid = jobOid;
	}

	public String getExceptionStackMessage() {
		return exceptionStackMessage;
	}

	public void setExceptionStackMessage(String exceptionStackMessage) {
		this.exceptionStackMessage = exceptionStackMessage;
	}

	public BatchTaskDTO() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BatchTaskDTO [taskOid=" + taskOid + ", jobOid=" + jobOid
				+ ", taskParameterKey=" + taskParameterKey + ", taskParameter="
				+ taskParameter + ", executeStatus=" + executeStatus
				+ ", planExecuteTime=" + planExecuteTime
				+ ", executeStartTime=" + executeStartTime
				+ ", executeEndTime=" + executeEndTime + ", executeTimes="
				+ executeTimes + ", createBy=" + createBy + ", createDate="
				+ createDate + ", updateBy=" + updateBy + ", updateDate="
				+ updateDate + ", exceptionMessage=" + exceptionMessage
				+ ", exceptionStackMessage=" + exceptionStackMessage
				+ ", Priority=" + Priority + ", JobExecuteClass="
				+ JobExecuteClass + ", JobExecuteMethod=" + JobExecuteMethod
				+ ", taskExceptionOid=" + taskExceptionOid + "]";
	}

}