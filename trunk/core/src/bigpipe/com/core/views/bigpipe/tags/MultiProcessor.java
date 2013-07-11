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

import java.io.Writer;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class MultiProcessor extends Component {

	private Integer				pageLetNum;

	private HttpServletRequest	request;

	/**
	 * @return the pageLetNum
	 */
	public Integer getPageLetNum()
	{
		return pageLetNum;
	}

	/**
	 * @param pageLetNum
	 *            the pageLetNum to set
	 */
	public void setPageLetNum(Integer pageLetNum)
	{
		this.pageLetNum = pageLetNum;
	}

	public MultiProcessor(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response)
	{
		super(stack);
		this.request = request;
	}

	@Override
	public boolean end(Writer writer, String body)
	{
		boolean end = super.end(writer, body);
		CountDownLatch c = (CountDownLatch) request
				.getAttribute(MultiProcessorTag.COUNT_DOWN);
		try
		{
			c.await();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return end;
	}

}
