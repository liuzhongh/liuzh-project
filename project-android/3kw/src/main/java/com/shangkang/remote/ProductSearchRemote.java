package com.shangkang.remote;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shangkang.bo.CommonType;
import com.shangkang.core.bo.Pagination;
import com.shangkang.core.exception.ServiceException1;
import com.shangkang.dto.AutoCompletedDto;
import com.shangkang.dto.ProductDetailDto;
import com.shangkang.facade.ProductSearchFacade;
import com.shangkang.facade.ProductSearchTfFacade;
import com.thrift.test.PersonService;
import com.thrift.test.pojo.Person;
import com.thrift.test.pojo.Result;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;

public class ProductSearchRemote {

    private ProductSearchFacade productSearchFacade;
    private ProductSearchTfFacade.Client productSearchTfFacadeClient;

    public ProductSearchRemote()
    {
//        String url = Constant.REMOTE_URL + "/product.remote";
//        String url = "http://192.168.13.158/test/index.php?service=ProductSearchTfFacadeImpl";
        String url = "http://192.168.13.154/index.php/product_api/thrift_server?service=ProductSearchTfFacadeImpl";

        productSearchFacade = ProductSearchRemote.loadRemote(
                ProductSearchFacade.class, url);

        productSearchTfFacadeClient = ProductSearchRemote.loadThriftRemote(ProductSearchTfFacade.Client.class, url);
    }

	public List<AutoCompletedDto> loadProductNamesResult(String keyword) {

		try {
            /*String url = Constant.REMOTE_URL + "/product.remote";

            productSearchFacade = (ProductSearchFacade) ProductSearchRemote.loadRemote(
                    ProductSearchFacade.class, url);*/

		   /* Hession方式调用
			*return productSearchFacade.queryProductNamesResult(keyword);
			*
			* */

            /*String url = "http://192.168.13.158/jsonrpc_server.php";


            String values = ProductSearchRemote.loadJsonrpc(url).callString("add", 12, 32);

            System.out.println(values);

            String s = ProductSearchRemote.loadJsonrpc(url).callString("test");

            System.out.println(s);*/

            /*String url = "http://192.168.13.158/test/index.php?service=PersonServiceImpl";

            PersonService.Client client = ProductSearchRemote.loadThriftRemote(PersonService.Client.class, url);

            System.out.println(client.getValue());

            Gson gson = new Gson();

            Result result = gson.fromJson(client.getValue(), Result.class);

            System.out.println(result.getValue());

            Person person = new Person();

            person.setAge(12);
            person.setName("name1");
            person.setPasswod("password");

            Person p = client.getPerson(person);

            System.out.println(p.getName());*/

            Gson gson = new Gson();

            String value = productSearchTfFacadeClient.queryProductNamesResult(keyword);

            Type type = new TypeToken<List<AutoCompletedDto>>(){}.getType();

            return gson.fromJson(value, type);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

    public Pagination<ProductDetailDto> loadProductDtosResult(Pagination<ProductDetailDto> pagination, Map<String, String> queryCondition)
    {
        try{
//            return productSearchFacade.queryProductResult(pagination, queryCondition);
            Gson gson = new Gson();

            String result = productSearchTfFacadeClient.queryProductResult(gson.toJson(pagination), gson.toJson(queryCondition));

            Type type = new TypeToken<Pagination<ProductDetailDto>>(){}.getType();

            return gson.fromJson(result, type);

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List<CommonType> listAreas()
    {
        try {
//            return productSearchFacade.listAreas();

            Gson gson = new Gson();

            String result = productSearchTfFacadeClient.listAreas();

            Type type = new TypeToken<List<CommonType>>(){}.getType();

            return gson.fromJson(result, type);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<CommonType> queryAllProductTypeCommonType()
    {
        try {
//            return productSearchFacade.queryAllProductTypeCommonType();

            Gson gson = new Gson();

            String result = productSearchTfFacadeClient.queryAllProductTypeCommonType();

            Type type = new TypeToken<List<CommonType>>(){}.getType();

            return gson.fromJson(result, type);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CommonType> queryProductEffByType(String productType)
    {
        try {
            return productSearchFacade.queryProductEffByType(productType);
        } catch (ServiceException1 e) {
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

    public static <T> T loadThriftRemote(Class<T> clazz, String url)
    {
        TTransport transport = null;
        try
        {

            transport = new THttpClient(url);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);

            Constructor constructor = clazz.getDeclaredConstructor(new Class[]{TProtocol.class});

            T client = (T) constructor.newInstance(protocol);

            return client;

        } catch (Exception e)
        {
        }
        finally {
            if(null != transport)
                transport.close();
        }

        return null;
    }

}
