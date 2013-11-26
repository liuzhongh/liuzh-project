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
package com.core.cache;

import java.util.ArrayList;
import java.util.List;


public class Rule {

	public static final String DEFAULT_CACHE_TYPE = "page";
	
	public static final String CACHE_TYPE_FRAGMENT = "fragment";
	
	private String requestUri;
	
	private String cacheType;
	
	private String namespace;

	private String regeneratedInterval;
	
	private boolean requestUriCaseSensitive;
	
	private List<String> includes = new ArrayList<String>();
	
	/**
	 * @return the namespace
	 */
	public String getNamespace()
	{
		return namespace;
	}

	/**
	 * @param namespace the namespace to set
	 */
	public void setNamespace(String namespace)
	{
		this.namespace = namespace;
	}

	/**
	 * @return the regeneratedInterval
	 */
	public String getRegeneratedInterval()
	{
		return regeneratedInterval;
	}

	/**
	 * @param regeneratedInterval the regeneratedInterval to set
	 */
	public void setRegeneratedInterval(String regeneratedInterval)
	{
		this.regeneratedInterval = regeneratedInterval;
	}

	/**
	 * @return the requestUri
	 */
	public String getRequestUri()
	{
		return requestUri;
	}

	/**
	 * @param requestUri the requestUri to set
	 */
	public void setRequestUri(String requestUri)
	{
		this.requestUri = requestUri;
	}

	/**
	 * @return the requestUriCaseSensitive
	 */
	public boolean isRequestUriCaseSensitive()
	{
		return requestUriCaseSensitive;
	}

	/**
	 * @param requestUriCaseSensitive the requestUriCaseSensitive to set
	 */
	public void setRequestUriCaseSensitive(boolean requestUriCaseSensitive)
	{
		this.requestUriCaseSensitive = requestUriCaseSensitive;
	}

	public String getCacheType()
	{
		return cacheType;
	}

	public void setCacheType(String cacheType)
	{
		this.cacheType = cacheType;
	}

	public List<String> getIncludes()
	{
		return includes;
	}
	
	public void addInclude(String include)
	{
		includes.add(include);
	}

	public void setIncludes(List<String> includes)
	{
		this.includes = includes;
	}

	@Override
	public String toString()
	{
		return "Rule [requestUri=" + requestUri + ", cacheType=" + cacheType
				+ ", namespace=" + namespace + ", regeneratedInterval="
				+ regeneratedInterval + ", requestUriCaseSensitive="
				+ requestUriCaseSensitive + ", includes=" + includes + "]";
	}
	
}
