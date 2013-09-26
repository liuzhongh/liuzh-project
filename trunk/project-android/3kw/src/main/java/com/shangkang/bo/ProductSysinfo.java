/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-5-7 17:12:36
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class ProductSysinfo extends Model{
	
	public static final String SALE_COUNT="SALE_COUNT";
	public static final String VIEW_COUNT="VIEW_COUNT";

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	
	  */
	private Long flowId;

	/**
	  *	
	  */
	private Long productId;

	/**
	  *	0：访问量
1：订购量
	  */
	private String infoType;

	/**
	  *	
	  */
	private String infoValue;

	/**
	  *	
	  */
	private String infoDate;

	/**
	  *	
	  */
	private String updateDate;

	/**
	  *	
	  */
	private String remark;

	/**
	  *	
	  */
	public Long getFlowId()
	{
		return flowId;
	}
	
	/**
	  *	
	  */
	public void setFlowId(Long flowId)
	{
		this.flowId = flowId;
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
	  *	0：访问量
1：订购量
	  */
	public String getInfoType()
	{
		return infoType;
	}
	
	/**
	  *	0：访问量
1：订购量
	  */
	public void setInfoType(String infoType)
	{
		this.infoType = infoType;
	}
	
	/**
	  *	
	  */
	public String getInfoValue()
	{
		return infoValue;
	}
	
	/**
	  *	
	  */
	public void setInfoValue(String infoValue)
	{
		this.infoValue = infoValue;
	}
	
	/**
	  *	
	  */
	public String getInfoDate()
	{
		return infoDate;
	}
	
	/**
	  *	
	  */
	public void setInfoDate(String infoDate)
	{
		this.infoDate = infoDate;
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
	
	public String toString()
	{
		return "ProductSysinfo [" + 
					"flowId=" + flowId + 
					", productId=" + productId + 
					", infoType=" + infoType + 
					", infoValue=" + infoValue + 
					", infoDate=" + infoDate + 
					", updateDate=" + updateDate + 
					", remark=" + remark + 
				"]";
	}
}

