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
package com.shangkang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shangkang.bo.BatchJob;
import com.shangkang.core.mapper.GenericIBatisMapper;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.DataAccessFailureException;

public interface BatchJobMapper extends GenericIBatisMapper<BatchJob, java.lang.Long> {

	public List<BatchJob> listPaginationByProperty(Pagination<BatchJob> pagination, BatchJob batchJob) throws DataAccessFailureException;
	
	public List<BatchJob> listJobByJobPriority(@Param("jobStatus")List<String> jobStatus) throws DataAccessFailureException;
	
	public List<BatchJob> listJobByJobPriorityAndJobType(String jobType) throws DataAccessFailureException;
	
	public List<BatchJob> listOnceJobByJobPriority(@Param("jobType")String jobType,@Param("jobStatus")List<String> jobStatus) throws DataAccessFailureException;
	
	public List<BatchJob> listJobByNotFinished(@Param("jobStatus")List<String> jobStatus) throws DataAccessFailureException;
	
	
}
