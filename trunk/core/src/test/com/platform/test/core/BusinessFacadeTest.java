/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Dec 18, 2010
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.platform.test.core;


import com.platform.test.GenericTestCase;
import com.shangkang.bo.Information;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.facade.test.BusinessFacade;

public class BusinessFacadeTest extends GenericTestCase {

	private BusinessFacade businessFacade;
	
	protected void setUp() throws Exception {
		super.setUp();
		businessFacade = (BusinessFacade) this.getBean("businessFacade");
	}
	
	public void testGetByPK() throws ServiceException
	{
		Information info = businessFacade.getByPK(1);
		
		System.out.println(info.getName());
	}
	
	public void testInsert() throws ServiceException
	{
		Information infor = new Information();
		
		infor.setName("3333333");
		infor.setPassword("pssdfsdsfd2");
		
		Information infor1 = new Information();
		
		infor1.setName("3434");
		infor1.setPassword("434");
		
		businessFacade.insert(infor1, infor);
		
		System.out.println(infor.getId());
	}
}
