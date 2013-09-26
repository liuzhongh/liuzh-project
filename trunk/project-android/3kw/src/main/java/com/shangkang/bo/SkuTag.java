/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-5-7 17:12:40
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import java.math.BigDecimal;

import com.shangkang.core.bo.Model;

public class SkuTag extends Model{

	public static final String NEW="1";//新品
	public static final String SPECIAL="2";//特价
	public static final String GIFT="3";//赠品
	public static final String NVALID="4";//近效期
	public static final String PROMOTION="5";//促销优惠
	public static final String PACKAGE="6";//打包优惠
	public static final String INCIDENTAL="7";//附带商品
	public static final String WEEKLY="8";//一周每日指定特价
	public static final String WEEKLY_MON="2";//周一指定特价
	public static final String WEEKLY_TUS="3";//周二指定特价
	public static final String WEEKLY_WEN="4";//周三指定特价
	public static final String WEEKLY_THU="5";//周四指定特价
	public static final String WEEKLY_FRI="6";//周五指定特价
	public static final String WEEKLY_SAT="7";//周六指定特价
	public static final String WEEKLY_SUN="1";//周日指定特价
	

	public static final String MARGIN="9";//高毛利
	public static final String MAINPUSH="10";//主推专区
	public static final String FTYPRO="11";//厂家促销列表
	public static final String NEW_YEAR="12";//年货一条街
	
	//产品标签
	public static final String PROMO_CN = "PROMOTION";
	public static final String NEW_CN = "NEW";
	
	public static final double SPECIAL_VS_VALID_PRICE_PERCENT =  0.03;
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	
	  */
	private Long skuTagId;

	/**
	  *	
	  */
	private Long skuId;

	/**
	  *	0：普通；
1：特价；
2：促销；
3：普药；
4：新药；
5：基药；
	  */
	private String tagType;

	/**
	  *	
	  */
	private String tag;

	/**
	  *	
	  */
	private String updateDate;

	/**
	  *	
	  */
	private String updateStaff;
	
	private String startDate;
	
	private String endDate;
	
	private BigDecimal skwPrice;
	
	private String remark;
	private String contact;

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the skwPrice
	 */
	public BigDecimal getSkwPrice() {
		return skwPrice;
	}

	/**
	 * @param skwPrice the skwPrice to set
	 */
	public void setSkwPrice(BigDecimal skwPrice) {
		this.skwPrice = skwPrice;
	}

	/**
	  *	
	  */
	public Long getSkuTagId()
	{
		return skuTagId;
	}
	
	/**
	  *	
	  */
	public void setSkuTagId(Long skuTagId)
	{
		this.skuTagId = skuTagId;
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
	  *	0：普通；
1：特价；
2：促销；
3：普药；
4：新药；
5：基药；
	  */
	public String getTagType()
	{
		return tagType;
	}
	
	/**
	  *	0：普通；
1：特价；
2：促销；
3：普药；
4：新药；
5：基药；
	  */
	public void setTagType(String tagType)
	{
		this.tagType = tagType;
	}
	
	/**
	  *	
	  */
	public String getTag()
	{
		return tag;
	}
	
	/**
	  *	
	  */
	public void setTag(String tag)
	{
		this.tag = tag;
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
	
	/*
	  * @return
	  * @see java.lang.Object#toString()
	  */
	@Override
	public String toString() {
		return "SkuTag [skuTagId=" + skuTagId + ", skuId=" + skuId
				+ ", tagType=" + tagType + ", tag=" + tag + ", updateDate="
				+ updateDate + ", updateStaff=" + updateStaff + ", startDate="
				+ startDate + ", endDate=" + endDate + ", skwPrice=" + skwPrice
				+ ", remark=" + remark + ", contact=" + contact + "]";
	}
}

