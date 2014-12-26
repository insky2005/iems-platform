<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<form name='f' action="j_spring_security_check" method='POST'>
		<table>
			<tr>
				<td>User:</td>
				<td><input type="text" name="j_username" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="j_password" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="checkbox" name="_spring_security_remember_me" />两周之内不必登陆&nbsp;&nbsp;<input name="submit" type="submit" value="Logon" /></td>
			</tr>
		</table>
	</form>
</body>
</html>