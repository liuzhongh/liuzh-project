/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Feb 16, 2011
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.core.system.flex;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import flex.messaging.FlexContext;
import flex.messaging.HttpFlexSession;
import flex.messaging.MessageBroker;
import flex.messaging.MessageException;
import flex.messaging.endpoints.Endpoint;

public class SimpleMessageBrokerHandlerAdapter implements HandlerAdapter, ServletConfigAware {

	 private static final Log logger = LogFactory.getLog(SimpleMessageBrokerHandlerAdapter.class);

	    private ServletConfig servletConfig;

	    /**
	     * 
	     * {@inheritDoc}
	     */
	    public long getLastModified(HttpServletRequest request, Object handler) {
	        return -1;
	    }

	    /**
	     * 
	     * {@inheritDoc}
	     */
	    public ModelAndView handle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
	        MessageBroker broker = (MessageBroker) handler;

	        try {
	            // Update thread locals
	            broker.initThreadLocals();
	            // Set this first so it is in place for the session creation event.
	            FlexContext.setThreadLocalObjects(null, null, broker, req, res, this.servletConfig);
	            HttpFlexSession.getFlexSession(req);

	            String contextPath = req.getContextPath();
	            String pathInfo = req.getPathInfo();
	            String endpointPath = req.getServletPath();
	            if (pathInfo != null) {
	                endpointPath = endpointPath + pathInfo;
	            }
	            Endpoint endpoint = null;
	            try {
	                endpoint = broker.getEndpoint(endpointPath, contextPath);
	            } catch (MessageException me) {
	                if (logger.isInfoEnabled()) {
	                    logger.info("Received invalid request for endpoint path '" + endpointPath + "'.");
	                }

	                if (!res.isCommitted()) {
	                    res.sendError(HttpServletResponse.SC_NOT_FOUND);
	                }

	                return null;
	            }

	            try {
	                if (logger.isInfoEnabled()) {
	                    logger.info("Channel endpoint " + endpoint.getId() + " received request.");
	                }
	                endpoint.service(req, res);
	            } catch (UnsupportedOperationException ue) {
	                if (logger.isInfoEnabled()) {
	                    logger.info("Channel endpoint " + endpoint.getId() + " received request for an unsupported operation.", ue);
	                }

	                if (!res.isCommitted()) {
	                    res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	                }
	            }
	        } finally {
	            FlexContext.clearThreadLocalObjects();
	        }

	        return null;
	    }

	    /**
	     * 
	     * {@inheritDoc}
	     */
	    public void setServletConfig(ServletConfig servletConfig) {
	        this.servletConfig = servletConfig;
	    }

	    /**
	     * 
	     * {@inheritDoc}
	     */
	    public boolean supports(Object handler) {
	        return handler instanceof MessageBroker;
	    }

}
