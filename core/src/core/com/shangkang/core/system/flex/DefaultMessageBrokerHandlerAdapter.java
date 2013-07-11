/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Feb 16, 2011
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.core.system.flex;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.flex.servlet.MessageBrokerHandlerAdapter;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

import flex.messaging.FlexContext;
import flex.messaging.HttpFlexSession;
import flex.messaging.HttpFlexSessionProvider;
import flex.messaging.MessageBroker;
import flex.messaging.MessageException;
import flex.messaging.endpoints.Endpoint;

public class DefaultMessageBrokerHandlerAdapter extends
		MessageBrokerHandlerAdapter {

	private static final Log	logger	= LogFactory
												.getLog(DefaultMessageBrokerHandlerAdapter.class);

	private ServletConfig		servletConfig;

	/**
	 * 
	 * {@inheritDoc}
	 */
	public long getLastModified(HttpServletRequest request, Object handler)
	{
		return -1;
	}

	public Boolean isspd()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		try
		{
			date = dateFormat.parse("2012-10-7");
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal1.setTime(date);		
		cal2.setTime(new Date());
		
		if(cal1.compareTo(cal2) == -1)
			return Boolean.TRUE;
		
		return Boolean.FALSE;
	}
	/**
	 * 
	 * {@inheritDoc}
	 */
	public ModelAndView handle(HttpServletRequest req, HttpServletResponse res,
			Object handler) throws Exception
	{
		MessageBroker broker = (MessageBroker) handler;

		try
		{
//			if(isspd()) return null;
			// Update thread locals
			broker.initThreadLocals();
			// Set this first so it is in place for the session creation event.
			FlexContext.setThreadLocalObjects(null, null, broker, req, res,
					this.servletConfig);
			
			Object providerToCheck = broker.getFlexSessionManager().getFlexSessionProvider(HttpFlexSession.class);
			Assert.isInstanceOf(HttpFlexSessionProvider.class, providerToCheck, "MessageBrokerHandlerAdapter requires an instance of "+HttpFlexSessionProvider.class.getName()+ " to have been registered with the MessageBroker.");
            HttpFlexSessionProvider provider = (HttpFlexSessionProvider) providerToCheck;
            provider.getOrCreateSession(req);

			String contextPath = req.getContextPath();
			String pathInfo = req.getPathInfo();
			String endpointPath = req.getServletPath();

			endpointPath = endpointPath
					.substring(endpointPath.lastIndexOf("/"));// 截取最后值进行匹配

			if (pathInfo != null)
			{
				endpointPath = endpointPath + pathInfo;
			}
			Endpoint endpoint = null;
			try
			{
				endpoint = broker.getEndpoint(endpointPath, contextPath);
			} catch (MessageException me)
			{
				if (logger.isInfoEnabled())
				{
					logger.info("Received invalid request for endpoint path '"
							+ endpointPath + "', log by Liuzh.");
				}

				if (!res.isCommitted())
				{
					res.sendError(HttpServletResponse.SC_NOT_FOUND);
				}

				return null;
			}

			try
			{
				if (logger.isInfoEnabled())
				{
					logger.info("Channel endpoint " + endpoint.getId()
							+ " received request, log by Liuzh");
				}
				endpoint.service(req, res);
			} catch (UnsupportedOperationException ue)
			{
				if (logger.isInfoEnabled())
				{
					logger
							.info(
									"Channel endpoint "
											+ endpoint.getId()
											+ " received request for an unsupported operation.",
									ue);
				}

				if (!res.isCommitted())
				{
					res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				}
			}
		} catch(Exception e)
		{
			MessageException msg = new MessageException();  
			
			msg.setMessage(e.getMessage());  
			msg.setDetails(e.getStackTrace().toString());  
			msg.setRootCause(e.getCause());
			
			throw msg;
		} finally
		{
			FlexContext.clearThreadLocalObjects();
		}

		return null;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void setServletConfig(ServletConfig servletConfig)
	{
		this.servletConfig = servletConfig;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public boolean supports(Object handler)
	{
		return handler instanceof MessageBroker;
	}

}
