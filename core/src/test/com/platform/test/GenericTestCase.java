/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Dec 20, 2010
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.platform.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class GenericTestCase extends TestCase {

	private static ApplicationContext ctx;
	static{
		String[] str = {"spring/spring-core.xml"};
		ctx =  new ClassPathXmlApplicationContext(str);
	}
	
	protected Object getBean(String beanId)
	{
		return ctx.getBean(beanId);
	}
}
