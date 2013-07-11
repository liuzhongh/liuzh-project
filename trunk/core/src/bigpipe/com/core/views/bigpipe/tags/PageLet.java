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
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.components.Component;
import org.apache.struts2.dispatcher.Dispatcher;

import com.core.views.bigpipe.supports.PageLetProcessThread;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class PageLet extends Component {

	private String				name;
	private String				template;
	private String				contentFilterClass;
	private Map<String, Object> parameters;

	private HttpServletRequest	request;
	private HttpServletResponse	response;
	
	private	Log		logger				= LogFactory.getLog(getClass());

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
	 * @param template
	 *            the template to set
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
	 * @return the parameters
	 */
	public Map<String, Object> getParameters()
	{
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<String, Object> parameters)
	{
		this.parameters = parameters;
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

	public PageLet(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response)
	{
		super(stack);
		this.request = request;
		this.response = response;
	}

	/*public boolean start(final Writer writer)
	{
		boolean result = super.start(writer);
		try
		{
			if(UtilHelper.isEmpty(name))
				name = this.hashCode() + "_";
			
			writer.write("<div id=\"" + name + "\">");
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		return result;
	}*/

	@Override
	public boolean end(final Writer writer, String body)
	{
		boolean end = super.end(writer, body);
		/*try
		{
			writer.write("</div>");
			writer.flush();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}*/
		
		final Dispatcher dispatcher = Dispatcher.getInstance();
		final ActionContext actionContext = ActionContext.getContext();
		
		if(logger.isDebugEnabled())
			logger.debug(stack);
			
		MultiProcessorTag.executor.execute(new PageLetProcessThread(request, dealClass, dispatcher, actionContext, response, name, stack, template, contentFilterClass, parameters, writer));

		return end;
	}
	
	public static void main(String[] args) {
//		(<div[^>]*>.*?</div>|.)*?
//		<script.*?</script>
		Pattern p = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>", Pattern.CASE_INSENSITIVE);
		String str = "123<scRipt id='d' type='text/javascript'> \n" +
				"jQuery(document).ready(function () 122{ " +
				" var options_productsResultDiv = {}; " +
	"options_productsResultDiv.jqueryaction = \"container\";" +
	"options_productsResultDiv.id = \"productsResultDiv\";" +
	"options_productsResultDiv.href = \"loadProducts.html\";" +
	"options_productsResultDiv.formids = \"request_param_form\";" +
	"options_productsResultDiv.listentopics = \"productListTopic\";" +
"jQuery.struts2_jquery.bind(jQuery('#productsResultDiv'),options_productsResultDiv);" +

 "});" +
"</script>456<script>sfffs是不是fs</script>?E&???<textarea>ffff</textarea><textarea>ddddffff</textarea>";
		str = str.replace("&", "&amp;").replace("</textarea", "&lt;/textarea");
		Matcher m = p.matcher(str);
		StringBuffer sb = new StringBuffer();
		while(m.find())
		{
			System.out.println(m.group());
			m.appendReplacement(sb, "");
		}
		
		m.appendTail(sb);
		System.out.println(sb);
	}
}
