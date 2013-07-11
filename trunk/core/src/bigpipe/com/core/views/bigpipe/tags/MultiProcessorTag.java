/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-12-28
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.views.bigpipe.tags;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;
import com.shangkang.tools.PropertyHelper;

public class MultiProcessorTag extends ComponentTagSupport{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	public static final String COUNT_DOWN = "COUNT_DOWN";
	
	public static ExecutorService executor;
	
	static{
		Properties properties = PropertyHelper.getProperty("bigpipe.properties");
		int nThreads = 20;
		
		if(properties != null)
		{
			String temp = (String) properties.get("number.of.threads");
			
			if(temp != null)
				nThreads = Integer.parseInt(temp);
		}
		
		executor = Executors.newFixedThreadPool(nThreads);
	}
	
	private Integer pageLetNum;

	/**
	 * @return the pageLetNum
	 */
	public Integer getPageLetNum()
	{
		return pageLetNum;
	}

	/**
	 * @param pageLetNum the pageLetNum to set
	 */
	public void setPageLetNum(Integer pageLetNum)
	{
		this.pageLetNum = pageLetNum;
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res)
	{
		CountDownLatch end = new CountDownLatch(pageLetNum);   
		req.setAttribute(COUNT_DOWN, end);
		
		return new MultiProcessor(stack, req, res);
	}
	
	protected void populateParams()
	{
		super.populateParams();
		
		MultiProcessor multiProcessor = (MultiProcessor) component;
		
		multiProcessor.setPageLetNum(pageLetNum);
	}

}
