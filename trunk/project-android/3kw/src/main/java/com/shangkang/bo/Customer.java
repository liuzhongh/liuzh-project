/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-2-26 10:33:20
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class Customer extends Model{

	public static final String DEFAULT_CODE = "-1";
	public static final String DEFAULT_TYPE = "0";
	public static final String DEFAULT_STATUS = "1";
	
	public static final String CUST_TYPE_NORMAL = "0";//普通会员
	public static final String CUST_TYPE_FIRM = "1";//企业会员
	
	public static final String CUST_ID_FOR_LEFT_TREE = "cust_id_for_left_tree";
	public static final String COMPANY__KEYWORD = "可输入:企业名称";
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	
	  */
	private Long custId;

	/**
	  *	
	  */
	private String custName;

	/**
	  *	A/B/C/D等
	  */
	private String custType;

	/**
	  *	
	  */
	private String contactPerson;

	/**
	  *	
	  */
	private String province;

	/**
	  *	
	  */
	private String cityCode;

	/**
	  *	
	  */
	private String contactTel;
	
	private String custFax;

	/**
	  *	
	  */
	private String custAddr;

	/**
	  *	
	  */
	private String custManagerCode;

	/**
	  *	
	  */
	private String areaCode;

	/**
	  *	
	  */
	private Long custCreditValue;

	/**
	  *	单位是天，超过该时间的信用度，审核不通过
	  */
	private Integer creditCycle;

	/**
	  *	0：先付费；
1：后付费；
	  */
	private String checkoutType;

	/**
	  *	
	  */
	private Integer checkoutCycle;

	/**
	  *	
	  */
	private Long custComment;

	/**
	  *	
	  */
	private String startDate;

	/**
	  *	
	  */
	private String endDate;

	/**
	  *	0：正常状态
1：锁定状态
2：注销状态
3：潜在客户
	  */
	private String custStatus;
	

	/**
	  *	
	  */
	private String remark;
	
	private String isProductAgree;
	
	private String qq;
	
	private String zipCode;
	
	private Long exhiId ;
	

	public Long getExhiId()
	{
		return exhiId;
	}

	public void setExhiId(Long exhiId)
	{
		this.exhiId = exhiId;
	}

	/**
	 * @return the qq
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * @param qq the qq to set
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * @return the isProductAgree
	 */
	public String getIsProductAgree() {
		return isProductAgree;
	}

	/**
	 * @param isProductAgree the isProductAgree to set
	 */
	public void setIsProductAgree(String isProductAgree) {
		this.isProductAgree = isProductAgree;
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
	public String getCustName()
	{
		return custName;
	}
	
	/**
	  *	
	  */
	public void setCustName(String custName)
	{
		this.custName = custName;
	}
	
	/**
	  *	A/B/C/D等
	  */
	public String getCustType()
	{
		return custType;
	}
	
	/**
	  *	A/B/C/D等
	  */
	public void setCustType(String custType)
	{
		this.custType = custType;
	}
	
	/**
	  *	
	  */
	public String getContactPerson()
	{
		return contactPerson;
	}
	
	/**
	  *	
	  */
	public void setContactPerson(String contactPerson)
	{
		this.contactPerson = contactPerson;
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
	public String getCityCode()
	{
		return cityCode;
	}
	
	/**
	  *	
	  */
	public void setCityCode(String cityCode)
	{
		this.cityCode = cityCode;
	}
	
	/**
	  *	
	  */
	public String getContactTel()
	{
		return contactTel;
	}
	
	/**
	  *	
	  */
	public void setContactTel(String contactTel)
	{
		this.contactTel = contactTel;
	}
	
	/**
	  *	
	  */
	public String getCustAddr()
	{
		return custAddr;
	}
	
	/**
	  *	
	  */
	public void setCustAddr(String custAddr)
	{
		this.custAddr = custAddr;
	}
	
	/**
	  *	
	  */
	public String getCustManagerCode()
	{
		return custManagerCode;
	}
	
	/**
	  *	
	  */
	public void setCustManagerCode(String custManagerCode)
	{
		this.custManagerCode = custManagerCode;
	}

	
	/**
	  *	
	  */
	public Long getCustCreditValue()
	{
		return custCreditValue;
	}
	
	/**
	  *	
	  */
	public void setCustCreditValue(Long custCreditValue)
	{
		this.custCreditValue = custCreditValue;
	}
	
	/**
	  *	单位是天，超过该时间的信用度，审核不通过
	  */
	public Integer getCreditCycle()
	{
		return creditCycle;
	}
	
	/**
	  *	单位是天，超过该时间的信用度，审核不通过
	  */
	public void setCreditCycle(Integer creditCycle)
	{
		this.creditCycle = creditCycle;
	}
	
	/**
	  *	0：先付费；
1：后付费；
	  */
	public String getCheckoutType()
	{
		return checkoutType;
	}
	
	/**
	  *	0：先付费；
1：后付费；
	  */
	public void setCheckoutType(String checkoutType)
	{
		this.checkoutType = checkoutType;
	}
	
	/**
	  *	
	  */
	public Integer getCheckoutCycle()
	{
		return checkoutCycle;
	}
	
	/**
	  *	
	  */
	public void setCheckoutCycle(Integer checkoutCycle)
	{
		this.checkoutCycle = checkoutCycle;
	}
	
	/**
	  *	
	  */
	public Long getCustComment()
	{
		return custComment;
	}
	
	/**
	  *	
	  */
	public void setCustComment(Long custComment)
	{
		this.custComment = custComment;
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
	
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	  *	0：正常状态
1：锁定状态
2：注销状态
3：潜在客户
	  */
	public String getCustStatus()
	{
		return custStatus;
	}
	
	/**
	  *	0：正常状态
1：锁定状态
2：注销状态
3：潜在客户
	  */
	public void setCustStatus(String custStatus)
	{
		this.custStatus = custStatus;
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
	
	
	public String getCustFax() {
		return custFax;
	}

	public void setCustFax(String custFax) {
		this.custFax = custFax;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName
				+ ", custType=" + custType + ", contactPerson=" + contactPerson
				+ ", province=" + province + ", cityCode=" + cityCode
				+ ", contactTel=" + contactTel + ", custFax=" + custFax
				+ ", custAddr=" + custAddr + ", custManagerCode="
				+ custManagerCode + ", areaCode=" + areaCode
				+ ", custCreditValue=" + custCreditValue + ", creditCycle="
				+ creditCycle + ", checkoutType=" + checkoutType
				+ ", checkoutCycle=" + checkoutCycle + ", custComment="
				+ custComment + ", startDate=" + startDate + ", endDate="
				+ endDate + ", custStatus=" + custStatus + ", remark=" + remark
				+ ", isProductAgree=" + isProductAgree + ", qq=" + qq
				+ ", zipCode=" + zipCode + "]";
	}

}

