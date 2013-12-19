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
	var newPassword = "<%=request.getParameter("newPassword")%>";
	
	function start()
	{
		if(username != null && password != "")
		{
			CallAutoAction.changePassword(username, password, newPassword, callback);
		}
	}
	
	function callback(data)
	{
		document.getElementById("result").innerHTML = data;
	}
</script>
</head>
<body onload="start()">
<div id="result"></div>
</body>
</html>