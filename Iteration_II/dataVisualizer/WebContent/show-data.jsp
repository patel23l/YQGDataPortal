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
	<c:forEach var="items" items = "${values}">
		${items.ward}
		${items.name}
		${items.votes}
	</c:forEach>
</body>
</html>