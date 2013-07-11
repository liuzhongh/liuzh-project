/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-3-23
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.batch.constant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BatchConstant {

	/**
	 * 工作类型-执行多次的工作
	 */
	public static final String 			JOB_TYPE_EXE_N_COUNT     =   "N";
	
	/**
	 * 工作类型-执行1次的工作
	 */
	public static final String 			JOB_TYPE_EXE_E_COUNT     =   "E";
	
	/**
	 * 工作更改类型-优先级
	 */
	public static final String			JOB_CHANGE_TYPE_PRIORITY		= "P";

	/**
	 * 工作更改类型-状态
	 */
	public static final String			JOB_CHANGE_TYPE_STATUS			= "S";

	/**
	 * 工作优先级-紧急的
	 */
	public static final String			JOB_PRIORITY_EXPEDITE			= "9";

	/**
	 * 工作优先级-高的
	 */
	public static final String			JOB_PRIORITY_HEIGHT				= "8";

	/**
	 * 工作优先级-普通的
	 */
	public static final String			JOB_PRIORITY_GENERAL			= "7";

	/**
	 * 工作优先级-低的
	 */
	public static final String			JOB_PRIORITY_LOW				= "6";

	/**
	 * 工作状态-待处理
	 */
	public static final String			JOB_STATUS_AWAIT				= "6";

	/**
	 * 工作状态-暂停的
	 */
	public static final String			JOB_STATUS_PAUSE				= "4";

	/**
	 * 工作状态-运行中
	 */
	public static final String			JOB_STATUS_RUNNING				= "8";

	/**
	 * 工作状态-取消的
	 */
	public static final String			JOB_STATUS_CANCEL				= "0";

	/**
	 * 工作状态-完成的
	 */
	public static final String			JOB_STATUS_FINISH				= "1";

	/**
	 * 任务执行状态-待处理
	 */
	public static final String			TASK_EXECUTE_AWAIT				= "A";

	/**
	 * 任务执行状态-暂停的
	 */
	public static final String			TASK_EXECUTE_PAUSE				= "P";

	/**
	 * 任务执行状态-运行中
	 */
	public static final String			TASK_EXECUTE_RUNNING			= "R";

	/**
	 * 任务执行状态-取消的
	 */
	public static final String			TASK_EXECUTE_CANCEL				= "C";

	/**
	 * 任务执行状态-失败的
	 */
	public static final String			TASK_EXECUTE_FAILURE			= "F";

	/**
	 * 任务执行状态-成功的
	 */
	public static final String			TASK_EXECUTE_SUCCESS			= "S";

	/**
	 * 系统参数-最大线程
	 */
	public static final String			SYS_PARAMETER_MAX_THREADS		= "max_threads";

	/**
	 * 系统参数-任务运行倍数
	 */
	public static final String			SYS_PARAMETER_TASK_RUN_MULTIPLE	= "taskRunMultiple";

	/**
	 * 系统参数-缺省的工作线程分配数
	 */
	public static final String			SYS_PARAMETER_THREAD_ASSIGN_NUM	= "defaultThreadAssignNum";

	/**
	 * 系统参数-线程轮循周期（秒）
	 */
	public static final String			SYS_PARAMETER_THREAD_CYCLE_TIME	= "threadCycleTime";

	/**
	 * 缺省的字符串分隔符
	 */
	public static final String			DEFAULT_SPLIT_SYMBOL			= ";";

	public static Map<String, String>	jobStatusMap					= new LinkedHashMap<String, String>();
	static
	{
		jobStatusMap.put(JOB_STATUS_AWAIT, "待处理");
		jobStatusMap.put(JOB_STATUS_PAUSE, "暂停");
		jobStatusMap.put(JOB_STATUS_RUNNING, "运行中");
		jobStatusMap.put(JOB_STATUS_CANCEL, "已取消");
		jobStatusMap.put(JOB_STATUS_FINISH, "已完成");
	}
	public static Map<String, String>	executeStatusMap				= new HashMap<String, String>();
	static
	{
		executeStatusMap.put(TASK_EXECUTE_AWAIT, "待处理");
		executeStatusMap.put(TASK_EXECUTE_PAUSE, "暂停");
		executeStatusMap.put(TASK_EXECUTE_RUNNING, "运行中");
		executeStatusMap.put(TASK_EXECUTE_CANCEL, "已取消");
		executeStatusMap.put(TASK_EXECUTE_FAILURE, "失败");
		executeStatusMap.put(TASK_EXECUTE_SUCCESS, "成功");
	}
	public static Map<String, String>	priorityMap						= new LinkedHashMap<String, String>();
	static
	{
		priorityMap.put(JOB_PRIORITY_LOW, "低");
		priorityMap.put(JOB_PRIORITY_GENERAL, "普通");
		priorityMap.put(JOB_PRIORITY_HEIGHT, "高");
		priorityMap.put(JOB_PRIORITY_EXPEDITE, "紧急");
	}
}
