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

import com.shangkang.bo.BatchSysParameter;
import com.shangkang.core.mapper.GenericIBatisMapper;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.DataAccessFailureException;

public interface BatchSysParameterMapper extends GenericIBatisMapper<BatchSysParameter, java.lang.Long> {

	public List<BatchSysParameter> listPaginationByProperty(Pagination<BatchSysParameter> pagination, BatchSysParameter batchSysParameter) throws DataAccessFailureException;
	
	public String getParameterValueByParameter(String parameter);
}
