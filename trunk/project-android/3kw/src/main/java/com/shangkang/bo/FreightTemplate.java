/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-7-2 9:44:17
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class FreightTemplate extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	
	public static final String SHIP_METHOD_EXPRESS = "1";
	public static final String SHIP_METHOD_FREIGHT = "2";
	public static final String SHIP_METHOD_INSTRUCTION = "3";
	
	
	public static final String SELLER_INCLUDED_FREIGHT = "0";
	
	/**
	  *	模板ID
	  */
	private Long templateId;

	/**
	  *	客户ID
	  */
	private Long custId;

	/**
	  *	模板名称
	  */
	private String templateName;

	/**
	  *	发货省份
	  */
	private String shipProvinces;

	/**
	  *	发货城市
	  */
	private String shipCity;

	/**
	  *	发货地区
	  */
	private String shipRegion;

	/**
	  *	运送方式
	  */
	private String shipMethod;

	/**
	  *	备注
	  */
	private String remark;
	
	private String shipInstructions;

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
	  *	客户ID
	  */
	public Long getCustId()
	{
		return custId;
	}
	
	/**
	  *	客户ID
	  */
	public void setCustId(Long custId)
	{
		this.custId = custId;
	}
	
	/**
	  *	模板名称
	  */
	public String getTemplateName()
	{
		return templateName;
	}
	
	/**
	  *	模板名称
	  */
	public void setTemplateName(String templateName)
	{
		this.templateName = templateName;
	}
	
	/**
	  *	发货省份
	  */
	public String getShipProvinces()
	{
		return shipProvinces;
	}
	
	/**
	  *	发货省份
	  */
	public void setShipProvinces(String shipProvinces)
	{
		this.shipProvinces = shipProvinces;
	}
	
	/**
	  *	发货城市
	  */
	public String getShipCity()
	{
		return shipCity;
	}
	
	/**
	  *	发货城市
	  */
	public void setShipCity(String shipCity)
	{
		this.shipCity = shipCity;
	}
	
	/**
	  *	发货地区
	  */
	public String getShipRegion()
	{
		return shipRegion;
	}
	
	/**
	  *	发货地区
	  */
	public void setShipRegion(String shipRegion)
	{
		this.shipRegion = shipRegion;
	}
	
	/**
	  *	运送方式
	  */
	public String getShipMethod()
	{
		return shipMethod;
	}
	
	/**
	  *	运送方式
	  */
	public void setShipMethod(String shipMethod)
	{
		this.shipMethod = shipMethod;
	}
	
	/**
	  *	备注
	  */
	public String getRemark()
	{
		return remark;
	}
	
	/**
	  *	备注
	  */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	/**
	 * @return the shipInstructions
	 */
	public String getShipInstructions() {
		return shipInstructions;
	}

	/**
	 * @param shipInstructions the shipInstructions to set
	 */
	public void setShipInstructions(String shipInstructions) {
		this.shipInstructions = shipInstructions;
	}

	/*
	  * @return
	  * @see java.lang.Object#toString()
	  */
	public String toString() {
		return "FreightTemplate [templateId=" + templateId + ", custId="
				+ custId + ", templateName=" + templateName
				+ ", shipProvinces=" + shipProvinces + ", shipCity=" + shipCity
				+ ", shipRegion=" + shipRegion + ", shipMethod=" + shipMethod
				+ ", remark=" + remark + ", shipInstructions="
				+ shipInstructions + "]";
	}
}

