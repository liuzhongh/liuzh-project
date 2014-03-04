/**
 * COPYRIGHT (C) 2013 Liuzh. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of Liuzh.
 *
 * Created By: liuzh
 * Created On: 2014年2月25日
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.news.bis;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.google.gson.Gson;

public class SorlHelper {

	private static final String	CORE_NAME	= "collection1";

	public String findResult(String keyword)
	{
		try
		{
			SolrQuery sQuery = new SolrQuery();
			
			String queryParam = "*:*";
			
			HttpSolrServer solrServer = SimpleSolrServer
					.getHttpSolrServer(CORE_NAME);
			
			sQuery.setQuery(queryParam);
			sQuery.setStart(0);
			sQuery.setRows(100);
			sQuery.addSort("tstamp", ORDER.desc);
			
			QueryResponse queryResponse = solrServer.query(sQuery);
			
			SolrDocumentList docs = queryResponse.getResults();
			
			Gson gson = new Gson();
			
			String result = gson.toJson(docs);
			
			System.out.println(result);
			
			return result;
		} catch (SolrServerException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
