/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-4-24
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.code.generator.config;

import org.code.generator.dom.XmlElement;

public class PluginConfiguration extends CommonPluginConfiguration {

	private JavaTypeResolverConfiguration javaTypeResolverConfiguration;
	

	public PluginConfiguration()
	{
		super();
	}


	public JavaTypeResolverConfiguration getJavaTypeResolverConfiguration()
	{
		return javaTypeResolverConfiguration;
	}

	public void setJavaTypeResolverConfiguration(
			JavaTypeResolverConfiguration javaTypeResolverConfiguration)
	{
		this.javaTypeResolverConfiguration = javaTypeResolverConfiguration;
	}

	public XmlElement toXmlElement() {
        XmlElement xmlElement = new XmlElement("plugin"); //$NON-NLS-1$
       
        if (javaTypeResolverConfiguration != null) {
            xmlElement.addElement(javaTypeResolverConfiguration.toXmlElement());
        }

        addXmlElements(xmlElement);

        return xmlElement;
    }
}
