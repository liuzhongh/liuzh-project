package com.core.batch.listener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.core.batch.manager.BatchManager;

/**
 * 起动任务线管理池管理器
 * Jan 6, 2010
 * Liuzh
 */
public class BatchManagerListener extends HttpServlet
{
	/**
	 * serial no
	 */
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(this.getClass());

	/**
	 * Initialize the root web application context.
	 * @throws JadeWorkFlowException 
	 */
	public void init() throws ServletException
	{		
		try {
			
			String start = this.getInitParameter("start");//随机启动应用标识,'Y'或'y'时启动应用
			
			if("Y".equalsIgnoreCase(start))
			{
				log.debug("后台批量定时任务启动");
				log.debug("ServletContext : " + getServletContext());
				
				BatchManager.start(this.getServletContext());
			}
			
//			CycleTimeManger.getInstance().startCycleTimeServer();
		}
		catch (Exception e) {
			log.error(e, e);
		}
	}

}
