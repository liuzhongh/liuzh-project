package com.shangkang.remote;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import com.caucho.hessian.client.HessianProxyFactory;
import com.shangkang.android.constant.Constant;
import com.shangkang.bo.CommonType;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException;
import com.shangkang.dto.AutoCompletedDto;
import com.shangkang.dto.ProductDetailDto;
import com.shangkang.dto.SkuDto;
import com.shangkang.facade.ProductSearchFacade;

public class ProductSearchRemote {

    private ProductSearchFacade productSearchFacade;

    public ProductSearchRemote()
    {
        String url = Constant.REMOTE_URL + "/product.remote";

        productSearchFacade = (ProductSearchFacade) ProductSearchRemote.loadRemote(
                ProductSearchFacade.class, url);
    }

	public List<AutoCompletedDto> loadProductNamesResult(String keyword) {

		try {
            /*String url = Constant.REMOTE_URL + "/product.remote";

            productSearchFacade = (ProductSearchFacade) ProductSearchRemote.loadRemote(
                    ProductSearchFacade.class, url);*/

			return productSearchFacade.queryProductNamesResult(keyword);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

    public Pagination<ProductDetailDto> loadProductDtosResult(Pagination<ProductDetailDto> pagination, Map<String, String> queryCondition)
    {
        try{
            return productSearchFacade.queryProductResult(pagination, queryCondition);

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List<CommonType> listAreas()
    {
        try {
            return productSearchFacade.listAreas();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<CommonType> queryAllProductTypeCommonType()
    {
        try {
            return productSearchFacade.queryAllProductTypeCommonType();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CommonType> queryProductEffByType(String productType)
    {
        try {
            return productSearchFacade.queryProductEffByType(productType);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

	public static <T> T loadRemote(Class<T> clazz, String url)
	{
        HessianProxyFactory factory = new HessianProxyFactory(); 
        
        factory.setOverloadEnabled(true);
        
        /*用 Hessian 实现 web service 过程中，需要创建对象时，是使用 HTTP POST 方法来传递数据的。但是在有反向代理 (nginx) 的情况下，会抛出异常 (com.caucho.hessian.client.HessianConnectionException: 411:java.io.IOException: Server returned HTTP response code: 411 for URL:http://xxxx/xxx/xxxService) 。
		 *首先来看下 HTTP 411 错误的解释： Length Required 服务器不能处理请求，除非客户发送一个 Content-Length 头。（ HTTP 1.1 新）这是因为 Hessian 与服务端通信默认是采取分块的方式 (chunked encoding) 发送数据，而反向代理要获得 Content-Length 这个头，才能处理请求，但是 Hessian 的请求中并没有加入这个参数。
		 */
		factory.setChunkedPost(false);
        
		try
		{
			T remote = (T) factory.create(clazz, url);
			factory.setHessian2Reply(false);
			
			return remote;
			
		} catch (MalformedURLException e)
		{
		}
		
		return null;
	}
}
