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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangkang.core.bo.Model;

public class Product extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	 * 一级产品分类的类型长度
	 */
	public static final int	FIRST_LEVEL_PRODUCT_TYPE_LENGTH	=  6;
	/**
	 * 二级产品分类的类型长度
	 */
	public static final int	SECOND_LEVEL_PRODUCT_TYPE_LENGTH	=  8;
	/**
	 * 三级产品分类的类型长度
	 */
	public static final int	THREE_LEVEL_PRODUCT_TYPE_LENGTH		=  10;
	
	public static final String PRODUCT_KEYWORD = "可输入:药品名 | 药品编号 | 拼音缩写 | 批准文号 | 厂家";
	
	public static final String PRODUCT_TYPE_CONNECTOR = " > ";
	
	/**
	 * 产品状态 ： 新增 客户新增
	 */
	public static final String PRODUCT_STATUS_NEW_ADD = "1";
	
	/**
	 * 产品状态 ： 正在出售  / 客户上架
	 */
	public static final String PRODUCT_STATUS_ONSALE = "8";
	
	/**
	 * 产品状态：客户自己下架
	 */
	public static final String PRODUCT_STATUS_CUSTOMER_DOWN_SALE = "5";
	
	/**
	 * 产品状态：永久删除
	 */
	public static final String PRODUCT_STATUS_FOREVER_DELETE = "93";
	
	/**
	 * 审核通过
	 */
	public static final String CHECK_STATUS_PASS =  "8";
	
	/**
	 * 审核未通过
	 */
	public static final String CHECK_STATUS_PASS_FAILURE = "0";
	
	/**
	 * 未审核 / 审核中
	 */
	public static final String CHECK_STATUS_NOT_CHECK = "1";
	
	public static final String PRODUCT_TYPE_SKW001="skw001";
	public static final String PRODUCT_TYPE_SKW002="skw002";
	public static final String PRODUCT_TYPE_SKW003="skw003";
	public static final String PRODUCT_TYPE_SKW004="skw004";
	public static final String PRODUCT_TYPE_SKW005="skw005";
	public static final String PRODUCT_TYPE_SKW006="skw006";
	public static final String PRODUCT_TYPE_SKW007="skw007";
	public static final String PRODUCT_TYPE_SKW008="skw008";
	public static final String PRODUCT_TYPE_SKW009="skw009";
	public static final String PRODUCT_TYPE_SKW010="skw010";
	public static final String PRODUCT_TYPE_SKW011="skw011";
	public static final String PRODUCT_TYPE_SKW012="skw012";//
	
	/**
	 * 不同类型的产品显示几号属性组
	 */
	public static final Map<String,Integer> PRODUCT_TYPE_SEQ_MAP = new HashMap<String,Integer>();
	
	/**
	 * 剂型编码头2个字母对应的 产品类型
	 */
//	public static final Map<String,String>  DRUGFORM_START_INITIAL_FOR_TYPE  = new HashMap<String,String>();
	
	/**
	 * 产品类型对应的搜索属性名 如 按**搜索    剂型  机型 中间体  性状   器械类别 鉴定标准 == 作用于产品列表页面中的 第4个搜索条件的搜索名
	 */
	public static final Map<String,String>  PRODUCT_TYPE_SEARCH_KEY_NAME = new HashMap<String,String>();
	
	/**
	 * 产品类型对应的搜索类型名  如 按**搜索    用途  类别 ==  作用于产品列表页面中的 第3个搜索条件的搜索名
	 */
	public static final Map<String,String>  PRODUCT_TYPE_SEARCH_TYPE_NAME = new HashMap<String,String>();
	
	/**
	 * 发布产品信息的有效期
	 */
	public static final List<CommonType> INFO_VALID_DATE_LIST = new ArrayList<CommonType>();
	
	static 
	{
		PRODUCT_TYPE_SEQ_MAP.put(PRODUCT_TYPE_SKW001, 1);
		PRODUCT_TYPE_SEQ_MAP.put(PRODUCT_TYPE_SKW002, 2);
		PRODUCT_TYPE_SEQ_MAP.put(PRODUCT_TYPE_SKW003, 3);
		PRODUCT_TYPE_SEQ_MAP.put(PRODUCT_TYPE_SKW004, 4);
		PRODUCT_TYPE_SEQ_MAP.put(PRODUCT_TYPE_SKW005, 5);
		PRODUCT_TYPE_SEQ_MAP.put(PRODUCT_TYPE_SKW006, 6);
		PRODUCT_TYPE_SEQ_MAP.put(PRODUCT_TYPE_SKW007, 7);
		PRODUCT_TYPE_SEQ_MAP.put(PRODUCT_TYPE_SKW008, 8);
		PRODUCT_TYPE_SEQ_MAP.put(PRODUCT_TYPE_SKW009, 9);
		PRODUCT_TYPE_SEQ_MAP.put(PRODUCT_TYPE_SKW010, 10);
		PRODUCT_TYPE_SEQ_MAP.put(PRODUCT_TYPE_SKW011, 11);
		PRODUCT_TYPE_SEQ_MAP.put(PRODUCT_TYPE_SKW012, 12);
		
		PRODUCT_TYPE_SEARCH_KEY_NAME.put(PRODUCT_TYPE_SKW001, "剂型");
		PRODUCT_TYPE_SEARCH_KEY_NAME.put(PRODUCT_TYPE_SKW002, "剂型");
		PRODUCT_TYPE_SEARCH_KEY_NAME.put(PRODUCT_TYPE_SKW003, "器械类别");
		PRODUCT_TYPE_SEARCH_KEY_NAME.put(PRODUCT_TYPE_SKW004, "剂型");
		PRODUCT_TYPE_SEARCH_KEY_NAME.put(PRODUCT_TYPE_SKW005, "机型");
		PRODUCT_TYPE_SEARCH_KEY_NAME.put(PRODUCT_TYPE_SKW006, "性状");
		PRODUCT_TYPE_SEARCH_KEY_NAME.put(PRODUCT_TYPE_SKW007, "剂型");
		PRODUCT_TYPE_SEARCH_KEY_NAME.put(PRODUCT_TYPE_SKW008, "性状");
		PRODUCT_TYPE_SEARCH_KEY_NAME.put(PRODUCT_TYPE_SKW009, "中间体");
		PRODUCT_TYPE_SEARCH_KEY_NAME.put(PRODUCT_TYPE_SKW010, "鉴定标准");
		PRODUCT_TYPE_SEARCH_KEY_NAME.put(PRODUCT_TYPE_SKW011, "材质");
		
		PRODUCT_TYPE_SEARCH_TYPE_NAME.put(PRODUCT_TYPE_SKW001, "功效");
		PRODUCT_TYPE_SEARCH_TYPE_NAME.put(PRODUCT_TYPE_SKW002, "功效");
		PRODUCT_TYPE_SEARCH_TYPE_NAME.put(PRODUCT_TYPE_SKW003, "功效");
		PRODUCT_TYPE_SEARCH_TYPE_NAME.put(PRODUCT_TYPE_SKW004, "功效");
		PRODUCT_TYPE_SEARCH_TYPE_NAME.put(PRODUCT_TYPE_SKW005, "用途");
		PRODUCT_TYPE_SEARCH_TYPE_NAME.put(PRODUCT_TYPE_SKW006, "用途");
		PRODUCT_TYPE_SEARCH_TYPE_NAME.put(PRODUCT_TYPE_SKW007, "用途");
		PRODUCT_TYPE_SEARCH_TYPE_NAME.put(PRODUCT_TYPE_SKW008, "用途");
		PRODUCT_TYPE_SEARCH_TYPE_NAME.put(PRODUCT_TYPE_SKW009, "类别");
		PRODUCT_TYPE_SEARCH_TYPE_NAME.put(PRODUCT_TYPE_SKW010, "类别");
		PRODUCT_TYPE_SEARCH_TYPE_NAME.put(PRODUCT_TYPE_SKW011, "用途");
		
		
//		DRUGFORM_START_INITIAL_FOR_TYPE.put("ef", PRODUCT_TYPE_SKW001);
//		DRUGFORM_START_INITIAL_FOR_TYPE.put("hf", PRODUCT_TYPE_SKW002);
//		DRUGFORM_START_INITIAL_FOR_TYPE.put("dc", PRODUCT_TYPE_SKW003);
//		DRUGFORM_START_INITIAL_FOR_TYPE.put("cf", PRODUCT_TYPE_SKW004);
//		DRUGFORM_START_INITIAL_FOR_TYPE.put("ec", PRODUCT_TYPE_SKW005);
//		DRUGFORM_START_INITIAL_FOR_TYPE.put("mt", PRODUCT_TYPE_SKW006);
//		DRUGFORM_START_INITIAL_FOR_TYPE.put("bf", PRODUCT_TYPE_SKW007);
//		DRUGFORM_START_INITIAL_FOR_TYPE.put("mt", PRODUCT_TYPE_SKW008);
//		DRUGFORM_START_INITIAL_FOR_TYPE.put("ic", PRODUCT_TYPE_SKW009);
//		DRUGFORM_START_INITIAL_FOR_TYPE.put("hl", PRODUCT_TYPE_SKW010);
//		DRUGFORM_START_INITIAL_FOR_TYPE.put("dm", PRODUCT_TYPE_SKW011);
		
		
		INFO_VALID_DATE_LIST.add(new CommonType("30","1个月"));
		INFO_VALID_DATE_LIST.add(new CommonType("60","2个月"));
		INFO_VALID_DATE_LIST.add(new CommonType("90","3个月"));
		INFO_VALID_DATE_LIST.add(new CommonType("150","5个月"));
		INFO_VALID_DATE_LIST.add(new CommonType("180","6个月"));
		INFO_VALID_DATE_LIST.add(new CommonType("365","1年"));
		INFO_VALID_DATE_LIST.add(new CommonType("730","2年"));
	}
	
	
	
	
	
	public static final String HAVE_FLAG_YES = "1";
	public static final String HAVE_FLAG_NO = "-1";
	
	/**
	  *	产品ID
	  */
	private Long productId;

	/**
	  *	产品类别ID
	  */
	private Long catalogId;

	/**
	  *	产品编码
	  */
	private String productCode;

	/**
	  *	供应商ID
	  */
	private Long supplyId;

	/**
	  *	产品通用名称
	  */
	private String productName;

	/**
	  *	产品化学名称
	  */
	private String productNameS;

	/**
	  *	产品助记名
	  */
	private String shortName;

	/**
	  *	产品拼音
	  */
	private String productNamePy;

	/**
	  *	品种类别
	  */
	private String productType;

	/**
	  *	经营类别
	  */
	private String busiType;

	/**
	  *	剂型类别
	  */
	private String drugformType;

	/**
	  *	功效类别
	  */
	private String effectType;

	/**
	  *	数量单位
	  */
	private String unit;

	/**
	  *	中包装规格
	  */
	private Integer mpackSpec;

	/**
	  *	整件规格
	  */
	private Integer wholeSpec;

	/**
	  *	OTC标志
	  */
	private String otcTag;

	/**
	  *	含麻标志
	  */
	private String maTag;

	/**
	  *	处方药标志
	  */
	private String rxTag;

	/**
	  *	批准文号
	  */
	private String approvalNum;

	/**
	  *	专利号
	  */
	private String patentNum;

	/**
	  *	生产企业
	  */
	private Long factoryId;

	/**
	  *	品牌
	  */
	private Long brandId;

	/**
	  *	规格
	  */
	private String spec;

	/**
	  *	产品说明
	  */
	private String productInfo;

	/**
	  *	性状
	  */
	private String characters;

	/**
	  *	储存方法
	  */
	private String store;

	/**
	  *	功能主治
	  */
	private String indications;

	/**
	  *	不良反应
	  */
	private String reaction;

	/**
	  *	禁忌
	  */
	private String taboo;

	/**
	  *	药物相互作用
	  */
	private String interaction;

	/**
	  *	注意事项
	  */
	private String precautions;

	/**
	  *	成份
	  */
	private String element;

	/**
	  *	保质期
	  */
	private String shelfLife;

	/**
	  *	用法用量
	  */
	private String dosage;

	/**
	  *	产品状态
	  */
	private String productStatus;

	/**
	  *	有效期
	  */
	private String startDate;

	/**
	  *	失效期
	  */
	private String endDate;

	/**
	  *	生产日期
	  */
	private String produceDate;

	/**
	  *	更新人
	  */
	private String updateStaff;

	/**
	  *	更新时间
	  */
	private String updateDate;

	/**
	  *	备注
	  */
	private String remark;
	
	/**
	  *	产品显示顺序
	  */
	private Integer showIndex;
	
	/**
	  *	商铺产品顺序
	  */
	private Integer exhiShowIndex;
	
	/**
	  *	材质
	  */
	private String material;
	
	/**
	  *	CAS号码
	  */
	private String casNumber;
	
	/**
	  *	质量标准
	  */
	private String qualStandards;
	
	/**
	  *	等级
	  */
	private String grade;
	
	/**
	  *	生产工艺
	  */
	private String productProcess;
	
	/**
	  *	含量
	  */
	private String content;
	
	/**
	  *	产地
	  */
	private String placeOrigin;
	
	/**
	  *	味
	  */
	private String taste;
	
	/**
	  *	归经
	  */
	private String meridian;
	
	/**
	  *	型号
	  */
	private String model;
	
	/**
	  *	是否加工定制
	  */
	private String isCustomProcess;
	
	private String tradeMark;
	
	/**
	  *	审核状态
	  */
	private String auditStatus;
	
	/**
	  *	浏览次数
	  */
	private Integer  hits;
	
	/**
	  *	收藏次数
	  */
	private Integer  favoriteNum;
	

	/**
	 * @return the hits
	 */
	public Integer getHits() {
		return hits;
	}

	/**
	 * @param hits the hits to set
	 */
	public void setHits(Integer hits) {
		this.hits = hits;
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
	 * @return the auditStatus
	 */
	public String getAuditStatus() {
		return auditStatus;
	}

	/**
	 * @param auditStatus the auditStatus to set
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getTradeMark()
	{
		return tradeMark;
	}

	public void setTradeMark(String tradeMark)
	{
		this.tradeMark = tradeMark;
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
	  *	产品类别ID
	  */
	public Long getCatalogId()
	{
		return catalogId;
	}
	
	/**
	  *	产品类别ID
	  */
	public void setCatalogId(Long catalogId)
	{
		this.catalogId = catalogId;
	}
	
	/**
	  *	产品编码
	  */
	public String getProductCode()
	{
		return productCode;
	}
	
	/**
	  *	产品编码
	  */
	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}
	
	/**
	  *	供应商ID
	  */
	public Long getSupplyId()
	{
		return supplyId;
	}
	
	/**
	  *	供应商ID
	  */
	public void setSupplyId(Long supplyId)
	{
		this.supplyId = supplyId;
	}
	
	/**
	  *	产品通用名称
	  */
	public String getProductName()
	{
		return productName;
	}
	
	/**
	  *	产品通用名称
	  */
	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	
	/**
	  *	产品化学名称
	  */
	public String getProductNameS()
	{
		return productNameS;
	}
	
	/**
	  *	产品化学名称
	  */
	public void setProductNameS(String productNameS)
	{
		this.productNameS = productNameS;
	}
	
	/**
	  *	产品助记名
	  */
	public String getShortName()
	{
		return shortName;
	}
	
	/**
	  *	产品助记名
	  */
	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}
	
	/**
	  *	产品拼音
	  */
	public String getProductNamePy()
	{
		return productNamePy;
	}
	
	/**
	  *	产品拼音
	  */
	public void setProductNamePy(String productNamePy)
	{
		this.productNamePy = productNamePy;
	}
	
	/**
	  *	品种类别
	  */
	public String getProductType()
	{
		return productType;
	}
	
	/**
	  *	品种类别
	  */
	public void setProductType(String productType)
	{
		this.productType = productType;
	}
	
	/**
	  *	经营类别
	  */
	public String getBusiType()
	{
		return busiType;
	}
	
	/**
	  *	经营类别
	  */
	public void setBusiType(String busiType)
	{
		this.busiType = busiType;
	}
	
	/**
	  *	剂型类别
	  */
	public String getDrugformType()
	{
		return drugformType;
	}
	
	/**
	  *	剂型类别
	  */
	public void setDrugformType(String drugformType)
	{
		this.drugformType = drugformType;
	}
	
	/**
	  *	功效类别
	  */
	public String getEffectType()
	{
		return effectType;
	}
	
	/**
	  *	功效类别
	  */
	public void setEffectType(String effectType)
	{
		this.effectType = effectType;
	}
	
	/**
	  *	数量单位
	  */
	public String getUnit()
	{
		return unit;
	}
	
	/**
	  *	数量单位
	  */
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	
	/**
	  *	中包装规格
	  */
	public Integer getMpackSpec()
	{
		return mpackSpec;
	}
	
	/**
	  *	中包装规格
	  */
	public void setMpackSpec(Integer mpackSpec)
	{
		this.mpackSpec = mpackSpec;
	}
	
	/**
	  *	整件规格
	  */
	public Integer getWholeSpec()
	{
		return wholeSpec;
	}
	
	/**
	  *	整件规格
	  */
	public void setWholeSpec(Integer wholeSpec)
	{
		this.wholeSpec = wholeSpec;
	}
	
	/**
	  *	OTC标志
	  */
	public String getOtcTag()
	{
		return otcTag;
	}
	
	/**
	  *	OTC标志
	  */
	public void setOtcTag(String otcTag)
	{
		this.otcTag = otcTag;
	}
	
	/**
	  *	含麻标志
	  */
	public String getMaTag()
	{
		return maTag;
	}
	
	/**
	  *	含麻标志
	  */
	public void setMaTag(String maTag)
	{
		this.maTag = maTag;
	}
	
	/**
	  *	处方药标志
	  */
	public String getRxTag()
	{
		return rxTag;
	}
	
	/**
	  *	处方药标志
	  */
	public void setRxTag(String rxTag)
	{
		this.rxTag = rxTag;
	}
	
	/**
	  *	批准文号
	  */
	public String getApprovalNum()
	{
		return approvalNum;
	}
	
	/**
	  *	批准文号
	  */
	public void setApprovalNum(String approvalNum)
	{
		this.approvalNum = approvalNum;
	}
	
	/**
	  *	专利号
	  */
	public String getPatentNum()
	{
		return patentNum;
	}
	
	/**
	  *	专利号
	  */
	public void setPatentNum(String patentNum)
	{
		this.patentNum = patentNum;
	}
	
	/**
	  *	生产企业
	  */
	public Long getFactoryId()
	{
		return factoryId;
	}
	
	/**
	  *	生产企业
	  */
	public void setFactoryId(Long factoryId)
	{
		this.factoryId = factoryId;
	}
	
	/**
	  *	品牌
	  */
	public Long getBrandId()
	{
		return brandId;
	}
	
	/**
	  *	品牌
	  */
	public void setBrandId(Long brandId)
	{
		this.brandId = brandId;
	}
	
	/**
	  *	规格
	  */
	public String getSpec()
	{
		return spec;
	}
	
	/**
	  *	规格
	  */
	public void setSpec(String spec)
	{
		this.spec = spec;
	}
	
	/**
	  *	产品说明
	  */
	public String getProductInfo()
	{
		return productInfo;
	}
	
	/**
	  *	产品说明
	  */
	public void setProductInfo(String productInfo)
	{
		this.productInfo = productInfo;
	}
	
	/**
	  *	性状
	  */
	public String getCharacters()
	{
		return characters;
	}
	
	/**
	  *	性状
	  */
	public void setCharacters(String characters)
	{
		this.characters = characters;
	}
	
	/**
	  *	储存方法
	  */
	public String getStore()
	{
		return store;
	}
	
	/**
	  *	储存方法
	  */
	public void setStore(String store)
	{
		this.store = store;
	}
	
	/**
	  *	功能主治
	  */
	public String getIndications()
	{
		return indications;
	}
	
	/**
	  *	功能主治
	  */
	public void setIndications(String indications)
	{
		this.indications = indications;
	}
	
	/**
	  *	不良反应
	  */
	public String getReaction()
	{
		return reaction;
	}
	
	/**
	  *	不良反应
	  */
	public void setReaction(String reaction)
	{
		this.reaction = reaction;
	}
	
	/**
	  *	禁忌
	  */
	public String getTaboo()
	{
		return taboo;
	}
	
	/**
	  *	禁忌
	  */
	public void setTaboo(String taboo)
	{
		this.taboo = taboo;
	}
	
	/**
	  *	药物相互作用
	  */
	public String getInteraction()
	{
		return interaction;
	}
	
	/**
	  *	药物相互作用
	  */
	public void setInteraction(String interaction)
	{
		this.interaction = interaction;
	}
	
	/**
	  *	注意事项
	  */
	public String getPrecautions()
	{
		return precautions;
	}
	
	/**
	  *	注意事项
	  */
	public void setPrecautions(String precautions)
	{
		this.precautions = precautions;
	}
	
	/**
	  *	成份
	  */
	public String getElement()
	{
		return element;
	}
	
	/**
	  *	成份
	  */
	public void setElement(String element)
	{
		this.element = element;
	}
	
	/**
	  *	保质期
	  */
	public String getShelfLife()
	{
		return shelfLife;
	}
	
	/**
	  *	保质期
	  */
	public void setShelfLife(String shelfLife)
	{
		this.shelfLife = shelfLife;
	}
	
	/**
	  *	用法用量
	  */
	public String getDosage()
	{
		return dosage;
	}
	
	/**
	  *	用法用量
	  */
	public void setDosage(String dosage)
	{
		this.dosage = dosage;
	}
	
	/**
	  *	产品状态
	  */
	public String getProductStatus()
	{
		return productStatus;
	}
	
	/**
	  *	产品状态
	  */
	public void setProductStatus(String productStatus)
	{
		this.productStatus = productStatus;
	}
	
	/**
	  *	有效期
	  */
	public String getStartDate()
	{
		return startDate;
	}
	
	/**
	  *	有效期
	  */
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	
	/**
	  *	失效期
	  */
	public String getEndDate()
	{
		return endDate;
	}
	
	/**
	  *	失效期
	  */
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
	
	/**
	  *	生产日期
	  */
	public String getProduceDate()
	{
		return produceDate;
	}
	
	/**
	  *	生产日期
	  */
	public void setProduceDate(String produceDate)
	{
		this.produceDate = produceDate;
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
	 * @return the showIndex
	 */
	public Integer getShowIndex() {
		return showIndex;
	}

	/**
	 * @param showIndex the showIndex to set
	 */
	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}

	/**
	 * @return the exhiShowIndex
	 */
	public Integer getExhiShowIndex() {
		return exhiShowIndex;
	}

	/**
	 * @param exhiShowIndex the exhiShowIndex to set
	 */
	public void setExhiShowIndex(Integer exhiShowIndex) {
		this.exhiShowIndex = exhiShowIndex;
	}

	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material the material to set
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * @return the casNumber
	 */
	public String getCasNumber() {
		return casNumber;
	}

	/**
	 * @param casNumber the casNumber to set
	 */
	public void setCasNumber(String casNumber) {
		this.casNumber = casNumber;
	}

	/**
	 * @return the qualStandards
	 */
	public String getQualStandards() {
		return qualStandards;
	}

	/**
	 * @param qualStandards the qualStandards to set
	 */
	public void setQualStandards(String qualStandards) {
		this.qualStandards = qualStandards;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the productProcess
	 */
	public String getProductProcess() {
		return productProcess;
	}

	/**
	 * @param productProcess the productProcess to set
	 */
	public void setProductProcess(String productProcess) {
		this.productProcess = productProcess;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the placeOrigin
	 */
	public String getPlaceOrigin() {
		return placeOrigin;
	}

	/**
	 * @param placeOrigin the placeOrigin to set
	 */
	public void setPlaceOrigin(String placeOrigin) {
		this.placeOrigin = placeOrigin;
	}

	/**
	 * @return the taste
	 */
	public String getTaste() {
		return taste;
	}

	/**
	 * @param taste the taste to set
	 */
	public void setTaste(String taste) {
		this.taste = taste;
	}

	/**
	 * @return the meridian
	 */
	public String getMeridian() {
		return meridian;
	}

	/**
	 * @param meridian the meridian to set
	 */
	public void setMeridian(String meridian) {
		this.meridian = meridian;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the isCustomProcess
	 */
	public String getIsCustomProcess() {
		return isCustomProcess;
	}

	/**
	 * @param isCustomProcess the isCustomProcess to set
	 */
	public void setIsCustomProcess(String isCustomProcess) {
		this.isCustomProcess = isCustomProcess;
	}

	/*
	  * @return
	  * @see java.lang.Object#toString()
	  */
	public String toString() {
		return "Product [productId=" + productId + ", catalogId=" + catalogId
				+ ", productCode=" + productCode + ", supplyId=" + supplyId
				+ ", productName=" + productName + ", productNameS="
				+ productNameS + ", shortName=" + shortName
				+ ", productNamePy=" + productNamePy + ", productType="
				+ productType + ", busiType=" + busiType + ", drugformType="
				+ drugformType + ", effectType=" + effectType + ", unit="
				+ unit + ", mpackSpec=" + mpackSpec + ", wholeSpec="
				+ wholeSpec + ", otcTag=" + otcTag + ", maTag=" + maTag
				+ ", rxTag=" + rxTag + ", approvalNum=" + approvalNum
				+ ", patentNum=" + patentNum + ", factoryId=" + factoryId
				+ ", brandId=" + brandId + ", spec=" + spec + ", productInfo="
				+ productInfo + ", characters=" + characters + ", store="
				+ store + ", indications=" + indications + ", reaction="
				+ reaction + ", taboo=" + taboo + ", interaction="
				+ interaction + ", precautions=" + precautions + ", element="
				+ element + ", shelfLife=" + shelfLife + ", dosage=" + dosage
				+ ", productStatus=" + productStatus + ", startDate="
				+ startDate + ", endDate=" + endDate + ", produceDate="
				+ produceDate + ", updateStaff=" + updateStaff
				+ ", updateDate=" + updateDate + ", remark=" + remark
				+ ", showIndex=" + showIndex + ", exhiShowIndex="
				+ exhiShowIndex + ", material=" + material + ", casNumber="
				+ casNumber + ", qualStandards=" + qualStandards + ", grade="
				+ grade + ", productProcess=" + productProcess + ", content="
				+ content + ", placeOrigin=" + placeOrigin + ", taste=" + taste
				+ ", meridian=" + meridian + ", model=" + model
				+ ", isCustomProcess=" + isCustomProcess + ", tradeMark="
				+ tradeMark + ", auditStatus=" + auditStatus + ", hits=" + hits
				+ ", favoriteNum=" + favoriteNum + "]";
	}
}

