<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Mitfahr-Zentrale</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href=<c:url value="/webjars/bootstrap/4.0.0-alpha.3/css/bootstrap.min.css"></c:url>
	rel="stylesheet">
<link href=<c:url value="/css/styles.css"></c:url> rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>


<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />


	<script src="<c:url value = '/webjars/jquery/3.1.0/jquery.min.js'></c:url>"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js"></script>
	<script src="<c:url value = '/webjars/bootstrap/4.0.0-alpha.3/js/bootstrap.min.js'></c:url>"></script>
	<tiles:insertAttribute name="script" />
	<tiles:insertAttribute name="basescript" />

</body>

</html>