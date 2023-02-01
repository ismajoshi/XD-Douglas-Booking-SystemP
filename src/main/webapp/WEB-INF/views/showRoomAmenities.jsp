<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Room Amenities</title>
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
</head>
<body>

	<jsp:include page="/common/header.jsp"></jsp:include>

	<div class="container">

		<h1>Room Amenities</h1>

		<hr />
		<c:if test="${ message != null }">
			<div class="alert alert-success" role="alert">${message}</div>
		</c:if>
		<table class="table table-striped table-bordered">

			<tr>
				<td>Room Amenities ID</td>
				<td>Name</td>
				<td>Serial Number</td>
				<td>Manufacturer</td>
				<td>Model</td>
				<td>Details Notes</td>
				<td>Amenity Type</td>
				<td>Room ID</td>
				<td>Edit</td>
				<td>Delete</td>
			</tr>
			<c:forEach var="roomAmenities" items="${roomAmenities}">
				<tr>
					<td>${roomAmenities.room_amentieId}</td>
					<td>${roomAmenities.name}</td>
					<td>${roomAmenities.serialNumber}</td>
					<td>${roomAmenities.manufacturer}</td>
					<td>${roomAmenities.model}</td>
					<td>${roomAmenities.detailsNotes}</td>
					<td>${roomAmenities.amenitie_type}</td>
					<td>${roomAmenities.roomId}</td>
					<td><a
						href="${pageContext.request.contextPath}/editRoomAmenity/?id=${roomAmenities.room_amentieId}">Edit</a></td>
					<td><a
						href="${pageContext.request.contextPath}/deleteRoomAmenity/?id=${roomAmenities.room_amentieId}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>

		<h3>Create Room Amenity</h3>

		<form:form
			action="${pageContext.request.contextPath}/createRoomAmenities/"
			cssClass="form-horizontal" method="post" modelAttribute="roomAmenity">

			<form:hidden path="room_amentieId" />

			<div class="form-group">
				<label for="name" class="col-md-3 control-label">Name</label>
				<div class="col-md-9">
					<form:input path="name" cssClass="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label for="serialNumber" class="col-md-3 control-label">serialNumber</label>
				<div class="col-md-9">
					<form:input path="serialNumber" cssClass="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label for="manufacturer" class="col-md-3 control-label">manufacturer</label>
				<div class="col-md-9">
					<form:input path="manufacturer" cssClass="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label for="model" class="col-md-3 control-label">model</label>
				<div class="col-md-9">
					<form:input path="model" cssClass="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label for="detailsNotes" class="col-md-3 control-label">detailsNotes</label>
				<div class="col-md-9">
					<form:input path="detailsNotes" cssClass="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label for="amenitie_type" class="col-md-3 control-label">Amenity
					Type</label>
				<div class="col-md-9">
					<form:select path="amenitie_type" cssClass="form-control"
						required="true">
						<form:options items="${amenityTypeList}" />
					</form:select>
				</div>
			</div>

			<div class="form-group">
				<label for="roomId" class="col-md-3 control-label">Room Id</label>
				<div class="col-md-9">
					<form:select path="roomId" cssClass="form-control" required="true"
						multiple="false">
						<form:options items="${currentRoomList}" itemValue="roomID"
							itemLabel="number" />
					</form:select>
				</div>
			</div>

			<div class="form-group">
				<!-- Button -->
				<div class="col-md-offset-3 col-md-9">
					<form:button cssClass="btn btn-primary">Submit</form:button>
				</div>
			</div>

		</form:form>

	</div>

	<jsp:include page="/common/footer.jsp"></jsp:include>

</body>
</html>
