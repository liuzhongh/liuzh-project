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
package com.core.cache.config;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.core.cache.BaseRuleValidated;
import com.core.cache.Rule;
import com.shangkang.tools.PropertyHelper;

public class ConfigLoad {

	private static Log	log	= LogFactory.getLog(ConfigLoad.class);
	
	public final String CONFIG_FILE = "cache.xml";
	
	private Configuration configuration;
	
	private BaseRuleValidated validated;
	
	public void init(InputStream inputStream, String baseGeneratedPath, String cacheType)
	{
		if(inputStream == null)
		{
			inputStream = PropertyHelper.getInputStream(CONFIG_FILE);
		}
		
		this.loadConf(inputStream, baseGeneratedPath, cacheType);
		
		validated = new BaseRuleValidated();
	}
	
	private void loadConf(InputStream inputStream, String baseGeneratedPath, String cacheType)
	{
		configuration = new Configuration(inputStream, "", cacheType);
	}
	
	public Rule validateRequestUri(String requestUri)
	{
		return validated.validateRequestUri(requestUri, configuration.getRuleMap());
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
