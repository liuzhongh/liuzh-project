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

public class LoginSecurityException extends SecurityException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private static final String ANONYMOUS_USER_LOGIN = "error.message.anonymousUserLogin";
	
	/**
	 * Creates object.
	 */
	public LoginSecurityException()
	{
		super(ANONYMOUS_USER_LOGIN);
	}

}
