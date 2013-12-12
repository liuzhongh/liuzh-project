/**
 * COPYRIGHT (C) 2013 Liuzh. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of Liuzh.
 *
 * Created By: liuzh
 * Created On: 2013年12月6日
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.thrift.test;

import org.apache.thrift.TException;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.remoting.thrift.ThriftProxyFactoryBean;

public class ThriftTestServiceClient {

	public static void main(String[] s)
	{
		try {
            //设置调用的服务器为本地，端口为9988
            /*TTransport transport = new TSocket("localhost", 9988);
            transport.open();
            //设置传输协议为TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(transport);
            CalService.Client client = new CalService.Client(protocol);
            int result = client.add(1212, 23424);
            
            System.out.println(result);*/
            
            for(int i = 0; i < 40000; i++)
            {
            	ThriftTestServiceClient c = new ThriftTestServiceClient();
            	
//            	c.new ThreadT(i, i*12).start();
            	
            	c.new ThreadHttp(i,  i * 10).start();
            	
            }
            
//            transport.close();
            
           /* THttpClient.Factory httpClientFactory = new THttpClient.Factory("http://192.168.13.158:8080/trans" + "/thriftTestService.remote");
            TTransport tTransport = httpClientFactory.getTransport(null);

            ThriftProxyFactoryBean proxy = new ThriftProxyFactoryBean();
            proxy.setTransport(tTransport);
            proxy.setServiceInterface(CalService.class);
            proxy.afterPropertiesSet();
            CalService.Iface cal = (CalService.Iface) proxy.getObject();
            
            System.out.println(cal.add(121212, 231131));*/
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
	}
	
	class ThreadHttp extends Thread
	{
		private int	param1;
		private int	param2;

		public ThreadHttp(int	param1, int	param2)
		{
			this.param1 = param1;
			this.param2 = param2;
		}

		@Override
		public void run()
		{
           
			try
			{
				System.out.println("p1 = " + param1 + ", p2 = " + param2);
				
				THttpClient.Factory httpClientFactory = new THttpClient.Factory("http://192.168.13.158:8080/trans" + "/thriftTestService.remote");
	            TTransport tTransport = httpClientFactory.getTransport(null);

	            ThriftProxyFactoryBean<CalService.Iface> proxy = new ThriftProxyFactoryBean<CalService.Iface>();
	            proxy.setTransport(tTransport);
	            proxy.setServiceInterface(CalService.class);
	            proxy.afterPropertiesSet();
	            CalService.Iface cal = proxy.getObject();
	            
	            System.out.println(cal.add(param1, param2));
			} catch (TException e)
			{
				e.printStackTrace();
			}
            
            
		}
	}
	
	class ThreadT extends Thread 
	{

		private int	param1;
		private int	param2;

		public ThreadT(int	param1, int	param2)
		{
			this.param1 = param1;
			this.param2 = param2;
		}

		@Override
		public void run()
		{
           
			try
			{
				System.out.println("p1 = " + param1 + ", p2 = " + param2);
				//设置调用的服务器为本地，端口为9988
	            TTransport transport = new TSocket("localhost", 9988);
	            transport.open();
	            //设置传输协议为TBinaryProtocol
	            TProtocol protocol = new TBinaryProtocol(transport);
	            
	            TMultiplexedProtocol processor = new TMultiplexedProtocol(protocol, CalServiceConstants.SERVICE_NAME);
	            
	            CalService.Client client = new CalService.Client(processor);
	            
	            TMultiplexedProtocol pro = new TMultiplexedProtocol(protocol, DivServiceConstants.SERVICE_NAME);
	            
	            DivService.Client div = new DivService.Client(pro);
	            
				int result = client.add(param1, param2);
				
				System.out.println("div = " + div.div(param1, param2));
				System.out.println(this.getName() + " : " + result);
				
				transport.close();
			} catch (TException e)
			{
				e.printStackTrace();
			}
            
            
		}
		
	}
}
