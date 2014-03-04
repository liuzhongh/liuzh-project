/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-12-5
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.news.bis;

import java.util.Properties;

import com.shangkang.tools.PropertyHelper;

public class SolrServerProperties {

	private static SolrServerProperties		solrServerProperties	= null;
	private static volatile Properties properties		= null;

	public static synchronized SolrServerProperties getInstance()
	{
		if (solrServerProperties == null)
		{
			solrServerProperties = new SolrServerProperties();
		}
		return solrServerProperties;
	}

	public Properties getProperties()
	{
		if (properties == null)
		{
			properties = PropertyHelper.getProperty("solr.properties");
		}
		
		return properties;
	}
}
