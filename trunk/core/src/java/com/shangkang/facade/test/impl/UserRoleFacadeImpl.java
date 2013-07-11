/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-2-26 10:33:24
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

import com.shangkang.bo.UserRole;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.facade.test.UserRoleFacade;
import com.shangkang.service.UserRoleService;

@Component("userRoleFacade")
public class UserRoleFacadeImpl implements UserRoleFacade {

	private UserRoleService userRoleService;
	
	@Autowired
	public void setUserRoleService(UserRoleService userRoleService)
	{
		this.userRoleService = userRoleService;
	}
	
	/**
	 * 通过主键查询实体对象
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public UserRole getByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return userRoleService.getByPK(primaryKey);
	}

	/**
	 * 查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<UserRole> list() throws ServiceException
	{
		return userRoleService.list();
	}

	/**
	 * 根据查询条件查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<UserRole> listByProperty(UserRole userRole)
			throws ServiceException
	{
		return userRoleService.listByProperty(userRole);
	}
	
	/**
	 * 根据查询条件查询分页记录
	 * @return
	 * @throws ServiceException
	 */
	public Pagination<UserRole> listPaginationByProperty(Pagination<UserRole> pagination, UserRole userRole)
			throws ServiceException
	{
		return userRoleService.listPaginationByProperty(pagination, userRole);
	}

	/**
	 * 根据主键删除记录
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return userRoleService.deleteByPK(primaryKey);
	}
	
	/**
	 * 根据多个主键删除记录
	 * @param primaryKeys
	 * @throws ServiceException
	 */
	public void deleteByPKeys(List<java.lang.Long> primaryKeys) throws ServiceException
	{
		userRoleService.deleteByPKeys(primaryKeys);
	}
	
	/**
	 * 根据传入参数删除记录
	 * @param userRole
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByProperty(UserRole userRole) throws ServiceException
	{
		return userRoleService.deleteByProperty(userRole);
	}

	/**
	 * 保存记录
	 * @param userRole
	 * @return
	 * @throws ServiceException
	 */
	public void save(UserRole userRole) throws ServiceException
	{
		userRoleService.save(userRole);
	}

	/**
	 * 更新记录
	 * @param userRole
	 * @return
	 * @throws ServiceException
	 */
	public int update(UserRole userRole) throws ServiceException
	{
		return userRoleService.update(userRole);
	}

	/**
	 * 根据条件查询记录条数
	 * @param userRole
	 * @return
	 * @throws ServiceException
	 */
	public int findByCount(UserRole userRole) throws ServiceException
	{
		return userRoleService.findByCount(userRole);
	}
}
