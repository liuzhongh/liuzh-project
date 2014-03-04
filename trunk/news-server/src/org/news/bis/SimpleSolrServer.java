/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-8-14
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.news.bis;

import java.util.Properties;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

import com.shangkang.tools.UtilHelper;

public class SimpleSolrServer {

	private String coreName;
	
	public SimpleSolrServer()
	{
	}
	
	public SimpleSolrServer(String coreName)
	{
		this.coreName = coreName;
	}
	
	public HttpSolrServer getServer()
	{
		Properties properties = SolrServerProperties.getInstance().getProperties();
		
		String url = properties.getProperty("solr.server.url");
		int timeout = Integer.parseInt(properties.getProperty("solr.server.timeout"));
		int connectionTimeout = Integer.parseInt(properties.getProperty("solr.server.connection.timeout"));
		int maxConnectionsPerHost = Integer.parseInt(properties.getProperty("solr.server.max.connections.per.host"));
		int maxTotalConnections = Integer.parseInt(properties.getProperty("solr.server.max.total.connections"));
		boolean followRedirects = Boolean.parseBoolean(properties.getProperty("solr.server.follow.redirects"));
		boolean allowCompression = 	Boolean.parseBoolean(properties.getProperty("solr.server.allow.compression"));	
		int maxRetries = Integer.parseInt(properties.getProperty("solr.server.max.retries"));
		
		if(!UtilHelper.isEmpty(coreName))
			url = url + "/" + coreName;
		
		HttpSolrServer server = new HttpSolrServer(url);
		server.setSoTimeout(timeout); // socket read timeout
		server.setConnectionTimeout(connectionTimeout);
		server.setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
		server.setMaxTotalConnections(maxTotalConnections);
		server.setFollowRedirects(followRedirects); // defaults to false
		// allowCompression defaults to false.
		// Server side must support gzip or deflate for this to have any
		// effect.
		server.setAllowCompression(allowCompression);
		server.setMaxRetries(maxRetries); // defaults to 0. > 1 not recommended.

		return server;
	}
	
	public static HttpSolrServer getHttpSolrServer()
	{
		return getHttpSolrServer(null);
	}
	
	public static HttpSolrServer getHttpSolrServer(String coreName)
	{
		SimpleSolrServer solrServer = new SimpleSolrServer(coreName);
		
		return solrServer.getServer();
	}
}
