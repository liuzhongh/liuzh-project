/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-3-8 16:03:11
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class CompanyCommend extends Model{
	/**
	 * 企业新秀
	 */
	public static final String COMMEND_TYPE_NEWER = "1";
	
	/**
	 * 活跃企业推荐
	 */
	public static final String COMMEND_TYPE_ACTIVE = "2";
	
	/**
	 * 优质企业推荐
	 */
	public static final String COMMEND_TYPE_QUALITY = "3";
	
	/**
	 * 重点推荐
	 */
	public static final String COMMEND_TYPE_NORMAL = "4";
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	推荐ID
	  */
	private Long commendId;

	/**
	  *	推荐类别
	  */
	private String commendType;

	/**
	  *	客户ID
	  */
	private Long productId;

	/**
	  *	
	  */
	private Long exhiId;

	/**
	  *	显示顺序
	  */
	private Integer showIndex;

	/**
	  *	开始时间
	  */
	private String startDate;

	/**
	  *	结束时间
	  */
	private String endDate;

	/**
	  *	推荐理由
	  */
	private String commendDesc;

	/**
	  *	推荐人
	  */
	private String commendStaff;

	/**
	  *	推荐时间
	  */
	private String commendDate;

	/**
	  *	备注
	  */
	private String remark;

	/**
	  *	推荐ID
	  */
	public Long getCommendId()
	{
		return commendId;
	}
	
	/**
	  *	推荐ID
	  */
	public void setCommendId(Long commendId)
	{
		this.commendId = commendId;
	}
	
	/**
	  *	推荐类别
	  */
	public String getCommendType()
	{
		return commendType;
	}
	
	/**
	  *	推荐类别
	  */
	public void setCommendType(String commendType)
	{
		this.commendType = commendType;
	}
	
	/**
	  *	客户ID
	  */
	public Long getProductId()
	{
		return productId;
	}
	
	/**
	  *	客户ID
	  */
	public void setProductId(Long productId)
	{
		this.productId = productId;
	}
	
	/**
	  *	
	  */
	public Long getExhiId()
	{
		return exhiId;
	}
	
	/**
	  *	
	  */
	public void setExhiId(Long exhiId)
	{
		this.exhiId = exhiId;
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
	  *	开始时间
	  */
	public String getStartDate()
	{
		return startDate;
	}
	
	/**
	  *	开始时间
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
	  *	推荐理由
	  */
	public String getCommendDesc()
	{
		return commendDesc;
	}
	
	/**
	  *	推荐理由
	  */
	public void setCommendDesc(String commendDesc)
	{
		this.commendDesc = commendDesc;
	}
	
	/**
	  *	推荐人
	  */
	public String getCommendStaff()
	{
		return commendStaff;
	}
	
	/**
	  *	推荐人
	  */
	public void setCommendStaff(String commendStaff)
	{
		this.commendStaff = commendStaff;
	}
	
	/**
	  *	推荐时间
	  */
	public String getCommendDate()
	{
		return commendDate;
	}
	
	/**
	  *	推荐时间
	  */
	public void setCommendDate(String commendDate)
	{
		this.commendDate = commendDate;
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
		return "CompanyCommend [" + 
					"commendId=" + commendId + 
					", commendType=" + commendType + 
					", productId=" + productId + 
					", exhiId=" + exhiId + 
					", showIndex=" + showIndex + 
					", startDate=" + startDate + 
					", endDate=" + endDate + 
					", commendDesc=" + commendDesc + 
					", commendStaff=" + commendStaff + 
					", commendDate=" + commendDate + 
					", remark=" + remark + 
				"]";
	}
}

