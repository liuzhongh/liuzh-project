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
package org.code.generator.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerHanlder {

	private SimpleHash	context;
	private Configuration freeMarkerEngine;
	private String templatePath;
	private String templateName;

	public SimpleHash getContext()
	{
		return context;
	}
	
	public FreeMarkerHanlder(String templatePath, String templateName)
	{
		this.templatePath = templatePath;
		this.templateName = templateName;
	}
	
	public void initTemplate()
	{
		File file = new File(templatePath);
		
		try
		{
			if (!file.exists())
				throw new Exception("模板路径" + templatePath + "不存在。");
		} catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		freeMarkerEngine = new Configuration();
		
		try
		{
			freeMarkerEngine.setTemplateLoader(new FileTemplateLoader(file));
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}
	public void initContext()
	{
		context = new SimpleHash(ObjectWrapper.BEANS_WRAPPER);
	}

	public String generateContext()
	{
		StringWriter tempWriter = new StringWriter();
		BufferedWriter bw = new BufferedWriter(tempWriter);

		try
		{
			Template template = freeMarkerEngine.getTemplate(templateName);
			template.process(getContext(), bw);
			
		} catch (TemplateException e1)
		{
			e1.printStackTrace();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		try
		{
			bw.flush();
		} catch (IOException e)
		{
			throw new RuntimeException("Error while flushing to string", e);
		}

		return tempWriter.toString();
	}
}
