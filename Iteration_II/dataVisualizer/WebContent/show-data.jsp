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
	<table border="1">
		<tr>
			<th>Ward</th>
			<th>Name</th>
			<th>Votes</th>
		</tr>
		<c:forEach var="items" items = "${values}">
			<tr>
				<td> ${items.ward} </td>
				<td> ${items.name} </td>
				<td> ${items.votes} </td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>