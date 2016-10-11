<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container container-fluid">
	<div class="row">
		<div class="col-md-12">
			<h1>Mitfahr-Zentrale</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-10 offset-md-2">
			<table class="table table-border">
				<tr>
					<th>Startort</th>
					<th>Zielort</th>
					<th>Tag</th>
					<th>Zeit</th>
					<th>Öffnen</th>
				</tr>

				<c:forEach var="offer" items="${offers}">
					<tr class="offerrow">

						<td class="startingPoint"><c:out
								value="${offer.startingPoint}"></c:out></td>

						<td class="destination"><c:out value="${offer.destination}"></c:out></td>

						<td class="destination"><fmt:formatDate
								value='${offer.dateObject.getTime()}' type='date' dateStyle='short' /></td>

						<td class="destination"><fmt:formatDate
								value='${offer.dateObject.getTime()}' type='time' pattern='HH:mm' dateStyle='short' /> Uhr</td>

						<td class="day"><a
							href="<c:url value='/offer/${offer.id}'></c:url>">"Öffnen"</a></td>

					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
</div>

