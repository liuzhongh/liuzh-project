/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-4-23
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.facade;

import java.util.List;
import java.util.Random;

import com.platform.test.GenericTestCase;
import com.shangkang.bo.SystemMenu;
import com.shangkang.bo.UserRole;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.dto.SystemMenuTreeDto;
import com.shangkang.facade.test.SystemMenuFacade;
import com.shangkang.facade.test.UserRoleFacade;

public class SystemMenuFacadeTest extends GenericTestCase {
 
	private SystemMenuFacade systemMenuFacade;
	private UserRoleFacade userRoleFacade;
	
	protected void setUp() throws Exception {
		super.setUp();
		systemMenuFacade = (SystemMenuFacade) this.getBean("systemMenuFacade");
		userRoleFacade = (UserRoleFacade) this.getBean("userRoleFacade");
	}
	
	public void test1() throws ServiceException
	{
		for(int i = 0; i < 5; i++)
			systemMenuFacade.getByPK(1007l);
		
		Long parentMenuId = 1007l;
		Long userId = 284l;
		List<SystemMenuTreeDto> dtos = systemMenuFacade.findMenuTreeDto4UserId(userId, parentMenuId);
		
		System.out.println(dtos);
	}
	
	public void test2() throws ServiceException
	{
		Long parentMenuId = 1007l;
		Long userId = 284l;
		
		UserRole userRole = userRoleFacade.getByPK(userId);
		Random random = new Random();
		
		userRole.setRemark("" + random.nextFloat() + "");
		
		userRoleFacade.update(userRole );
		
		List<SystemMenuTreeDto> dtos = systemMenuFacade.findMenuTreeDto4UserId(userId, parentMenuId);
		
		SystemMenu systemMenu = new SystemMenu();
		Pagination<SystemMenu> pagination = new Pagination<SystemMenu>();
		
		pagination.setPageNo(1);
		pagination.setPageSize(10);
		pagination.setPaginationFlag(true);
		
		systemMenuFacade.listPaginationByProperty(pagination , systemMenu);
	}
	
	public void test3() throws ServiceException
	{
		SystemMenu systemMenu = new SystemMenu();
		Pagination<SystemMenu> pagination = new Pagination<SystemMenu>();
		
		pagination.setPageNo(1);
		pagination.setPageSize(10);
		pagination.setPaginationFlag(true);
		
		systemMenuFacade.listPaginationByProperty(pagination , systemMenu);
	}
}
