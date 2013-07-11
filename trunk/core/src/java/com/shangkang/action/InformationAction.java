package com.shangkang.action;

import com.shangkang.bo.Information;
import com.shangkang.core.action.BaseAction;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.facade.test.BusinessFacade;

public class InformationAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7512780373454925047L;

	private Information infor;

	public Information getInfor() {
		return infor;
	}

	public void setInfor(Information infor) {
		this.infor = infor;
	}
	
	public String doLogin() throws ServiceException
	{
		BusinessFacade facade = (BusinessFacade) this.getBean("businessFacade");
		
		if(infor == null)
			return ERROR;
		
		facade.insert(infor, infor);
		
		return SUCCESS;
	}
}
