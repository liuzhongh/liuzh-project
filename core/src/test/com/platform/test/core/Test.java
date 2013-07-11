/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-1-24
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.platform.test.core;

import com.caucho.hessian.client.HessianProxyFactory;
import com.shangkang.facade.HessianTestFacade;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long l = System.currentTimeMillis();
		System.out.println(l);
		String url = "http://localhost:8080/hello.remote";  
        HessianProxyFactory factory = new HessianProxyFactory(); 
        factory.setOverloadEnabled(true);
        
        HessianTestFacade businessFacade;
		try
		{
			businessFacade = (HessianTestFacade) factory.create(HessianTestFacade.class, url);
			
			System.out.println(businessFacade.test(121212));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
        
		System.out.println("times = " + (System.currentTimeMillis() - l));
	}

}
