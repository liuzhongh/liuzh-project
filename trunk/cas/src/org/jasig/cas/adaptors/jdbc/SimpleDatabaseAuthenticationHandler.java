/**
 * COPYRIGHT (C) 2013 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-7-3
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.jasig.cas.adaptors.jdbc;

import java.util.Map;

import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.BadPasswordAuthenticationException;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

public class SimpleDatabaseAuthenticationHandler extends
		AbstractJdbcUsernamePasswordAuthenticationHandler {

	@Override
	protected boolean authenticateUsernamePasswordInternal(
			UsernamePasswordCredentials credentials)
			throws AuthenticationException
	{
		final String username = getPrincipalNameTransformer().transform(credentials.getUsername());
        final String password = credentials.getPassword();
        final String encryptedPassword = this.getPasswordEncoder().encode(
            password);
        final String sql = "select LOGIN_PASSWORD, USER_STATUS from T_USER where lower(LOGIN_NAME) = lower(?)";
        
        try {
        	
            final Map<String, Object> result = getJdbcTemplate().queryForMap(sql, username);
            
            if(!result.get("LOGIN_PASSWORD").toString().equals(encryptedPassword))
            	throw new BadPasswordAuthenticationException("p");
            
            String userStatus = (String) result.get("USER_STATUS");
            
            if("0".equals(userStatus))
            	throw new BadPasswordAuthenticationException(userStatus);
            
            return true;
        } catch (final IncorrectResultSizeDataAccessException e) {
            // this means the username was not found.
        	throw new BadPasswordAuthenticationException("u");
        }
	}

}
