/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-9-4
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.code.generator.config;

import static org.code.generator.util.StringUtility.stringHasValue;

import org.code.generator.dom.Attribute;
import org.code.generator.dom.XmlElement;

public class CommonPluginConfiguration extends PropertyHolder {

	private String	templatePath;

	private String	templateName;

	private String	fileSuffix;

	private String	fileExtension;

	private String	fileTargetProject;

	private String	targetPackage;

	private boolean	override;

	public CommonPluginConfiguration()
	{
		super();

		override = true;
	}

	public String getTemplatePath()
	{
		return templatePath;
	}

	public void setTemplatePath(String templatePath)
	{
		this.templatePath = templatePath;
	}

	public String getTemplateName()
	{
		return templateName;
	}

	public void setTemplateName(String templateName)
	{
		this.templateName = templateName;
	}

	public String getFileSuffix()
	{
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix)
	{
		this.fileSuffix = fileSuffix;
	}

	public String getFileExtension()
	{
		return fileExtension;
	}

	public void setFileExtension(String fileExtension)
	{
		this.fileExtension = fileExtension;
	}

	public String getFileTargetProject()
	{
		return fileTargetProject;
	}

	public void setFileTargetProject(String fileTargetProject)
	{
		this.fileTargetProject = fileTargetProject;
	}

	public String getTargetPackage()
	{
		return targetPackage;
	}

	public void setTargetPackage(String targetPackage)
	{
		this.targetPackage = targetPackage;
	}

	public boolean isOverride()
	{
		return override;
	}

	public void setOverride(boolean override)
	{
		this.override = override;
	}

	public XmlElement toXmlElement()
	{
		XmlElement xmlElement = new XmlElement("commonPlugin"); //$NON-NLS-1$

		addXmlElements(xmlElement);

		return xmlElement;
	}

	protected void addXmlElements(XmlElement xmlElement)
	{
		if (stringHasValue(templatePath))
		{
			xmlElement
					.addAttribute(new Attribute("templatePath", templatePath)); //$NON-NLS-1$
		}

		if (stringHasValue(templateName))
		{
			xmlElement
					.addAttribute(new Attribute("templateName", templateName)); //$NON-NLS-1$
		}

		if (stringHasValue(fileSuffix))
		{
			xmlElement.addAttribute(new Attribute("fileSuffix", fileSuffix)); //$NON-NLS-1$
		}

		if (stringHasValue(fileExtension))
		{
			xmlElement.addAttribute(new Attribute(
					"fileExtension", fileExtension)); //$NON-NLS-1$
		}

		if (stringHasValue(fileTargetProject))
		{
			xmlElement.addAttribute(new Attribute(
					"fileTargetProject", fileTargetProject)); //$NON-NLS-1$
		}

		if (stringHasValue(targetPackage))
		{
			xmlElement.addAttribute(new Attribute(
					"targetPackage", targetPackage)); //$NON-NLS-1$
		}

		if (!override)
		{
			xmlElement.addAttribute(new Attribute("override", "false")); //$NON-NLS-1$ //$NON-NLS-2$
		}

		addPropertyXmlElements(xmlElement);
	}
}
