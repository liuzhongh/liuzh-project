/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-2-26 10:33:23
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shangkang.bo.SystemMenu;
import com.shangkang.core.mapper.GenericIBatisMapper;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.DataAccessFailureException;

public interface SystemMenuMapper extends GenericIBatisMapper<SystemMenu, java.lang.Long> {

	public List<SystemMenu> listPaginationByProperty(Pagination<SystemMenu> pagination, SystemMenu systemMenu) throws DataAccessFailureException;
	
	public List<SystemMenu> findMenu4UserIdAndParentMenuId(@Param("userId")Long userId, @Param("parentMenuId")Long parentMenuId) throws DataAccessFailureException;
	
	public List<SystemMenu> findRootMenu4UserId(Long userId) throws DataAccessFailureException;
}
