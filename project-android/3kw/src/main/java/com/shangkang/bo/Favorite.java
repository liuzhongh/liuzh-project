/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-2-26 10:33:21
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class Favorite extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	public static final String FAVORITE_TYPE_PRODUCT="1";//货品收藏

	public static final String FAVORITE_TYPE_SUPPLY="2";//供应商收藏

	public static final String FAVORITE_TYPE_DEAL="3";//交易过的供应商
	/**
	  *	
	  */
	private Long favoriteId;

	/**
	  *	
	  */
	private Long custId;

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
	private String infoValue;

	/**
	  *	
	  */
	private String favoriteDate;

	/**
	  *	
	  */
	private String remark;
	
	private Integer favoriteNumber;

	/**
	 * @return the favoriteNumber
	 */
	public Integer getFavoriteNumber() {
		return favoriteNumber;
	}

	/**
	 * @param favoriteNumber the favoriteNumber to set
	 */
	public void setFavoriteNumber(Integer favoriteNumber) {
		this.favoriteNumber = favoriteNumber;
	}

	/**
	  *	
	  */
	public Long getFavoriteId()
	{
		return favoriteId;
	}
	
	/**
	  *	
	  */
	public void setFavoriteId(Long favoriteId)
	{
		this.favoriteId = favoriteId;
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
	 * @return the infoId
	 */
	public Long getInfoId() {
		return infoId;
	}

	/**
	 * @param infoId the infoId to set
	 */
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	/**
	  *	
	  */
	public String getInfoType()
	{
		return infoType;
	}
	
	/**
	  *	
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
	public String getFavoriteDate()
	{
		return favoriteDate;
	}
	
	/**
	  *	
	  */
	public void setFavoriteDate(String favoriteDate)
	{
		this.favoriteDate = favoriteDate;
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
		return "Favorite [" + 
					"favoriteId=" + favoriteId + 
					", custId=" + custId + 
					", infoId=" + infoId + 
					", infoType=" + infoType + 
					", infoValue=" + infoValue + 
					", favoriteDate=" + favoriteDate + 
					", remark=" + remark + 
					", favoriteNumber=" + favoriteNumber + 
				"]";
	}
}

