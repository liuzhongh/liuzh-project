<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.springframework.webflow.core.collection.MutableAttributeMap"%>
<%@page import="org.jasig.cas.authentication.principal.UsernamePasswordCredentials"%>
<%@page import="org.springframework.binding.message.MessageContext"%>
<%@page import="org.springframework.webflow.execution.RequestContextHolder"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>会员登录</title>
</head>
<body onload="on_load_submit()">
<%
	final String redirectUrl = "http://192.168.3.189:8090/trans/loginMain.html";

	MessageContext messageContext = RequestContextHolder.getRequestContext().getMessageContext();
	UsernamePasswordCredentials credentials = (UsernamePasswordCredentials) RequestContextHolder.getRequestContext().getFlowScope().get("credentials");
	String service = (String) request.getParameter("service");
	/* String serviceUrl1 = "";
	String serviceUrl2 = "";
	
	if(null != service && !"".equals(service.trim()))
	{
		service = URLEncoder.encode(service, "UTF-8");
		serviceUrl1 = "&sv=" + service;
		serviceUrl2 = "?sv=" + service;
	} */
	
	if(messageContext.hasErrorMessages())
	{
		String msg = messageContext.getAllMessages()[0].getText();
		String errorCode = "10";
		
		if(msg.length() == 1)
			errorCode = msg;
	%>	
	<form id="submit_form" action="<%=redirectUrl %>" method="post">
		<input type="hidden" name="sv" value="<%=service%>"/>
		<input type="hidden" name="un" value="<%=credentials.getUsername() %>"/>
		<input type="hidden" name="ec" value="<%=msg%>"/>
	</form>
	<%
		//response.sendRedirect(redirectUrl + "?un=" + credentials.getUsername() + "&ec=" + msg + serviceUrl1);
		
	}else
	{
	%>
	<form id="submit_form" action="<%=redirectUrl %>" method="post">
		<input type="hidden" name="sv" value="<%=service%>"/>
	</form>
	<%
		//response.sendRedirect(redirectUrl + serviceUrl2);
	}
    		
    		//System.out.println("******************" + messageContext.hasErrorMessages());
%>
	
	<script type="text/javascript">
	function on_load_submit()
	{
		document.getElementById("submit_form").submit();
	}
	</script>
</body>
</html>