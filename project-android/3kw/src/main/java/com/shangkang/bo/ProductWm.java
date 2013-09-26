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

public class ProductWm extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	库存信息ID
	  */
	private Long wmId;

	/**
	  *	产品ID
	  */
	private Long productId;

	/**
	  *	库存数量
	  */
	private Integer wmCount;

	/**
	  *	最低销售价格
	  */
	private java.math.BigDecimal lowestPrice;

	/**
	  *	批发参考价格
	  */
	private java.math.BigDecimal wholesalePrice;

	/**
	  *	平均成本
	  */
	private java.math.BigDecimal avgCost;

	/**
	  *	最小销售数量
	  */
	private Integer minSale;

	/**
	  *	最大销售数量
	  */
	private Integer maxSale;

	/**
	  *	递增数量
	  */
	private Integer saleStep;

	/**
	  *	总销售量
	  */
	private java.math.BigDecimal totalSales;

	/**
	  *	总销售次数
	  */
	private Integer numberTotal;

	/**
	  *	更新日期
	  */
	private String updateDate;

	/**
	  *	更新人
	  */
	private String updateStaff;

	/**
	  *	库存信息ID
	  */
	public Long getWmId()
	{
		return wmId;
	}
	
	/**
	  *	库存信息ID
	  */
	public void setWmId(Long wmId)
	{
		this.wmId = wmId;
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
	  *	库存数量
	  */
	public Integer getWmCount()
	{
		return wmCount;
	}
	
	/**
	  *	库存数量
	  */
	public void setWmCount(Integer wmCount)
	{
		this.wmCount = wmCount;
	}
	
	/**
	  *	最低销售价格
	  */
	public java.math.BigDecimal getLowestPrice() 
	{
		return lowestPrice;
	}
	
	/**
	  *	最低销售价格
	  */
	public void setLowestPrice(java.math.BigDecimal lowestPrice) 
	{
		this.lowestPrice = lowestPrice;
	}
	
	/**
	  *	批发参考价格
	  */
	public java.math.BigDecimal getWholesalePrice() 
	{
		return wholesalePrice;
	}
	
	/**
	  *	批发参考价格
	  */
	public void setWholesalePrice(java.math.BigDecimal wholesalePrice) 
	{
		this.wholesalePrice = wholesalePrice;
	}
	
	/**
	  *	平均成本
	  */
	public java.math.BigDecimal getAvgCost() 
	{
		return avgCost;
	}
	
	/**
	  *	平均成本
	  */
	public void setAvgCost(java.math.BigDecimal avgCost) 
	{
		this.avgCost = avgCost;
	}
	
	/**
	  *	最小销售数量
	  */
	public Integer getMinSale()
	{
		return minSale;
	}
	
	/**
	  *	最小销售数量
	  */
	public void setMinSale(Integer minSale)
	{
		this.minSale = minSale;
	}
	
	/**
	  *	最大销售数量
	  */
	public Integer getMaxSale()
	{
		return maxSale;
	}
	
	/**
	  *	最大销售数量
	  */
	public void setMaxSale(Integer maxSale)
	{
		this.maxSale = maxSale;
	}
	
	/**
	  *	递增数量
	  */
	public Integer getSaleStep()
	{
		return saleStep;
	}
	
	/**
	  *	递增数量
	  */
	public void setSaleStep(Integer saleStep)
	{
		this.saleStep = saleStep;
	}
	
	/**
	  *	总销售量
	  */
	public java.math.BigDecimal getTotalSales() 
	{
		return totalSales;
	}
	
	/**
	  *	总销售量
	  */
	public void setTotalSales(java.math.BigDecimal totalSales) 
	{
		this.totalSales = totalSales;
	}
	
	/**
	  *	总销售次数
	  */
	public Integer getNumberTotal()
	{
		return numberTotal;
	}
	
	/**
	  *	总销售次数
	  */
	public void setNumberTotal(Integer numberTotal)
	{
		this.numberTotal = numberTotal;
	}
	
	/**
	  *	更新日期
	  */
	public String getUpdateDate()
	{
		return updateDate;
	}
	
	/**
	  *	更新日期
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
	
	public String toString()
	{
		return "ProductWm [" + 
					"wmId=" + wmId + 
					", productId=" + productId + 
					", wmCount=" + wmCount + 
					", lowestPrice=" + lowestPrice + 
					", wholesalePrice=" + wholesalePrice + 
					", avgCost=" + avgCost + 
					", minSale=" + minSale + 
					", maxSale=" + maxSale + 
					", saleStep=" + saleStep + 
					", totalSales=" + totalSales + 
					", numberTotal=" + numberTotal + 
					", updateDate=" + updateDate + 
					", updateStaff=" + updateStaff + 
				"]";
	}
}

