/**
 * COPYRIGHT (C) 2013 3KW. ALL RIGHTS RESERVED.
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

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.jasig.cas.authentication.principal.Service;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public class AjaxLoginServiceTicketAction extends AbstractAction {

	// The default call back function name.  
    protected static final String J_CALLBACK = "feedBackUrlCallBack";  
  
    protected Event doExecute(final RequestContext context) {  
        HttpServletRequest request = WebUtils.getHttpServletRequest(context);  
        Event event = context.getCurrentEvent();  
        boolean isAjax = BooleanUtils.toBoolean(request.getParameter("isajax"));  
          
        if (!isAjax){  // 非 ajax/iframe 方式登录，返回当前 event.  
            return event;  
        }  
        boolean isLoginSuccess;  
        // Login Successful.  
        if ("success".equals(event.getId())){ //是否登录成功  
            final Service service = WebUtils.getService(context);  
            final String serviceTicket = WebUtils.getServiceTicketFromRequestScope(context);  
            if (service != null){  //设置登录成功之后 跳转的地址  
                request.setAttribute("service", service.getId());  
            }  
            request.setAttribute("ticket", serviceTicket);  
            isLoginSuccess = true;  
        } else { // Login Fails..  
            isLoginSuccess = false;  
        }  
  
        boolean isFrame = BooleanUtils.toBoolean(request.getParameter("isframe"));  
        String callback = request.getParameter("callback");  
        if(StringUtils.isEmpty(callback)){ // 如果未转入 callback 参数，则采用默认 callback 函数名  
            callback = J_CALLBACK;  
        }  
        if(isFrame){ // 如果采用了 iframe ，则 concat 其 parent 。  
            callback = "".concat(callback);  
        }  
        request.setAttribute("isFrame", isFrame);  
        request.setAttribute("callback", callback);  
        request.setAttribute("isLogin", isLoginSuccess);  
          
        return new Event(this, "local"); // 转入 ajaxLogin.jsp 页面  
    }  

}
