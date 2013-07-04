/**
 * COPYRIGHT (C) 2013 3kw. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: liuzh
 * Created On: 2013-6-18
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.jasig.cas.web.flow;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.util.UniqueTicketIdGenerator;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public class ProvideLoginTicketAction extends AbstractAction {

	private static final String PREFIX = "LT";  
    
	/** Logger instance */  
    private final Log logger = LogFactory.getLog(getClass());  
    
    public final String generate(final RequestContext context) {  
        final String loginTicket = this.ticketIdGenerator.getNewTicketId(PREFIX);  
        this.logger.debug("Generated login ticket " + loginTicket);  
        WebUtils.putLoginTicket(context, loginTicket);  
        return "generated";  
    }  
    
    @Override  
    protected Event doExecute(RequestContext context) throws Exception {  
        final HttpServletRequest request = WebUtils.getHttpServletRequest(context);  
        if (request.getParameter("get-lt") != null && request.getParameter("get-lt").equalsIgnoreCase("true")) {  
            final String loginTicket = this.ticketIdGenerator.getNewTicketId(PREFIX);  
            this.logger.debug("Generated login ticket " + loginTicket); 
            WebUtils.putLoginTicket(context, loginTicket);  
            return result("loginTicketRequested");  
        }  
        return result("continue");  
    }  
      
    private UniqueTicketIdGenerator ticketIdGenerator;  
      
    public void setTicketIdGenerator(final UniqueTicketIdGenerator generator) {  
        this.ticketIdGenerator = generator;  
    }  
}
