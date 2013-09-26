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

public class Sku extends Model{
	
	public static final String VALID_FAR_TAG="0";
	public static final String VALID_NEAR_TAG="1";
	
	public static final String NON_VALID_DATE_BATCH_CODE = "-1";
	
	/**
	 * 审核通过
	 */
	public static final String STATUS_AUTH_PASS="2";
	/**
	 * 审核不通过
	 */
	public static final String STATUS_AUTH_FAIL="3";
	/**
	 * 已上架
	 */
	public static final String STATUS_ON_SALE="4";
	/**
	 * 已下架
	 */
	public static final String STATUS_OFF_SALE="5";
	/**
	 * 删除销售品
	 */
	public static final String STATUS_DELETE = "6";
	/**
	 * 不可见
	 */
	public static final String STATUS_INVISIBLE = "7";
	/**
	 * 可见
	 */
	public static final String STATUS_VISIBLE = "8";
	
	/**
	 * 积分兑换礼品
	 */
	public static final String TYPE_GIFT="S";
	
	/**
	 * 人气兑换
	 */
	public static final String WHOLE_TAG_MOODS = "1";
	
	/**
	 * 超值兑换
	 */
	public static final String WHOLE_TAG_OVERFLOW = "2";
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	
	  */
	private Long skuId;

	/**
	  *	
	  */
	private String skuName;

	/**
	  *	
	  */
	private Long productId;

	/**
	  *	
	  */
	private Long supplyId;

	/**
	  *	
	  */
	private Short mpackSpec;

	/**
	  *	
	  */
	private Short wholeSpec;

	/**
	  *	0:整件;1:中包装;2:散包装
	  */
	private String wholeTag;

	/**
	  *	A/B/C/D/X/Y
	  */
	private String skuType;

	/**
	  *	0：不支持拆零；1：支持拆零
	  */
	private String switchTag;

	/**
	  *	批次号,-1表示不限制批次
	  */
	private String batchCode;

	/**
	  *	
	  */
	private String produceDate;

	/**
	  *	
	  */
	private String validDate;

	/**
	  *	
	  */
	private String volume;

	/**
	  *	最小销售单位规格，如只支持整件，为整件数量
	  */
	private Short minSale;

	/**
	  *	
	  */
	private Short maxSale;

	/**
	  *	递增数量；如支持中包装销售，递增为中包规格，否则为整件规格，下单数为该字段整数倍
	  */
	private Short saleStep;

	/**
	  *	
	  */
	private String unit;

	/**
	  *	
	  */
	private java.math.BigDecimal retailPrice;

	/**
	  *	
	  */
	private java.math.BigDecimal wholesalePrice;

	/**
	  *	
	  */
	private java.math.BigDecimal skwPrice;

	/**
	  *	来自ERP
	  */
	private java.math.BigDecimal lowestPrice;

	/**
	  *	
	  */
	private java.math.BigDecimal avgCost;

	/**
	  *	
	  */
	private String erpSkuId;

	/**
	  *	
	  */
	private String wmsSkuId;

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
	private String skuStatus;
	
	private String cashDeliver;
	private String channel;
	private String province;
	private java.math.BigDecimal minPrice;
	private java.math.BigDecimal maxPrice;
	

	/**
	 * @return the cashDeliver
	 */
	public String getCashDeliver() {
		return cashDeliver;
	}

	/**
	 * @param cashDeliver the cashDeliver to set
	 */
	public void setCashDeliver(String cashDeliver) {
		this.cashDeliver = cashDeliver;
	}

	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	public String getSkuStatus() {
		return skuStatus;
	}

	public void setSkuStatus(String skuStatus) {
		this.skuStatus = skuStatus;
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
	public String getSkuName()
	{
		return skuName;
	}
	
	/**
	  *	
	  */
	public void setSkuName(String skuName)
	{
		this.skuName = skuName;
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
	public Long getSupplyId()
	{
		return supplyId;
	}
	
	/**
	  *	
	  */
	public void setSupplyId(Long supplyId)
	{
		this.supplyId = supplyId;
	}
	
	/**
	  *	
	  */
	public Short getMpackSpec()
	{
		return mpackSpec;
	}
	
	/**
	  *	
	  */
	public void setMpackSpec(Short mpackSpec)
	{
		this.mpackSpec = mpackSpec;
	}
	
	/**
	  *	
	  */
	public Short getWholeSpec()
	{
		return wholeSpec;
	}
	
	/**
	  *	
	  */
	public void setWholeSpec(Short wholeSpec)
	{
		this.wholeSpec = wholeSpec;
	}
	
	/**
	  *	0:整件;1:中包装;2:散包装
	  */
	public String getWholeTag()
	{
		return wholeTag;
	}
	
	/**
	  *	0:整件;1:中包装;2:散包装
	  */
	public void setWholeTag(String wholeTag)
	{
		this.wholeTag = wholeTag;
	}
	
	/**
	  *	A/B/C/D/X/Y
	  */
	public String getSkuType()
	{
		return skuType;
	}
	
	/**
	  *	A/B/C/D/X/Y
	  */
	public void setSkuType(String skuType)
	{
		this.skuType = skuType;
	}
	
	/**
	  *	0：不支持拆零；1：支持拆零
	  */
	public String getSwitchTag()
	{
		return switchTag;
	}
	
	/**
	  *	0：不支持拆零；1：支持拆零
	  */
	public void setSwitchTag(String switchTag)
	{
		this.switchTag = switchTag;
	}
	
	/**
	  *	批次号,-1表示不限制批次
	  */
	public String getBatchCode()
	{
		return batchCode;
	}
	
	/**
	  *	批次号,-1表示不限制批次
	  */
	public void setBatchCode(String batchCode)
	{
		this.batchCode = batchCode;
	}
	
	/**
	  *	
	  */
	public String getProduceDate()
	{
		return produceDate;
	}
	
	/**
	  *	
	  */
	public void setProduceDate(String produceDate)
	{
		this.produceDate = produceDate;
	}
	
	/**
	  *	
	  */
	public String getValidDate()
	{
		return validDate;
	}
	
	/**
	  *	
	  */
	public void setValidDate(String validDate)
	{
		this.validDate = validDate;
	}
	
	/**
	  *	
	  */
	public String getVolume()
	{
		return volume;
	}
	
	/**
	  *	
	  */
	public void setVolume(String volume)
	{
		this.volume = volume;
	}
	
	/**
	  *	最小销售单位规格，如只支持整件，为整件数量
	  */
	public Short getMinSale()
	{
		return minSale;
	}
	
	/**
	  *	最小销售单位规格，如只支持整件，为整件数量
	  */
	public void setMinSale(Short minSale)
	{
		this.minSale = minSale;
	}
	
	/**
	  *	
	  */
	public Short getMaxSale()
	{
		return maxSale;
	}
	
	/**
	  *	
	  */
	public void setMaxSale(Short maxSale)
	{
		this.maxSale = maxSale;
	}
	
	/**
	  *	递增数量；如支持中包装销售，递增为中包规格，否则为整件规格，下单数为该字段整数倍
	  */
	public Short getSaleStep()
	{
		return saleStep;
	}
	
	/**
	  *	递增数量；如支持中包装销售，递增为中包规格，否则为整件规格，下单数为该字段整数倍
	  */
	public void setSaleStep(Short saleStep)
	{
		this.saleStep = saleStep;
	}
	
	/**
	  *	
	  */
	public String getUnit()
	{
		return unit;
	}
	
	/**
	  *	
	  */
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	
	/**
	  *	
	  */
	public java.math.BigDecimal getRetailPrice() 
	{
		return retailPrice;
	}
	
	/**
	  *	
	  */
	public void setRetailPrice(java.math.BigDecimal retailPrice) 
	{
		this.retailPrice = retailPrice;
	}
	
	/**
	  *	
	  */
	public java.math.BigDecimal getWholesalePrice() 
	{
		return wholesalePrice;
	}
	
	/**
	  *	
	  */
	public void setWholesalePrice(java.math.BigDecimal wholesalePrice) 
	{
		this.wholesalePrice = wholesalePrice;
	}
	
	/**
	  *	
	  */
	public java.math.BigDecimal getSkwPrice() 
	{
		return skwPrice;
	}
	
	/**
	  *	
	  */
	public void setSkwPrice(java.math.BigDecimal skwPrice) 
	{
		this.skwPrice = skwPrice;
	}
	
	/**
	  *	来自ERP
	  */
	public java.math.BigDecimal getLowestPrice() 
	{
		return lowestPrice;
	}
	
	/**
	  *	来自ERP
	  */
	public void setLowestPrice(java.math.BigDecimal lowestPrice) 
	{
		this.lowestPrice = lowestPrice;
	}
	
	/**
	  *	
	  */
	public java.math.BigDecimal getAvgCost() 
	{
		return avgCost;
	}
	
	/**
	  *	
	  */
	public void setAvgCost(java.math.BigDecimal avgCost) 
	{
		this.avgCost = avgCost;
	}
	
	/**
	  *	
	  */
	public String getErpSkuId()
	{
		return erpSkuId;
	}
	
	/**
	  *	
	  */
	public void setErpSkuId(String erpSkuId)
	{
		this.erpSkuId = erpSkuId;
	}
	
	/**
	  *	
	  */
	public String getWmsSkuId()
	{
		return wmsSkuId;
	}
	
	/**
	  *	
	  */
	public void setWmsSkuId(String wmsSkuId)
	{
		this.wmsSkuId = wmsSkuId;
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

	
	public java.math.BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(java.math.BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public java.math.BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(java.math.BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	/*
	  * @return
	  * @see java.lang.Object#toString()
	  */
	public String toString() {
		return "Sku [skuId=" + skuId + ", skuName=" + skuName + ", productId="
				+ productId + ", supplyId=" + supplyId + ", mpackSpec="
				+ mpackSpec + ", wholeSpec=" + wholeSpec + ", wholeTag="
				+ wholeTag + ", skuType=" + skuType + ", switchTag="
				+ switchTag + ", batchCode=" + batchCode + ", produceDate="
				+ produceDate + ", validDate=" + validDate + ", volume="
				+ volume + ", minSale=" + minSale + ", maxSale=" + maxSale
				+ ", saleStep=" + saleStep + ", unit=" + unit
				+ ", retailPrice=" + retailPrice + ", wholesalePrice="
				+ wholesalePrice + ", skwPrice=" + skwPrice + ", lowestPrice="
				+ lowestPrice + ", avgCost=" + avgCost + ", erpSkuId="
				+ erpSkuId + ", wmsSkuId=" + wmsSkuId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", updateDate="
				+ updateDate + ", updateStaff=" + updateStaff + ", skuStatus="
				+ skuStatus + ", cashDeliver=" + cashDeliver + ", channel="
				+ channel + ", province=" + province + ", minPrice=" + minPrice
				+ ", maxPrice=" + maxPrice + "]";
	}

	
	
}

