/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-5-7 17:12:12
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class CompSale extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	
	  */
	private Long compSaleId;

	/**
	  *	
	  */
	private String inModeCode;

	/**
	  *	
	  */
	private String province;

	/**
	  *	
	  */
	private String city;

	/**
	  *	
	  */
	private String custType;

	/**
	  *	
	  */
	private String vipLevel;

	/**
	  *	
	  */
	private Long productId;

	/**
	  *	
	  */
	private Long skuId;

	/**
	  *	
	  */
	private Short minCount;

	/**
	  *	
	  */
	private Short maxCount;

	/**
	  *	
	  */
	private java.math.BigDecimal compPrice;

	/**
	  *	
	  */
	private String compCode;
	
	private String compName;

	/**
	  *	
	  */
	private String condition;

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
	private String updateStaff;

	/**
	  *	
	  */
	private String updateDate;

	/**
	  *	
	  */
	private java.math.BigDecimal totalPrice;
	public Long getCompSaleId()
	{
		return compSaleId;
	}
	
	/**
	  *	
	  */
	public void setCompSaleId(Long compSaleId)
	{
		this.compSaleId = compSaleId;
	}
	
	/**
	  *	
	  */
	public String getInModeCode()
	{
		return inModeCode;
	}
	
	/**
	  *	
	  */
	public void setInModeCode(String inModeCode)
	{
		this.inModeCode = inModeCode;
	}
	
	/**
	  *	
	  */
	public String getProvince()
	{
		return province;
	}
	
	/**
	  *	
	  */
	public void setProvince(String province)
	{
		this.province = province;
	}
	
	/**
	  *	
	  */
	public String getCity()
	{
		return city;
	}
	
	/**
	  *	
	  */
	public void setCity(String city)
	{
		this.city = city;
	}
	
	/**
	  *	
	  */
	public String getCustType()
	{
		return custType;
	}
	
	/**
	  *	
	  */
	public void setCustType(String custType)
	{
		this.custType = custType;
	}
	
	/**
	  *	
	  */
	public String getVipLevel()
	{
		return vipLevel;
	}
	
	/**
	  *	
	  */
	public void setVipLevel(String vipLevel)
	{
		this.vipLevel = vipLevel;
	}
	
	/**
	  *	
	  */
	public Long getProductId()
	{
		return productId;
	}
	
	/**
	  *	
	  */
	public void setProductId(Long productId)
	{
		this.productId = productId;
	}
	
	/**
	  *	
	  */
	public Long getSkuId()
	{
		return skuId;
	}
	
	/**
	  *	
	  */
	public void setSkuId(Long skuId)
	{
		this.skuId = skuId;
	}
	
	/**
	  *	
	  */
	public Short getMinCount()
	{
		return minCount;
	}
	
	/**
	  *	
	  */
	public void setMinCount(Short minCount)
	{
		this.minCount = minCount;
	}
	
	/**
	  *	
	  */
	public Short getMaxCount()
	{
		return maxCount;
	}
	
	/**
	  *	
	  */
	public void setMaxCount(Short maxCount)
	{
		this.maxCount = maxCount;
	}
	
	/**
	  *	
	  */
	public java.math.BigDecimal getCompPrice() 
	{
		return compPrice;
	}
	
	/**
	  *	
	  */
	public void setCompPrice(java.math.BigDecimal compPrice) 
	{
		this.compPrice = compPrice;
	}
	
	/**
	  *	
	  */
	public String getCompCode()
	{
		return compCode;
	}
	
	/**
	  *	
	  */
	public void setCompCode(String compCode)
	{
		this.compCode = compCode;
	}
	
	/**
	  *	
	  */
	public String getCondition()
	{
		return condition;
	}
	
	/**
	  *	
	  */
	public void setCondition(String condition)
	{
		this.condition = condition;
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
	
	/**
	  *	
	  */
	public String getUpdateDate()
	{
		return updateDate;
	}
	
	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	/**
	  *	
	  */
	public void setUpdateDate(String updateDate)
	{
		this.updateDate = updateDate;
	}
	
	public java.math.BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(java.math.BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String toString()
	{
		return "CompSale [" + 
					"compSaleId=" + compSaleId + 
					", inModeCode=" + inModeCode + 
					", province=" + province + 
					", city=" + city + 
					", custType=" + custType + 
					", vipLevel=" + vipLevel + 
					", productId=" + productId + 
					", skuId=" + skuId + 
					", minCount=" + minCount + 
					", maxCount=" + maxCount + 
					", compPrice=" + compPrice + 
					", compCode=" + compCode + 
					", condition=" + condition + 
					", startDate=" + startDate + 
					", endDate=" + endDate + 
					", updateStaff=" + updateStaff + 
					", updateDate=" + updateDate + 
				"]";
	}
}

