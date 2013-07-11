package com.shangkang.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHelper {

	public static String getProperty(String fileName, String key)
	{
		Properties props = new Properties();
		try
		{
			props.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(fileName));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return props.getProperty(key);
	}
	
	public static Properties getProperty(String fileName)
	{
		Properties props = new Properties();
		try
		{
			props.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(fileName));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return props;
	}

	@SuppressWarnings("rawtypes")
	public static InputStream getInputStream(String fileName, Class clazz)
	{
		InputStream is = null;
		
		if (clazz != null)
		{
			is = clazz.getResourceAsStream(fileName);
		} else
		{
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		}
		
		return is;
	}
	
	public static InputStream getInputStream(String fileName)
	{		
		return getInputStream(fileName, null);
	}
	
	@SuppressWarnings("rawtypes")
	public static String getProperty(String fileName, String key, Class clazz)
	{
		Properties props = new Properties();
		InputStream is = null;
		
		try
		{
			is = getInputStream(fileName, clazz);
			
			props.load(is);
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			is = null;
		}
		
		return props.getProperty(key);
		
	}

}
