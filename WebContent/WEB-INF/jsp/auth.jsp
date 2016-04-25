<%@ page language="java" import="com.wechat.constants.*,com.wechat.service.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>auth test</title>
</head>
<body style="font-size: 25px;">

	<h2>获取用户信息测试(base方式，只获取用户 openid )</h2>
	<a href="<%=WeChatAccount.getUserIdUrl("http://newkinglong.duapp.com/SimpleServlet/control/pages/userinfo", WeChatAccount.scope_snsapi_base, "base") %>>">click me</a>

	<h2>获取用户信息测试(userinfo方式，获取用户相关信息)</h2>
	<a href="<%=WeChatAccount.getUserIdUrl("http://newkinglong.duapp.com/SimpleServlet/control/pages/userinfo", WeChatAccount.scope_snsapi_userinfo, "userinfo") %>>">click me</a>
</body>
</html>