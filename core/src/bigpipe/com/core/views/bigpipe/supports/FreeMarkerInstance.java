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
package com.core.views.bigpipe.supports;

import javax.servlet.ServletContext;

import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.views.freemarker.FreemarkerManager;

import freemarker.template.Configuration;

public class FreeMarkerInstance {

	private static FreeMarkerInstance	instance	= null;

	private static Configuration		config;

	private static FreemarkerManager	freemarkerManager;

	/**
	 * @return the freemarkerManager
	 */
	public FreemarkerManager getFreemarkerManager()
	{
		return freemarkerManager;
	}

	public Configuration getConfiguration()
	{
		return config;
	}

	public static FreeMarkerInstance instance(ServletContext servletContext)
	{
		try
		{
			if (instance == null)
			{
				freemarkerManager = Dispatcher.getInstance().getContainer().getInstance(FreemarkerManager.class);
				
				config = freemarkerManager
						.getConfiguration(servletContext);

				instance = new FreeMarkerInstance();
			}
			return instance;
		} finally
		{
		}
	}
}
