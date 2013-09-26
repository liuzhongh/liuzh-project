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

import static org.code.generator.util.StringUtility.isTrue;
import static org.code.generator.util.StringUtility.stringHasValue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.code.generator.config.CommonPluginConfiguration;
import org.code.generator.config.Configuration;
import org.code.generator.config.Context;
import org.code.generator.config.JDBCConnectionConfiguration;
import org.code.generator.config.JavaTypeResolverConfiguration;
import org.code.generator.config.PluginConfiguration;
import org.code.generator.config.PropertyHolder;
import org.code.generator.config.TableConfiguration;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GeneratorConfigurationParser {

	private Properties	properties;

	public GeneratorConfigurationParser(Properties properties)
	{
		super();
		if (properties == null)
		{
			this.properties = System.getProperties();
		} else
		{
			this.properties = properties;
		}
	}

	public Configuration parseConfiguration(Element rootNode) throws Exception
	{
		Configuration configuration = new Configuration();

		NodeList nodeList = rootNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node childNode = nodeList.item(i);

			if (childNode.getNodeType() != Node.ELEMENT_NODE)
			{
				continue;
			}

			if ("properties".equals(childNode.getNodeName())) { //$NON-NLS-1$
				parseProperties(configuration, childNode);
			} else if ("classPathEntry".equals(childNode.getNodeName())) { //$NON-NLS-1$
				parseClassPathEntry(configuration, childNode);
			} else if ("context".equals(childNode.getNodeName())) { //$NON-NLS-1$
				parseContext(configuration, childNode);
			}
		}

		return configuration;
	}

	private void parseContext(Configuration configuration, Node node)
	{

		Properties attributes = parseAttributes(node);
		String targetRuntime = attributes.getProperty("targetRuntime"); //$NON-NLS-1$
		String id = attributes.getProperty("id"); //$NON-NLS-1$

		Context context = new Context();
		context.setId(id);

		if (stringHasValue(targetRuntime))
		{
			context.setTargetRuntime(targetRuntime);
		}

		configuration.setContext(context);

		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node childNode = nodeList.item(i);

			if (childNode.getNodeType() != Node.ELEMENT_NODE)
			{
				continue;
			}

			if ("property".equals(childNode.getNodeName())) { //$NON-NLS-1$
				parseProperty(context, childNode);
			} else if ("jdbcConnection".equals(childNode.getNodeName())) { //$NON-NLS-1$
				parseJdbcConnection(context, childNode);
			} else if ("table".equals(childNode.getNodeName())) { //$NON-NLS-1$
				parseTable(context, childNode);
			} else if ("commonPlugin".equals(childNode.getNodeName())) { //$NON-NLS-1$
				parseCommonPlugin(context, childNode);
			}else if ("plugin".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parsePlugin(context, childNode);
            }else if("javaTypeResolver".equals(childNode.getNodeName()))
            {
            	parseJavaTypeResolver(context, childNode);
            }
		}
	}
	
	private void parseCommonPlugin(Context context, Node node)
	{
		CommonPluginConfiguration configuration = new CommonPluginConfiguration();
		
		context.addCommonPluginConfiguration(configuration);
		
		doParsePlugin(configuration, node);
		
		NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);

            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if ("property".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseProperty(configuration, childNode);
            }
        }
	}

	/**
	 * @param context
	 * @param node
	 */
	private void doParsePlugin(CommonPluginConfiguration configuration, Node node)
	{
        Properties attributes = parseAttributes(node);
        
        String templatePath = attributes.getProperty("templatePath"); //$NON-NLS-1$
        String templateName = attributes.getProperty("templateName");
        String fileSuffix = attributes.getProperty("fileSuffix");
        String fileExtension = attributes.getProperty("fileExtension");
        String fileTargetProject = attributes.getProperty("fileTargetProject");
        String targetPackage = attributes.getProperty("targetPackage");
        String override = attributes.getProperty("override");

        configuration.setTemplatePath(templatePath);
        configuration.setTemplateName(templateName);
        configuration.setFileSuffix(fileSuffix);
        configuration.setFileExtension(fileExtension);
        configuration.setFileTargetProject(fileTargetProject);
        configuration.setTargetPackage(targetPackage);
        
        if (stringHasValue(override)) {
        	configuration.setOverride(
                    isTrue(override));
        }
	}

	private void parsePlugin(Context context, Node node) {
		PluginConfiguration pluginConfiguration = new PluginConfiguration();
		
		context.addPluginConfiguration(pluginConfiguration);
        doParsePlugin(pluginConfiguration, node);

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);

            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if ("property".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseProperty(pluginConfiguration, childNode);
            }else if("javaTypeResolver".equals(childNode.getNodeName()))
            {
            	parseJavaTypeResolver4Plugin(pluginConfiguration, childNode);
            }
        }
    }
	
	private void parseJavaTypeResolver(Context context, Node node) {
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();

        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);

        doParseJavaTypeResolver(node, javaTypeResolverConfiguration);
    }

	private void doParseJavaTypeResolver(Node node,
			JavaTypeResolverConfiguration javaTypeResolverConfiguration)
	{
		Properties attributes = parseAttributes(node);
        
        String type = attributes.getProperty("type"); //$NON-NLS-1$
        String typeResolverImpl = attributes.getProperty("typeResolverImpl");

        javaTypeResolverConfiguration.setConfigurationType(type);
        javaTypeResolverConfiguration.setTypeResolverImpl(typeResolverImpl);

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);

            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if ("property".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseProperty(javaTypeResolverConfiguration, childNode);
            }
        }
	}
	
	private void parseJavaTypeResolver4Plugin(PluginConfiguration context, Node node) {
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();

        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);

        doParseJavaTypeResolver(node, javaTypeResolverConfiguration);
    }
	
	private void parseTable(Context context, Node node)
	{
		TableConfiguration tc = new TableConfiguration(context);
		context.setTableConfiguration(tc);

		Properties attributes = parseAttributes(node);
		
		String catalog = attributes.getProperty("catalog"); //$NON-NLS-1$
        String schema = attributes.getProperty("schema"); //$NON-NLS-1$
        String tablesName = attributes.getProperty("tablesName");
        String tableType = attributes.getProperty("tableType");
        String allTable = attributes.getProperty("allTable");
        String tablePrefix = attributes.getProperty("tablePrefix");
        
        if (stringHasValue(catalog)) {
            tc.setCatalog(catalog);
        }

        if (stringHasValue(schema)) {
            tc.setSchema(schema);
        }

        if (stringHasValue(tablesName)) {
            tc.setTablesName(tablesName);
        }
        
        if (stringHasValue(tableType)) {
            tc.setTableType(tableType);
        }
        
        if (stringHasValue(tablePrefix)) {
            tc.setTablePrefix(tablePrefix);
        }
        
        if (stringHasValue(allTable)) {
            tc.setAllTable(
                    isTrue(allTable));
        }
        
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);

            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if ("property".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseProperty(tc, childNode);
            } 
        }
	}

	private void parseJdbcConnection(Context context, Node node)
	{
		JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();

		context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

		Properties attributes = parseAttributes(node);
		String driverClass = attributes.getProperty("driverClass"); //$NON-NLS-1$
		String connectionURL = attributes.getProperty("connectionURL"); //$NON-NLS-1$
		String userId = attributes.getProperty("userId"); //$NON-NLS-1$
		String password = attributes.getProperty("password"); //$NON-NLS-1$
		String remarksReporting = attributes.getProperty("remarksReporting");

		jdbcConnectionConfiguration.setDriverClass(driverClass);
		jdbcConnectionConfiguration.setConnectionURL(connectionURL);

		if (stringHasValue(userId))
		{
			jdbcConnectionConfiguration.setUserId(userId);
		}

		if (stringHasValue(password))
		{
			jdbcConnectionConfiguration.setPassword(password);
		}

		if (stringHasValue(remarksReporting))
		{
			jdbcConnectionConfiguration.setRemarksReporting(remarksReporting);
		}

		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node childNode = nodeList.item(i);

			if (childNode.getNodeType() != Node.ELEMENT_NODE)
			{
				continue;
			}

			if ("property".equals(childNode.getNodeName())) { //$NON-NLS-1$
				parseProperty(jdbcConnectionConfiguration, childNode);
			}
		}
	}

	private void parseProperties(Configuration configuration, Node node)
			throws Exception
	{
		Properties attributes = parseAttributes(node);
		String resource = attributes.getProperty("resource"); //$NON-NLS-1$
		String url = attributes.getProperty("url"); //$NON-NLS-1$

		if (!stringHasValue(resource) && !stringHasValue(url))
		{
			throw new Exception("RuntimeError.14"); //$NON-NLS-1$
		}

		if (stringHasValue(resource) && stringHasValue(url))
		{
			throw new Exception("RuntimeError.14"); //$NON-NLS-1$
		}

		URL resourceUrl;

		try
		{
			if (stringHasValue(resource))
			{
				resourceUrl = Thread.currentThread().getContextClassLoader()
						.getResource(resource);
				if (resourceUrl == null)
				{
					throw new Exception(resource); //$NON-NLS-1$
				}
			} else
			{
				resourceUrl = new URL(url);
			}

			InputStream inputStream = resourceUrl.openConnection()
					.getInputStream();

			properties.load(inputStream);
			inputStream.close();
		} catch (IOException e)
		{
			if (stringHasValue(resource))
			{
				throw new Exception(resource); //$NON-NLS-1$
			} else
			{
				throw new Exception(url); //$NON-NLS-1$
			}
		}
	}

	private void parseClassPathEntry(Configuration configuration, Node node)
	{
		Properties attributes = parseAttributes(node);

		configuration.addClasspathEntry(attributes.getProperty("location")); //$NON-NLS-1$
	}

	private Properties parseAttributes(Node node)
	{
		Properties attributes = new Properties();
		NamedNodeMap nnm = node.getAttributes();
		for (int i = 0; i < nnm.getLength(); i++)
		{
			Node attribute = nnm.item(i);
			String value = parsePropertyTokens(attribute.getNodeValue());
			attributes.put(attribute.getNodeName(), value);
		}

		return attributes;
	}

	private String parsePropertyTokens(String string)
	{
		final String OPEN = "${"; //$NON-NLS-1$
		final String CLOSE = "}"; //$NON-NLS-1$

		String newString = string;
		if (newString != null)
		{
			int start = newString.indexOf(OPEN);
			int end = newString.indexOf(CLOSE);

			while (start > -1 && end > start)
			{
				String prepend = newString.substring(0, start);
				String append = newString.substring(end + CLOSE.length());
				String propName = newString.substring(start + OPEN.length(),
						end);
				String propValue = properties.getProperty(propName);
				if (propValue != null)
				{
					newString = prepend + propValue + append;
				}

				start = newString.indexOf(OPEN, end);
				end = newString.indexOf(CLOSE, end);
			}
		}

		return newString;
	}

	private void parseProperty(PropertyHolder propertyHolder, Node node)
	{
		Properties attributes = parseAttributes(node);

		String name = attributes.getProperty("name"); //$NON-NLS-1$
		String value = attributes.getProperty("value"); //$NON-NLS-1$

		propertyHolder.addProperty(name, value);
	}
}
