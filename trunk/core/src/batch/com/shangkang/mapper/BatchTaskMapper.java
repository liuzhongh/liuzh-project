/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-3-23 14:34:23
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

import com.shangkang.bo.BatchTask;
import com.shangkang.core.mapper.GenericIBatisMapper;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.DataAccessFailureException;

public interface BatchTaskMapper extends GenericIBatisMapper<BatchTask, java.lang.Long> {

	public List<BatchTask> listPaginationByProperty(Pagination<BatchTask> pagination, BatchTask batchTask) throws DataAccessFailureException;
	
	public List<BatchTask> listBatchTaskByJobOidAndStatus(@Param("jobOid") Long jobOid, @Param("status") List<String> status) throws DataAccessFailureException;

	public List<BatchTask> listBatchTaskByJobOidAndExceptStatus(@Param("jobOid") Long jobOid, @Param("status") List<String> status) throws DataAccessFailureException;
}
