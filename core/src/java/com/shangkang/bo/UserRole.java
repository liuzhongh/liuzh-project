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
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class UserRole extends Model{

	/**
	 * 基本权限有效期默认时间
	 */
	public static final String DEFAULT_ROLE_END_DATE = "2030-01-01";
	/**
	 * 基本权限有效期默认角色ID
	 */
	public static final long DEFAULT_ROLE_ID = 10000;
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	
	  */
	private java.lang.Long userRoleId;

	/**
	  *	
	  */
	private java.lang.Long userId;

	/**
	  *	
	  */
	private java.lang.Long roleId;

	/**
	  *	
	  */
	private java.lang.String reqDate;

	/**
	  *	
	  */
	private java.lang.String checkDate;

	/**
	  *	
	  */
	private java.lang.String checkStaff;

	/**
	  *	
	  */
	private java.lang.String startDate;

	/**
	  *	
	  */
	private java.lang.String endDate;

	/**
	  *	
	  */
	private java.lang.String remark;

	/**
	  *	
	  */
	public java.lang.Long getUserRoleId() 
	{
		return userRoleId;
	}
	
	/**
	  *	
	  */
	public void setUserRoleId(java.lang.Long userRoleId) 
	{
		this.userRoleId = userRoleId;
	}
	
	/**
	  *	
	  */
	public java.lang.Long getUserId() 
	{
		return userId;
	}
	
	/**
	  *	
	  */
	public void setUserId(java.lang.Long userId) 
	{
		this.userId = userId;
	}
	
	/**
	  *	
	  */
	public java.lang.Long getRoleId() 
	{
		return roleId;
	}
	
	/**
	  *	
	  */
	public void setRoleId(java.lang.Long roleId) 
	{
		this.roleId = roleId;
	}
	
	/**
	  *	
	  */
	public java.lang.String getReqDate() 
	{
		return reqDate;
	}
	
	/**
	  *	
	  */
	public void setReqDate(java.lang.String reqDate) 
	{
		this.reqDate = reqDate;
	}
	
	/**
	  *	
	  */
	public java.lang.String getCheckDate() 
	{
		return checkDate;
	}
	
	/**
	  *	
	  */
	public void setCheckDate(java.lang.String checkDate) 
	{
		this.checkDate = checkDate;
	}
	
	/**
	  *	
	  */
	public java.lang.String getCheckStaff() 
	{
		return checkStaff;
	}
	
	/**
	  *	
	  */
	public void setCheckStaff(java.lang.String checkStaff) 
	{
		this.checkStaff = checkStaff;
	}
	
	/**
	  *	
	  */
	public java.lang.String getStartDate() 
	{
		return startDate;
	}
	
	/**
	  *	
	  */
	public void setStartDate(java.lang.String startDate) 
	{
		this.startDate = startDate;
	}
	
	/**
	  *	
	  */
	public java.lang.String getEndDate() 
	{
		return endDate;
	}
	
	/**
	  *	
	  */
	public void setEndDate(java.lang.String endDate) 
	{
		this.endDate = endDate;
	}
	
	/**
	  *	
	  */
	public java.lang.String getRemark() 
	{
		return remark;
	}
	
	/**
	  *	
	  */
	public void setRemark(java.lang.String remark) 
	{
		this.remark = remark;
	}
	
	public String toString()
	{
		return "UserRole [" + 
					"userRoleId=" + userRoleId + 
					", userId=" + userId + 
					", roleId=" + roleId + 
					", reqDate=" + reqDate + 
					", checkDate=" + checkDate + 
					", checkStaff=" + checkStaff + 
					", startDate=" + startDate + 
					", endDate=" + endDate + 
					", remark=" + remark + 
				"]";
	}
}

