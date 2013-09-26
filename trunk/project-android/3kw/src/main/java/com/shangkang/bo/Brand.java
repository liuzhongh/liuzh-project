/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-5-7 17:12:11
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class Brand extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	
	  */
	private Long brandId;

	/**
	  *	
	  */
	private String brandName;

	/**
	  *	
	  */
	private String brandCode;

	/**
	  *	
	  */
	private String story;

	/**
	  *	
	  */
	private Long factoryId;

	/**
	  *	
	  */
	private String brandLabel;

	/**
	  *	
	  */
	private String brandUrl;

	/**
	  *	
	  */
	private String brandLogo;

	/**
	  *	
	  */
	private Short showIndex;

	/**
	  *	
	  */
	private String authTag;

	/**
	  *	
	  */
	private String startDate;

	/**
	  *	
	  */
	private String endDate;

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
	public String getBrandName()
	{
		return brandName;
	}
	
	/**
	  *	
	  */
	public void setBrandName(String brandName)
	{
		this.brandName = brandName;
	}
	
	/**
	  *	
	  */
	public String getBrandCode()
	{
		return brandCode;
	}
	
	/**
	  *	
	  */
	public void setBrandCode(String brandCode)
	{
		this.brandCode = brandCode;
	}
	
	/**
	  *	
	  */
	public String getStory()
	{
		return story;
	}
	
	/**
	  *	
	  */
	public void setStory(String story)
	{
		this.story = story;
	}
	
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
	public String getBrandLabel()
	{
		return brandLabel;
	}
	
	/**
	  *	
	  */
	public void setBrandLabel(String brandLabel)
	{
		this.brandLabel = brandLabel;
	}
	
	/**
	  *	
	  */
	public String getBrandUrl()
	{
		return brandUrl;
	}
	
	/**
	  *	
	  */
	public void setBrandUrl(String brandUrl)
	{
		this.brandUrl = brandUrl;
	}
	
	/**
	  *	
	  */
	public String getBrandLogo()
	{
		return brandLogo;
	}
	
	/**
	  *	
	  */
	public void setBrandLogo(String brandLogo)
	{
		this.brandLogo = brandLogo;
	}
	
	/**
	  *	
	  */
	public Short getShowIndex()
	{
		return showIndex;
	}
	
	/**
	  *	
	  */
	public void setShowIndex(Short showIndex)
	{
		this.showIndex = showIndex;
	}
	
	/**
	  *	
	  */
	public String getAuthTag()
	{
		return authTag;
	}
	
	/**
	  *	
	  */
	public void setAuthTag(String authTag)
	{
		this.authTag = authTag;
	}
	
	/**
	  *	
	  */
	public String getStartDate()
	{
		return startDate;
	}
	
	/**
	  *	
	  */
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	
	/**
	  *	
	  */
	public String getEndDate()
	{
		return endDate;
	}
	
	/**
	  *	
	  */
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
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
		return "Brand [" + 
					"brandId=" + brandId + 
					", brandName=" + brandName + 
					", brandCode=" + brandCode + 
					", story=" + story + 
					", factoryId=" + factoryId + 
					", brandLabel=" + brandLabel + 
					", brandUrl=" + brandUrl + 
					", brandLogo=" + brandLogo + 
					", showIndex=" + showIndex + 
					", authTag=" + authTag + 
					", startDate=" + startDate + 
					", endDate=" + endDate + 
					", updateDate=" + updateDate + 
					", updateStaff=" + updateStaff + 
				"]";
	}
}

