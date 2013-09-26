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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.code.generator.config.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ConfigurationParser {

	private List<String> warnings;
    private List<String> parseErrors;
    private Properties properties;

    public static final String GENERATOR_CONFIG_SYSTEM_ID = "F:/work/workspace/code-generator/src/code/org/code/generator/dom/code-generator-config_1_0.dtd";
    
    public ConfigurationParser(List<String> warnings) {
        this(null, warnings);
    }

    public ConfigurationParser(Properties properties, List<String> warnings) {
        super();
        if (properties == null) {
            this.properties = System.getProperties();
        } else {
            this.properties = properties;
        }

        if (warnings == null) {
            this.warnings = new ArrayList<String>();
        } else {
            this.warnings = warnings;
        }

        parseErrors = new ArrayList<String>();
    }

    public Configuration parseConfiguration(File inputFile) throws IOException,
            Exception {

        FileReader fr = new FileReader(inputFile);

        return parseConfiguration(fr);
    }

    public Configuration parseConfiguration(Reader reader) throws IOException,
            Exception {

        InputSource is = new InputSource(reader);

        return parseConfiguration(is);
    }

    public Configuration parseConfiguration(InputStream inputStream)
            throws IOException, Exception {

        InputSource is = new InputSource(inputStream);

        return parseConfiguration(is);
    }

    private Configuration parseConfiguration(InputSource inputSource)
            throws IOException, Exception {
        parseErrors.clear();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver(new ParserEntityResolver());

            ParserErrorHandler handler = new ParserErrorHandler(warnings,
                    parseErrors);
            builder.setErrorHandler(handler);

            Document document = null;
            try {
                document = builder.parse(inputSource);
            } catch (SAXParseException e) {
            	e.printStackTrace();
                throw new Exception(e.toString());
            } catch (SAXException e) {
            	e.printStackTrace();
                if (e.getException() == null) {
                    parseErrors.add(e.getMessage());
                } else {
                    parseErrors.add(e.getException().getMessage());
                }
            }

            if (parseErrors.size() > 0) {
                throw new Exception(parseErrors.toString());
            }

            Configuration config = null;
            Element rootNode = document.getDocumentElement();
//            DocumentType docType = document.getDoctype();
            if (rootNode.getNodeType() == Node.ELEMENT_NODE
//                    && docType.getSystemId().equals(
//                    		GENERATOR_CONFIG_SYSTEM_ID)
                    		) {
                config = parseGeneratorConfiguration(rootNode);
            } 

            return config;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new Exception(e);
        }
    }
    
    private Configuration parseGeneratorConfiguration(Element rootNode)
            throws Exception {
        GeneratorConfigurationParser parser = new GeneratorConfigurationParser(
                properties);
        return parser.parseConfiguration(rootNode);
    }
}
