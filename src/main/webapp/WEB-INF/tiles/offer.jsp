<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>

<div class="container">
	<div class="col-md-6 offset-md-3">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">
					<c:out value="${offer.startingPoint}"></c:out> - <c:out value="${offer.destination}"></c:out>
				</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class=" col-md-9 col-lg-9 ">
						<table class="table table-user-information">
							<tbody>
								<tr>
									<td>Start:</td>
									<td><c:out value="${offer.startingPoint}"></c:out></td>
								</tr>
								<tr>
									<td>Ziel:</td>
									<td><c:out value="${offer.destination}"></c:out></td>
								</tr>
								<tr>
									<td>Tag:</td>
									<td><fmt:formatDate value='${offer.dateObject}' type='date' dateStyle='short' /></td>
								</tr>
								<tr>
									<td>Zeit:</td>
									<td><fmt:formatDate value='${offer.dateObject}' type='time' pattern='HH:mm' dateStyle='short' /> Uhr</td>
								</tr>
								<tr>
									<td>Angeboten von:</td>
									<td><a href="<c:url value='/profile/${user.username}'></c:url>"><c:out value="${user.username}"></c:out></a></td>
								</tr>
								<tr>
									<td>Preis:</td>
									<td><c:out value="${offer.price}"></c:out></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



