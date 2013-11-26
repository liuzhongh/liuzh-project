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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.cache.BaseRuleValidated;
import com.core.cache.Rule;

import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.GenericResponseWrapper;
import net.sf.ehcache.constructs.web.PageInfo;

public abstract class PageFragmentCachingFilter extends CachingFilter {

	/**
     * Performs the filtering for a request.
     */
    protected void doFilter(final HttpServletRequest request, final HttpServletResponse response,
                            final FilterChain chain) throws Exception {

        PageInfo pageInfo = buildPageInfo(request, response, chain);

        // Send the page to the client
        writeResponse(response, pageInfo);
    }

    /**
     * {@inheritDoc}
     *
     * @param request  {@inheritDoc}
     * @param response {@inheritDoc}
     * @param chain    {@inheritDoc}
     * @return {@inheritDoc}
     * @throws AlreadyGzippedException {@inheritDoc}
     * @throws Exception               {@inheritDoc}
     */
    protected PageInfo buildPage(final HttpServletRequest request, final HttpServletResponse response,
                                 final FilterChain chain) throws AlreadyGzippedException, Exception {

        // Invoke the next entity in the chain
        final ByteArrayOutputStream outstr = new ByteArrayOutputStream();
        final GenericResponseWrapper wrapper = new GenericResponseWrapper(response, outstr);
        chain.doFilter(request, wrapper);
        wrapper.flush();

        long timeToLiveSeconds = getTimeToLiveSeconds();

        // Return the page info
        return new PageInfo(wrapper.getStatus(), wrapper.getContentType(), 
                wrapper.getCookies(),
                outstr.toByteArray(), false, timeToLiveSeconds, wrapper.getAllHeaders());
    }

    /**
     * Assembles a response from a cached page include.
     * These responses are never gzipped
     * The content length should not be set in the response, because it is a fragment of a page.
     * Don't write any headers at all.
     */
    protected void writeResponse(final HttpServletResponse response, final PageInfo pageInfo) throws IOException {
        // Write the page
        final byte[] cachedPage = pageInfo.getUngzippedBody();
        //needed to support multilingual
        final String page = new String(cachedPage, response.getCharacterEncoding());


        String implementationVendor = response.getClass().getPackage().getImplementationVendor();
        if (implementationVendor != null && implementationVendor.equals("\"Evermind\"")) {
            response.getOutputStream().print(page);
        } else {
            response.getWriter().write(page);
        }
    }
    
    @Override
	protected String initCacheType()
	{
		return Rule.CACHE_TYPE_FRAGMENT;
	}

	@Override
	protected boolean noCache(Rule rule, HttpServletRequest httpRequest)
	{
		return BaseRuleValidated.validateRequestUri((String)httpRequest.getAttribute("javax.servlet.include.servlet_path"), rule.getIncludes());
	}

}
