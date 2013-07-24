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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangkang.tools.DateHelper;

public class CmsHandler {

	private static Log	log	= LogFactory.getLog(CmsHandler.class);
	
	private HttpClient httpClient;
	
	private PostMethod postMethod;

	public CmsHandler()
	{
		super();
		this.httpClient = new HttpClient();
	}

	public void parse2Html(String url, String htmlPath, int regeneratedInterval)
	{
		
		BufferedWriter writer = null;
		File file = null;
		try
		{
			// postMethod.addRequestHeader("accept-encoding", "gzip,deflate");
			// GetMethod doget =new GetMethod(url);
//			if(true) return;
			
			log.debug("parseUrl = " + url + " htmlPath = " + htmlPath);
			
			file = new File(htmlPath);
			
			if(file.exists())
			{
				long now = DateHelper.getMillis(DateHelper.now());
				long last = file.lastModified();
				long differ = now - last;
				
				log.debug("Time now: " + now + " lastModified: " + last + " regeneratedInterval: "  + regeneratedInterval * 60000 + " differ times: " + differ);
				
				if(regeneratedInterval <= 0 || differ < regeneratedInterval * 60000)
					return;
			}
			
			postMethod = new PostMethod(url);
			postMethod.setParameter("isHttpClientRequest", "Y");
			
			int statusCode = httpClient.executeMethod(postMethod);
			
			if (statusCode != HttpStatus.SC_OK)
			{
				log.error("Http connect error code:" + statusCode + " url:" + url);
				return;
			}
			// byte[] body = doget.getResponseBody();

			file = new File(htmlPath.substring(0,
					htmlPath.lastIndexOf(File.separator)));

			if (!file.exists())
			{
				file.mkdirs();
			}

			OutputStreamWriter outStreamWriter = new OutputStreamWriter(
					new FileOutputStream(htmlPath), "UTF-8");
			writer = new BufferedWriter(outStreamWriter);
			writer.write(postMethod.getResponseBodyAsString());

			log.debug("Generate file :" + htmlPath + " success.");
		} catch (HttpException e)
		{
			log.error(e);
			e.printStackTrace();
		} catch (IOException e)
		{
			log.error(e);
			e.printStackTrace();
		} finally
		{

			try
			{
				if (writer != null)
					writer.close();
				if (postMethod != null)
					postMethod.releaseConnection();
				
				file = null;
			} catch (IOException e)
			{
				log.error(e);
			}

		}

	}
	
	public void releaseConnection()
	{
		if (postMethod != null)
			postMethod.releaseConnection();

		httpClient = null;
	}
}
