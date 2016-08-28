	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<div class="container container-fluid">
	<div class="row">
		<div class="col-md-12">
			<h1>About</h1>
			<p>
				This site was made with Spring-Boot, Spring-Security, Spring-Data,
				Apache Tiles and Bootstrap. It features a REST-api whose
				documentation can be found <a href="<c:url value='/api'></c:url>">here</a>.
				For now the api needs adminrights. Please use the username "Admin"
				with the password "admin". This side was only made to practice the
				used technologies. Therefore the design is pretty simple due to the
				focus on functionality.
			</p>
		</div>
	</div>
</div>
