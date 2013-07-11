/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-1-14
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.views.bigpipe.supports;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.dispatcher.Dispatcher;

import com.core.views.bigpipe.tags.MultiProcessorTag;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;
import com.shangkang.tools.DateHelper;
import com.shangkang.tools.UtilHelper;

import freemarker.template.SimpleHash;
import freemarker.template.Template;

public class PageLetProcessThread implements Runnable {

	private HttpServletRequest	request;
	private String	dealClass;
	private Dispatcher	dispatcher;
	private ActionContext	actionContext;
	private HttpServletResponse	response;
	private String	name;
	private ValueStack	stack;
	private String	template;
	private String	contentFilterClass;
	private Map<String, Object> parameters;
	private Writer	writer;
	private	Log		logger				= LogFactory.getLog(getClass());

	public PageLetProcessThread(HttpServletRequest request, String dealClass,
			Dispatcher dispatcher, ActionContext actionContext,
			HttpServletResponse response, String name, ValueStack stack,
			String template, String contentFilterClass, Map<String, Object> parameters, Writer writer)
	{
		super();
		this.request = request;
		this.dealClass = dealClass;
		this.dispatcher = dispatcher;
		this.actionContext = actionContext;
		this.response = response;
		this.name = name;
		this.stack = stack;
		this.template = template;
		this.contentFilterClass = contentFilterClass;
		this.parameters = parameters;
		this.writer = writer;
	}

	@Override
	public void run()
	{
		long s = System.currentTimeMillis();
		
		if(logger.isInfoEnabled())
			logger.info("Thread : " + Thread.currentThread().getName() + " writer [" + writer + "] executor start :" + DateHelper.nowTimestamp());
		
		CountDownLatch attribute = (CountDownLatch) request
				.getAttribute(MultiProcessorTag.COUNT_DOWN);
		try
		{
			if (!UtilHelper.isEmpty(dealClass))
			{
				Dispatcher.setInstance(dispatcher);
				ActionContext.setContext(actionContext);
				
				IPageLetDealer pld = (IPageLetDealer) Class.forName(
						dealClass).newInstance();
				
				ResultContext<Object> deal = pld.deal(stack,
						request, response, parameters);
				StringWriter sw = new StringWriter();
				ServletContext servletContext = request.getSession().getServletContext();
				
				FreeMarkerInstance freeMarkerInstance = FreeMarkerInstance.instance(servletContext);
				
				ActionInvocation ai = ActionContext.getContext().getActionInvocation();

		        Object action = (ai == null) ? null : ai.getAction();
		        
				SimpleHash model = freeMarkerInstance.getFreemarkerManager().buildTemplateModel(stack, action, servletContext, request, response, freeMarkerInstance.getConfiguration().getObjectWrapper());

		        model.put("resultContext", deal.getContext());
		        
		        String div = name + s;
		        Template tp = freeMarkerInstance.getConfiguration().getTemplate(template);
		        
		        tp.setEncoding("UTF-8");
		        tp.process(model, sw);
		        
		        StringBuffer content = new StringBuffer();
		        StringBuffer script = new StringBuffer();
		        
		        IContentFilter filter = new SimpleContentFilter();
		        
		        if(!UtilHelper.isEmpty(contentFilterClass))
		        	filter = (IContentFilter) Class.forName(contentFilterClass).newInstance();
		        
		        filter.filterContent(sw.getBuffer().toString(), script, content);
		        
		        String text = "<textarea id=\"" + div + "\" style=\"display:none;\">" + content + "</textarea>";
		        String scriptText = "<script type=\"text/javascript\">onPageletRender('" +
						name +
						"', '" + div + "');</script>";
		        
		        if(logger.isDebugEnabled())
		        {
			        logger.debug("org content :" + sw.getBuffer());
			        logger.debug("content :" + content);
			        logger.debug("script content :" + script);
			        logger.debug("text :" + text);
			        logger.debug("scriptText :" + scriptText);
		        }
		        
		        StringBuffer sb = new StringBuffer();
		        
		        sb.append(text);
		        
		        if(script != null && script.length() > 0)
		        	sb.append(script);
		        
		        sb.append(scriptText);
		        
		        synchronized (writer)
				{
		        	writer.write(sb.toString());
		        	writer.flush();
				}
		        
		        if(logger.isInfoEnabled())
		        	logger.info("Thread : [" + Thread.currentThread().getName() + "] PageLet Dealer class: [" + pld.getClass() + "] executor end : [" + DateHelper.nowTimestamp() + "] executor times : [" + (System.currentTimeMillis() - s) + "]");
			}
		} catch (Exception e)
		{
			try
			{
				synchronized (writer)
				{
					String div = name + System.currentTimeMillis();
					
					String text = "<textarea id=\"" + div + "\" style=\"display:none;\"><font color=\"red\">" + e.getMessage() + "</font></textarea>";
			        String scriptText = "<script type=\"text/javascript\">onPageletRender('" +
							name +
							"', '" + div + "');</script>";
			        
					writer.write(new StringBuffer(text).append(scriptText).toString());
					writer.flush();
				}
			} catch (IOException e1)
			{
				logger.error(e1, e1);
			}
			
			logger.error(e, e);
		} finally
		{
			attribute.countDown();
		}
	}

}
