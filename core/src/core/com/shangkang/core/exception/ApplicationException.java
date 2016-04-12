/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Dec 15, 2010
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.core.exception;

import com.shangkang.core.util.ResourceBundleMessageSourceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ApplicationException extends Exception {
	private Logger				logger				= LoggerFactory.getLogger(getClass());
	
	private String message;
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Creates object.
	 */
	protected ApplicationException()
	{
		super();
	}
	
	/**
	 * Creates object with message.
	 */
	protected ApplicationException(String messageKey)
	{
		super(messageKey);
		setMessage(messageKey, null, null);
	}
	
	/**
	 * Creates object with message and parameters.
	 */
	protected ApplicationException(String messageKey, Object[] parameters)
	{
		super(messageKey);
		setMessage(messageKey, parameters, null);
	}
	
	/**
	 * Creates object with message and parameters and exception.
	 */
	protected ApplicationException(String messageKey, Object[] parameters, Exception exception)
	{
		super(messageKey);
		setMessage(messageKey, parameters, exception);
	}
	
	protected void printExcStackTrace(Exception e)
	{
		if(null != e)
		{
			logger.error("", e);
			e.printStackTrace();
		}
	}
	
	private void setMessage(String messageKey, Object[] parameters, Exception exception)
	{
		if(null != messageKey)
		{
			try
			{
				message = ResourceBundleMessageSourceHelper.getMessage(messageKey, parameters);
			}catch(Exception e)
			{
				message = e.getMessage();

				if(message == null)
					message = messageKey;
//				printExcStackTrace(e);
			}
		}

		printExcStackTrace(exception);
	}
	
	public String getMessage()
	{
		return message;
	}
}
