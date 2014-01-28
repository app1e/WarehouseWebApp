<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<style  type="text/css">

#content { position: relative;}

#login {
	position: relative;
	top: 80px;
}

.align-right {
	text-align: right;
}

table {
	border: 1px solid gray;
	padding: 20px;
	background-color: #0088CC;
}

.login-error {
	font-size: 16px;
	font-weight: bold;
	font-color: red;
}


</style>

</head>
<body>

<center>

<div id="login">

<h3>Log In</h3>

<form method="post" action="<%= response.encodeURL(request.getContextPath() + "/LoginController?action=dologin") %>">

<input type="hidden" name="action" value="doLogin" />

<table>

<% if(request.getAttribute("email") != null) {%>
<tr><td class="align-right">E-mail address: </td><td><input type="text" name="email" value="<%= request.getAttribute("email") %>"/></td></tr>
<%} else {%>
<tr><td class="align-right">E-mail address: </td><td><input type="text" name="email" value=""/></td></tr>
<%} %>
<% if(request.getAttribute("password") != null) {%>
<tr><td class="align-right">Password: </td><td><input type="password" name="password" value="<%= request.getAttribute("password") %>"/></td></tr>
<%} else {%>
<tr><td class="align-right">Password: </td><td><input type="password" name="password" value=""/></td></tr>
<%} %>
<tr><td class="align-right" colspan="2"><input type="submit" value="Log in"/></td></tr>

</table>
<% if(request.getAttribute("message") != null) {%>
<p class="login-error"><%= request.getAttribute("message") %></p>
<%} %>

</form>

</div>


</center>


</body>
</html>