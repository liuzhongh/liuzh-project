/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-10-13
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.cms.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.core.cms.BaseRuleValidated;
import com.core.cms.CmsHandler;
import com.core.cms.IncludeRule;
import com.core.cms.Rule;
import com.shangkang.tools.PropertyHelper;
import com.shangkang.tools.UtilHelper;

public class ConfigLoad {

	public final String CONFIG_FILE = "cms.xml";
	
	private Configuration configuration;
	
	private BaseRuleValidated validated;
	
	public void init(InputStream inputStream, String baseGeneratedPath)
	{
		if(inputStream == null)
		{
			inputStream = PropertyHelper.getInputStream(CONFIG_FILE);
		}
		
		this.loadConf(inputStream, baseGeneratedPath);
		
		validated = new BaseRuleValidated();
	}
	
	private void loadConf(InputStream inputStream, String baseGeneratedPath)
	{
		configuration = new Configuration(inputStream, "");
		
		if(UtilHelper.isEmpty(configuration.getBaseGeneratedPath()))
			configuration.setBaseGeneratedPath(baseGeneratedPath);
	}
	
	public boolean validateRequestUri(String requestUri)
	{
		return validated.validateRequestUri(requestUri, configuration.getRequestUris());
	}
	
	public void resolveRule2Html(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
//		String site = configuration.getSite();
//		String contextPath = configuration.getContextPath();
		String defaultGeneratedPath = configuration.getDefaultGeneratedPath();
		List<Rule> rules = configuration.getRules();
		String baseGeneratedPath = configuration.getBaseGeneratedPath();
		String defaultRegeneratedInterval = configuration.getDefaultRegeneratedInterval();
		
		if(!UtilHelper.isEmpty(rules))
		{
			CmsHandler handler = new CmsHandler();

			HttpServletRequest hsRequest = (HttpServletRequest) request;
			String baseUrl = new StringBuffer(hsRequest.getScheme()).append("://")
					.append(hsRequest.getLocalAddr()).append(":").append(hsRequest.getLocalPort()).append(hsRequest.getRequestURI()).toString();
			String matcherUrl = hsRequest.getRequestURI().replace(hsRequest.getContextPath(), "");
			
			for(Rule rule :rules)
			{
//				doParse(handler, request, response, configuration.getBaseGeneratedPath(), configuration.getDefaultRegeneratedInterval(), defaultGeneratedPath, rule);
				
				String requestUri = rule.getRequestUri();
				
				if(UtilHelper.isEmpty(requestUri) || !validated.validateRequestUri(matcherUrl, requestUri))
					continue;
				
				String dispatcher = rule.getDispatcher();
				String generatedPath = rule.getGeneratedPath();
				StringBuilder fileSavePath = new StringBuilder(baseGeneratedPath);
				
				int regeneratedInterval = 0;
				
				if(!UtilHelper.isEmpty(rule.getRegeneratedInterval()))
					regeneratedInterval = Integer.parseInt(rule.getRegeneratedInterval());
				else
					regeneratedInterval = Integer.parseInt(defaultRegeneratedInterval);
				
				String finalPath = defaultGeneratedPath;
				
				if(!UtilHelper.isEmpty(generatedPath))
					finalPath = generatedPath;
					
				if(!UtilHelper.isEmpty(finalPath))
					fileSavePath.append(File.separator).append(finalPath);
				
				if(Rule.DISPATCHER_INCLUDE.equalsIgnoreCase(dispatcher))
				{
					List<IncludeRule> iRules = rule.getIncludeRules();
					
					if(!UtilHelper.isEmpty(iRules))
					{
						for(IncludeRule includeRule : iRules)
						{
							String from = includeRule.getFrom();
							String to = includeRule.getTo();
							StringBuffer filePath = new StringBuffer(fileSavePath);
							
							String tmpUrl = baseUrl.substring(0, baseUrl.lastIndexOf(matcherUrl));
							
							StringBuilder url = new StringBuilder(tmpUrl);
							
							if(!UtilHelper.isEmpty(from))
								url.append("/").append(from);
							
							if(!UtilHelper.isEmpty(to))
								filePath.append(File.separator).append(to);
							else
							{
								filePath.append(File.separator);
								if(from.contains("."))
								{
									from = from.substring(0, from.lastIndexOf("."));
								}
								
								filePath.append(from.replace("/", "\\")).append(".htm");
							}
							
							handler.parse2Html(url.toString(), filePath.toString(), regeneratedInterval);
						}
					}
				}else
				{
					
					String forward = rule.getForward();
					
					if(!UtilHelper.isEmpty(forward))
						fileSavePath.append(File.separator).append(forward.replace("/", "\\"));
					
					handler.parse2Html(baseUrl.toString(), fileSavePath.toString(), regeneratedInterval);
					handler.releaseConnection();
					
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/" + finalPath + "/" + forward);
					
					requestDispatcher.forward(request, response);
					
					return;
				}
			}
			
			handler.releaseConnection();
			
			chain.doFilter(request, response);
		}
	}

	/**
	 * @param handler
	 * @param url
	 * @param savePath
	 * @param rule
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doParse(CmsHandler handler, ServletRequest request, ServletResponse response,
			String baseGeneratedPath, String defaultRegeneratedInterval,
			String defaultGeneratedPath, Rule rule) throws IOException, ServletException
	{
		String requestUri = rule.getRequestUri();
		HttpServletRequest hsRequest = (HttpServletRequest) request;
		String baseUrl = hsRequest.getRequestURL().toString();
		String matcherUrl = hsRequest.getRequestURI().replace(hsRequest.getContextPath(), "");
		
		if(UtilHelper.isEmpty(requestUri) || !validated.validateRequestUri(matcherUrl, requestUri))
			return;
		
		String dispatcher = rule.getDispatcher();
		String generatedPath = rule.getGeneratedPath();
		StringBuilder fileSavePath = new StringBuilder(baseGeneratedPath);
		
		int regeneratedInterval = 0;
		
		if(!UtilHelper.isEmpty(rule.getRegeneratedInterval()))
			regeneratedInterval = Integer.parseInt(rule.getRegeneratedInterval());
		else
			regeneratedInterval = Integer.parseInt(defaultRegeneratedInterval);
		
		String finalPath = defaultGeneratedPath;
		
		if(!UtilHelper.isEmpty(generatedPath))
			finalPath = generatedPath;
			
		if(!UtilHelper.isEmpty(finalPath))
			fileSavePath.append(File.separator).append(finalPath);
		
		if(Rule.DISPATCHER_INCLUDE.equalsIgnoreCase(dispatcher))
		{
			List<IncludeRule> rules = rule.getIncludeRules();
			
			if(!UtilHelper.isEmpty(rules))
			{
				for(IncludeRule includeRule : rules)
				{
					String from = includeRule.getFrom();
					String to = includeRule.getTo();
					StringBuffer filePath = new StringBuffer(fileSavePath);
					
					String tmpUrl = baseUrl.substring(0, baseUrl.lastIndexOf(matcherUrl));
					
					StringBuilder url = new StringBuilder(tmpUrl);
					
					if(!UtilHelper.isEmpty(from))
						url.append("/").append(from);
					
					if(!UtilHelper.isEmpty(to))
						filePath.append(File.separator).append(to);
					else
					{
						filePath.append(File.separator);
						if(from.contains("."))
						{
							from = from.substring(0, from.lastIndexOf("."));
						}
						
						filePath.append(from.replace("/", "\\")).append(".htm");
					}
					
					handler.parse2Html(url.toString(), filePath.toString(), regeneratedInterval);
				}
			}
		}else
		{
			
			String forward = rule.getForward();
			
			if(!UtilHelper.isEmpty(forward))
				fileSavePath.append(File.separator).append(forward.replace("/", "\\"));
			
			handler.parse2Html(baseUrl.toString(), fileSavePath.toString(), regeneratedInterval);
			handler.releaseConnection();
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(hsRequest.getContextPath() + "/" + finalPath + "/" + forward);
			
			requestDispatcher.forward(request, response);
		}
		
	}
	
	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration()
	{
		return configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Configuration configuration)
	{
		this.configuration = configuration;
	}

	public static void main(String[] str)
	{
//		ConfigLoad load = new ConfigLoad();
//		
//		load.loadConf(null, null);
		
		Pattern pattern = Pattern.compile("^/abc$");
		
		Matcher matcher = pattern.matcher("/abc");
		
		System.out.println(matcher.find());
	}
}
