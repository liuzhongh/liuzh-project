/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-4-23
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.code.generator.config;

import org.code.generator.dom.Attribute;
import org.code.generator.dom.XmlElement;

public class JDBCConnectionConfiguration extends PropertyHolder {

	private String driverClass;

    private String connectionURL;

    private String userId;

    private String password;
    
    private String remarksReporting;

	public String getDriverClass()
	{
		return driverClass;
	}

	public void setDriverClass(String driverClass)
	{
		this.driverClass = driverClass;
	}

	public String getConnectionURL()
	{
		return connectionURL;
	}

	public void setConnectionURL(String connectionURL)
	{
		this.connectionURL = connectionURL;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getRemarksReporting()
	{
		return remarksReporting;
	}

	public void setRemarksReporting(String remarksReporting)
	{
		this.remarksReporting = remarksReporting;
	}
    
	public XmlElement toXmlElement() {
        XmlElement xmlElement = new XmlElement("jdbcConnection"); //$NON-NLS-1$
        xmlElement.addAttribute(new Attribute("driverClass", driverClass)); //$NON-NLS-1$
        xmlElement.addAttribute(new Attribute("connectionURL", connectionURL)); //$NON-NLS-1$

        if (stringHasValue(userId)) {
            xmlElement.addAttribute(new Attribute("userId", userId)); //$NON-NLS-1$
        }
        
        if(stringHasValue(remarksReporting))
        {
        	xmlElement.addAttribute(new Attribute("remarksReporting", remarksReporting)); //$NON-NLS-1$
        }

        if (stringHasValue(password)) {
            xmlElement.addAttribute(new Attribute("password", password)); //$NON-NLS-1$
        }

        addPropertyXmlElements(xmlElement);

        return xmlElement;
    }

	public static boolean stringHasValue(String s) {
        return s != null && s.length() > 0;
    }
}
