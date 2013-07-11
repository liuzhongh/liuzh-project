/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: 2011-3-15
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.core.bo;

import java.util.List;

public class Pagination<T> implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public final static int DEFAULT_PAGE_SIZE = 15; 

	private int total;
	
	private int totalPage;
	
	private int pageNo;
	
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	private boolean paginationFlag = false;
	
	private List<T> resultList;

	public Pagination()
	{
		super();
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public int getPageNo()
	{
		return pageNo;
	}

	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public boolean isPaginationFlag()
	{
		return paginationFlag;
	}

	public void setPaginationFlag(boolean paginationFlag)
	{
		this.paginationFlag = paginationFlag;
	}

	public List<T> getResultList()
	{
		return resultList;
	}

	public void setResultList(List<T> resultList)
	{
		this.resultList = resultList;
	}

	public int getTotalPage()
	{
		this.totalPage = (int)Math.ceil((double) this.total / this.pageSize);
		
		return totalPage;
	}

	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage;
	}

	@Override
	public String toString()
	{
		return "Pagination [total=" + total + ", totalPage=" + totalPage
				+ ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", paginationFlag=" + paginationFlag + ", resultList="
				+ resultList + "]";
	}
	
}
