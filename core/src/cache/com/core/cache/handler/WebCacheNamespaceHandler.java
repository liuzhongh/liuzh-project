/**
 * COPYRIGHT (C) 2013 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013年11月23日
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.cache.handler;

import java.util.ArrayList;
import java.util.List;

import com.shangkang.tools.UtilHelper;

public class WebCacheNamespaceHandler {

	private static List<String> namespaces;
	
	private static CacheHandler cacheHandler;
	
	public WebCacheNamespaceHandler()
	{
		namespaces = new ArrayList<String>();
	}
	
	public static WebCacheNamespaceHandler getSingleton()
	{
		return SingletonContainer.singleton;
	}
	
	private static class SingletonContainer {
		private static WebCacheNamespaceHandler singleton = new WebCacheNamespaceHandler();
	}
	
	public void populateNamespace(String namespace)
	{
		if(UtilHelper.isEmpty(namespace))
			return;
		
		if(!namespaces.contains(namespace))
		{
			namespaces.add(namespace);
		}
	}
	
	@SuppressWarnings("static-access")
	public void populateCacheHandler(CacheHandler cacheHandler)
	{
		this.cacheHandler = cacheHandler;
	}
	
	/**
     * 将相应命名空间缓存数据清除
     * @param namespace
     */
    public void invalidateNamespace()
    {
    	if(!UtilHelper.isEmpty(namespaces))
    	{
    		for(String namespace : namespaces)
    		{
    			cacheHandler.invalidateNamespace(namespace);
    		}
    	}
    }
}
