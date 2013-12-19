/**
 * COPYRIGHT (C) 2013 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013年11月16日
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.im.singleton;

import java.util.Properties;

import com.shangkang.tools.PropertyHelper;

public class ConfigureSingleton {

	public static final String				FILE_NAME	= "config.properties";

	private static Properties				properties;
	
	public ConfigureSingleton()
	{
		properties = PropertyHelper.getProperty(FILE_NAME);
	}

	public static ConfigureSingleton getSingleton()
	{
		return SingletonContainer.singleton;
	}

	private static class SingletonContainer {
		private static ConfigureSingleton	singleton	= new ConfigureSingleton();
	}

	public Properties getProperties()
	{
		return properties;
	}
	
	public Object getValue(String key)
	{
		return getProperties().get(key);
	}
}
