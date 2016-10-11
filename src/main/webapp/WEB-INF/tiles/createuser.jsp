<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-md-12">
		<div class="container createuser">
			<form action="<c:url value='/newuser'></c:url>" method="post" id="form">
				<div class="form-group row">
					<label for="username" class="col-xs-2 col-form-label">Username</label>
					<div class="col-xs-10">
						<input class="form-control" type="text" name="username"
							placeholder="Username" id="username">
					</div>
				</div>
				<div class="form-group row">
					<label for="firstname" class="col-xs-2 col-form-label">Vorname</label>
					<div class="col-xs-10">
						<input class="form-control" type="text" name="firstname"
							placeholder="Vorname" id="firstname">
					</div>
				</div>
				<div class="form-group row">
					<label for="lastname" class="col-xs-2 col-form-label">Nachname</label>
					<div class="col-xs-10">
						<input class="form-control" type="text" name="lastname" 
							placeholder="Nachname" id="lastname">
					</div>
				</div>
				<div class="form-group row">
					<label for="email" class="col-xs-2 col-form-label">Email</label>
					<div class="col-xs-10">
						<input class="form-control" type="email" name="email"
							placeholder="bootstrap@example.com" id="email">
					</div>
				</div>
				<div class="form-group row">
					<label for="tel" class="col-xs-2 col-form-label">Telephon-Nummer</label>
					<div class="col-xs-10">
						<input class="form-control" type="tel" name="phone" id="phone">
					</div>
				</div>
				<div class="form-group row">
					<label for="password" class="col-xs-2 col-form-label">Passwort</label>
					<div class="col-xs-10">
						<input class="form-control" type="password" name="password"
							id="password">
					</div>
				</div>
				<div class="form-group row">
					<label for="confirmpassword" class="col-xs-2 col-form-label">Passwort
						wiederholen</label>
					<div class="col-xs-10">
						<input class="form-control" type="password" name="confirmpassword"
							id="confirmpassword">
					</div>
				</div>
				<div class="form-group row alertdiv" id="passwordalert">
					<div class="alert alert-danger col-md-10" role="alert"
						>
						<strong>Die Passwörter stimmen nicht überein</strong>
					</div>
				</div>
				<div class="form-group row alertdiv" id="usernamealert">
					<div class="alert alert-danger col-md-10" role="alert"
						>
						<strong>Der Username muss zwischen 5 und 80 Zeichen lang
							sein</strong>
					</div>
				</div>
				<div class="form-group row alertdiv" id="firstnamealert">
					<div class="alert alert-danger col-md-10" role="alert"
						>
						<strong>Der Vorname muss zwischen 5 und 80 Zeichen lang
							sein</strong>
					</div>
				</div>
				<div class="form-group row alertdiv" id="lastnamealert">
					<div class="alert alert-danger col-md-10" role="alert"
						>
						<strong>Der Nachname muss zwischen 5 und 80 Zeichen lang
							sein</strong>
					</div>
				</div>
				<div class="form-group row alertdiv" id="phonealert">
					<div class="alert alert-danger col-md-10" role="alert"
						>
						<strong>Dies scheint keine gültige Telefonnummer zu sein</strong>
					</div>
				</div>
				<div class="form-group row alertdiv" id="emailalert">
					<div class="alert alert-danger col-md-10" role="alert"
						>
						<strong>Dies scheint keine gültige email-adresse zu sein</strong>
					</div>
				</div>
				<div class="form-group row alertdiv" id="submit-success">
					<div class="alert alert-success col-md-10"
						role="alert">
						<strong>Account erfolgreich erstellt.</strong>
					</div>
				</div>
				<div class="form-group row alertdiv" id="submit-error" >
					<div class="alert alert-danger col-md-10 " role="alert">
						<strong>Fehler beim erstellen.</strong>
					</div>
				</div>
				<div class="form-group row">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</form>
		</div>
	</div>
</div>

