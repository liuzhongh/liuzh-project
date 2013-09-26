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

public class ProductExt extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public static final String TYPE_IMG="1";
	public static final String TYPE_VIDEO="2";
	
	public static final String WHETHERAFFRE_YES = "1"; 
	public static final String WHETHERAFFRE_NO= "-1"; 
	
	public static final String VIP_PRODUCT_YES  = "1";
	
	/**
	  *	扩展信息ID
	  */
	private Long productExtId;

	/**
	  *	产品ID
	  */
	private Long productId;

	/**
	  *	发货地点
	  */
	private Long deliveryLocation;

	/**
	  *	是否允许代理
	  */
	private String whetherAgent;

	/**
	  *	代理说明
	  */
	private String agentDesc;

	/**
	  *	是否支持混批
	  */
	private String whetherBatch;

	/**
	  *	混批说明
	  */
	private String batchDesc;

	/**
	  *	是否包运
	  */
	private String whetherAffre;

	/**
	  *	包运地点
	  */
	private String affreLocation;

	/**
	  *	不包运地点
	  */
	private String notAffreLocation;

	/**
	  *	托运说明
	  */
	private String consignDesc;

	/**
	  *	拆零支持标记
	  */
	private String switchTag;

	/**
	  *	近效期标记
	  */
	private String wholeTag;

	/**
	  *	体积
	  */
	private String volume;

	/**
	  *	买家需知
	  */
	private String know;

	/**
	  *	订购说明
	  */
	private String orderDesc;
	
	/**
	  *	是否支持网购
	  */
	private String isOnlineShop;
	
	/**
	  *	是否授权可见
	  */
	private String isAuthorVisible;

	/**
	  *	备注
	  */
	private String remark;

	/**
	  *	更新时间
	  */
	private String updateDate;

	/**
	  *	更新人
	  */
	private String updateStaff;

	private String picStatus;
	
	private String isVisibleType;
	
	private String isVipProduct;
	
	private String templateId;
	
	/**
	 * @return the isVisibleType
	 */
	public String getIsVisibleType() {
		return isVisibleType;
	}

	/**
	 * @param isVisibleType the isVisibleType to set
	 */
	public void setIsVisibleType(String isVisibleType) {
		this.isVisibleType = isVisibleType;
	}

	/**
	 * @return the isVipProduct
	 */
	public String getIsVipProduct() {
		return isVipProduct;
	}

	/**
	 * @param isVipProduct the isVipProduct to set
	 */
	public void setIsVipProduct(String isVipProduct) {
		this.isVipProduct = isVipProduct;
	}

	public String getPicStatus()
	{
		return picStatus;
	}

	public void setPicStatus(String picStatus)
	{
		this.picStatus = picStatus;
	}

	/**
	  *	扩展信息ID
	  */
	public Long getProductExtId()
	{
		return productExtId;
	}
	
	/**
	  *	扩展信息ID
	  */
	public void setProductExtId(Long productExtId)
	{
		this.productExtId = productExtId;
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
	  *	发货地点
	  */
	public Long getDeliveryLocation()
	{
		return deliveryLocation;
	}
	
	/**
	  *	发货地点
	  */
	public void setDeliveryLocation(Long deliveryLocation)
	{
		this.deliveryLocation = deliveryLocation;
	}
	
	/**
	  *	是否允许代理
	  */
	public String getWhetherAgent()
	{
		return whetherAgent;
	}
	
	/**
	  *	是否允许代理
	  */
	public void setWhetherAgent(String whetherAgent)
	{
		this.whetherAgent = whetherAgent;
	}
	
	/**
	  *	代理说明
	  */
	public String getAgentDesc()
	{
		return agentDesc;
	}
	
	/**
	  *	代理说明
	  */
	public void setAgentDesc(String agentDesc)
	{
		this.agentDesc = agentDesc;
	}
	
	/**
	  *	是否支持混批
	  */
	public String getWhetherBatch()
	{
		return whetherBatch;
	}
	
	/**
	  *	是否支持混批
	  */
	public void setWhetherBatch(String whetherBatch)
	{
		this.whetherBatch = whetherBatch;
	}
	
	/**
	  *	混批说明
	  */
	public String getBatchDesc()
	{
		return batchDesc;
	}
	
	/**
	  *	混批说明
	  */
	public void setBatchDesc(String batchDesc)
	{
		this.batchDesc = batchDesc;
	}
	
	/**
	  *	是否包运
	  */
	public String getWhetherAffre()
	{
		return whetherAffre;
	}
	
	/**
	  *	是否包运
	  */
	public void setWhetherAffre(String whetherAffre)
	{
		this.whetherAffre = whetherAffre;
	}
	
	/**
	  *	包运地点
	  */
	public String getAffreLocation()
	{
		return affreLocation;
	}
	
	/**
	  *	包运地点
	  */
	public void setAffreLocation(String affreLocation)
	{
		this.affreLocation = affreLocation;
	}
	
	/**
	  *	不包运地点
	  */
	public String getNotAffreLocation()
	{
		return notAffreLocation;
	}
	
	/**
	  *	不包运地点
	  */
	public void setNotAffreLocation(String notAffreLocation)
	{
		this.notAffreLocation = notAffreLocation;
	}
	
	/**
	  *	托运说明
	  */
	public String getConsignDesc()
	{
		return consignDesc;
	}
	
	/**
	  *	托运说明
	  */
	public void setConsignDesc(String consignDesc)
	{
		this.consignDesc = consignDesc;
	}
	
	/**
	  *	拆零支持标记
	  */
	public String getSwitchTag()
	{
		return switchTag;
	}
	
	/**
	  *	拆零支持标记
	  */
	public void setSwitchTag(String switchTag)
	{
		this.switchTag = switchTag;
	}
	
	/**
	  *	近效期标记
	  */
	public String getWholeTag()
	{
		return wholeTag;
	}
	
	/**
	  *	近效期标记
	  */
	public void setWholeTag(String wholeTag)
	{
		this.wholeTag = wholeTag;
	}
	
	/**
	 * @return the isAuthorVisible
	 */
	public String getIsAuthorVisible() {
		return isAuthorVisible;
	}

	/**
	 * @param isAuthorVisible the isAuthorVisible to set
	 */
	public void setIsAuthorVisible(String isAuthorVisible) {
		this.isAuthorVisible = isAuthorVisible;
	}
	
	/**
	  *	体积
	  */
	public String getVolume()
	{
		return volume;
	}
	
	/**
	  *	体积
	  */
	public void setVolume(String volume)
	{
		this.volume = volume;
	}
	
	/**
	  *	买家需知
	  */
	public String getKnow()
	{
		return know;
	}
	
	/**
	  *	买家需知
	  */
	public void setKnow(String know)
	{
		this.know = know;
	}
	
	/**
	  *	订购说明
	  */
	public String getOrderDesc()
	{
		return orderDesc;
	}
	
	/**
	  *	订购说明
	  */
	public void setOrderDesc(String orderDesc)
	{
		this.orderDesc = orderDesc;
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
	  *	更新时间
	  */
	public String getUpdateDate()
	{
		return updateDate;
	}
	
	/**
	  *	更新时间
	  */
	public void setUpdateDate(String updateDate)
	{
		this.updateDate = updateDate;
	}
	
	/**
	  *	更新人
	  */
	public String getUpdateStaff()
	{
		return updateStaff;
	}
	
	/**
	  *	更新人
	  */
	public void setUpdateStaff(String updateStaff)
	{
		this.updateStaff = updateStaff;
	}
	
	/**
	 * @return the isOnlineShop
	 */
	public String getIsOnlineShop() {
		return isOnlineShop;
	}

	/**
	 * @param isOnlineShop the isOnlineShop to set
	 */
	public void setIsOnlineShop(String isOnlineShop) {
		this.isOnlineShop = isOnlineShop;
	}

	/**
	 * @return the templateId
	 */
	public String getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/*
	  * @return
	  * @see java.lang.Object#toString()
	  */
	public String toString() {
		return "ProductExt [productExtId=" + productExtId + ", productId="
				+ productId + ", deliveryLocation=" + deliveryLocation
				+ ", whetherAgent=" + whetherAgent + ", agentDesc=" + agentDesc
				+ ", whetherBatch=" + whetherBatch + ", batchDesc=" + batchDesc
				+ ", whetherAffre=" + whetherAffre + ", affreLocation="
				+ affreLocation + ", notAffreLocation=" + notAffreLocation
				+ ", consignDesc=" + consignDesc + ", switchTag=" + switchTag
				+ ", wholeTag=" + wholeTag + ", volume=" + volume + ", know="
				+ know + ", orderDesc=" + orderDesc + ", isOnlineShop="
				+ isOnlineShop + ", isAuthorVisible=" + isAuthorVisible
				+ ", remark=" + remark + ", updateDate=" + updateDate
				+ ", updateStaff=" + updateStaff + ", picStatus=" + picStatus
				+ ", isVisibleType=" + isVisibleType + ", isVipProduct="
				+ isVipProduct + ", templateId=" + templateId + "]";
	}
}

