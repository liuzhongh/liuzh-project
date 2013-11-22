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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXParseException;

import com.core.cache.Rule;
import com.shangkang.tools.UtilHelper;

public class Configuration {

	private static Log			log		= LogFactory.getLog(Configuration.class);

	private final List<String>	errors	= new ArrayList<String>();
	
	private List<String> requestUris = new ArrayList<String>();
	
	private Map<String, Rule> ruleMap = new HashMap<String, Rule>(50);
	
	private String defaultNamespace;
	private String defaultRegeneratedInterval;
	private String cacheType;

	public Configuration(final InputStream inputStream, String fileName, String cacheType)
	{
		this.cacheType = cacheType;
		loadDom(inputStream, fileName);
	}


	public String getDefaultNamespace()
	{
		return defaultNamespace;
	}


	public void setDefaultNamespace(String defaultNamespace)
	{
		this.defaultNamespace = defaultNamespace;
	}


	/**
	 * @return the defaultRegeneratedInterval
	 */
	public String getDefaultRegeneratedInterval()
	{
		return defaultRegeneratedInterval;
	}

	/**
	 * @param defaultRegeneratedInterval the defaultRegeneratedInterval to set
	 */
	public void setDefaultRegeneratedInterval(String defaultRegeneratedInterval)
	{
		this.defaultRegeneratedInterval = defaultRegeneratedInterval;
	}

	public Map<String, Rule> getRuleMap()
	{
		return ruleMap;
	}

	/**
	 * @return the requestUris
	 */
	public List<String> getRequestUris()
	{
		return requestUris;
	}
	
	private void addRequestUri(String requestUri)
	{
		requestUris.add(requestUri);
	}
	
	protected synchronized void loadDom(final InputStream inputStream,
			String confSystemId)
	{
		if (inputStream == null)
		{
			log.error("inputstream is null");
			return;
		}
		DocumentBuilder parser;

		/**
		 * the thing that resolves dtd's and other xml entities.
		 */
		ConfigHandler handler = new ConfigHandler(confSystemId);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		log.debug("XML builder factory is: " + factory.getClass().getName());
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		try
		{
			parser = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e)
		{
			log.error("Unable to setup XML parser for reading conf", e);
			return;
		}
		log.debug("XML Parser: " + parser.getClass().getName());

		parser.setErrorHandler(handler);
		parser.setEntityResolver(handler);

		try
		{
			log.debug("about to parse conf");
			Document doc = parser.parse(inputStream, confSystemId);
			processConfDoc(doc);

		} catch (SAXParseException e)
		{
			addError(
					"Parse error on line " + e.getLineNumber() + " "
							+ e.getMessage(), e);

		} catch (Exception e)
		{
			addError("Exception loading conf " + " " + e.getMessage(), e);
		}
	}

	protected void processConfDoc(Document doc)
	{
		Element rootElement = doc.getDocumentElement();

		NodeList rootElementList = rootElement.getChildNodes();
		for (int i = 0; i < rootElementList.getLength(); i++)
		{
			Node node = rootElementList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE
					&& ((Element) node).getTagName().equals("context"))
			{
				Element contextElement = (Element) node;
				
				processContext(contextElement);
				
			}else if (node.getNodeType() == Node.ELEMENT_NODE
					&& ((Element) node).getTagName().equals("cache"))
			{
				Element ruleElement = (Element) node;

				processRule(ruleElement);
			}

		}
	}
	
	/**
	 * @param ruleElement
	 * @param rule
	 */
	private void processRule(Element ruleElement)
	{
		String cacheType = getAttrValue(ruleElement, "type");
		
		if(UtilHelper.isEmpty(cacheType))
			cacheType = Rule.DEFAULT_CACHE_TYPE;
		
		if(!this.cacheType.equals(cacheType))
		{
			return;
		}
		
		processParserNodes(ruleElement, cacheType);
	}
	
	private void processParserNodes(Element ruleElement, String cacheType)
	{
		NodeList parserNodes = ruleElement.getElementsByTagName("parser");
		
		if(UtilHelper.isEmpty(parserNodes))
			return;
		
		Rule rule;
		
		for (int j = 0; j < parserNodes.getLength(); j++) 
		{
			Node node = parserNodes.item(j);
			
            if (node == null) continue;
            if (node.getNodeType() != Node.ELEMENT_NODE) continue;
            
            Element parserElement = (Element) node;
            rule = new Rule();
            
            Node requestUriNode = parserElement.getElementsByTagName("requestUri").item(0);
    		Node generatedPathNode = parserElement.getElementsByTagName("namespace").item(0);
    		Node regeneratedIntervalNode = parserElement.getElementsByTagName("regeneratedInterval").item(0);
    		
    		String requestUri = getNodeValue(requestUriNode);
    		
    		addRequestUri(requestUri);
    		
    		rule.setCacheType(cacheType);
    		rule.setRequestUri(requestUri);
    		rule.setNamespace(getNodeValue(generatedPathNode));
    		rule.setRegeneratedInterval(getNodeValue(regeneratedIntervalNode));
    		
    		if ("true".equalsIgnoreCase(getAttrValue(requestUriNode, "caseSensitive"))) 
    			rule.setRequestUriCaseSensitive(true);
    		
    		ruleMap.put(rule.getRequestUri(), rule);
		}
	}

	/**
	 * @param contextElement
	 */
	private void processContext(Element contextElement)
	{
		setDefaultNamespace(getAttrValue(contextElement, "defaultNamespace"));
		setDefaultRegeneratedInterval(getAttrValue(contextElement, "defaultRegeneratedInterval"));
	}
	
	private static String getNodeValue(Node node)
	{
		if (node == null)
			return null;
		NodeList nodeList = node.getChildNodes();
		if (nodeList == null)
			return null;
		Node child = nodeList.item(0);
		if (child == null)
			return null;
		if ((child.getNodeType() == Node.TEXT_NODE))
		{
			String value = ((Text) child).getData();
			return value.trim();
		}
		return null;
	}

	private static String getAttrValue(Node n, String attrName)
	{
		if (n == null)
			return null;
		NamedNodeMap attrs = n.getAttributes();
		if (attrs == null)
			return null;
		Node attr = attrs.getNamedItem(attrName);
		if (attr == null)
			return null;
		String val = attr.getNodeValue();
		if (val == null)
			return null;
		return val.trim();
	}

	private void addError(final String errorMsg, final Exception e)
	{
		errors.add(errorMsg);
		log.error(errorMsg, e);
	}
}
