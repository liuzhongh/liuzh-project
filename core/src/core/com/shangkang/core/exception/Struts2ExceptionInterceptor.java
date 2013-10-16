package com.shangkang.core.exception;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.StaticParametersInterceptor;
import com.shangkang.core.action.BaseAction;
import com.shangkang.tools.UtilHelper;

public class Struts2ExceptionInterceptor extends StaticParametersInterceptor {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Log					logger				= LogFactory
															.getLog(getClass());

	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
		try
		{
			return invocation.invoke();
		} catch (Exception e)
		{
			if(e instanceof AutoProcessException)
				throw (AutoProcessException) e;
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();

			if ("XMLHttpRequest".equalsIgnoreCase(request
					.getHeader("x-requested-with")))
			{
				processAjax(response, e);
	            
	            return Action.NONE;
			} else
			{
				ActionSupport actionSupport = (ActionSupport) invocation
						.getAction();
				
				if(actionSupport instanceof BaseAction)
				{
					String code = ((BaseAction) actionSupport).getAjaxErrorCode();
					
					if(!UtilHelper.isEmpty(code))
		        	{
						JSONObject obj= new JSONObject(); 
						
						obj.element("errorMsg", e.getMessage());
						String msg = obj.toString();
						
						response.setContentType("text/html; charset=UTF-8");
						response.setCharacterEncoding("UTF-8");
						response.setContentLength(msg.getBytes("UTF-8").length);
				        PrintWriter out = response.getWriter();
				        out.print(msg);
				        out.flush();
				        out.close();
				        logger.error("", e);
			        
				        return code;
		        	}
				}
				
				exception(e, actionSupport);
			}

			logger.error("", e);

			return Action.ERROR;
		}
		
	}
	
	private void processAjax(HttpServletResponse response, Exception e) throws Exception
	{
		response.setStatus(401);
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		if(null != e.getMessage())
			response.setContentLength(e.getMessage().getBytes("UTF-8").length);
		
        PrintWriter out = response.getWriter();
        out.print(e.getMessage());
        out.flush();
        out.close();
        logger.error("", e);
	}

	private void exception(Exception e, ActionSupport actionSupport)
			throws Exception
	{
		if (e instanceof ServiceException)
		{
			ServiceException se = (ServiceException) e;
			actionSupport.addActionError(se.getMessage());
		} else
		{
			actionSupport.addActionError(e.getMessage());
		}
	}

}
