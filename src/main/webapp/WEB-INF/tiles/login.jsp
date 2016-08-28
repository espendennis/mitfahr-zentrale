<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

 <div class="container col-md-4 offset-md-4">

      <form class="form-signin" name="f" action="<c:url value='/login'></c:url>" method="POST" class="form-signin">
        <h2 class="form-signin-heading">Bitte einloggen</h2>
        <label for="inputEmail" class="sr-only">Username</label>
        <input type="text" id="input" class="form-control" placeholder="Username" name="username" required autofocus>
        <label for="inputPassword" class="sr-only">Passwort</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Passwort" name="password" required>
		<a href="<c:url value='/createuser'></c:url>" class="btn btn-lg btn-primary btn-block loginbtn_2" > Registrieren</a>
        <button class="btn btn-lg btn-primary btn-block loginbtn" type="submit">Einloggen</button>
      </form>

    </div>