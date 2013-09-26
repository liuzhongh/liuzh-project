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

public class CustDelivery extends Model{

	public static final String DELIVERY_TYPE_SENDER = "1";//发货地址信息
	public static final String DELIVERY_TYPE_RECEIVER = "-1";//收获地址信息
	
	public static final String DEFAULT_TAG_YES = "1";//默认标识
	public static final String DEFAULT_TAG_No = "0";//非默认标识
	
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	
	  */
	private Long custDeliveryId;

	/**
	  *	
	  */
	private Long custId;

	/**
	  *	
	  */
	private String deliveryType;
	
	/**
	  *	
	  */
	private String receiveAddress;

	/**
	  *	
	  */
	private String receivePerson;

	/**
	  *	
	  */
	private String receiveRegion;

	/**
	  *	
	  */
	private String receiveCity;

	/**
	  *	
	  */
	private String receiveProvince;

	/**
	  *	
	  */
	private String email;

	/**
	  *	
	  */
	private String zipCode;

	/**
	  *	
	  */
	private String contactTel;
	
	/**
	  *	
	  */
	private String mobileTel;

	/**
	  *	
	  */
	private Integer usage;

	/**
	  *	
	  */
	private String defaultTag;

	/**
	  *	
	  */
	private String updateDate;

	/**
	  *	
	  */
	private String updateStaff;

	private String wholeAddress;
	
	public String getWholeAddress()
	{
		return wholeAddress;
	}

	public void setWholeAddress(String wholeAddress)
	{
		this.wholeAddress = wholeAddress;
	}

	/**
	  *	
	  */
	public Long getCustDeliveryId()
	{
		return custDeliveryId;
	}
	
	/**
	  *	
	  */
	public void setCustDeliveryId(Long custDeliveryId)
	{
		this.custDeliveryId = custDeliveryId;
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
	public String getReceiveAddress()
	{
		return receiveAddress;
	}
	
	/**
	  *	
	  */
	public void setReceiveAddress(String receiveAddress)
	{
		this.receiveAddress = receiveAddress;
	}
	
	/**
	  *	
	  */
	public String getReceivePerson()
	{
		return receivePerson;
	}
	
	/**
	  *	
	  */
	public void setReceivePerson(String receivePerson)
	{
		this.receivePerson = receivePerson;
	}
	
	/**
	  *	
	  */
	public String getReceiveRegion()
	{
		return receiveRegion;
	}
	
	/**
	  *	
	  */
	public void setReceiveRegion(String receiveRegion)
	{
		this.receiveRegion = receiveRegion;
	}
	
	/**
	  *	
	  */
	public String getReceiveCity()
	{
		return receiveCity;
	}
	
	/**
	  *	
	  */
	public void setReceiveCity(String receiveCity)
	{
		this.receiveCity = receiveCity;
	}
	
	/**
	  *	
	  */
	public String getReceiveProvince()
	{
		return receiveProvince;
	}
	
	/**
	  *	
	  */
	public void setReceiveProvince(String receiveProvince)
	{
		this.receiveProvince = receiveProvince;
	}
	
	/**
	  *	
	  */
	public String getEmail()
	{
		return email;
	}
	
	/**
	  *	
	  */
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	/**
	  *	
	  */
	public String getZipCode()
	{
		return zipCode;
	}
	
	/**
	  *	
	  */
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
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
	public Integer getUsage()
	{
		return usage;
	}
	
	/**
	  *	
	  */
	public void setUsage(Integer usage)
	{
		this.usage = usage;
	}
	
	/**
	  *	
	  */
	public String getDefaultTag()
	{
		return defaultTag;
	}
	
	/**
	  *	
	  */
	public void setDefaultTag(String defaultTag)
	{
		this.defaultTag = defaultTag;
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
	
	/**
	 * @return the deliveryType
	 */
	public String getDeliveryType() {
		return deliveryType;
	}

	/**
	 * @param deliveryType the deliveryType to set
	 */
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	/**
	 * @return the mobileTel
	 */
	public String getMobileTel() {
		return mobileTel;
	}

	/**
	 * @param mobileTel the mobileTel to set
	 */
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String toString()
	{
		return "CustDelivery [" + 
					"custDeliveryId=" + custDeliveryId + 
					", custId=" + custId + 
					", receiveAddress=" + receiveAddress + 
					", receivePerson=" + receivePerson + 
					", receiveRegion=" + receiveRegion + 
					", receiveCity=" + receiveCity + 
					", receiveProvince=" + receiveProvince + 
					", email=" + email + 
					", zipCode=" + zipCode + 
					", contactTel=" + contactTel + 
					", usage=" + usage + 
					", defaultTag=" + defaultTag + 
					", deliveryType=" + deliveryType + 
					", mobileTel=" + mobileTel + 
					", updateDate=" + updateDate + 
					", updateStaff=" + updateStaff + 
				"]";
	}
}

