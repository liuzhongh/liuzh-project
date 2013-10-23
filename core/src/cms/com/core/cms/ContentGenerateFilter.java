/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-10-17
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.cms;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.core.cms.config.ConfigLoad;
import com.shangkang.tools.UtilHelper;

public class ContentGenerateFilter implements Filter {

	private static Log			log							= LogFactory.getLog(ContentGenerateFilter.class);

	public static final String	DEFAULT_CONFIG_FILE_PATH	= "/WEB-INF/cms.xml";

	private ServletContext	context;
	
	private String configFilePath;
	
	private ConfigLoad load;

	@Override
	public void destroy()
	{
		load = null;
		
		configFilePath = null;
		context = null;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		final HttpServletRequest hsRequest = (HttpServletRequest) request;
        
		log.debug("Forward uri :" + hsRequest.getAttribute("javax.servlet.forward.servlet_path"));
	
        log.debug(hsRequest.getRequestURI() + " url = " + hsRequest.getRequestURL() + " servlet : " + hsRequest.getServletPath());
        log.debug("Include uri :" + hsRequest.getAttribute("javax.servlet.include.servlet_path"));
        
        Rule rule = load.validateRequestUri(hsRequest.getRequestURI().replace(hsRequest.getContextPath(), ""), 
        		(String)hsRequest.getAttribute("javax.servlet.forward.servlet_path"));
        
        if(null == rule 
        		|| "Y".equalsIgnoreCase(request.getParameter("isHttpClientRequest")))
        {
        	chain.doFilter(request, response);
        	return;
        }
        load.resolveRule2Html(request, response, rule, chain);
        
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		load = new ConfigLoad();
		
		log.debug("filter init called");
		if (filterConfig == null)
		{
			log.error("unable to init filter as filter config is null");
			return;
		}

		log.debug("init: calling destroy just in case we are being re-inited uncleanly");

		context = filterConfig.getServletContext();
		if (context == null)
		{
			log.error("unable to init as servlet context is null");
			return;
		}
		String basePath = context.getRealPath("");

		configFilePath = filterConfig.getInitParameter("config");
		
		if(UtilHelper.isEmpty(configFilePath))
			configFilePath = DEFAULT_CONFIG_FILE_PATH;
		
		InputStream inputStream = context.getResourceAsStream(configFilePath);
		
		load.init(inputStream, basePath);
	}

}
