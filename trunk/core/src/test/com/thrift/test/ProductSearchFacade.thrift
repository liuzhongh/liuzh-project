include "ServiceException.thrift"

namespace java com.shangkang.facade

service ProductSearchTfFacade {
	string queryProductNamesResult(1:string keyword)
			throws (1:ServiceException.ServiceException se);

	string queryProductResult(1:string pagination, 2:string queryCondition)
			throws (1:ServiceException.ServiceException se);

	string listAreas() throws (1:ServiceException.ServiceException se);

	string queryProductEffByType(1:string productType)
			throws (1:ServiceException.ServiceException se);

	string queryAllProductTypeCommonType()
			throws (1:ServiceException.ServiceException se);
}