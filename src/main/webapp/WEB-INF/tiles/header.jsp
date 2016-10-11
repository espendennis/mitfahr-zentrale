<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-light bg-faded">
	<a class="navbar-brand" href="/">Mitfahr-Zentrale</a>
	<ul class="nav navbar-nav">
		<li class="nav-item" id="homeLink"><a class="nav-link" href="/">Home
				<span class="sr-only">(current)</span>
		</a></li>
		<li class="nav-item" id="createOfferLink"><a class="nav-link"
			href="/createoffer">Neues Angebot</a></li>
		<li class="nav-item" id="aboutLink"><a class="nav-link"
			href="/about">About</a></li>
		<li class="nav-item" id="apiLink"><a class="nav-link" href="/api">API</a></li>
	</ul>
	<div class="pull-lg-right">
		<sec:authorize access="!isAuthenticated()">
			<div class="dropdown closed">
				<button class="btn btn-submit dropdown-toggle" type="button"
					id="dropdownLogIn" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Log In</button>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="dropdownLogIn">
					<div>
						<form name="f" action="<c:url value='/login'></c:url>" method='POST'
							>
							<label for="inputEmail" class="sr-only">Username</label> <input
								type="text" id="input" class="form-control"
								placeholder="Username" name="username" required autofocus>
							<label for="inputPassword" class="sr-only">Passwort</label> <input
								type="password" id="inputPassword" class="form-control"
								placeholder="Passwort" name="password" required>
							<a href="<c:url value='/createuser'></c:url>" class="btn btn-lg btn-primary btn-block loginbtn_2" > Registrieren</a>
							<button class="btn btn-lg btn-primary btn-block loginbtn_2"
								type="submit">Einloggen</button>
						</form>

					</div>
				</div>
			</div>
		</sec:authorize>

		<sec:authorize access="isAuthenticated()">
			<div class="dropdown closed">
				<button class="btn btn-submit dropdown-toggle" type="button"
					id="dropdownProfil" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Profil</button>
				<div class="dropdown-menu" aria-labelledby="dropdownProfil">
					<form action="<c:url value='/ownprofile/'></c:url>" method="post">
						<button class="btn dropdown-item" type="submit">Profil</button>
					</form>
					<form action="<c:url value='/logout'></c:url>" method="post">
						<button class="btn dropdown-item" type="submit">Logout</button>
					</form>
				</div>
			</div>
		</sec:authorize>
	</div>
</nav>


