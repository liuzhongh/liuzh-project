/**
 * COPYRIGHT (C) 2013 Liuzh. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of Liuzh.
 *
 * Created By: liuzh
 * Created On: 2014年1月25日
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
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.thrift.test.PersonService.Iface;
import com.thrift.test.pojo.Person;

public class PersonServiceImpl implements Iface {

	@Override
	public String getValue() throws TException
	{
		return "it is test奶奶的";
	}

	@Override
	public Person getPerson(Person person) throws TException
	{
		person.setName(person.getName() + "sdfsfsf");
		System.out.println(person.getName());
		return person;
	}

	public static void main(String[] s)
	{
		try
		{
			// 设置服务器端口为9988
			TServerSocket serverTransport = new TServerSocket(9988);
			// 设置协议工厂为TBinaryProtocol.Factory
			Factory proFactory = new TBinaryProtocol.Factory();
			
			TMultiplexedProcessor processor = new TMultiplexedProcessor();
			
			/*// 关联处理器与Hello服务的实现
			TProcessor processor = new CalService.Processor<CalService.Iface>(
					new ThriftTestService());
			TServer.Args tArgs = new TServer.Args(serverTransport);
			tArgs.processor(processor);
			tArgs.protocolFactory(proFactory);*/
			// 使用TThreadPoolServer
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
			
			processor.registerProcessor("PERSON_SERVICE", new PersonService.Processor<PersonService.Iface>(new PersonServiceImpl()));
			
			System.out.println("Start server on port 9988....");
			server.serve();
		} catch (TTransportException e)
		{
			e.printStackTrace();
		}
	}
}
