/**
 * COPYRIGHT (C) 2013 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013年9月14日
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.facade;

import java.util.List;
import java.util.Map;

import com.shangkang.bo.CommonType;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException1;
import com.shangkang.dto.AutoCompletedDto;
import com.shangkang.dto.ProductDetailDto;

public interface ProductSearchFacade {

	public abstract List<AutoCompletedDto> queryProductNamesResult(String keyword)
			throws ServiceException1;

	public abstract Pagination<ProductDetailDto> queryProductResult(Pagination<ProductDetailDto> pagination, Map<String, String> queryCondition)
			throws ServiceException1;

	public abstract List<CommonType> listAreas() throws ServiceException1;

	public abstract List<CommonType> queryProductEffByType(String productType)
			throws ServiceException1;

	public abstract List<CommonType> queryAllProductTypeCommonType()
			throws ServiceException1;

}