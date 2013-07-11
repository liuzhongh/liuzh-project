/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-2-26 10:33:23
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.facade.test.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shangkang.bo.SystemMenu;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.dto.SystemMenuTreeDto;
import com.shangkang.facade.test.SystemMenuFacade;
import com.shangkang.service.SystemMenuManager;
import com.shangkang.service.SystemMenuService;

@Component("systemMenuFacade")
public class SystemMenuFacadeImpl implements SystemMenuFacade {

	@Autowired
	private SystemMenuService systemMenuService;
	
	@Autowired
	private SystemMenuManager systemMenuManager;
	
	/**
	 * 通过主键查询实体对象
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public SystemMenu getByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return systemMenuService.getByPK(primaryKey);
	}

	/**
	 * 查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<SystemMenu> list() throws ServiceException
	{
		return systemMenuService.list();
	}

	/**
	 * 根据查询条件查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<SystemMenu> listByProperty(SystemMenu systemMenu)
			throws ServiceException
	{
		return systemMenuService.listByProperty(systemMenu);
	}
	
	/**
	 * 根据查询条件查询分页记录
	 * @return
	 * @throws ServiceException
	 */
	public Pagination<SystemMenu> listPaginationByProperty(Pagination<SystemMenu> pagination, SystemMenu systemMenu)
			throws ServiceException
	{
		return systemMenuService.listPaginationByProperty(pagination, systemMenu);
	}

	/**
	 * 根据主键删除记录
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return systemMenuService.deleteByPK(primaryKey);
	}
	
	/**
	 * 根据多个主键删除记录
	 * @param primaryKeys
	 * @throws ServiceException
	 */
	public void deleteByPKeys(List<java.lang.Long> primaryKeys) throws ServiceException
	{
		systemMenuService.deleteByPKeys(primaryKeys);
	}
	
	/**
	 * 根据传入参数删除记录
	 * @param systemMenu
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByProperty(SystemMenu systemMenu) throws ServiceException
	{
		return systemMenuService.deleteByProperty(systemMenu);
	}

	/**
	 * 保存记录
	 * @param systemMenu
	 * @return
	 * @throws ServiceException
	 */
	public void save(SystemMenu systemMenu) throws ServiceException
	{
		systemMenuService.save(systemMenu);
	}

	/**
	 * 更新记录
	 * @param systemMenu
	 * @return
	 * @throws ServiceException
	 */
	public int update(SystemMenu systemMenu) throws ServiceException
	{
		return systemMenuService.update(systemMenu);
	}

	/**
	 * 根据条件查询记录条数
	 * @param systemMenu
	 * @return
	 * @throws ServiceException
	 */
	public int findByCount(SystemMenu systemMenu) throws ServiceException
	{
		return systemMenuService.findByCount(systemMenu);
	}
	
	/**
	 * 根据userId, parentMenuId取得user相对应的菜单树
	 * @param userId
	 * @param parentMenuId
	 * @return
	 * @throws ServiceException
	 */
	public List<SystemMenuTreeDto> findMenuTreeDto4UserId(Long userId, Long parentMenuId) throws ServiceException
	{
		return systemMenuManager.findMenuTreeDto4UserId(userId, parentMenuId);
	}
}
