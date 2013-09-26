/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-2-26 10:33:21
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class Factory extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	
	  */
	private Long factoryId;

	/**
	  *	
	  */
	private Long brandId;

	/**
	  *	
	  */
	private String factoryName;

	/**
	  *	
	  */
	private String factoryAddress;

	/**
	  *	
	  */
	private String factoryInfo;

	/**
	  *	
	  */
	private Long custId;

	/**
	  *	
	  */
	private String remark;

	/**
	  *	
	  */
	private String updateDate;

	/**
	  *	
	  */
	private String updateStaff;

	/**
	  *	
	  */
	public Long getFactoryId()
	{
		return factoryId;
	}
	
	/**
	  *	
	  */
	public void setFactoryId(Long factoryId)
	{
		this.factoryId = factoryId;
	}
	
	/**
	  *	
	  */
	public Long getBrandId()
	{
		return brandId;
	}
	
	/**
	  *	
	  */
	public void setBrandId(Long brandId)
	{
		this.brandId = brandId;
	}
	
	/**
	  *	
	  */
	public String getFactoryName()
	{
		return factoryName;
	}
	
	/**
	  *	
	  */
	public void setFactoryName(String factoryName)
	{
		this.factoryName = factoryName;
	}
	
	/**
	  *	
	  */
	public String getFactoryAddress()
	{
		return factoryAddress;
	}
	
	/**
	  *	
	  */
	public void setFactoryAddress(String factoryAddress)
	{
		this.factoryAddress = factoryAddress;
	}
	
	/**
	  *	
	  */
	public String getFactoryInfo()
	{
		return factoryInfo;
	}
	
	/**
	  *	
	  */
	public void setFactoryInfo(String factoryInfo)
	{
		this.factoryInfo = factoryInfo;
	}
	
	/**
	  *	
	  */
	public Long getCustId()
	{
		return custId;
	}
	
	/**
	  *	
	  */
	public void setCustId(Long custId)
	{
		this.custId = custId;
	}
	
	/**
	  *	
	  */
	public String getRemark()
	{
		return remark;
	}
	
	/**
	  *	
	  */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	/**
	  *	
	  */
	public String getUpdateDate()
	{
		return updateDate;
	}
	
	/**
	  *	
	  */
	public void setUpdateDate(String updateDate)
	{
		this.updateDate = updateDate;
	}
	
	/**
	  *	
	  */
	public String getUpdateStaff()
	{
		return updateStaff;
	}
	
	/**
	  *	
	  */
	public void setUpdateStaff(String updateStaff)
	{
		this.updateStaff = updateStaff;
	}
	
	public String toString()
	{
		return "Factory [" + 
					"factoryId=" + factoryId + 
					", brandId=" + brandId + 
					", factoryName=" + factoryName + 
					", factoryAddress=" + factoryAddress + 
					", factoryInfo=" + factoryInfo + 
					", custId=" + custId + 
					", remark=" + remark + 
					", updateDate=" + updateDate + 
					", updateStaff=" + updateStaff + 
				"]";
	}
}

