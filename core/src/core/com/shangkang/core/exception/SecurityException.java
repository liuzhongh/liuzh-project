/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: 2011-3-22
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.core.exception;

import java.util.Vector;

public class SecurityException extends ServiceException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private static final String CERTIFICATION_AUTHORITY_ERROR = "error.message.certificationAuthorityError";
	
	public SecurityException()
	{
		super(CERTIFICATION_AUTHORITY_ERROR);
	}
	
	public SecurityException(String messageKey)
	{
		super(messageKey);
	}
	
	/**
	 * Creates object with messageKey and parameters.
	 */
	@SuppressWarnings("rawtypes")
	public SecurityException(String messageKey, Vector parameters)
	{
		super(messageKey, parameters.toArray());
	}
	
	/**
	 * Creates object with message and parameters.
	 */
	public SecurityException(String messageKey, Object[] parameters)
	{
		super(messageKey, parameters);
	}
	
	/**
	 * Creates object with message and 1 parameter.
	 */
	public SecurityException(String messageKey, String parameter1)
	{
		super(messageKey, new Object[]{parameter1});
	}
	
	/**
	 * Creates object with message and 2 parameter.
	 */
	public SecurityException(String messageKey, String parameter1, String parameter2)
	{
		super(messageKey, new Object[]{parameter1, parameter2});
	}
	
	/**
	 * Creates object with message and 3 parameter.
	 */
	public SecurityException(String messageKey, String parameter1, String parameter2, String parameter3)
	{
		super(messageKey, new Object[]{parameter1, parameter2, parameter3});
	}
	
	/**
	 * Creates object with message and 4 parameter.
	 */
	public SecurityException(String messageKey, String parameter1, String parameter2, String parameter3, String parameter4)
	{
		super(messageKey, new Object[]{parameter1, parameter2, parameter3, parameter4});
	}
}
