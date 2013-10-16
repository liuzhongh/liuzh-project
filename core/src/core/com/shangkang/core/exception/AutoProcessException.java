/**
 * COPYRIGHT (C) 2013 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013年10月16日
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.core.exception;

public class AutoProcessException extends ServiceException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public AutoProcessException()
	{
		super();
	}

	public AutoProcessException(Exception exception)
	{
		super(exception);
	}

	public AutoProcessException(String messageKey, Exception exception)
	{
		super(messageKey, exception);
	}

	public AutoProcessException(String messageKey, Exception exception, Object... parameters)
	{
		super(messageKey, parameters, exception);
	}

	public AutoProcessException(String messageKey, Object... parameters)
	{
		super(messageKey, parameters);
	}

	public AutoProcessException(String messageKey)
	{
		super(messageKey);
	}

	
}
