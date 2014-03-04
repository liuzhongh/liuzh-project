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
package org.news.server;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.news.services.NewsService;
import org.news.services.impl.NewsServiceImpl;

public class Server {

	public static void main(String[] args)
	{
		try
		{
			// 设置服务器端口为9988
			TServerSocket serverTransport = new TServerSocket(9988);

			TMultiplexedProcessor processor = new TMultiplexedProcessor();
			
			/*// 关联处理器与Hello服务的实现
			TProcessor processor = new CalService.Processor<CalService.Iface>(
					new ThriftTestService());
			TServer.Args tArgs = new TServer.Args(serverTransport);
			tArgs.processor(processor);
			tArgs.protocolFactory(proFactory);*/
			// 使用TThreadPoolServer
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
			
			processor.registerProcessor("NEWS_SERVICE", new NewsService.Processor<NewsService.Iface>(new NewsServiceImpl()));
			
			System.out.println("Start server on port 9988....");
			server.serve();
		} catch (TTransportException e)
		{
			e.printStackTrace();
		}
	}

}
