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
package org.code.generator.dom;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParserEntityResolver implements EntityResolver {

	public ParserEntityResolver() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String,
     * java.lang.String)
     */
    public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException, IOException {
//        if (ConfigurationParser.GENERATOR_CONFIG_SYSTEM_ID.equalsIgnoreCase(systemId)) {
            InputStream is = getClass().getClassLoader().getResourceAsStream(
                    "org/code/generator/dom/code-generator-config_1_0.dtd"); //$NON-NLS-1$
            InputSource ins = new InputSource(is);

            return ins;        
//        } else {
//            return null;
//        }
    }
}
