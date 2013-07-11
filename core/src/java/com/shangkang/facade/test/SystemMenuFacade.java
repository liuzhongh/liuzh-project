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
package com.shangkang.facade.test;

import java.util.List;

import com.shangkang.bo.SystemMenu;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.dto.SystemMenuTreeDto;

public interface SystemMenuFacade {

	/**
	 * 通过主键查询实体对象
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public SystemMenu getByPK(java.lang.Long primaryKey) throws ServiceException;

	/**
	 * 查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<SystemMenu> list() throws ServiceException;

	/**
	 * 根据查询条件查询所有记录
	 * @return
	 * @throws ServiceException
	 */
	public List<SystemMenu> listByProperty(SystemMenu systemMenu)
			throws ServiceException;

	/**
	 * 根据主键删除记录
	 * @param primaryKey
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByPK(java.lang.Long primaryKey) throws ServiceException;
	
	/**
	 * 根据多个主键删除记录
	 * @param primaryKeys
	 * @throws ServiceException
	 */
	public void deleteByPKeys(List<java.lang.Long> primaryKeys) throws ServiceException;

	/**
	 * 根据传入参数删除记录
	 * @param systemMenu
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByProperty(SystemMenu systemMenu) throws ServiceException;

	/**
	 * 保存记录
	 * @param systemMenu
	 * @return
	 * @throws ServiceException
	 */
	public void save(SystemMenu systemMenu) throws ServiceException;

	/**
	 * 更新记录
	 * @param systemMenu
	 * @return
	 * @throws ServiceException
	 */
	public int update(SystemMenu systemMenu) throws ServiceException;

	/**
	 * 根据条件查询记录条数
	 * @param systemMenu
	 * @return
	 * @throws ServiceException
	 */
	public int findByCount(SystemMenu systemMenu) throws ServiceException;
	
	/**
	 * 根据查询条件查询分页记录
	 * @return
	 * @throws ServiceException
	 */
	public Pagination<SystemMenu> listPaginationByProperty(Pagination<SystemMenu> pagination, SystemMenu systemMenu)
			throws ServiceException;

	public abstract List<SystemMenuTreeDto> findMenuTreeDto4UserId(Long userId, Long parentMenuId)
			throws ServiceException;

}
