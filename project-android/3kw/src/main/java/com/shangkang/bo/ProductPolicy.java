/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-3-6 11:09:20
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class ProductPolicy extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	 * 是否授权才能看到策略  :不需要授权也能看到策略
	 */
	public static final String IS_AUTHOR_VISIBLE_NO = "-1";
	/**
	 * 是否授权才能看到策略:一定要授权才能看到策略
	 */
	public static final String IS_AUTHOR_VISIBLE_YES = "1";
	
	
	/**
	  *	销售策略ID
	  */
	private Long productPolicyId;

	/**
	  *	产品ID
	  */
	private Long productId;

	/**
	  *	最小订购量
	  */
	private Integer minCount;

	/**
	  *	最大订购量
	  */
	private Integer maxCount;

	/**
	  *	区域编码
	  */
	private String areaCode;

	/**
	  *	销售价格
	  */
	private java.math.BigDecimal policyPrice;

	/**
	  *	产品单位
	  */
	private String productUnit;

	/**
	  *	生效时间
	  */
	private String startDate;

	/**
	  *	结束时间
	  */
	private String endDate;

	/**
	  *	创建时间
	  */
	private String updateDate;

	/**
	  *	创建人
	  */
	private Long custId;

	/**
	  *	备注
	  */
	private String remark;

	/**
	  *	销售策略ID
	  */
	public Long getProductPolicyId()
	{
		return productPolicyId;
	}
	
	/**
	  *	销售策略ID
	  */
	public void setProductPolicyId(Long productPolicyId)
	{
		this.productPolicyId = productPolicyId;
	}
	
	/**
	  *	产品ID
	  */
	public Long getProductId()
	{
		return productId;
	}
	
	/**
	  *	产品ID
	  */
	public void setProductId(Long productId)
	{
		this.productId = productId;
	}
	
	/**
	  *	最小订购量
	  */
	public Integer getMinCount()
	{
		return minCount;
	}
	
	/**
	  *	最小订购量
	  */
	public void setMinCount(Integer minCount)
	{
		this.minCount = minCount;
	}
	
	/**
	  *	最大订购量
	  */
	public Integer getMaxCount()
	{
		return maxCount;
	}
	
	/**
	  *	最大订购量
	  */
	public void setMaxCount(Integer maxCount)
	{
		this.maxCount = maxCount;
	}
	
	/**
	  *	区域编码
	  */
	public String getAreaCode()
	{
		return areaCode;
	}
	
	/**
	  *	区域编码
	  */
	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}
	
	/**
	  *	销售价格
	  */
	public java.math.BigDecimal getPolicyPrice() 
	{
		return policyPrice;
	}
	
	/**
	  *	销售价格
	  */
	public void setPolicyPrice(java.math.BigDecimal policyPrice) 
	{
		this.policyPrice = policyPrice;
	}
	
	/**
	  *	产品单位
	  */
	public String getProductUnit()
	{
		return productUnit;
	}
	
	/**
	  *	产品单位
	  */
	public void setProductUnit(String productUnit)
	{
		this.productUnit = productUnit;
	}
	
	/**
	  *	生效时间
	  */
	public String getStartDate()
	{
		return startDate;
	}
	
	/**
	  *	生效时间
	  */
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	
	/**
	  *	结束时间
	  */
	public String getEndDate()
	{
		return endDate;
	}
	
	/**
	  *	结束时间
	  */
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
	
	/**
	  *	创建时间
	  */
	public String getUpdateDate()
	{
		return updateDate;
	}
	
	/**
	  *	创建时间
	  */
	public void setUpdateDate(String updateDate)
	{
		this.updateDate = updateDate;
	}
	
	/**
	  *	创建人
	  */
	public Long getCustId()
	{
		return custId;
	}
	
	/**
	  *	创建人
	  */
	public void setCustId(Long custId)
	{
		this.custId = custId;
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
	
	public String toString()
	{
		return "ProductPolicy [" + 
					"productPolicyId=" + productPolicyId + 
					", productId=" + productId + 
					", minCount=" + minCount + 
					", maxCount=" + maxCount + 
					", areaCode=" + areaCode + 
					", policyPrice=" + policyPrice + 
					", productUnit=" + productUnit + 
					", startDate=" + startDate + 
					", endDate=" + endDate + 
					", updateDate=" + updateDate + 
					", custId=" + custId + 
					", remark=" + remark + 
				"]";
	}
}

