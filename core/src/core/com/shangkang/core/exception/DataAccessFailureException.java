/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Dec 18, 2010
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

public class DataAccessFailureException extends ServiceException{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Creates object.
	 */
	public DataAccessFailureException()
	{
		super(MessageResource.DATA_ACCESS_ERROR);
	}
	
	/**
	 * Creates object with exception.
	 */
	public DataAccessFailureException(Exception exception)
	{
		super(MessageResource.DATA_ACCESS_ERROR, exception);
	}

	/**
	 * Creates object with messageKey.
	 */
	public DataAccessFailureException(String messageKey)
	{
		super(messageKey);
	}
	
	/**
	 * Creates object with messageKey and exception.
	 */
	public DataAccessFailureException(String messageKey, Exception exception)
	{
		super(messageKey, exception);
	}

	/**
	 * Creates object with messageKey and parameters.
	 */
	@SuppressWarnings("rawtypes")
	public DataAccessFailureException(String messageKey, Vector parameters)
	{
		super(messageKey, parameters.toArray());
	}
	
	/**
	 * Creates object with messageKey and parameters and exception.
	 */
	@SuppressWarnings("rawtypes")
	public DataAccessFailureException(String messageKey, Vector parameters, Exception exception)
	{
		super(messageKey, parameters.toArray(), exception);
	}
	
	/**
	 * Creates object with messageKey and parameters.
	 */
	public DataAccessFailureException(String messageKey, Object[] parameters)
	{
		super(messageKey, parameters);
	}
	
	/**
	 * Creates object with messageKey and parameters and exception.
	 */
	public DataAccessFailureException(String messageKey, Object[] parameters, Exception exception)
	{
		super(messageKey, parameters, exception);
	}

	/**
	 * Creates object with messageKey and 1 parameter.
	 */
	public DataAccessFailureException(String messageKey, String parameter1)
	{
		super(messageKey, new Object[]{parameter1});
	}
	
	/**
	 * Creates object with messageKey and 1 parameter and exception.
	 */
	public DataAccessFailureException(String messageKey, String parameter1, Exception exception)
	{
		super(messageKey, new Object[]{parameter1}, exception);
	}

	/**
	 * Creates object with messageKey and 2 parameter.
	 */
	public DataAccessFailureException(String messageKey, String parameter1, String parameter2)
	{
		super(messageKey, new Object[]{parameter1, parameter2});
	}
	
	/**
	 * Creates object with messageKey and 2 parameter and exception.
	 */
	public DataAccessFailureException(String messageKey, String parameter1, String parameter2, Exception exception)
	{
		super(messageKey, new Object[]{parameter1, parameter2}, exception);
	}

	/**
	 * Creates object with messageKey and 3 parameter.
	 */
	public DataAccessFailureException(String messageKey, String parameter1, String parameter2, String parameter3)
	{
		super(messageKey, new Object[]{parameter1, parameter2, parameter3});
	}
	
	/**
	 * Creates object with messageKey and 3 parameter and exception.
	 */
	public DataAccessFailureException(String messageKey, String parameter1, String parameter2, String parameter3, Exception exception)
	{
		super(messageKey, new Object[]{parameter1, parameter2, parameter3}, exception);
	}

	/**
	 * Creates object with message and 4 parameter.
	 */
	public DataAccessFailureException(String messageKey, String parameter1, String parameter2, String parameter3, String parameter4)
	{
		super(messageKey, new Object[]{parameter1, parameter2, parameter3, parameter4});
	}
	
	/**
	 * Creates object with message and 4 parameter and exception.
	 */
	public DataAccessFailureException(String messageKey, String parameter1, String parameter2, String parameter3, String parameter4, Exception exception)
	{
		super(messageKey, new Object[]{parameter1, parameter2, parameter3, parameter4}, exception);
	}
}
