<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
	<div id="container">
		<table>
			<tr>
				<td><tiles:insertAttribute name="body" /></td>
			</tr>
		</table>
	</div>
</body>
</html>