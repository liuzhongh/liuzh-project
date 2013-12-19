<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自动拨号</title>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/CallAutoAction.js'></script>
<script type="text/javascript">
function start()
{
	var phoneNo = document.getElementById("phone_no").value;
	var toUser = document.getElementById("to_user").value;
	
	CallAutoAction.callPhone(toUser, phoneNo, callback);
}

function callback()
{
	alert("拨号请求成功发送!");	
}

function reg()
{
	
}

</script>
</head>
<body>
<label>被叫帐号：</label><input id="to_user" type="text"></br>
<label>被叫号码：</label><input id="phone_no" type="text" maxlength="11">
<input type="button" value="发送" onclick="start()" />
<br/>
<p>用户注册：</p>
<form action="regist.jsp">
<label>帐号：</label><input name="username" id="username" type="text"></br>
<label>密码：</label><input name="password" id="pwd" type="password"></br>
<label>别名：</label><input name="aliases" id="aliases" type="text"></br>
<label>邮箱：</label><input name="email" id="email" type="text"></br>
<input type="submit" value="注册">
</form>
<p>密码修改：</p>
<form action="changePwd.jsp">
<label>帐号：</label><input name="username" id="user_name" type="text"></br>
<label>原密码：</label><input name="password" id="old_pwd" type="password"></br>
<label>新密码：</label><input name="newPassword" id="new_pwd" type="password"></br>
<input type="submit" value="修改" />
</form>
</body>
</html>