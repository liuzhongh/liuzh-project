<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>你好</title>
<style type="text/css">
.spclass{
font: red;
}
</style>
</head>
<body>
	登陆：
	<s:form action="login">
		<s:textfield name="infor.name" label="用户名"></s:textfield>
		<s:password name="infor.password" label="密码"></s:password>
		<s:submit label="提交"></s:submit>
	</s:form>
	<br />
	<span class="spclass">
		<s:actionerror />
	</span>
	
	<s:actionmessage />
</body>
</html>