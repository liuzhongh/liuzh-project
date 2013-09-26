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

import java.util.Enumeration;
import java.util.Properties;

import org.code.generator.dom.Attribute;
import org.code.generator.dom.XmlElement;

public abstract class PropertyHolder {

	private Properties properties;

    /**
	 *  
	 */
    public PropertyHolder() {
        super();
        properties = new Properties();
    }

    public void addProperty(String name, String value) {
        properties.setProperty(name, value);
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }

    public Properties getProperties() {
        return properties;
    }

    protected void addPropertyXmlElements(XmlElement xmlElement) {
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String propertyName = (String) enumeration.nextElement();

            XmlElement propertyElement = new XmlElement("property"); //$NON-NLS-1$
            propertyElement.addAttribute(new Attribute("name", propertyName)); //$NON-NLS-1$
            propertyElement.addAttribute(new Attribute(
                    "value", properties.getProperty(propertyName))); //$NON-NLS-1$
            xmlElement.addElement(propertyElement);
        }
    }
}
