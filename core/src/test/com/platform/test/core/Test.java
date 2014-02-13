/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-1-24
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.platform.test.core;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.caucho.hessian.client.HessianProxyFactory;
import com.shangkang.facade.HessianTestFacade;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*long l = System.currentTimeMillis();
		System.out.println(l);
		String url = "http://localhost:8080/hello.remote";  
        HessianProxyFactory factory = new HessianProxyFactory(); 
        factory.setOverloadEnabled(true);
        
        HessianTestFacade businessFacade;
		try
		{
			businessFacade = (HessianTestFacade) factory.create(HessianTestFacade.class, url);
			
			System.out.println(businessFacade.test(121212));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
        
		System.out.println("times = " + (System.currentTimeMillis() - l));
		
		PostMethod	postMethod = new PostMethod("http://search.3kw.com.cn/im/phoneCall.jsp?n=15074814855&t=test111");
		HttpClient	httpClient = new HttpClient();
		
		try
		{
			int statusCode = httpClient.executeMethod(postMethod);
			
			System.out.println(postMethod.getResponseBodyAsString());
			
			postMethod.releaseConnection();
		} catch (HttpException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}*/
		
		for(int i = 0; i < 2000; i++)
        {
			Test t = new Test();
			
			t.new ThreadHttp(i + "test").start();
        }
	}

	class ThreadHttp extends Thread
	{
		private String value;
		
		

		public ThreadHttp(String value)
		{
			super();
			this.value = value;
		}

		@Override
		public void run()
		{
			GetMethod	postMethod = new GetMethod("http://192.168.13.158:8080/hello-" + value + ".html");
			HttpClient	httpClient = new HttpClient();
			
			try
			{
				int statusCode = httpClient.executeMethod(postMethod);
				
				System.out.println(postMethod.getResponseBodyAsString());
				
				postMethod.releaseConnection();
			} catch (HttpException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}
}
