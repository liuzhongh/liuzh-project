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

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.zip.DataFormatException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.constructs.blocking.LockTimeoutException;
import net.sf.ehcache.constructs.web.AlreadyCommittedException;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.GenericResponseWrapper;
import net.sf.ehcache.constructs.web.Header;
import net.sf.ehcache.constructs.web.PageInfo;
import net.sf.ehcache.constructs.web.ResponseHeadersNotModifiableException;
import net.sf.ehcache.constructs.web.ResponseUtil;
import net.sf.ehcache.constructs.web.SerializableCookie;
import net.sf.ehcache.constructs.web.filter.FilterNonReentrantException;

import com.core.cache.handler.CacheHandler;
import com.core.cache.handler.WebCacheNamespaceHandler;

public abstract class CachingFilter extends Filter {

	private static final Logger LOG = LoggerFactory.getLogger(CachingFilter.class);

    private final VisitLog visitLog = new VisitLog();
    
    private CacheHandler cacheHandler;

    /**
     * Initialises blockingCache to use. The BlockingCache created by this
     * method does not have a lock timeout set.
     * <p/>
     * A timeout can be appled using
     * <code>blockingCache.setTimeoutMillis(int timeout)</code> and takes effect
     * immediately for all new requests
     * 
     * @throws CacheException
     *             The most likely cause is that a cache has not been configured
     *             in ehcache's configuration file ehcache.xml for the filter
     *             name
     * @param filterConfig
     *            this filter's configuration.
     */
    public void doInit(FilterConfig filterConfig) throws CacheException {
        /*synchronized (this.getClass()) {
            if (blockingCache == null) {
                setCacheNameIfAnyConfigured(filterConfig);
                final String localCacheName = getCacheName();
                Ehcache cache = getCacheManager().getEhcache(localCacheName);
                if (cache == null) {
                    throw new CacheException("cache '" + localCacheName
                            + "' not found in configuration");
                }
                if (!(cache instanceof BlockingCache)) {
                    // decorate and substitute
                    BlockingCache newBlockingCache = new BlockingCache(cache);
                    getCacheManager().replaceCacheWithDecoratedCache(cache,
                            newBlockingCache);
                }
                blockingCache = (BlockingCache) getCacheManager().getEhcache(
                        localCacheName);
                Integer blockingTimeoutMillis = parseBlockingCacheTimeoutMillis(filterConfig);
                if (blockingTimeoutMillis != null && blockingTimeoutMillis > 0) {
                    blockingCache.setTimeoutMillis(blockingTimeoutMillis);
                }
            }
        }*/
    	
    	cacheHandler = getCacheHandler();
    	
    	WebCacheNamespaceHandler.getSingleton().populateCacheHandler(cacheHandler);
    }

    /**
     * Destroys the filter.
     */
    protected void doDestroy() {
        // noop
    	cacheHandler = null;
    }

    /**
     * Performs the filtering for a request. This method caches based responses
     * keyed by {@link #calculateKey(javax.servlet.http.HttpServletRequest)}
     * <p/>
     * By default this method will queue requests requesting the page response
     * for a given key until the first thread in the queue has completed. The
     * request which occurs when the page expires incurs the cost of waiting for
     * the downstream processing to return the respone.
     * <p/>
     * The maximum time to wait can be configured by setting
     * <code>setTimeoutMillis</code> on the underlying
     * <code>BlockingCache</code>.
     * 
     * @param request
     * @param response
     * @param chain
     * @throws AlreadyGzippedException
     *             if a double gzip is attempted
     * @throws AlreadyCommittedException
     *             if the response was committed on the way in or the on the way
     *             back
     * @throws FilterNonReentrantException
     *             if an attempt is made to reenter this filter in the same
     *             request.
     * @throws LockTimeoutException
     *             if this request is waiting on another that is populating the
     *             cache entry and timeouts while waiting. Only occurs if the
     *             BlockingCache has a timeout set.
     * @throws Exception
     *             for all other exceptions. They will be caught and logged in
     *             {@link Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)}
     */
    protected void doFilter(final HttpServletRequest request,
            final HttpServletResponse response, final FilterChain chain)
            throws AlreadyGzippedException, AlreadyCommittedException,
            FilterNonReentrantException, LockTimeoutException, Exception {
        if (response.isCommitted()) {
//            throw new AlreadyCommittedException(
//                    "Response already committed before doing buildPage.");
        }
        logRequestHeaders(request);
        PageInfo pageInfo = buildPageInfo(request, response, chain);

        if (pageInfo.isOk()) {
            if (response.isCommitted()) {
                throw new AlreadyCommittedException(
                        "Response already committed after doing buildPage"
                                + " but before writing response from PageInfo.");
            }
            writeResponse(request, response, pageInfo);
        }
    }

    /**
     * Build page info either using the cache or building the page directly.
     * <p/>
     * Some requests are for page fragments which should never be gzipped, or
     * for other pages which are not gzipped.
     */
    protected PageInfo buildPageInfo(final HttpServletRequest request,
            final HttpServletResponse response, final FilterChain chain)
            throws Exception {
        // Look up the cached page
        final String key = calculateKey(request);
        PageInfo pageInfo = null;
        try {
            checkNoReentry(request);
            String namespace = getCacheNamespace();
            
            pageInfo = cacheHandler.getWithNamespace(namespace, key);
            
            if (pageInfo == null) {
                try {
                    // Page is not cached - build the response, cache it, and
                    // send to client
                    pageInfo = buildPage(request, response, chain);
                    if (pageInfo.isOk()) {
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("PageInfo ok. Adding to cache with key "
                                    + key);
                        }
                        cacheHandler.setWithNamespace(namespace, key, pageInfo, getTimeToLiveSeconds());
                    } else {
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("PageInfo was not ok(200). Putting null into cache "
                                  //  + blockingCache.getName()
                                    + " with key "
                                    + key);
                        }
                    }
                } catch (final Throwable throwable) {
                    // Must unlock the cache if the above fails. Will be logged
                    // at Filter
                    
                    throw new Exception(throwable);
                }
            } else {
                
                if (LOG.isDebugEnabled())
                	LOG.debug("Get the info from cache with key :" + key);
            }
        } catch (LockTimeoutException e) {
            // do not release the lock, because you never acquired it
            throw e;
        } finally {
            // all done building page, reset the re-entrant flag
            visitLog.clear();
        }
        return pageInfo;
    }

    /**
     * Builds the PageInfo object by passing the request along the filter chain
     * 
     * @param request
     * @param response
     * @param chain
     * @return a Serializable value object for the page or page fragment
     * @throws AlreadyGzippedException
     *             if an attempt is made to double gzip the body
     * @throws Exception
     */
    protected PageInfo buildPage(final HttpServletRequest request,
            final HttpServletResponse response, final FilterChain chain)
            throws AlreadyGzippedException, Exception {

        // Invoke the next entity in the chain
        final ByteArrayOutputStream outstr = new ByteArrayOutputStream();
        final GenericResponseWrapper wrapper = new GenericResponseWrapper(
                response, outstr);
        chain.doFilter(request, wrapper);
        wrapper.flush();

        long timeToLiveSeconds = getTimeToLiveSeconds();

        // Return the page info
        return new PageInfo(wrapper.getStatus(), wrapper.getContentType(),
                wrapper.getCookies(), outstr.toByteArray(), true,
                timeToLiveSeconds, wrapper.getAllHeaders());
    }

    protected int getTimeToLiveSeconds()
    {
    	String regeneratedInterval = this.getRule().getRegeneratedInterval() == null ? this.getConfiguration().getDefaultRegeneratedInterval() : this.getRule().getRegeneratedInterval() ;
    
    	return Integer.parseInt(regeneratedInterval);
    }
    
    /**
     * Writes the response from a PageInfo object.
     * <p/>
     * Headers are set last so that there is an opportunity to override
     * 
     * @param request
     * @param response
     * @param pageInfo
     * @throws IOException
     * @throws DataFormatException
     * @throws ResponseHeadersNotModifiableException
     * 
     */
    protected void writeResponse(final HttpServletRequest request,
            final HttpServletResponse response, final PageInfo pageInfo)
            throws IOException, DataFormatException,
            ResponseHeadersNotModifiableException {
        boolean requestAcceptsGzipEncoding = acceptsGzipEncoding(request);

        setStatus(response, pageInfo);
        setContentType(response, pageInfo);
        setCookies(pageInfo, response);
        // do headers last so that users can override with their own header sets
        setHeaders(pageInfo, requestAcceptsGzipEncoding, response);
        writeContent(request, response, pageInfo);
    }

    /**
     * Set the content type.
     * 
     * @param response
     * @param pageInfo
     */
    protected void setContentType(final HttpServletResponse response,
            final PageInfo pageInfo) {
        String contentType = pageInfo.getContentType();
        if (contentType != null && contentType.length() > 0) {
            response.setContentType(contentType);
        }
    }

    /**
     * Set the serializableCookies
     * 
     * @param pageInfo
     * @param response
     */
    @SuppressWarnings("rawtypes")
	protected void setCookies(final PageInfo pageInfo,
            final HttpServletResponse response) {

        final Collection cookies = pageInfo.getSerializableCookies();
        for (Iterator iterator = cookies.iterator(); iterator.hasNext();) {
            final Cookie cookie = ((SerializableCookie) iterator.next())
                    .toCookie();
            response.addCookie(cookie);
        }
    }

    /**
     * Status code
     * 
     * @param response
     * @param pageInfo
     */
    protected void setStatus(final HttpServletResponse response,
            final PageInfo pageInfo) {
        response.setStatus(pageInfo.getStatusCode());
    }

    /**
     * Set the headers in the response object, excluding the Gzip header
     * 
     * @param pageInfo
     * @param requestAcceptsGzipEncoding
     * @param response
     */
    protected void setHeaders(final PageInfo pageInfo,
            boolean requestAcceptsGzipEncoding,
            final HttpServletResponse response) {

        final Collection<Header<? extends Serializable>> headers = pageInfo
                .getHeaders();

        // Track which headers have been set so all headers of the same name
        // after the first are added
        final TreeSet<String> setHeaders = new TreeSet<String>(
                String.CASE_INSENSITIVE_ORDER);

        for (final Header<? extends Serializable> header : headers) {
            final String name = header.getName();

            switch (header.getType()) {
            case STRING:
                if (setHeaders.contains(name)) {
                    response.addHeader(name, (String) header.getValue());
                } else {
                    setHeaders.add(name);
                    response.setHeader(name, (String) header.getValue());
                }
                break;
            case DATE:
                if (setHeaders.contains(name)) {
                    response.addDateHeader(name, (Long) header.getValue());
                } else {
                    setHeaders.add(name);
                    response.setDateHeader(name, (Long) header.getValue());
                }
                break;
            case INT:
                if (setHeaders.contains(name)) {
                    response.addIntHeader(name, (Integer) header.getValue());
                } else {
                    setHeaders.add(name);
                    response.setIntHeader(name, (Integer) header.getValue());
                }
                break;
            default:
                throw new IllegalArgumentException("No mapping for Header: "
                        + header);
            }
        }
    }

    /**
     * A meaningful name representative of the JSP page being cached.
     * <p/>
     * The <code>cacheName</code> field is be set by the <code>doInit</code>
     * method. Override to control the name used. The significance is that the
     * name is used to find a cache configuration in <code>ehcache.xml</code>
     * 
     * @return the name of the cache to use for this filter.
     */
    protected abstract CacheHandler getCacheHandler();

    /**
     * CachingFilter works off a key.
     * <p/>
     * The key should be unique. Factors to consider in generating a key are:
     * <ul>
     * <li>The various hostnames that a request could come through
     * <li>Whether additional parameters used for referral tracking e.g. google
     * should be excluded to maximise cache hits
     * <li>Additional parameters can be added to any page. The page will still
     * work but will miss the cache. Consider coding defensively around this
     * issue.
     * </ul>
     * <p/>
     * Implementers should differentiate between GET and HEAD requests otherwise
     * blank pages can result. See SimplePageCachingFilter for an example
     * implementation.
     * 
     * @param httpRequest
     * @return the key, generally the URL plus request parameters
     */
    protected abstract String calculateKey(final HttpServletRequest httpRequest);

    /**
     * Writes the response content. This will be gzipped or non gzipped
     * depending on whether the User Agent accepts GZIP encoding.
     * <p/>
     * If the body is written gzipped a gzip header is added.
     * 
     * @param response
     * @param pageInfo
     * @throws IOException
     */
    protected void writeContent(final HttpServletRequest request,
            final HttpServletResponse response, final PageInfo pageInfo)
            throws IOException, ResponseHeadersNotModifiableException {
        byte[] body;

        boolean shouldBodyBeZero = ResponseUtil.shouldBodyBeZero(request,
                pageInfo.getStatusCode());
        if (shouldBodyBeZero) {
            body = new byte[0];
        } else if (acceptsGzipEncoding(request)) {
            body = pageInfo.getGzippedBody();
            if (ResponseUtil.shouldGzippedBodyBeZero(body, request)) {
                body = new byte[0];
            } else {
                ResponseUtil.addGzipHeader(response);
            }

        } else {
            body = pageInfo.getUngzippedBody();
        }

        response.setContentLength(body.length);
        OutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(body);
        out.flush();
    }

    /**
     * Check that this caching filter is not being reentered by the same
     * recursively. Recursive calls will block indefinitely because the first
     * request has not yet unblocked the cache.
     * <p/>
     * This condition usually indicates an error in filter chaining or
     * RequestDispatcher dispatching.
     * 
     * @param httpRequest
     * @throws FilterNonReentrantException
     *             if reentry is detected
     */
    protected void checkNoReentry(final HttpServletRequest httpRequest)
            throws FilterNonReentrantException {
        String filterName = getClass().getName();
        if (visitLog.hasVisited()) {
            throw new FilterNonReentrantException(
                    "The request thread is attempting to reenter" + " filter "
                            + filterName + ". URL: "
                            + httpRequest.getRequestURL());
        } else {
            // mark this thread as already visited
            visitLog.markAsVisited();
            if (LOG.isDebugEnabled()) {
                LOG.debug("Thread {}  has been marked as visited.", Thread
                        .currentThread().getName());
            }
        }
    }

    /**
     * threadlocal class to check for reentry
     * 
     * @author hhuynh
     * 
     */
    private static class VisitLog extends ThreadLocal<Boolean> {
        @Override
        protected Boolean initialValue() {
            return false;
        }

        public boolean hasVisited() {
            return get();
        }

        public void markAsVisited() {
            set(true);
        }

        public void clear() {
            super.remove();
        }
    }

}
