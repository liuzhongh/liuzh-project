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
package com.shangkang.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.shangkang.bo.Information;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.mapper.InformationMapper;

public class BusinessService {

	private InformationMapper	informationMapper;

	@Autowired
	public void setInformationDao(InformationMapper informationMapper)
	{
		this.informationMapper = informationMapper;
	}

	public Information getByPK(Integer pk) throws ServiceException
	{
		Information info = informationMapper.getByPK(pk);
		return info;
	}
	
	public void insert(Information infor, Information infor1) throws ServiceException
	{
		informationMapper.save(infor);
		informationMapper.save(infor1);
	}
}
