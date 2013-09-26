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

public class Exhibition extends Model{
	
	public static final String REQUEST_EXHI_ID = "request_exhi_id";
	
	// 展厅企业类型标签
	public static final String FACTORY_CN = "生产企业";
	public static final String COMPANY_CN = "医药公司";

	// 展厅企业类型
	public static final String FACTORY = "A";
	public static final String COMPANY = "B";

	/**
	 * 新开
	 */
	public static final String STATUS_NEW = "1";
	/**
	 * 审核通过
	 */
	public static final String STATUS_PASS = "2";
	/**
	 * 审核不通过
	 */
	public static final String STATUS_FAIL = "3";
	/**
	 * 完成开通
	 */
	public static final String STATUS_FINISH = "4";
	/**
	 * 暂停
	 */
	public static final String STATUS_SUSPEND = "5";
	/**
	 * 关闭
	 */
	public static final String STATUS_CLOSED = "6";

	/**
	 * 水印内容类型 ： 网站名字
	 */
	public static final String WATER_TYPE_SITE = "1";
	/**
	 * 水印内容类型 ：公司名字
	 */
	public static final String WATER_TYPE_NAME = "2";
	
	public static final String WATER_TYPE_ALL = "3";
	
	/**
	 * 水印位置 中间
	 */
	public static final String WATER_LOCATION_CENTER = "1";
	/**
	 * 水印位置 左下角
	 */
	public static final String WATER_LOCATION_LRTB = "2";
	/**
	 * 水印位置 右下角
	 */
	public static final String WATER_LOCATION_RIGHTBUTTOM = "3";
	
	/**
	 * 自定义分类开启/关闭状态
	 */
	public static final String CUSTOM_CLASS_OPEN = "1";// 开启
	public static final String CUSTOM_CLASS_CLOSE = "2";// 关闭
	
	public static final String SELECT_KEYWORD = "可输入:厂商编号 | 厂商名称";
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	展厅ID
	  */
	private Long exhiId;

	/**
	  *	客户ID
	  */
	private Long custId;

	/**
	  *	展厅编号
	  */
	private String exhiBh;

	/**
	  *	展厅名称
	  */
	private String exhiName;

	/**
	  *	展厅顶部图片
	  */
	private String exhiPic;

	/**
	  *	企业LOGO
	  */
	private String exhiLogo;

	/**
	  *	展厅分类
	  */
	private String exhiType;

	/**
	  *	展厅介绍
	  */
	private String description;

	/**
	  *	所在省份
	  */
	private String province;

	/**
	  *	所在城市
	  */
	private String city;

	/**
	  *	所在县城
	  */
	private String county;

	/**
	  *	所在地址
	  */
	private String address;

	/**
	  *	展厅状态
	  */
	private String exhiStatus;

	/**
	  *	开启时间
	  */
	private String startDate;

	/**
	  *	关闭时间
	  */
	private String endDate;

	/**
	  *	显示顺序
	  */
	private Integer showIndex;

	/**
	  *	访问次数
	  */
	private Integer hits;

	/**
	  *	
	  */
	private String factoryType;
	
	/**
	  *	
	  */
	private Integer favoriteNum;
	
	/**
	  *	
	  */
	private String domainName;
	
	/**
	  * 是否启用自定义分类
	  */
	private String customClass;
	
	/**
	  * 图片水印位置
	  */
	private String waterPosition;
	
	/**
	  * 水印内容类型
	  */
	private String waterType;
	
	private String secondaryDomain;

	/**
	 * @return the waterPosition
	 */
	public String getWaterPosition() {
		return waterPosition;
	}

	/**
	 * @param waterPosition the waterPosition to set
	 */
	public void setWaterPosition(String waterPosition) {
		this.waterPosition = waterPosition;
	}

	/**
	 * @return the waterType
	 */
	public String getWaterType() {
		return waterType;
	}

	/**
	 * @param waterType the waterType to set
	 */
	public void setWaterType(String waterType) {
		this.waterType = waterType;
	}

	/**
	 * @return the customClass
	 */
	public String getCustomClass() {
		return customClass;
	}

	/**
	 * @param customClass the customClass to set
	 */
	public void setCustomClass(String customClass) {
		this.customClass = customClass;
	}

	/**
	  *	展厅ID
	  */
	public Long getExhiId()
	{
		return exhiId;
	}
	
	/**
	  *	展厅ID
	  */
	public void setExhiId(Long exhiId)
	{
		this.exhiId = exhiId;
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
	  *	展厅编号
	  */
	public String getExhiBh()
	{
		return exhiBh;
	}
	
	/**
	  *	展厅编号
	  */
	public void setExhiBh(String exhiBh)
	{
		this.exhiBh = exhiBh;
	}
	
	/**
	  *	展厅名称
	  */
	public String getExhiName()
	{
		return exhiName;
	}
	
	/**
	  *	展厅名称
	  */
	public void setExhiName(String exhiName)
	{
		this.exhiName = exhiName;
	}
	
	/**
	  *	展厅顶部图片
	  */
	public String getExhiPic()
	{
		return exhiPic;
	}
	
	/**
	  *	展厅顶部图片
	  */
	public void setExhiPic(String exhiPic)
	{
		this.exhiPic = exhiPic;
	}
	
	/**
	  *	企业LOGO
	  */
	public String getExhiLogo()
	{
		return exhiLogo;
	}
	
	/**
	  *	企业LOGO
	  */
	public void setExhiLogo(String exhiLogo)
	{
		this.exhiLogo = exhiLogo;
	}
	
	/**
	  *	展厅分类
	  */
	public String getExhiType()
	{
		return exhiType;
	}
	
	/**
	  *	展厅分类
	  */
	public void setExhiType(String exhiType)
	{
		this.exhiType = exhiType;
	}
	
	/**
	  *	展厅介绍
	  */
	public String getDescription()
	{
		return description;
	}
	
	/**
	  *	展厅介绍
	  */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	  *	所在省份
	  */
	public String getProvince()
	{
		return province;
	}
	
	/**
	  *	所在省份
	  */
	public void setProvince(String province)
	{
		this.province = province;
	}
	
	/**
	  *	所在城市
	  */
	public String getCity()
	{
		return city;
	}
	
	/**
	  *	所在城市
	  */
	public void setCity(String city)
	{
		this.city = city;
	}
	
	/**
	  *	所在县城
	  */
	public String getCounty()
	{
		return county;
	}
	
	/**
	  *	所在县城
	  */
	public void setCounty(String county)
	{
		this.county = county;
	}
	
	/**
	  *	所在地址
	  */
	public String getAddress()
	{
		return address;
	}
	
	/**
	  *	所在地址
	  */
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	/**
	  *	展厅状态
	  */
	public String getExhiStatus()
	{
		return exhiStatus;
	}
	
	/**
	  *	展厅状态
	  */
	public void setExhiStatus(String exhiStatus)
	{
		this.exhiStatus = exhiStatus;
	}
	
	/**
	  *	开启时间
	  */
	public String getStartDate()
	{
		return startDate;
	}
	
	/**
	  *	开启时间
	  */
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	
	/**
	  *	关闭时间
	  */
	public String getEndDate()
	{
		return endDate;
	}
	
	/**
	  *	关闭时间
	  */
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
	
	/**
	  *	显示顺序
	  */
	public Integer getShowIndex()
	{
		return showIndex;
	}
	
	/**
	  *	显示顺序
	  */
	public void setShowIndex(Integer showIndex)
	{
		this.showIndex = showIndex;
	}
	
	/**
	  *	访问次数
	  */
	public Integer getHits()
	{
		return hits;
	}
	
	/**
	  *	访问次数
	  */
	public void setHits(Integer hits)
	{
		this.hits = hits;
	}
	
	/**
	  *	
	  */
	public String getFactoryType()
	{
		return factoryType;
	}
	
	/**
	  *	
	  */
	public void setFactoryType(String factoryType)
	{
		this.factoryType = factoryType;
	}
	
	/**
	 * @return the favoriteNum
	 */
	public Integer getFavoriteNum() {
		return favoriteNum;
	}

	/**
	 * @param favoriteNum the favoriteNum to set
	 */
	public void setFavoriteNum(Integer favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	/**
	 * @return the domainName
	 */
	public String getDomainName() {
		return domainName;
	}

	/**
	 * @param domainName the domainName to set
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	/**
	 * @return the secondaryDomain
	 */
	public String getSecondaryDomain() {
		return secondaryDomain;
	}

	/**
	 * @param secondaryDomain the secondaryDomain to set
	 */
	public void setSecondaryDomain(String secondaryDomain) {
		this.secondaryDomain = secondaryDomain;
	}

	/*
	  * @return
	  * @see java.lang.Object#toString()
	  */
	@Override
	public String toString() {
		return "Exhibition [exhiId=" + exhiId + ", custId=" + custId
				+ ", exhiBh=" + exhiBh + ", exhiName=" + exhiName
				+ ", exhiPic=" + exhiPic + ", exhiLogo=" + exhiLogo
				+ ", exhiType=" + exhiType + ", description=" + description
				+ ", province=" + province + ", city=" + city + ", county="
				+ county + ", address=" + address + ", exhiStatus="
				+ exhiStatus + ", startDate=" + startDate + ", endDate="
				+ endDate + ", showIndex=" + showIndex + ", hits=" + hits
				+ ", factoryType=" + factoryType + ", favoriteNum="
				+ favoriteNum + ", domainName=" + domainName + ", customClass="
				+ customClass + ", waterPosition=" + waterPosition
				+ ", waterType=" + waterType + ", secondaryDomain="
				+ secondaryDomain + "]";
	}
}

