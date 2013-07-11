/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-4-28
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangkang.bo.SystemMenu;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.dto.SystemMenuTreeDto;
import com.shangkang.mapper.SystemMenuMapper;
import com.shangkang.tools.UtilHelper;

@Service("systemMenuManager")
public class SystemMenuManager {

	private SystemMenuMapper systemMenuMapper;

	@Autowired
	public void setSystemMenuMapper(SystemMenuMapper systemMenuMapper)
	{
		this.systemMenuMapper = systemMenuMapper;
	}
	
	/**
	 * 根据userId取得系统根菜单
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<SystemMenu> findSystemMenuRoot(Long userId) throws ServiceException
	{
		return systemMenuMapper.findRootMenu4UserId(userId);
	}
	
	/**
	 * 根据父菜单ID取得树结构
	 * @param parentMenuId
	 * @return
	 * @throws ServiceException
	 */
	public List<SystemMenuTreeDto> getSystemMenuTree(Long parentMenuId) throws ServiceException
	{
		SystemMenu systemMenu = new SystemMenu();
		
		systemMenu.setParentMenuId(parentMenuId);
		
		List<SystemMenu> list = systemMenuMapper.listByProperty(systemMenu);
		
		List<SystemMenuTreeDto> treeList = new ArrayList<SystemMenuTreeDto>();
		
		if(!UtilHelper.isEmpty(list))
		{
			for(SystemMenu menu : list)
			{
				SystemMenuTreeDto tree = new SystemMenuTreeDto();
				
				tree.parserSystemMenu2Dto(menu);
				
				List<SystemMenuTreeDto> dtos = this.getSystemMenuTree(menu.getMenuId());
				
				if(!UtilHelper.isEmpty(dtos))
				{
					tree.setChildren(dtos);
				}
				
				treeList.add(tree);
			}
		}
		
		return treeList;
		
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
		List<SystemMenu> list = systemMenuMapper.findMenu4UserIdAndParentMenuId(userId, parentMenuId);
		
		return doGetTreeFromSystemMenus(list, parentMenuId);
	}
	
	private List<SystemMenuTreeDto> doGetTreeFromSystemMenus(List<SystemMenu> list, Long parentMenuId) throws ServiceException
	{
		if(UtilHelper.isEmpty(list))
		{
			return null;
		}
		
		Map<Long, List<SystemMenuTreeDto>> map = new HashMap<Long, List<SystemMenuTreeDto>>();
		
		for(SystemMenu menu : list)
		{
			Long key = menu.getParentMenuId();
			SystemMenuTreeDto tree = new SystemMenuTreeDto();
			
			tree.parserSystemMenu2Dto(menu);
			
			if(map.containsKey(key))
			{
				List<SystemMenuTreeDto> menus = map.get(key);
				
				tree.parserSystemMenu2Dto(menu);
				
				menus.add(tree);
			}else
			{
				List<SystemMenuTreeDto> menus = new ArrayList<SystemMenuTreeDto>();
				
				menus.add(tree);
				map.put(key, menus);
			}
		}
		
		if(UtilHelper.isEmpty(map))
		{
			return null;
		}
		
		List<SystemMenuTreeDto> treeDtos = map.get(parentMenuId);
		
		map.remove(parentMenuId);
		
		this.doGetChildren(treeDtos, map);
		
		return treeDtos;
	}
	
	private void doGetChildren(List<SystemMenuTreeDto> dtos, Map<Long, List<SystemMenuTreeDto>> map) throws ServiceException
	{
		if(UtilHelper.isEmpty(dtos) || UtilHelper.isEmpty(map))
			return;
		
		for(SystemMenuTreeDto dto : dtos)
		{
			Long key = dto.getMenuId();
			
			List<SystemMenuTreeDto> children = map.get(key);
			map.remove(key);
			
			doGetChildren(children, map);
			
			dto.setChildren(children);
		}
	}
}
