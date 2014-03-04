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
package org.news.test;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.news.services.NewsService;

public class ClientTest {

	public static void main(String[] str)
	{
		try
		{
			//设置调用的服务器为本地，端口为9988
            TTransport transport = new TSocket("192.168.13.158", 9988);
            transport.open();
            //设置传输协议为TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(transport);
            
            TMultiplexedProtocol processor = new TMultiplexedProtocol(protocol, "NEWS_SERVICE");
            
            NewsService.Client client = new NewsService.Client(processor);
            
			String result = client.findResult("");
			
			System.out.println("result = " + result);
			
			transport.close();
		} catch (TException e)
		{
			e.printStackTrace();
		}
	}
}
