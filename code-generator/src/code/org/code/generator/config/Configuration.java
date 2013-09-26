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

import static org.code.generator.util.StringUtility.stringHasValue;

import java.util.ArrayList;
import java.util.List;

import org.code.generator.dom.Attribute;
import org.code.generator.dom.Document;
import org.code.generator.dom.XmlElement;

public class Configuration {

	private Context			context;
	private List<String>	classPathEntries;

	public Configuration()
	{
		super();
		classPathEntries = new ArrayList<String>();
	}

	public void addClasspathEntry(String entry)
	{
		classPathEntries.add(entry);
	}

	/**
	 * @return Returns the classPathEntries.
	 */
	public List<String> getClassPathEntries()
	{
		return classPathEntries;
	}

	/**
	 * This method does a simple validate, it makes sure that all required
	 * fields have been filled in and that all implementation classes exist and
	 * are of the proper type. It does not do any more complex operations such
	 * as: validating that database tables exist or validating that named
	 * columns exist
	 */
	public void validate() throws Exception
	{
		List<String> errors = new ArrayList<String>();

		for (String classPathEntry : classPathEntries)
		{
			if (!stringHasValue(classPathEntry))
			{
				errors.add("ValidationError.19"); //$NON-NLS-1$
				// only need to state this error once
				break;
			}
		}

		if (context == null)
			errors.add("Context is null."); //$NON-NLS-1$
		
		context.validate(errors);

		if (errors.size() > 0)
		{
			throw new Exception(errors.toString());
		}
	}

	public Context getContext()
	{
		return context;
	}

	public void setContext(Context context)
	{
		this.context = context;
	}

	/**
	 * Builds an XML representation of this configuration. This can be used to
	 * persist a programtically generated configuration.
	 * 
	 * @return the XML representation of this configuration
	 */
	public Document toDocument()
	{
		// note that this method will not reconstruct a properties
		// element - that element is only used in XML parsing

		Document document = new Document(null, null);
		XmlElement rootElement = new XmlElement("configuration"); //$NON-NLS-1$
		document.setRootElement(rootElement);

		for (String classPathEntry : classPathEntries)
		{
			XmlElement cpeElement = new XmlElement("classPathEntry"); //$NON-NLS-1$
			cpeElement.addAttribute(new Attribute("location", classPathEntry)); //$NON-NLS-1$
			rootElement.addElement(cpeElement);
		}

		if (context != null)
		{
			rootElement.addElement(context.toXmlElement());
		}

		return document;
	}
}
