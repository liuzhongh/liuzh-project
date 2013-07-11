package com.platform.test.core.dao;

import com.platform.test.GenericTestCase;
import com.shangkang.bo.Information;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.mapper.InformationMapper;

public class InformationDaoTest extends GenericTestCase {

	private InformationMapper informationMapper;
	
	protected void setUp() throws Exception {
		super.setUp();
		informationMapper = (InformationMapper) this.getBean("informationMapper");
	}
	
	public void testSelect() throws ServiceException
	{
		Information infor = informationMapper.getByPK(1);
		
		System.out.println(infor.getName());
	}
	
	public void testSave() throws ServiceException
	{
		Information infor = new Information();
		
		infor.setName("dfd24");
		infor.setPassword("dfd24");
		
		informationMapper.save(infor);
		
		System.out.println(infor.getId());
	}
}
