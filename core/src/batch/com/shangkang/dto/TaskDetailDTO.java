package com.shangkang.dto;

import java.util.Arrays;

public class TaskDetailDTO {

	private String key;
	private Object[] executeMethodParameter;
	/**
	  *	工作执行类
	  */
	private java.lang.String jobExecuteClass;

	/**
	  *	工作执行方法
	  */
	private java.lang.String jobExecuteMethod;

	public Object[] getExecuteMethodParameter() {
		return executeMethodParameter;
	}

	/**
	 * 设置任务参数，只能为基础类型
	 * @param executeMethodParameter
	 */
	public void setExecuteMethodParameter(Object[] executeMethodParameter) {
		this.executeMethodParameter = executeMethodParameter;
	}

	public String getKey() {
		return key;
	}

	/**
	 * 设置任务关键字（如人员索引、事项索引，将每个任务与业务进行关联，由调用者定义）
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TaskDetailDTO [key=" + key + ", executeMethodParameter="
				+ Arrays.toString(executeMethodParameter)
				+ ", jobExecuteClass=" + jobExecuteClass
				+ ", jobExecuteMethod=" + jobExecuteMethod + "]";
	}
	
}
