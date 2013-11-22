/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-10-13
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.cache.config;

import java.util.Hashtable;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ConfigHandler extends DefaultHandler {

	private static Log log = LogFactory.getLog(ConfigHandler.class);

    // pattern to match file://, http://, jndi://
    private static final Pattern HAS_PROTOCOL = Pattern.compile("^\\w+:");

    private String confSystemId;

	private static Hashtable<String, String> dtdPaths = new Hashtable<String, String>();

    static {
        dtdPaths.put("-//cms.comp//DTD Content Management System 1.0//EN", "/comp/cms/web/dtds/cms1.0.dtd");
    }

    public ConfigHandler(String confSystemId) {
        this.confSystemId = confSystemId;
    }

    /**
     * Resolve the requested external entity.
     *
     * @param publicId The public identifier of the entity being referenced
     * @param systemId The system identifier of the entity being referenced
     * @throws org.xml.sax.SAXException if a parsing exception occurs
     */
    public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException {
        if (publicId == null) {
            if (log.isDebugEnabled()) {
                log.debug("Couldn't resolve entity with no publicId, system id is " + systemId);
            }
            if (confSystemId != null && !hasProtocol(systemId)) {
                return new InputSource(confSystemId.substring(0, confSystemId.lastIndexOf('/')) + "/" + systemId);
            }
            return new InputSource(systemId);
        }
        String entity = dtdPaths.get(publicId);

        if (entity == null) {
            if (log.isDebugEnabled()) {
                log.debug("Couldn't resolve DTD: " + publicId + ", " + systemId);
            }
            return null;
        }

        if (log.isDebugEnabled()) {
            log.debug("Resolving to DTD " + entity);
        }
        return new InputSource(ConfigHandler.class.getResourceAsStream(entity));
    }

    /**
     * Check for protocol on a systemId.
     * eg, file://blah, http://blah, jndi://blah have protocols
     * /blah does not
     *
     * @param systemId the full systemId
     * @return true if systemId has protocol
     */
    private static boolean hasProtocol(String systemId) {
        return systemId != null && HAS_PROTOCOL.matcher(systemId).find();
    }

    //
    // ErrorHandler methods
    //

    public void warning(SAXParseException ex) {
        log.debug("error: " + ex.getMessage());
    }

    public void error(SAXParseException ex) {

        log.debug("error: " + ex.getMessage());
    }

    public void fatalError(SAXParseException ex) throws SAXException {
        log.debug("error: " + ex.getMessage());
    }

}
