package com.core.batch.thread;

import java.lang.reflect.Method;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.core.batch.support.ParameterProcess;

/**
 * Jan 13, 2010
 * Liuzh
 */
@Service("taskExecutor")
@Scope("prototype")
public class DefaultTaskExecutor implements TaskExecutor {

	/* (non-Javadoc)
	 * @see jade.framework.batch.thread.TaskExecutor#processBusinessTask(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes" })
	public Object processBatchBusinessTask(Object owner, String methodName, String taskParameter, ServletContext servletContext) throws Exception
	{		
		Class ownerClass = owner.getClass();
		
		Class[] argsClass = ParameterProcess.getMehtodParameterClass(owner, methodName);
		Object[] args = ParameterProcess.string2Object(taskParameter, argsClass);
		
		Method setMethod = doGetMethod("setServletContext", ownerClass, new Class[]{ServletContext.class}, false);
		
		if(null != setMethod)
			setMethod.invoke(owner, servletContext);
				
		Method method = doGetMethod(methodName, ownerClass, argsClass, true);
		return method.invoke(owner, args);
	}

	/**
	 * @param methodName
	 * @param ownerClass
	 * @param argsClass
	 * @return
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Method doGetMethod(String methodName, Class ownerClass,
			Class[] argsClass, boolean isThrowException) throws NoSuchMethodException
	{
		Method method = null;
		try
		{
			method = ownerClass.getMethod(methodName, argsClass);
		} catch (SecurityException e)
		{
			throw e;
		} catch (NoSuchMethodException e)
		{
			if(isThrowException)
				throw e;
			
			return null;
		}
		
		return method;
	}	
}
