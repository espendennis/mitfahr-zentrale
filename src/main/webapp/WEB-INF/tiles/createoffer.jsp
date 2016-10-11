	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-md-12">
		<h1>Neues Angebot</h1>
	</div>
</div>
<div class="row">
	<div class="col-md-8 offset-md-2">
		<div class="container createoffer">
			<form action="<c:url value='/newoffer'></c:url>" method="post" id="form">
				<div class="form-group row">
					<label for="startingpoint" class="col-md-2 col-form-label">Startort</label>
					<div class="col-md-8">
						<input class="form-control" type="text" name="startingPoint"
							placeholder="Startort" id="startingpoint">
					</div>
				</div>
				<div class="form-group row">
					<label for="destination" class="col-md-2 col-form-label">Ziel</label>
					<div class="col-md-8">
						<input class="form-control" type="text" name="destination"
							placeholder="Ziel" id="destination">
					</div>
				</div>
				<div class="form-group row">
					<label for="date" class="col-md-2 col-form-label">Zeit</label>
					<div class="col-md-8">
						<input class="form-control" type="datetime-local" name="date"
							value="2016-08-19T13:45:00" id="date">
					</div>
				</div>
				<div class="form-group row">
					<label for="price" class="col-md-2 col-form-label">Preis</label>
					<div class="col-md-8">
						<input class="form-control" type="number" name="price" id="price">
					</div>
				</div>
				<div class="form-group row">
					<div class="alert alert-success col-md-10 alertdiv" role="alert">
						<strong>Angebot erfolgreich erstellt.</strong>
					</div>
				</div>
				<div class="form-group row">
					<div class="alert alert-danger col-md-10 alertdiv" role="alert">
						<strong>Fehler beim erstellen.</strong>
					</div>
				</div>
				<div class="form-group row alertdiv" id="startingpointalert">
					<div class="alert alert-danger col-md-10" role="alert"
						>
						<strong>Der Startort muss zwischen 1 und 80 Zeichen lang sein</strong>
					</div>
				</div>
				<div class="form-group row alertdiv" id="destinationalert">
					<div class="alert alert-danger col-md-10" role="alert"
						>
						<strong>Der Zielort muss zwischen 1 und 80 Zeichen lang sein</strong>
					</div>
				</div>
				<div class="form-group row alertdiv" id="pricealert">
					<div class="alert alert-danger col-md-10" role="alert"
						>
						<strong>Der Preis muss eine Zahl sein</strong>
					</div>
				</div>

				<button type="submit" class="btn btn-primary">Angebot erstellen</button>
			</form>
		</div>
	</div>
</div>
