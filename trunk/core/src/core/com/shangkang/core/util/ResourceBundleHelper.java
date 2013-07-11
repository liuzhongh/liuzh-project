/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Jan 6, 2011
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

import com.shangkang.tools.UtilHelper;

public class ResourceBundleHelper extends ResourceBundleMessageSource {

	public void setPackageNames(String[] packageNames)
	{
		this.setBaseNames(packageNames);
	}

	public void setPackageName(String packageName)
	{
		setPackageNames(new String[]{packageName});
	}

	private void setBaseNames(String[] packageNames)
	{
		List<String> fileNames = new ArrayList<String>();
		
		if(!UtilHelper.isEmpty(packageNames))
		{
			for(String basename : packageNames)
			{
				fileNames.addAll(setFileNames(basename));
			}
		}
		
		if(!UtilHelper.isEmpty(fileNames))
		{
			String[] basenames = new String[fileNames.size()];
			
			this.setBasenames(fileNames.toArray(basenames));
		}
	}
	
	private List<String> setFileNames(String packageName)
	{
		StringBuilder path = new StringBuilder();
		path.append(UtilHelper.isEmpty(packageName) ? "" : packageName);
		
		List<String> fileNames = new ArrayList<String>();
		File fileDirectory = new File(getBundleClassLoader().getResource(path.toString().replace(".", "/")).getPath());
		
		try
		{
			if(fileDirectory != null && fileDirectory.exists() && fileDirectory.isDirectory())
			{
				File[] files = fileDirectory.listFiles();
				
				Locale[] locales = Locale.getAvailableLocales();
				
				for(File file : files)
				{
					if(file.isFile())
					{
						StringBuilder filePath = new StringBuilder();
						
						if(!UtilHelper.isEmpty(packageName))
							filePath.append(packageName).append(".");
						
						String fileName = file.getName();
						
						if(!fileName.endsWith(".properties"))
							continue;
						
						boolean isContains = false;
						
						for(Locale locale : locales)
						{
							StringBuilder sb = new StringBuilder();
							sb.append('_').append(locale.getLanguage());
						
							if(fileName.contains(sb.toString()))
							{
								isContains = true;
								break;
							}
						}
						
						if(!isContains)
							fileNames.add(filePath.append(fileName.replace(".properties", "")).toString());
					}
				}
			}
			
			return fileNames;
		}finally
		{
			fileDirectory = null;
		}
	}
}
