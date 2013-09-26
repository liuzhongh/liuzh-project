/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: zzqiang
 * Created On: 2013-3-6
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.dto;

import java.io.Serializable;

import com.shangkang.bo.ProductPolicy;

public class ProductPolicyDto implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private ProductPolicy productPolicy;

	/**
	 * 该销售策略对当前登录的用户或者匿名访问的用户是不是可以的
	 */
	private boolean isVisible;

	
	public ProductPolicy getProductPolicy()
	{
		return productPolicy;
	}

	public void setProductPolicy(ProductPolicy productPolicy)
	{
		this.productPolicy = productPolicy;
	}

	public boolean isVisible()
	{
		return isVisible;
	}

	public void setVisible(boolean isVisible)
	{
		this.isVisible = isVisible;
	}

	@Override
	public String toString()
	{
		return "ProductPolicyDto [productPolicy=" + productPolicy
				+ ", isVisible=" + isVisible + "]";
	}
	
	
}
