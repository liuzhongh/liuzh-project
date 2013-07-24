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
package com.core.cms.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

import com.core.cms.IncludeRule;
import com.core.cms.Rule;
import com.shangkang.tools.UtilHelper;

public class Configuration {

	private static Log			log		= LogFactory.getLog(Configuration.class);

	private final List<String>	errors	= new ArrayList<String>();
	
	private final List<Rule> rules = new ArrayList<Rule>(50);
	
	private List<String> requestUris = new ArrayList<String>();
	
	private String site;
	
	private String contextPath;
	private String defaultGeneratedPath;
	private String defaultRegeneratedInterval;
	private String baseGeneratedPath;

	public Configuration(final InputStream inputStream, String fileName)
	{
		loadDom(inputStream, fileName);
	}

	/**
	 * @return the site
	 */
	public String getSite()
	{
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(String site)
	{
		this.site = site;
	}

	/**
	 * @return the baseGeneratedPath
	 */
	public String getBaseGeneratedPath()
	{
		return baseGeneratedPath;
	}

	/**
	 * @param baseGeneratedPath the baseGeneratedPath to set
	 */
	public void setBaseGeneratedPath(String baseGeneratedPath)
	{
		this.baseGeneratedPath = baseGeneratedPath;
	}

	/**
	 * @return the contextPath
	 */
	public String getContextPath()
	{
		return contextPath;
	}

	/**
	 * @param contextPath the contextPath to set
	 */
	public void setContextPath(String contextPath)
	{
		this.contextPath = contextPath;
	}

	/**
	 * @return the defaultGeneratedPath
	 */
	public String getDefaultGeneratedPath()
	{
		return defaultGeneratedPath;
	}

	/**
	 * @param defaultGeneratedPath the defaultGeneratedPath to set
	 */
	public void setDefaultGeneratedPath(String defaultGeneratedPath)
	{
		this.defaultGeneratedPath = defaultGeneratedPath;
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

	/**
	 * @return the rules
	 */
	public List<Rule> getRules()
	{
		return rules;
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
					&& ((Element) node).getTagName().equals("parser"))
			{
				Element ruleElement = (Element) node;
				Rule rule = new Rule();

				processRule(ruleElement, rule);
				processIncludeNodes(ruleElement, rule);

				addRule(rule);
			}

		}
	}

	/**
	 * @param ruleElement
	 * @param rule
	 */
	private void processRule(Element ruleElement, Rule rule)
	{
		Node forwardNode = ruleElement.getElementsByTagName("forward").item(0);
		Node requestUriNode = ruleElement.getElementsByTagName("requestUri").item(0);
		Node generatedPathNode = ruleElement.getElementsByTagName("generatedPath").item(0);
		Node regeneratedIntervalNode = ruleElement.getElementsByTagName("regeneratedInterval").item(0);
		
		String requestUri = getNodeValue(requestUriNode);
		
		addRequestUri(requestUri);
		
		rule.setRequestUri(requestUri);
		rule.setForward(getNodeValue(forwardNode));
		rule.setGeneratedPath(getNodeValue(generatedPathNode));
		rule.setRegeneratedInterval(getNodeValue(regeneratedIntervalNode));
		
		if ("true".equalsIgnoreCase(getAttrValue(requestUriNode, "caseSensitive"))) 
			rule.setRequestUriCaseSensitive(true);
		
		String dispatcher = getAttrValue(requestUriNode, "dispatcher");
		
		if(!UtilHelper.isEmpty(dispatcher))
			rule.setDispatcher(dispatcher);
	}
	
	private void processIncludeNodes(Element ruleElement, Rule rule)
	{
		NodeList includeNodes = ruleElement.getElementsByTagName("include");
		
		if(UtilHelper.isEmpty(includeNodes))
			return;
		
		for (int j = 0; j < includeNodes.getLength(); j++) 
		{
			Node node = includeNodes.item(j);
			
            if (node == null) continue;
            if (node.getNodeType() != Node.ELEMENT_NODE) continue;
            
            Element includeElement = (Element) node;
            
            IncludeRule includeRule = new IncludeRule();
            
            Node fromNode = includeElement.getElementsByTagName("from").item(0);
            Node toNode = includeElement.getElementsByTagName("to").item(0);
            
            includeRule.setFrom(getNodeValue(fromNode));
            includeRule.setTo(getNodeValue(toNode));
            
            if ("true".equalsIgnoreCase(getAttrValue(fromNode, "caseSensitive"))) 
            	includeRule.setFromCaseSensitive(true);
            
            rule.addIncludeRule(includeRule);
		}
	}

	/**
	 * @param contextElement
	 */
	private void processContext(Element contextElement)
	{
		setSite(getAttrValue(contextElement, "site"));
		setContextPath(getAttrValue(contextElement, "contextPath"));
		setBaseGeneratedPath(getAttrValue(contextElement, "baseGeneratedPath"));
		setDefaultGeneratedPath(getAttrValue(contextElement, "defaultGeneratedPath"));
		setDefaultRegeneratedInterval(getAttrValue(contextElement, "defaultRegeneratedInterval"));
	}
	
	private void addRule(final Rule rule) {
        rules.add(rule);
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
