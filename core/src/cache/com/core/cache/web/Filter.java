/**
 * COPYRIGHT (C) 2013 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013年11月20日
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.cache.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.core.cache.Rule;
import com.core.cache.config.ConfigLoad;
import com.core.cache.config.Configuration;
import com.shangkang.tools.UtilHelper;

public abstract class Filter implements javax.servlet.Filter {

	/**
     * If a request attribute NO_FILTER is set, then filtering will be skipped
     */
    public static final String NO_FILTER = "NO_FILTER";

    private static final Logger LOG = LoggerFactory.getLogger(Filter.class);

    /**
     * The filter configuration.
     */
    protected FilterConfig filterConfig;

    /**
     * The exceptions to log differently, as a comma separated list
     */
    protected String exceptionsToLogDifferently;

    /**
     * Most {@link Throwable}s in Web applications propagate to the user. Usually they are logged where they first
     * happened. Printing the stack trace once a {@link Throwable} as propagated to the servlet is sometimes
     * just clutters the log.
     * <p/>
     * This field corresponds to an init-param of the same name. If set to true stack traces will be suppressed.
     */
    protected boolean suppressStackTraces;
    
    public static final String	DEFAULT_CONFIG_FILE_PATH	= "/WEB-INF/web-cache.xml";

	private ServletContext	context;
	
	private String configFilePath;
	
	private ConfigLoad load;
	
	private Configuration configuration;
	
	private Rule rule;
	
    /**
     * Performs the filtering.  This method calls template method
     * {@link #doFilter(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,javax.servlet.FilterChain) } which does the filtering.
     * This method takes care of error reporting and handling.
     * Errors are reported at warn level because http tends to produce lots of errors.
     *
     * @throws IOException if an IOException occurs during this method it will be rethrown and will not be wrapped
     */
    public final void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws ServletException, IOException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        try {
            //NO_FILTER set for RequestDispatcher forwards to avoid double gzipping
            if (filterNotDisabled(httpRequest)) {
                doFilter(httpRequest, httpResponse, chain);
            } else {
                chain.doFilter(request, response);
            }
        } catch (final Throwable throwable) {
            logThrowable(throwable, httpRequest);
        }
    }

    /**
     * Filters can be disabled programmatically by adding a {@link #NO_FILTER} parameter to the request.
     * This parameter is normally added to make RequestDispatcher include and forwards work.
     *
     * @param httpRequest the request
     * @return true if NO_FILTER is not set.
     */
    protected boolean filterNotDisabled(final HttpServletRequest httpRequest) {
    	
		LOG.debug("当前请求转向uri为:" + httpRequest.getAttribute("javax.servlet.forward.servlet_path"));
	
        LOG.debug(httpRequest.getRequestURI() + " url = " + httpRequest.getRequestURL() + " servlet : " + httpRequest.getServletPath());
        LOG.debug("包含页面 uri为:" + httpRequest.getAttribute("javax.servlet.include.servlet_path"));
        LOG.debug("getRequestURI" + httpRequest.getRequestURI());
        
        String noFilter = httpRequest.getParameter(NO_FILTER);//如果页面请求中加入了参数NO_FILTER,则不进行缓存
        
        LOG.debug("是否不缓存:" + noFilter);
        
        rule = load.validateRequestUri(httpRequest.getRequestURI().replace(httpRequest.getContextPath(), ""));
        
        return (rule != null && noFilter == null);
    }

    /**
     * This method should throw IOExceptions, not wrap them.
     */
    private void logThrowable(final Throwable throwable, final HttpServletRequest httpRequest)
            throws ServletException, IOException {
        StringBuffer messageBuffer = new StringBuffer("Throwable thrown during doFilter on request with URI: ")
                .append(httpRequest.getRequestURI())
                .append(" and Query: ")
                .append(httpRequest.getQueryString());
        String message = messageBuffer.toString();
        boolean matchFound = matches(throwable);
        if (matchFound) {
            try {
                if (suppressStackTraces) {
                    LOG.error(throwable.getMessage());
                } else {
                    LOG.error(throwable.getMessage(), throwable);
                }
            } catch (Exception e) {
                LOG.error("Could not invoke Log method", e);
            }
            if (throwable instanceof IOException) {
                throw (IOException) throwable;
            } else {
                throw new ServletException(message, throwable);
            }
        } else {

            if (suppressStackTraces) {
                LOG.warn(messageBuffer.append(throwable.getMessage()).append("\nTop StackTraceElement: ")
                        .append(throwable.getStackTrace()[0].toString()).toString());
            } else {
                LOG.warn(messageBuffer.append(throwable.getMessage()).toString(), throwable);
            }
            if (throwable instanceof IOException) {
                throw (IOException) throwable;
            } else {
                throw new ServletException(throwable);
            }
        }
    }

    /**
     * Checks whether a throwable, its root cause if it is a {@link ServletException}, or its cause, if it is a
     * Chained Exception matches an entry in the exceptionsToLogDifferently list
     *
     * @param throwable
     * @return true if the class name of any of the throwables is found in the exceptions to log differently
     */
    private boolean matches(Throwable throwable) {
        if (exceptionsToLogDifferently == null) {
            return false;
        }
        if (exceptionsToLogDifferently.indexOf(throwable.getClass().getName()) != -1) {
            return true;
        }
        if (throwable instanceof ServletException) {
            Throwable rootCause = (((ServletException) throwable).getRootCause());
            if (exceptionsToLogDifferently.indexOf(rootCause.getClass().getName()) != -1) {
                return true;
            }
        }
        if (throwable.getCause() != null) {
            Throwable cause = throwable.getCause();
            if (exceptionsToLogDifferently.indexOf(cause.getClass().getName()) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Initialises the filter.
     * <p/>
     * Calls template method {@link #doInit(javax.servlet.FilterConfig)} to perform any filter specific initialisation.
     */
    public final void init(final FilterConfig filterConfig) throws ServletException {
        try {

            this.filterConfig = filterConfig;
            processInitParams(filterConfig);
            initConfig(filterConfig);

            // Attempt to initialise this filter
            doInit(filterConfig);
        } catch (final Exception e) {
            LOG.error("Could not initialise servlet filter.", e);
            throw new ServletException("Could not initialise servlet filter.", e);
        }
    }
    
    private void initConfig(FilterConfig filterConfig)
    {
    	load = new ConfigLoad();
		
    	LOG.debug("filter init called");
		if (filterConfig == null)
		{
			LOG.error("unable to init filter as filter config is null");
			return;
		}

		LOG.debug("init: calling destroy just in case we are being re-inited uncleanly");

		context = filterConfig.getServletContext();
		if (context == null)
		{
			LOG.error("unable to init as servlet context is null");
			return;
		}
		String basePath = context.getRealPath("");

		configFilePath = filterConfig.getInitParameter("config");
		
		if(UtilHelper.isEmpty(configFilePath))
			configFilePath = DEFAULT_CONFIG_FILE_PATH;
		
		InputStream inputStream = context.getResourceAsStream(configFilePath);

		load.init(inputStream, basePath, initCacheType());
		configuration = load.getConfiguration();
    }
    
    protected abstract String initCacheType();
    
    protected Configuration getConfiguration()
    {
    	return this.configuration;
    }
    
    protected Rule getRule()
    {
    	return rule;
    }

    protected String getCacheNamespace()
    {
    	return rule.getNamespace() == null ? configuration.getDefaultNamespace() : rule.getNamespace();
    }
    /**
     * Processes initialisation parameters. These are configured in web.xml in accordance with the
     * Servlet specification using the following syntax:
     * <pre>
     * <filter>
     *      ...
     *      <init-param>
     *          <param-name>blah</param-name>
     *          <param-value>blahvalue</param-value>
     *      </init-param>
     *      ...
     * </filter>
     * </pre>
     * @throws ServletException
     */
    protected void processInitParams(final FilterConfig config) throws ServletException {
        String exceptions = config.getInitParameter("exceptionsToLogDifferently");
        String level = config.getInitParameter("exceptionsToLogDifferentlyLevel");
        String suppressStackTracesString = config.getInitParameter("suppressStackTraces");
        suppressStackTraces = Boolean.valueOf(suppressStackTracesString).booleanValue();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Suppression of stack traces enabled for " + this.getClass().getName());
        }

        if (exceptions != null) {
            validateMandatoryParameters(exceptions, level);
            exceptionsToLogDifferently = exceptions;
            if (LOG.isDebugEnabled()) {
                LOG.debug("Different logging levels configured for " + this.getClass().getName());
            }
        }
    }

    private void validateMandatoryParameters(String exceptions, String level) throws ServletException {
        if ((exceptions != null && level == null) || (level != null && exceptions == null)) {
            throw new ServletException("Invalid init-params. Both exceptionsToLogDifferently"
                    + " and exceptionsToLogDifferentlyLevelvalue should be specified if one is"
                    + " specified.");
        }
    }

    /**
     * Destroys the filter. Calls template method {@link #doDestroy()}  to perform any filter specific
     * destruction tasks.
     */
    public final void destroy() {
        this.filterConfig = null;
        this.configuration = null;
        this.load = null;
		
        this.configFilePath = null;
        this.context = null;
		
        doDestroy();
    }

    /**
     * Checks if request accepts the named encoding.
     */
    protected boolean acceptsEncoding(final HttpServletRequest request, final String name) {
        final boolean accepts = headerContains(request, "Accept-Encoding", name);
        return accepts;
    }

    /**
     * Checks if request contains the header value.
     */
    @SuppressWarnings("rawtypes")
	private boolean headerContains(final HttpServletRequest request, final String header, final String value) {

        logRequestHeaders(request);

        final Enumeration accepted = request.getHeaders(header);
        while (accepted.hasMoreElements()) {
            final String headerValue = (String) accepted.nextElement();
            if (headerValue.indexOf(value) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Logs the request headers, if debug is enabled.
     *
     * @param request
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected void logRequestHeaders(final HttpServletRequest request) {
        if (LOG.isDebugEnabled()) {
            Map headers = new HashMap();
            Enumeration enumeration = request.getHeaderNames();
            StringBuffer logLine = new StringBuffer();
            logLine.append("Request Headers");
            while (enumeration.hasMoreElements()) {
                String name = (String) enumeration.nextElement();
                String headerValue = request.getHeader(name);
                headers.put(name, headerValue);
                logLine.append(": ").append(name).append(" -> ").append(headerValue);
            }
            LOG.debug(logLine.toString());
        }
    }


    /**
     * A template method that performs any Filter specific destruction tasks.
     * Called from {@link #destroy()}
     */
    protected abstract void doDestroy();


    /**
     * A template method that performs the filtering for a request.
     * Called from {@link #doFilter(ServletRequest,ServletResponse,FilterChain)}.
     */
    protected abstract void doFilter(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse,
                                     final FilterChain chain) throws Throwable;

    /**
     * A template method that performs any Filter specific initialisation tasks.
     * Called from {@link #init(FilterConfig)}.
     * @param filterConfig
     */
    protected abstract void doInit(FilterConfig filterConfig) throws Exception;
    
    /**
     * Returns the filter config.
     */
    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    /**
     * Determine whether the user agent accepts GZIP encoding. This feature is part of HTTP1.1.
     * If a browser accepts GZIP encoding it will advertise this by including in its HTTP header:
     * <p/>
     * <code>
     * Accept-Encoding: gzip
     * </code>
     * <p/>
     * Requests which do not accept GZIP encoding fall into the following categories:
     * <ul>
     * <li>Old browsers, notably IE 5 on Macintosh.
     * <li>Search robots such as yahoo. While there are quite a few bots, they only hit individual
     * pages once or twice a day. Note that Googlebot as of August 2004 now accepts GZIP.
     * <li>Internet Explorer through a proxy. By default HTTP1.1 is enabled but disabled when going
     * through a proxy. 90% of non gzip requests are caused by this.
     * <li>Site monitoring tools
     * </ul>
     * As of September 2004, about 34% of requests coming from the Internet did not accept GZIP encoding.
     *
     * @param request
     * @return true, if the User Agent request accepts GZIP encoding
     */
    protected boolean acceptsGzipEncoding(HttpServletRequest request) {
        return acceptsEncoding(request, "gzip");
    }

}
