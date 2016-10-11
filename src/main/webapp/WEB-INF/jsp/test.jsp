<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>


<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Mitfahr-Zentrale</title>
<link href="webjars/bootstrap/4.0.0-alpha.3/css/bootstrap.min.css"
	rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>


<body>

	<div class="container container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h1>Mitfahr-Zentrale</h1>
			</div>
		</div>
	</div>

	<form action="/testsubmit" method="post">
		<div class="form-group row">
			<label for="example-datetime-local-input"
				class="col-xs-2 col-form-label">Date and time</label>
			<div class="col-xs-10">
				<input class="form-control" type="datetime-local" name="date"
					value="2011-08-19T13:45:00" id="example-datetime-local-input">
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
	</form>




	<script src="webjars/jquery/3.1.0/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js"></script>
	<script src="webjars/bootstrap/4.0.0-alpha.3/js/bootstrap.min.js"></script>
</body>
</html>