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
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class SystemMenu extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	
	  */
	private java.lang.Long menuId;

	/**
	  *	
	  */
	private java.lang.String menuCode;

	/**
	  *	
	  */
	private java.lang.String menuName;

	/**
	  *	
	  */
	private java.lang.Integer menuOrder;

	/**
	  *	
	  */
	private java.lang.Long parentMenuId;

	/**
	  *	
	  */
	private java.lang.String menuIcon;

	/**
	  *	
	  */
	private java.lang.String pageUrl;

	/**
	  *	
	  */
	private java.lang.String remarks;

	/**
	  *	
	  */
	private java.lang.String creator;

	/**
	  *	
	  */
	private java.lang.String createTime;

	/**
	  *	
	  */
	private java.lang.String updateUser;

	/**
	  *	
	  */
	private java.lang.String updateTime;

	/**
	  *	
	  */
	public java.lang.Long getMenuId() 
	{
		return menuId;
	}
	
	/**
	  *	
	  */
	public void setMenuId(java.lang.Long menuId) 
	{
		this.menuId = menuId;
	}
	
	/**
	  *	
	  */
	public java.lang.String getMenuCode() 
	{
		return menuCode;
	}
	
	/**
	  *	
	  */
	public void setMenuCode(java.lang.String menuCode) 
	{
		this.menuCode = menuCode;
	}
	
	/**
	  *	
	  */
	public java.lang.String getMenuName() 
	{
		return menuName;
	}
	
	/**
	  *	
	  */
	public void setMenuName(java.lang.String menuName) 
	{
		this.menuName = menuName;
	}
	
	/**
	  *	
	  */
	public java.lang.Integer getMenuOrder() 
	{
		return menuOrder;
	}
	
	/**
	  *	
	  */
	public void setMenuOrder(java.lang.Integer menuOrder) 
	{
		this.menuOrder = menuOrder;
	}
	
	/**
	  *	
	  */
	public java.lang.Long getParentMenuId() 
	{
		return parentMenuId;
	}
	
	/**
	  *	
	  */
	public void setParentMenuId(java.lang.Long parentMenuId) 
	{
		this.parentMenuId = parentMenuId;
	}
	
	/**
	  *	
	  */
	public java.lang.String getMenuIcon() 
	{
		return menuIcon;
	}
	
	/**
	  *	
	  */
	public void setMenuIcon(java.lang.String menuIcon) 
	{
		this.menuIcon = menuIcon;
	}
	
	/**
	  *	
	  */
	public java.lang.String getPageUrl() 
	{
		return pageUrl;
	}
	
	/**
	  *	
	  */
	public void setPageUrl(java.lang.String pageUrl) 
	{
		this.pageUrl = pageUrl;
	}
	
	/**
	  *	
	  */
	public java.lang.String getRemarks() 
	{
		return remarks;
	}
	
	/**
	  *	
	  */
	public void setRemarks(java.lang.String remarks) 
	{
		this.remarks = remarks;
	}
	
	/**
	  *	
	  */
	public java.lang.String getCreator() 
	{
		return creator;
	}
	
	/**
	  *	
	  */
	public void setCreator(java.lang.String creator) 
	{
		this.creator = creator;
	}
	
	/**
	  *	
	  */
	public java.lang.String getCreateTime() 
	{
		return createTime;
	}
	
	/**
	  *	
	  */
	public void setCreateTime(java.lang.String createTime) 
	{
		this.createTime = createTime;
	}
	
	/**
	  *	
	  */
	public java.lang.String getUpdateUser() 
	{
		return updateUser;
	}
	
	/**
	  *	
	  */
	public void setUpdateUser(java.lang.String updateUser) 
	{
		this.updateUser = updateUser;
	}
	
	/**
	  *	
	  */
	public java.lang.String getUpdateTime() 
	{
		return updateTime;
	}
	
	/**
	  *	
	  */
	public void setUpdateTime(java.lang.String updateTime) 
	{
		this.updateTime = updateTime;
	}
	
	public String toString()
	{
		return "SystemMenu [" + 
					"menuId=" + menuId + 
					", menuCode=" + menuCode + 
					", menuName=" + menuName + 
					", menuOrder=" + menuOrder + 
					", parentMenuId=" + parentMenuId + 
					", menuIcon=" + menuIcon + 
					", pageUrl=" + pageUrl + 
					", remarks=" + remarks + 
					", creator=" + creator + 
					", createTime=" + createTime + 
					", updateUser=" + updateUser + 
					", updateTime=" + updateTime + 
				"]";
	}
}

