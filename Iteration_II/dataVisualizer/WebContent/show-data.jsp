<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Data Visualizer</title>
</head>
<body>
	<h1 align="center">Election</h1>
	<table border="1" cellpadding="5%" align="center">
		<c:forEach var="items" items = "${values}">
			<tr>
				<!-- if there is time, separate each field into row -->
				<td> ${items.entry} </td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>