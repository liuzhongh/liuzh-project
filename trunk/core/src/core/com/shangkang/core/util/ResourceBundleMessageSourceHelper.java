/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Dec 30, 2010
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.core.util;

import org.springframework.context.support.ResourceBundleMessageSource;

public class ResourceBundleMessageSourceHelper {

	public static String getMessage(String messageKey)
	{
		return getMessage(messageKey, null);
	}
	
	public static String getMessage(String messageKey, Object[] parameters)
	{
		ResourceBundleMessageSource messageSource = (ResourceBundleMessageSource) SpringBeanHelper.getBean("messageSource");
		String message = messageSource.getMessage(messageKey, parameters, null);
		
		return message;
	}
}
