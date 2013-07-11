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
package com.shangkang.dto;

import java.util.List;

import com.shangkang.bo.SystemMenu;

public class SystemMenuTreeDto extends SystemMenu {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private List<SystemMenuTreeDto> children;

	public List<SystemMenuTreeDto> getChildren()
	{
		return children;
	}

	public void setChildren(List<SystemMenuTreeDto> children)
	{
		this.children = children;
	}
	
	public void parser2SystemMenu(SystemMenu systemMenu)
	{
		if(systemMenu == null)
			return;
		
		systemMenu.setMenuId(this.getMenuId());
		systemMenu.setMenuCode(this.getMenuCode());
		systemMenu.setMenuName(this.getMenuName());
		systemMenu.setMenuOrder(this.getMenuOrder());
		systemMenu.setParentMenuId(this.getParentMenuId());
		systemMenu.setMenuIcon(this.getMenuIcon());
		systemMenu.setPageUrl(this.getPageUrl());
		systemMenu.setRemarks(this.getRemarks());
		systemMenu.setCreator(this.getCreator());
		systemMenu.setCreateTime(this.getCreateTime());
		systemMenu.setUpdateUser(this.getUpdateUser());
		systemMenu.setUpdateTime(this.getUpdateTime());
	}
	
	public void parserSystemMenu2Dto(SystemMenu systemMenu)
	{
		if(systemMenu == null)
			return;
		
		this.setMenuId(systemMenu.getMenuId());
		this.setMenuCode(systemMenu.getMenuCode());
		this.setMenuName(systemMenu.getMenuName());
		this.setMenuOrder(systemMenu.getMenuOrder());
		this.setParentMenuId(systemMenu.getParentMenuId());
		this.setMenuIcon(systemMenu.getMenuIcon());
		this.setPageUrl(systemMenu.getPageUrl());
		this.setRemarks(systemMenu.getRemarks());
		this.setCreator(systemMenu.getCreator());
		this.setCreateTime(systemMenu.getCreateTime());
		this.setUpdateUser(systemMenu.getUpdateUser());
		this.setUpdateTime(systemMenu.getUpdateTime());
	}

	@Override
	public String toString()
	{
		return "SystemMenuTreeDto [children=" + children + ", toString()="
				+ super.toString() + "]";
	}
	
}
