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
package com.shangkang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangkang.bo.SystemMenu;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.mapper.SystemMenuMapper;

@Service("systemMenuService")
public class SystemMenuService {

	private SystemMenuMapper	systemMenuMapper;

	@Autowired
	public void setSystemMenuMapper(SystemMenuMapper systemMenuMapper)
	{
		this.systemMenuMapper = systemMenuMapper;
	}
	
	/**
	 * 通过主键查询实体对象
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public SystemMenu getByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return systemMenuMapper.getByPK(primaryKey);
	}

	/**
	 * 查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<SystemMenu> list() throws ServiceException
	{
		return systemMenuMapper.list();
	}

	/**
	 * 根据查询条件查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<SystemMenu> listByProperty(SystemMenu systemMenu)
			throws ServiceException
	{
		return systemMenuMapper.listByProperty(systemMenu);
	}
	
	/**
	 * 根据查询条件查询分页记录
	 * @return
	 * @throws ServiceException
	 */
	public Pagination<SystemMenu> listPaginationByProperty(Pagination<SystemMenu> pagination, SystemMenu systemMenu)
			throws ServiceException
	{
		List<SystemMenu> list = systemMenuMapper.listPaginationByProperty(pagination, systemMenu);
		
		pagination.setResultList(list);
		
		return pagination;
	}

	/**
	 * 根据主键删除记录
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByPK(java.lang.Long primaryKey) throws ServiceException
	{
		return systemMenuMapper.deleteByPK(primaryKey);
	}
	
	/**
	 * 根据多个主键删除记录
	 * @param primaryKeys
	 * @throws ServiceException
	 */
	public void deleteByPKeys(List<java.lang.Long> primaryKeys) throws ServiceException
	{
		systemMenuMapper.deleteByPKeys(primaryKeys);
	}
	
	/**
	 * 根据传入参数删除记录
	 * @param systemMenu
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByProperty(SystemMenu systemMenu) throws ServiceException
	{
		return systemMenuMapper.deleteByProperty(systemMenu);
	}

	/**
	 * 保存记录
	 * @param systemMenu
	 * @return
	 * @throws ServiceException
	 */
	public void save(SystemMenu systemMenu) throws ServiceException
	{
		systemMenuMapper.save(systemMenu);
	}

	/**
	 * 更新记录
	 * @param systemMenu
	 * @return
	 * @throws ServiceException
	 */
	public int update(SystemMenu systemMenu) throws ServiceException
	{
		return systemMenuMapper.update(systemMenu);
	}

	/**
	 * 根据条件查询记录条数
	 * @param systemMenu
	 * @return
	 * @throws ServiceException
	 */
	public int findByCount(SystemMenu systemMenu) throws ServiceException
	{
		return systemMenuMapper.findByCount(systemMenu);
	}
}