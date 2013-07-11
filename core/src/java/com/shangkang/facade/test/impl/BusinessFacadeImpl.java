/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Dec 16, 2010
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.facade.test.impl;

import com.shangkang.bo.Information;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.facade.test.BusinessFacade;
import com.shangkang.service.BusinessService;
import com.shangkang.tools.UtilHelper;

public class BusinessFacadeImpl implements BusinessFacade {

	private BusinessService businessService;
	
	public void setBusinessService(BusinessService businessService)
	{
		this.businessService = businessService;
	}

	public Information getByPK(Integer pk) throws ServiceException
	{
		return businessService.getByPK(pk);
	}

	@Override
	public void insert(Information infor, Information infor1) throws ServiceException {
		
		if(UtilHelper.isEmpty(infor.getName()))
			throw new ServiceException();
		
		businessService.insert(infor, infor1);
	}
}
