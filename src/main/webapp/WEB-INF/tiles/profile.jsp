<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<div class="container">
	<div class="col-md-6 offset-md-3">

		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">
					<c:out value="${user.username}"></c:out>
				</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class=" col-md-9 col-lg-9 ">
						<table class="table table-user-information">
							<tbody>
								<tr>
									<td>Vorname:</td>
									<td><c:out value="${user.firstname}"></c:out></td>
								</tr>
								<tr>
									<td>Nachname:</td>
									<td><c:out value="${user.lastname}"></c:out></td>
								</tr>
								<tr>
									<td>Email:</td>
									<td><c:out value="${user.email}"></c:out></td>
								</tr>
								<tr>
									<td>Telephone:</td>
									<td><c:out value="${user.phone}"></c:out></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



