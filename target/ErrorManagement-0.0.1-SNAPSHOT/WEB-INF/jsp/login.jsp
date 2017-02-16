<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form action="<c:url value='../j_spring_security_chk'></c:url>" method="POST">
		<table>
			<tr><img src="../static/img/login.png" /></tr>
			
			<tr><td>User Id</td></tr>
			<tr>
				<td><input type="text" name="userName"></td>
			</tr>
			<tr><td>Password</td></tr>
			<tr>
				<td><input type="password" name="password"></td>
			</tr>
			
			<tr>
				<td><input type="submit" name="LOGIN"></td>
			</tr>
		</table>
		
		</form>
</body>
</html>