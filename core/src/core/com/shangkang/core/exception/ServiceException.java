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

import java.util.Vector;

import com.shangkang.core.resources.MessageResource;

public class ServiceException extends ApplicationException {

	protected String errorMessage;
	
	public String getErrorMessage()
	{
		if(errorMessage == null)
			return getMessage();
		
		return errorMessage;
	}
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Creates object.
	 */
	public ServiceException()
	{
		super(MessageResource.SERVICE_ERROR);
	}
	
	/**
	 * Creates object with exception.
	 */
	public ServiceException(Exception exception)
	{
		this();
		printExcStackTrace(exception);
	}

	/**
	 * Creates object with messageKey.
	 */
	public ServiceException(String messageKey)
	{
		super(messageKey);
	}
	
	/**
	 * Creates object with messageKey and exception.
	 */
	public ServiceException(String messageKey, Exception exception)
	{
		this(messageKey);
		printExcStackTrace(exception);
	}

	/**
	 * Creates object with messageKey and parameters.
	 */
	@SuppressWarnings("rawtypes")
	public ServiceException(String messageKey, Vector parameters)
	{
		super(messageKey, parameters.toArray());
	}
	
	/**
	 * Creates object with messageKey and parameters and exception.
	 */
	@SuppressWarnings("rawtypes")
	public ServiceException(String messageKey, Vector parameters, Exception exception)
	{
		this(messageKey, parameters.toArray(), exception);
	}
	
	/**
	 * Creates object with messageKey and parameters.
	 */
	public ServiceException(String messageKey, Object[] parameters)
	{
		super(messageKey, parameters);
	}
	
	/**
	 * Creates object with messageKey and parameters and exception.
	 */
	public ServiceException(String messageKey, Object[] parameters, Exception exception)
	{
		super(messageKey, parameters, exception);
	}

	/**
	 * Creates object with messageKey and 1 parameter.
	 */
	public ServiceException(String messageKey, String parameter1)
	{
		super(messageKey, new Object[]{parameter1});
	}
	
	/**
	 * Creates object with messageKey and 1 parameter and exception.
	 */
	public ServiceException(String messageKey, String parameter1, Exception exception)
	{
		super(messageKey, new Object[]{parameter1}, exception);
	}

	/**
	 * Creates object with messageKey and 2 parameter.
	 */
	public ServiceException(String messageKey, String parameter1, String parameter2)
	{
		super(messageKey, new Object[]{parameter1, parameter2});
	}
	
	/**
	 * Creates object with messageKey and 2 parameter and exception.
	 */
	public ServiceException(String messageKey, String parameter1, String parameter2, Exception exception)
	{
		super(messageKey, new Object[]{parameter1, parameter2}, exception);
	}

	/**
	 * Creates object with messageKey and 3 parameter.
	 */
	public ServiceException(String messageKey, String parameter1, String parameter2, String parameter3)
	{
		super(messageKey, new Object[]{parameter1, parameter2, parameter3});
	}
	
	/**
	 * Creates object with messageKey and 3 parameter and exception.
	 */
	public ServiceException(String messageKey, String parameter1, String parameter2, String parameter3, Exception exception)
	{
		super(messageKey, new Object[]{parameter1, parameter2, parameter3}, exception);
	}

	/**
	 * Creates object with message and 4 parameter.
	 */
	public ServiceException(String messageKey, String parameter1, String parameter2, String parameter3, String parameter4)
	{
		super(messageKey, new Object[]{parameter1, parameter2, parameter3, parameter4});
	}
	
	/**
	 * Creates object with message and 4 parameter and exception.
	 */
	public ServiceException(String messageKey, String parameter1, String parameter2, String parameter3, String parameter4, Exception exception)
	{
		super(messageKey, new Object[]{parameter1, parameter2, parameter3, parameter4}, exception);
	}

}
