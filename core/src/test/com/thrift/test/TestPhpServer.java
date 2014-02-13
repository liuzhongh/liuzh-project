/**
 * COPYRIGHT (C) 2013 Liuzh. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of Liuzh.
 *
 * Created By: liuzh
 * Created On: 2014年2月9日
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.thrift.test;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;

import com.thrift.test.pojo.Person;

public class TestPhpServer {

	public static void main(String[] args)
	{
		try
		{
			TTransport transport = new THttpClient("http://192.168.13.158/thriftServer.php?srv=PersonServiceImpl");
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);

			PersonService.Client client = new PersonService.Client(protocol);

			System.out.println(client.getValue());
			Person person = new Person();
			
			person.setAge(12);
			person.setName("name1");
			person.setPasswod("password");
			
			Person p = client.getPerson(person);
			
			System.out.println(p);

			transport.close();
		} catch (TException x)
		{
			x.printStackTrace();
		}
	}

}
