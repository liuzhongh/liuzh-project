<%@page import="com.shangkang.im.MsgManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MsgManager manager = new MsgManager();

String phoneNo = request.getParameter("n");
String toUser = request.getParameter("t");

if(phoneNo != null && phoneNo != "")
	manager.callPhone(toUser, phoneNo);

//phoneNo = null;
%>
<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自动拨号</title>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/CallAutoAction.js'></script>
<script type="text/javascript">
	var phoneNo = "<%=phoneNo%>";
	var toUser = "<%=request.getParameter("t")%>";
	
	function start()
	{
		if(phoneNo != null && phoneNo != "")
		{
			CallAutoAction.callPhone(toUser, phoneNo);
		}
	}
</script>
</head>
<body onload="start()">
<p>拨号操作已完成!</p>
</body>
</html> --%>