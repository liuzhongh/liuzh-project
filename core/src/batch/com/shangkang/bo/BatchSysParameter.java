/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-3-23 14:34:25
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class BatchSysParameter extends Model{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	索引
	  */
	private java.lang.Long batchSysParameterOid;

	/**
	  *	参数名
	  */
	private java.lang.String batchParameter;

	/**
	  *	参数值
	  */
	private java.lang.String parameterValue;

	/**
	  *	描述
	  */
	private java.lang.String description;

	/**
	  *	创建人
	  */
	private java.lang.String createBy;

	/**
	  *	创建时间
	  */
	private java.lang.String createDate;

	/**
	  *	修改人
	  */
	private java.lang.String updateBy;

	/**
	  *	修改时间
	  */
	private java.lang.String updateDate;

	/**
	  *	索引
	  */
	public java.lang.Long getBatchSysParameterOid() 
	{
		return batchSysParameterOid;
	}
	
	/**
	  *	索引
	  */
	public void setBatchSysParameterOid(java.lang.Long batchSysParameterOid) 
	{
		this.batchSysParameterOid = batchSysParameterOid;
	}
	
	/**
	  *	参数名
	  */
	public java.lang.String getBatchParameter() 
	{
		return batchParameter;
	}
	
	/**
	  *	参数名
	  */
	public void setBatchParameter(java.lang.String batchParameter) 
	{
		this.batchParameter = batchParameter;
	}
	
	/**
	  *	参数值
	  */
	public java.lang.String getParameterValue() 
	{
		return parameterValue;
	}
	
	/**
	  *	参数值
	  */
	public void setParameterValue(java.lang.String parameterValue) 
	{
		this.parameterValue = parameterValue;
	}
	
	/**
	  *	描述
	  */
	public java.lang.String getDescription() 
	{
		return description;
	}
	
	/**
	  *	描述
	  */
	public void setDescription(java.lang.String description) 
	{
		this.description = description;
	}
	
	/**
	  *	创建人
	  */
	public java.lang.String getCreateBy() 
	{
		return createBy;
	}
	
	/**
	  *	创建人
	  */
	public void setCreateBy(java.lang.String createBy) 
	{
		this.createBy = createBy;
	}
	
	/**
	  *	创建时间
	  */
	public java.lang.String getCreateDate() 
	{
		return createDate;
	}
	
	/**
	  *	创建时间
	  */
	public void setCreateDate(java.lang.String createDate) 
	{
		this.createDate = createDate;
	}
	
	/**
	  *	修改人
	  */
	public java.lang.String getUpdateBy() 
	{
		return updateBy;
	}
	
	/**
	  *	修改人
	  */
	public void setUpdateBy(java.lang.String updateBy) 
	{
		this.updateBy = updateBy;
	}
	
	/**
	  *	修改时间
	  */
	public java.lang.String getUpdateDate() 
	{
		return updateDate;
	}
	
	/**
	  *	修改时间
	  */
	public void setUpdateDate(java.lang.String updateDate) 
	{
		this.updateDate = updateDate;
	}
	
	public String toString()
	{
		return "BatchSysParameter [" + 
					"batchSysParameterOid=" + batchSysParameterOid + 
					", batchParameter=" + batchParameter + 
					", parameterValue=" + parameterValue + 
					", description=" + description + 
					", createBy=" + createBy + 
					", createDate=" + createDate + 
					", updateBy=" + updateBy + 
					", updateDate=" + updateDate + 
				"]";
	}
}

