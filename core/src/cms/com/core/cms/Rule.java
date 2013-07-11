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
package com.core.cms;

import java.util.ArrayList;
import java.util.List;

public class Rule {

	public final static String DISPATCHER_INCLUDE = "include";
	private String requestUri;
	
	private String generatedPath;

	private String regeneratedInterval;
	
	private boolean requestUriCaseSensitive;
	
	private String dispatcher = DISPATCHER_INCLUDE;
	
	private String forward;
	
	private List<IncludeRule> includeRules = new ArrayList<IncludeRule>();

	/**
	 * @return the generatedPath
	 */
	public String getGeneratedPath()
	{
		return generatedPath;
	}

	/**
	 * @param generatedPath the generatedPath to set
	 */
	public void setGeneratedPath(String generatedPath)
	{
		this.generatedPath = generatedPath;
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

	/**
	 * @return the dispatcher
	 */
	public String getDispatcher()
	{
		return dispatcher;
	}

	/**
	 * @param dispatcher the dispatcher to set
	 */
	public void setDispatcher(String dispatcher)
	{
		this.dispatcher = dispatcher;
	}

	/**
	 * @return the forward
	 */
	public String getForward()
	{
		return forward;
	}

	/**
	 * @param forward the forward to set
	 */
	public void setForward(String forward)
	{
		this.forward = forward;
	}

	/**
	 * @return the includeRules
	 */
	public List<IncludeRule> getIncludeRules()
	{
		return includeRules;
	}

	public void addIncludeRule(IncludeRule includeRule)
	{
		this.includeRules.add(includeRule);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Rule [requestUri=" + requestUri + ", generatedPath="
				+ generatedPath + ", regeneratedInterval="
				+ regeneratedInterval + ", requestUriCaseSensitive="
				+ requestUriCaseSensitive + ", dispatcher=" + dispatcher
				+ ", forward=" + forward + ", includeRules=" + includeRules
				+ "]";
	}
	
}
