package com.core.batch.manager;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.core.batch.thread.DefaultThreadPoolExecutor;
import com.core.batch.thread.TaskThreadExecutor;

/**
 * Dec 30, 2009
 * Liuzh
 */
public class ThreadPoolManager {

	private static ThreadPoolManager threadPoolManager = null;
	
	private static int maxThread;
	
	private static DefaultThreadPoolExecutor defaultThreadPoolExecutor = null;
	
	public static ThreadPoolManager getInstance()
	{
		if(defaultThreadPoolExecutor.isShutdown())
			defaultThreadPoolExecutor = new DefaultThreadPoolExecutor(
					maxThread, maxThread, 0L, TimeUnit.MILLISECONDS,
					new LinkedBlockingQueue<Runnable>());
		
		return threadPoolManager;
	}
	
	public static ThreadPoolManager getInstance(int maxThreads)
	{
		if(threadPoolManager == null)
		{
			threadPoolManager = new ThreadPoolManager();
			
			defaultThreadPoolExecutor = new DefaultThreadPoolExecutor(
					maxThreads, maxThreads, 0L, TimeUnit.MILLISECONDS,
					new LinkedBlockingQueue<Runnable>());
			
			maxThread = maxThreads;
		}
		
		return threadPoolManager;
	}
	
	/**
	 * 线程池是否已关闭
	 * @return
	 */
	public static boolean isThreadPoolShutdown()
	{
		if(defaultThreadPoolExecutor == null || defaultThreadPoolExecutor.isTerminated())
			return true;
		else
			return false;
	}
	
	/**
	 * 线程池队列是否为空
	 * @return
	 */
	public boolean isEmpty()
	{
		return defaultThreadPoolExecutor.getQueue().isEmpty();
	}
		
	/**
	 * 清除线程池队列中的任务
	 */
	public void clearQueue()
	{
		defaultThreadPoolExecutor.getQueue().clear();
	}
	
	/**
	 * 关闭线程池
	 */
	public void shutdown()
	{
		defaultThreadPoolExecutor.shutdown();
	}
	
	/**
	 * 向线程池中添加任务
	 * @param taskThreadExecutor
	 */
	public void addTask(TaskThreadExecutor taskThreadExecutor)
	{
		defaultThreadPoolExecutor.execute(taskThreadExecutor);
	}
}
