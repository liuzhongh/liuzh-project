package com.shangkang.core.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shangkang.core.util.SpringBeanHelper;

public abstract class BaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	protected Log				logger				= LogFactory
															.getLog(getClass());
	
	private String ajaxErrorCode;
	
	protected int pageNo = 1;
	
	protected int total;
	
	protected int pageSize = 15;
	
	@SuppressWarnings("rawtypes")
	protected List rows;
	
	protected boolean	paginationFlag;
	
	/**
	 * @return the ajaxErrorCode
	 */
	public String getAjaxErrorCode()
	{
		return ajaxErrorCode;
	}

	/**
	 * @param ajaxErrorCode the ajaxErrorCode to set
	 */
	public void setAjaxErrorCode(String ajaxErrorCode)
	{
		this.ajaxErrorCode = ajaxErrorCode;
	}

	/**
	 * 
	 * @param paginationFlag
	 */
	public void setPaginationFlag(boolean paginationFlag)
	{
		this.paginationFlag = paginationFlag;
	}
	
	/**
	 * @return the paginationFlag
	 */
	public boolean isPaginationFlag()
	{
		return paginationFlag;
	}

	/**
	 * @return the pageNo
	 */
	public int getPageNo()
	{
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNumber)
	{
		this.pageNo = pageNumber;
	}

	/**
	 * @return the total
	 */
	public int getTotal()
	{
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total)
	{
		this.total = total;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize()
	{
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * @return the list
	 */
	@SuppressWarnings("rawtypes")
	public List getRows()
	{
		return rows;
	}

	protected Object getBean(String beanId)
	{
		return SpringBeanHelper.getBean(beanId);
	}
	protected HttpServletResponse getResponse()
	{
		return ServletActionContext.getResponse();
	}

	protected HttpServletRequest getRequest()
	{
		return ServletActionContext.getRequest();
	}

	protected HttpSession getSession()
	{
		return getRequest().getSession();
	}

	protected String getParameter(String para)
	{
		return getRequest().getParameter(para);
	}

}
