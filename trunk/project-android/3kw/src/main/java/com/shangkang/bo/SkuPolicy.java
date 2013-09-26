/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-5-7 17:12:39
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class SkuPolicy extends Model{
	
	public static final String POLICY_TYPE_EXE="0";//执行销售政策
	public static final String POLICY_TYPE_STOP="1";//禁止执行
	
	public static final String POLICY_CODE_P="P";//产品
	public static final String POLICY_CODE_S="S";//销售品
	public static final String POLICY_CODE_O="O";//订单
	public static final String POLICY_CODE_R="R";//利润
	
	public static final String POLICY_ACTION_COUPON="COUPON";//送券
	public static final String POLICY_ACTION_SCORE="SCORE";//积分
	public static final String POLICY_ACTION_GIFT="GIFT";//赠品
	public static final String POLICY_ACTION_PRICE="PRICE";//整单打折
	public static final String POLICY_ACTION_DISCOUNT="DISCOUNT";//返点（整单）
	
	public static final String POLICY_STATUS_OK="1";
	public static final String POLICY_STATUS_STOP="2";//end_date<=sysdate
	public static final String POLICY_STATUS_SAVE="0";
	public static final String POLICY_STATUS_PAUSE="3";
	

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	
	  */
	private Long skuPolicyId;

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
	private Long custId;

	/**
	  *	
	  */
	private Long productId;

	/**
	  *	
	  */
	private Long skuId;

	/**
	  *	0：非特价
1：包含特价
	  */
	private String policyCode;

	/**
	  *	
	  */
	private Short totalCount;

	/**
	  *	
	  */
	private Double totalPay;

	/**
	  *	
	  */
	private String extValue1;

	/**
	  *	
	  */
	private String extValue2;

	/**
	  *	
	  */
	private String extValue3;

	/**
	  *	
	  */
	private String extValue4;

	/**
	  *	0：执行销售
1：禁止销售
	  */
	private String policyType;

	/**
	  *	
	  */
	private String policyDesc;

	/**
	  *	0：送券
1：送积分
2：送赠品
3：打折
4：返点
	  */
	private String policyAction;

	/**
	  *	
	  */
	private String saleObject;

	/**
	  *	
	  */
	private Short saleNumber;

	/**
	  *	
	  */
	private String extAction1;

	/**
	  *	
	  */
	private String extAction2;

	/**
	  *	
	  */
	private String extAction3;

	/**
	  *	
	  */
	private String extAction4;

	/**
	  *	
	  */
	private String extAction5;

	/**
	  *	0：正常
1：暂停
2：终止
	  */
	private String policyStatus;

	/**
	  *	
	  */
	private Short popIndex;

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
	public Long getSkuPolicyId()
	{
		return skuPolicyId;
	}
	
	/**
	  *	
	  */
	public void setSkuPolicyId(Long skuPolicyId)
	{
		this.skuPolicyId = skuPolicyId;
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
	  *	0：非特价
1：包含特价
	  */
	public String getPolicyCode()
	{
		return policyCode;
	}
	
	/**
	  *	0：非特价
1：包含特价
	  */
	public void setPolicyCode(String policyCode)
	{
		this.policyCode = policyCode;
	}
	
	/**
	  *	
	  */
	public Short getTotalCount()
	{
		return totalCount;
	}
	
	/**
	  *	
	  */
	public void setTotalCount(Short totalCount)
	{
		this.totalCount = totalCount;
	}
	
	/**
	  *	
	  */
	public Double getTotalPay()
	{
		return totalPay;
	}
	
	/**
	  *	
	  */
	public void setTotalPay(Double totalPay)
	{
		this.totalPay = totalPay;
	}
	
	/**
	  *	
	  */
	public String getExtValue1()
	{
		return extValue1;
	}
	
	/**
	  *	
	  */
	public void setExtValue1(String extValue1)
	{
		this.extValue1 = extValue1;
	}
	
	/**
	  *	
	  */
	public String getExtValue2()
	{
		return extValue2;
	}
	
	/**
	  *	
	  */
	public void setExtValue2(String extValue2)
	{
		this.extValue2 = extValue2;
	}
	
	/**
	  *	
	  */
	public String getExtValue3()
	{
		return extValue3;
	}
	
	/**
	  *	
	  */
	public void setExtValue3(String extValue3)
	{
		this.extValue3 = extValue3;
	}
	
	/**
	  *	
	  */
	public String getExtValue4()
	{
		return extValue4;
	}
	
	/**
	  *	
	  */
	public void setExtValue4(String extValue4)
	{
		this.extValue4 = extValue4;
	}
	
	/**
	  *	0：执行销售
1：禁止销售
	  */
	public String getPolicyType()
	{
		return policyType;
	}
	
	/**
	  *	0：执行销售
1：禁止销售
	  */
	public void setPolicyType(String policyType)
	{
		this.policyType = policyType;
	}
	
	/**
	  *	
	  */
	public String getPolicyDesc()
	{
		return policyDesc;
	}
	
	/**
	  *	
	  */
	public void setPolicyDesc(String policyDesc)
	{
		this.policyDesc = policyDesc;
	}
	
	/**
	  *	0：送券
1：送积分
2：送赠品
3：打折
4：返点
	  */
	public String getPolicyAction()
	{
		return policyAction;
	}
	
	/**
	  *	0：送券
1：送积分
2：送赠品
3：打折
4：返点
	  */
	public void setPolicyAction(String policyAction)
	{
		this.policyAction = policyAction;
	}
	
	/**
	  *	
	  */
	public String getSaleObject()
	{
		return saleObject;
	}
	
	/**
	  *	
	  */
	public void setSaleObject(String saleObject)
	{
		this.saleObject = saleObject;
	}
	
	/**
	  *	
	  */
	public Short getSaleNumber()
	{
		return saleNumber;
	}
	
	/**
	  *	
	  */
	public void setSaleNumber(Short saleNumber)
	{
		this.saleNumber = saleNumber;
	}
	
	/**
	  *	
	  */
	public String getExtAction1()
	{
		return extAction1;
	}
	
	/**
	  *	
	  */
	public void setExtAction1(String extAction1)
	{
		this.extAction1 = extAction1;
	}
	
	/**
	  *	
	  */
	public String getExtAction2()
	{
		return extAction2;
	}
	
	/**
	  *	
	  */
	public void setExtAction2(String extAction2)
	{
		this.extAction2 = extAction2;
	}
	
	/**
	  *	
	  */
	public String getExtAction3()
	{
		return extAction3;
	}
	
	/**
	  *	
	  */
	public void setExtAction3(String extAction3)
	{
		this.extAction3 = extAction3;
	}
	
	/**
	  *	
	  */
	public String getExtAction4()
	{
		return extAction4;
	}
	
	/**
	  *	
	  */
	public void setExtAction4(String extAction4)
	{
		this.extAction4 = extAction4;
	}
	
	/**
	  *	
	  */
	public String getExtAction5()
	{
		return extAction5;
	}
	
	/**
	  *	
	  */
	public void setExtAction5(String extAction5)
	{
		this.extAction5 = extAction5;
	}
	
	/**
	  *	0：正常
1：暂停
2：终止
	  */
	public String getPolicyStatus()
	{
		return policyStatus;
	}
	
	/**
	  *	0：正常
1：暂停
2：终止
	  */
	public void setPolicyStatus(String policyStatus)
	{
		this.policyStatus = policyStatus;
	}
	
	/**
	  *	
	  */
	public Short getPopIndex()
	{
		return popIndex;
	}
	
	/**
	  *	
	  */
	public void setPopIndex(Short popIndex)
	{
		this.popIndex = popIndex;
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
		return "SkuPolicy [" + 
					"skuPolicyId=" + skuPolicyId + 
					", inModeCode=" + inModeCode + 
					", province=" + province + 
					", city=" + city + 
					", custType=" + custType + 
					", vipLevel=" + vipLevel + 
					", custId=" + custId + 
					", productId=" + productId + 
					", skuId=" + skuId + 
					", policyCode=" + policyCode + 
					", totalCount=" + totalCount + 
					", totalPay=" + totalPay + 
					", extValue1=" + extValue1 + 
					", extValue2=" + extValue2 + 
					", extValue3=" + extValue3 + 
					", extValue4=" + extValue4 + 
					", policyType=" + policyType + 
					", policyDesc=" + policyDesc + 
					", policyAction=" + policyAction + 
					", saleObject=" + saleObject + 
					", saleNumber=" + saleNumber + 
					", extAction1=" + extAction1 + 
					", extAction2=" + extAction2 + 
					", extAction3=" + extAction3 + 
					", extAction4=" + extAction4 + 
					", extAction5=" + extAction5 + 
					", policyStatus=" + policyStatus + 
					", popIndex=" + popIndex + 
					", startDate=" + startDate + 
					", endDate=" + endDate + 
					", updateDate=" + updateDate + 
					", updateStaff=" + updateStaff + 
				"]";
	}
}

