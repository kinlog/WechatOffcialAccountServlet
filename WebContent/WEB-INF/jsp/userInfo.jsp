<%@page import="org.apache.catalina.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>user info</title>
</head>
<body>

	<div class="userInfo" style="font-size: 25px;">
		<h2>base 方式获取的用户信息：</h2>
		<ul>
			<li>username:"${code}"</li>
			<li>access_token:"${object.access_token}"</li>
			<li>expires_in:"${object.expires_in}"</li>
			<li>refresh_token:"${object.refresh_token}"</li>
			<li>openid:"${object.openid}"</li>
			<li>scope:"${object.scope}"</li>
			<li>unionid:"${object.unionid}"</li>
		</ul>
		<h2>userinfo 方式获取的用户详细信息：</h2>
		<ul>
			<li>openid:"${user.openid}"</li>
			<li>nickname:"${user.nickname}"</li>
			<li>sex:"${user.sex}"</li>
			<li>province:"${user.province}"</li>
			<li>city:"${user.city}"</li>
			<li>country:"${user.country}"</li>
			<li>headimgurl:"${user.headimgurl}"</li>
			<li><a href="${User.headimgurl}">click to see head img</a></li>
			<li>privileges:"${user.privilege}"</li>
			<li>unionid:"${user.unionid}"</li>
		</ul>
	</div>

</body>
</html>