/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Dec 20, 2010
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.platform.test.core;

import com.platform.test.GenericTestCase;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.core.resources.MessageResource;

public class ServiceExceptionTest extends GenericTestCase{

	public void setUp() throws Exception
	{
		super.setUp();
	}
	
	public void testServiceException() throws ServiceException
	{
		if(true) throw new ServiceException();
	}
	
	public void testServiceException1() throws ServiceException
	{
		if(true) throw new ServiceException(MessageResource.SYSTEM_ERROR);
	}
	
	public void testServiceException2() throws ServiceException
	{
		try
		{
			int t = 4/ 0;
			
			System.out.println(t);
		}
		catch(Exception e)
		{
			if(true) throw new ServiceException(MessageResource.ERROR_MESSAGE_TEST, "tewe", "dfd");
		}
	}
}
