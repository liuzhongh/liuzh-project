<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/CallAutoAction.js'></script>
<script type="text/javascript">
	var username = "<%=request.getParameter("username")%>";
	var password = "<%=request.getParameter("password")%>";
	var aliases = "<%=request.getParameter("aliases")%>";
	var email = "<%=request.getParameter("email")%>";
	
	function start()
	{
		if(username != null && password != "")
		{
			CallAutoAction.regist(username, password, aliases, email, callback);
		}
	}
	
	function callback(data)
	{
		window.location.href = "result.jsp?result=" + data;
	}
</script>
</head>
<body onload="start()">
</body>
</html>