/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-2-26 10:33:19
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class CommonType extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String PRODUCT_SEARCH_KEY = "SEARCH_P_KEY";
	
	public static final String PRODUCT_STATUS = "PRODUCT_STATUS";
	
	public static final String PRODUCT_CATALOG="PRODUCT_CATALOG";
	
	public static final String QUAL_TYPE = "QUAL";// 资质类型
	public static final String CERT_STATUS = "CERT_STATUS";
	public static final String DONOT_PASS = "DONOTPASS";
	public static final String THROUGH = "THROUGH";
	public static final String ORDER_STATUS = "ORDER_STATUS";//订单类型
	public static final String PRODUCT_AUDIT_STATUS = "PRODUCT_AUDIT_STATUS";
	
	public static final String PRODUCT_SHOW_INDEX = "P_SHOW_INDEX";
	
	public static final String HAVE_CHILDREN_YES = "1";
	public static final String HAVE_CHILDREN_NO = "0";
	
	public static final int JK_PARENT_INFO=101;//父类类型健康资讯
	public static final int GQ_PARENT_INFO=64;//父类类型供求商机
	public static final int PARENT_INFO=100;//父类类型产业资讯
	
	/**
	 * 推荐类型与位置定义
	 */
	public static final String COMMEND_TYPE_P = "COMMEND_TYPE_P";
	
	public static final String CATALOG_TYPE="PRODUCT_CATALOG";//商品分类
	
	public static final String PRODUCT_TYPE_XY = "skw001";//西药
	public static final String PRODUCT_TYPE_ZY = "skw002";//中成药
	public static final String PRODUCT_TYPE_QX = "skw003";//医疗器械
	public static final String PRODUCT_TYPE_BJ = "skw004";//保健食品
	public static final String PRODUCT_TYPE_SB = "skw005";//制药设备
	public static final String PRODUCT_TYPE_TQ = "skw006";//动植物提取
	public static final String PRODUCT_TYPE_ZJ = "skw007";//生物制剂
	public static final String PRODUCT_TYPE_YL = "skw008";//原料药和辅料
	public static final String PRODUCT_TYPE_ZYC = "skw010";//中药材及中药饮片
	public static final String PRODUCT_TYPE_ZJT = "skw009";//中间体
	public static final String PRODUCT_TYPE_BC = "skw011";//制药包材
	
	public static final String BANK_TYPE = "BANK_CODE";//银行类型
	public static final String BANK_AREA = "BANK_AREA";//银行省份区域
	public static final String BANK_CITY = "BANK_CITY";//银行所在城市
	
	public static final String AREA_TYPE="AREA_CODE";//区域编码
	
	public static final String AUTH_TAG = "AUTH_TAG";//服务审核状态
	public static final String USER_STATUS = "USER_STATUS";//用户状态
	public static final String CUST_TYPE = "CUST_TYPE";//客户类型
	public static final String FIRM_TYPE = "FIRM_TYPE";//企业类型
	public static final String AREA_CODE = "AREA_CODE";//地址
	public static final String SERVICE_TYPE = "SERVICE_TYPE";//服务类型
	public static final String FIRM_NATURE = "FIRM_NATURE";//企业性质
	public static final String CHECKOUT_TYPE = "CHECKOUT_TYPE";//结帐类型
	public static final String CHECK_STATUS = "CHECK_STATUS";//资质审核状态
	public static final String SUPPLY_TYPE = "SUPPLY_TYPE";//供求类型
	public static final String SUPPLY_TYPE_ALL = "SUPPLY_TYPE_ALL";//供求类型(包含询价单)
	public static final String SUPPLY_STATUS = "SUPPLY_STATUS";//供求状态
	public static final String IS_OTC = "ZC_X_TYPE";//中成药/西药类型
	public static final String S_D_COMMEND_TYPE = "S_D_COMMEND_TYPE";//供求推荐类型
	public static final String CUST_COMMEND_TYPE = "CUST_COMMEND_TYPE";//会员推荐类型
	public static final String FIRM_MODE = "FIRM_MODE";//经营模式
	public static final String AGENCY_CHAN = "AGENCY_CHAN";//代理渠道
	public static final String INVOICE_TYPE = "INVOICE_TYPE";//税务发票类型
	
	public static final String USAGE_TYPE="USAGE";
	
	public static final String AD_POSID_PREFIX = "AD_POSID_PREFIX";
	
	public static final String INFORMATION_VALID_DATE = "INFO_VALID_DATE";//发布产品时候的信息有效期
	public static final String DRUGFORM_TYPE = "MEDIA";//剂型
	public static final String UNIT_TYPE = "UNIT";//单位
	public static final String BUSI_SCOPE = "BUSI_SCOPE";//主营品种  经营类型
	public static final String EFFECT_TYPE = "EFFECT_TYPE"; //作用类别功效类别
	
	public static final String S_D_B_PRODUCT_TYPE = "S_D_B_PRODUCT_TYPE"; //品牌专区类型 
	public static final String S_D_C_PRODUCT_TYPE = "S_D_C_PRODUCT_TYPE";//加盟产品类型
	public static final String S_D_P_PRODUCT_TYPE = "S_D_P_PRODUCT_TYPE";//加工产品类型 
	public static final String S_D_M_A_PRODUCT_TYPE = "S_D_M_A_PRODUCT_TYPE";//招商代理产品类型 3_4
	public static final String S_D_M_PRODUCT_TYPE = "S_D_M_PRODUCT_TYPE";//其他商机产品类型 
	public static final String SEARCH_P_KEY = "SEARCH_P_KEY";
	
	/**
	 * 不同类型的商机  对应的产品类型 的 infoType
	 */
	public static final String S_D_PRODUCT_TYPE = "S_D_PRODUCT_TYPE";
	
	/**
	 * 除了特定产品类型的商机类型Code集合外。其他商机类型的产品类型查询用  parentType = OTHER_SD_TYPE_FOR_PRODUCT_TYPE来查
	 */
	public static final String OTHER_SD_TYPE_FOR_PRODUCT_TYPE = "";
	
	//公司动态
	public static final String COMPANY_DYNAMIC_TYPE = "COMPANY_DYNAMIC_TYPE";//动态类型
	public static final String COMPANY_DYNAMIC_DIRECTORY = "COMPANY_DYNAMIC_DIRECTORY";//动态目录
	
	//展厅产品推荐类型
	public static final String PRODUCT_RECOMMEND_TYPE = "RECOMMEND_TYPE";
	
	//订单状态-初定
	public static final String ORDER_STATUS_WPAY = "0"; //等待付款
	public static final String ORDER_STATUS_PAYW = "1"; //已付款待发货
	public static final String ORDER_STATUS_PROOW = "2"; //已发货
	public static final String ORDER_STATUS_RECPRO = "3"; //已收货
	public static final String ORDER_STATUS_SUCCESSING="13";//转账处理中
	public static final String ORDER_STATUS_PORDER = "4"; //取消订单
	public static final String ORDER_STATUS_TOBACK = "5"; //申请退款
	public static final String ORDER_STATUS_BSUCCESS = "6"; //退款成功	
	public static final String ORDER_STATUS_APP = "7"; //等待评价
	public static final String ORDER_STATUS_SUCCESS = "8"; //交易成功
	public static final String ORDER_STATUS_ONBACK = "9"; //退款处理中
	
	public static final String COMMENT_STATUS_BUYER = "10"; //买家已评论
	public static final String COMMENT_STATUS_SALER = "11"; //卖家已评论
	public static final String COMMENT_STATUS_BOTH= "12"; //双方已评
	//订单处理流程
	public static final String ORDER_PROCESS_INI= "-1"; //等待付款
	public static final String ORDER_PROCESS_ZER= "0"; //付款
	public static final String ORDER_PROCESS_ONE= "1"; //确认发货
	public static final String ORDER_PROCESS_TWO= "2"; //确认收货
	public static final String ORDER_PROCESS_THR= "3"; //转账
	public static final String ORDER_PROCESS_FOU= "4"; //确认转账成功
	public static final String ORDER_PROCESS_FIV= "5"; //买家评价
	public static final String ORDER_PROCESS_SIX= "6"; //卖家评价
	public static final String ORDER_PROCESS_SEV= "7"; //取消订单

	//确认结算状态
	//可进行结算
	public static final String ORDER_SETTLE_ACCOUNT_G="2";
	//已确认结算
	public static final String ORDER_SETTLE_ACCOUNT_Y="1";
	//未确认结算
	public static final String ORDER_SETTLE_ACCOUNT_N="0";
	
	//停滞节点
	public final static String NODEBOGDOWM="A1";

	
	//用户状态
	public static final String USER_TYPE_BUYER = "BUYER"; //买家
	public static final String USER_TYPE_SALER = "SALER"; //卖家
	
	//订单发票
	public static final String ORDER_BILLTAG_Y = "Y"; //需要发票
	public static final String ORDER_BILLTAG_N = "N"; //不需要发票
	
	//购物车状态
	public static final String SHOPCART_STATUS_W="1";//正常
	public static final String SHOPCART_STATUS_D="0";//删除
	
	public static final String FLYING2BUY="0";//立即订购
	
	public static final String ACCOUNT_STATUS="ACCOUNT_STATUS";//帐户状态
	public static final String ACCOUNT_TAG="ACCOUNT_TAG";//帐户标识
	
	public static final String SECRET_QUESTION="SECRET_QUESTION";//密码问题
	
	public static final String QUESTION_TYPE="QUESTION_TYPE";//问题类型
	
	public static final String SC_FAVORITE_MAYBE = "shopCart001";//猜你喜欢的
	
	public static final String SUPPLY_TYPE_MERGED = "SUPPLY_TYPE_MERGED";
	
	public static final String APPLICATION_FEE = "APPLICATION_FEE";//加盟费用类型
	
	public static final String MATTER_AREA = "MATTER_AREA";//区域
	
	public static final String OPERATOR = "OPERATOR";
	
	public static final String INTERVAL = "interval";
	
	/**
	 * 出库状态类型
	 */
	public static final String SPCK_STATUS = "SPCK_STATUS";
	
	/**
	 * 入库状态类型
	 */
	public static final String SPRK_STATUS = "SPRK_STATUS";
	
	/**
	  *	
	  */
	private Long infoId;

	/**
	  *	
	  */
	private String infoType;

	/**
	  *	
	  */
	private String infoCode;

	/**
	  *	
	  */
	private String parentInfo;

	/**
	  *	
	  */
	private String infoName;

	/**
	  *	
	  */
	private String typeImg;

	/**
	  *	
	  */
	private String hotTag;

	/**
	  *	
	  */
	private Integer showIndex;

	/**
	  *	
	  */
	private String infoValue;

	/**
	  *	
	  */
	private String infoStatus;

	/**
	  *	
	  */
	private String remark;
	
	private Integer infoHits;

	
	private String haveChildren="0";
	
	public String getHaveChildren()
	{
		return haveChildren;
	}

	public void setHaveChildren(String haveChildren)
	{
		this.haveChildren = haveChildren;
	}

	/**
	  *	
	  */
	public Long getInfoId() {
		return infoId;
	}

	/**
	  *	
	  */
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	/**
	  *	
	  */
	public String getInfoType() {
		return infoType;
	}

	/**
	  *	
	  */
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	/**
	  *	
	  */
	public String getInfoCode() {
		return infoCode;
	}

	/**
	  *	
	  */
	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}

	/**
	  *	
	  */
	public String getParentInfo() {
		return parentInfo;
	}

	/**
	  *	
	  */
	public void setParentInfo(String parentInfo) {
		this.parentInfo = parentInfo;
	}

	/**
	  *	
	  */
	public String getInfoName() {
		return infoName;
	}

	/**
	  *	
	  */
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	/**
	  *	
	  */
	public String getTypeImg() {
		return typeImg;
	}

	/**
	  *	
	  */
	public void setTypeImg(String typeImg) {
		this.typeImg = typeImg;
	}

	/**
	  *	
	  */
	public String getHotTag() {
		return hotTag;
	}

	/**
	  *	
	  */
	public void setHotTag(String hotTag) {
		this.hotTag = hotTag;
	}

	/**
	  *	
	  */
	public Integer getShowIndex() {
		return showIndex;
	}

	/**
	  *	
	  */
	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}

	/**
	  *	
	  */
	public String getInfoValue() {
		return infoValue;
	}

	/**
	  *	
	  */
	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}

	/**
	  *	
	  */
	public String getInfoStatus() {
		return infoStatus;
	}

	/**
	  *	
	  */
	public void setInfoStatus(String infoStatus) {
		this.infoStatus = infoStatus;
	}

	/**
	  *	
	  */
	public String getRemark() {
		return remark;
	}

	/**
	  *	
	  */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the infoHits
	 */
	public Integer getInfoHits() {
		return infoHits;
	}

	/**
	 * @param infoHits the infoHits to set
	 */
	public void setInfoHits(Integer infoHits) {
		this.infoHits = infoHits;
	}

	/*
	  * @return
	  * @see java.lang.Object#toString()
	  */
	@Override
	public String toString() {
		return "CommonType [infoId=" + infoId + ", infoType=" + infoType
				+ ", infoCode=" + infoCode + ", parentInfo=" + parentInfo
				+ ", infoName=" + infoName + ", typeImg=" + typeImg
				+ ", hotTag=" + hotTag + ", showIndex=" + showIndex
				+ ", infoValue=" + infoValue + ", infoStatus=" + infoStatus
				+ ", remark=" + remark + ", infoHits=" + infoHits
				+ ", haveChildren=" + haveChildren + "]";
	}

	public CommonType(String infoValue, String infoName)
	{
		super();
		this.infoValue = infoValue;
		this.infoName = infoName;
	}

	public CommonType()
	{
		super();
	}
	
}
