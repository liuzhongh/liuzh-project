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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class PageLetTag extends ComponentTagSupport {

	private static final long	serialVersionUID	= 1L;

	private String				name;
	private String 				template;
	private String				contentFilterClass;
	private Map<String, Object> parameters;

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	/**
	 * @return the template
	 */
	public String getTemplate()
	{
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template)
	{
		this.template = template;
	}

	private String	dealClass;

	public void setDealClass(String dealClass)
	{
		this.dealClass = dealClass;
	}

	public String getDealClass()
	{
		return dealClass;
	}

	/**
	 * @return the contentFilterClass
	 */
	public String getContentFilterClass()
	{
		return contentFilterClass;
	}

	/**
	 * @param contentFilterClass the contentFilterClass to set
	 */
	public void setContentFilterClass(String contentFilterClass)
	{
		this.contentFilterClass = contentFilterClass;
	}

	@Override
	public Component getBean(ValueStack vs, HttpServletRequest arg1,
			HttpServletResponse arg2)
	{
		return new PageLet(vs, arg1, arg2);
	}

	protected void populateParams()
	{
		super.populateParams();
		PageLet pages = (PageLet) component;
		
		pages.setName(name);
		pages.setTemplate(template);
		pages.setDealClass(dealClass);
		pages.setContentFilterClass(contentFilterClass);
		pages.setParameters(parameters);
	}

	public Map<String, Object> getParameters()
	{
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters)
	{
		this.parameters = parameters;
	}
}
