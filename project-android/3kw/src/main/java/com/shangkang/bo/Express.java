/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-7-2 9:44:18
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class Express extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	public static final String PRICINGTYPE_NUM="0";//按件计价
	
	public static final String PRICINGTYPE_WEIGHT="1";//按重量计价
	
	public static final String ISDESIGNATED_Y="0";//指定区域
	
	public static final String ISDESIGNATED_N="1";//不指定区域
	
	/**
	  *	快递设置ID
	  */
	private Long expressId;

	/**
	  *	模板ID
	  */
	private Long templateId;

	/**
	  *	物流公司ID
	  */
	private Long deliveryFirmId;

	/**
	  *	计价类型
	  */
	private String pricingType;

	/**
	  *	默认单位数量
	  */
	private Long defaultNumber;

	/**
	  *	默认单位费用
	  */
	private java.math.BigDecimal defaultFee;

	/**
	  *	递增数量
	  */
	private Long increasingNumber;

	/**
	  *	递增费用
	  */
	private java.math.BigDecimal incrementalCosts;

	/**
	  *	是否指定区域设置
	  */
	private String isDesignated;

	/**
	  *	快递设置ID
	  */
	public Long getExpressId()
	{
		return expressId;
	}
	
	/**
	  *	快递设置ID
	  */
	public void setExpressId(Long expressId)
	{
		this.expressId = expressId;
	}
	
	/**
	  *	模板ID
	  */
	public Long getTemplateId()
	{
		return templateId;
	}
	
	/**
	  *	模板ID
	  */
	public void setTemplateId(Long templateId)
	{
		this.templateId = templateId;
	}
	
	/**
	  *	物流公司ID
	  */
	public Long getDeliveryFirmId()
	{
		return deliveryFirmId;
	}
	
	/**
	  *	物流公司ID
	  */
	public void setDeliveryFirmId(Long deliveryFirmId)
	{
		this.deliveryFirmId = deliveryFirmId;
	}
	
	/**
	  *	计价类型
	  */
	public String getPricingType()
	{
		return pricingType;
	}
	
	/**
	  *	计价类型
	  */
	public void setPricingType(String pricingType)
	{
		this.pricingType = pricingType;
	}
	
	/**
	  *	默认单位数量
	  */
	public Long getDefaultNumber()
	{
		return defaultNumber;
	}
	
	/**
	  *	默认单位数量
	  */
	public void setDefaultNumber(Long defaultNumber)
	{
		this.defaultNumber = defaultNumber;
	}
	
	/**
	  *	默认单位费用
	  */
	public java.math.BigDecimal getDefaultFee() 
	{
		return defaultFee;
	}
	
	/**
	  *	默认单位费用
	  */
	public void setDefaultFee(java.math.BigDecimal defaultFee) 
	{
		this.defaultFee = defaultFee;
	}
	
	/**
	  *	递增数量
	  */
	public Long getIncreasingNumber()
	{
		return increasingNumber;
	}
	
	/**
	  *	递增数量
	  */
	public void setIncreasingNumber(Long increasingNumber)
	{
		this.increasingNumber = increasingNumber;
	}
	
	/**
	  *	递增费用
	  */
	public java.math.BigDecimal getIncrementalCosts() 
	{
		return incrementalCosts;
	}
	
	/**
	  *	递增费用
	  */
	public void setIncrementalCosts(java.math.BigDecimal incrementalCosts) 
	{
		this.incrementalCosts = incrementalCosts;
	}
	
	/**
	  *	是否指定区域设置
	  */
	public String getIsDesignated()
	{
		return isDesignated;
	}
	
	/**
	  *	是否指定区域设置
	  */
	public void setIsDesignated(String isDesignated)
	{
		this.isDesignated = isDesignated;
	}
	
	public String toString()
	{
		return "Express [" + 
					"expressId=" + expressId + 
					", templateId=" + templateId + 
					", deliveryFirmId=" + deliveryFirmId + 
					", pricingType=" + pricingType + 
					", defaultNumber=" + defaultNumber + 
					", defaultFee=" + defaultFee + 
					", increasingNumber=" + increasingNumber + 
					", incrementalCosts=" + incrementalCosts + 
					", isDesignated=" + isDesignated + 
				"]";
	}
}

