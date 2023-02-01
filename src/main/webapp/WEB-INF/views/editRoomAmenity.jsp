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

		<h1>Edit Room Amenity - ${roomAmenities.room_amentieId}</h1>
		<hr />

		<h3>Edit Room Amenity</h3>

		<form:form action="${pageContext.request.contextPath}/editRoomAmenity"
			cssClass="form-horizontal" method="post" modelAttribute="roomAmenity">

			<div class="form-group">
				<label for="room_amentieId" class="col-md-3 control-label">Room
					Amenity Id</label>
				<div class="col-md-9">
					<form:input path="room_amentieId"
						value="${roomAmenities.room_amentieId}" cssClass="form-control"
						readonly="true" />
				</div>

				<label for="name" class="col-md-3 control-label">Name</label>
				<div class="col-md-9">
					<form:input path="name" value="${roomAmenities.name}"
						cssClass="form-control" />
				</div>


				<label for="serial_Number" class="col-md-3 control-label">Serial
					Number</label>
				<div class="col-md-9">
					<form:input path="serialNumber"
						value="${roomAmenities.serialNumber}" cssClass="form-control" />
				</div>

				<label for="manufacturer" class="col-md-3 control-label">Manufacturer</label>
				<div class="col-md-9">
					<form:input path="manufacturer"
						value="${roomAmenities.manufacturer}" cssClass="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label for="model" class="col-md-3 control-label">Model</label>
				<div class="col-md-9">
					<form:input path="model" value="${roomAmenities.model}"
						cssClass="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label for="detailsNotes" class="col-md-3 control-label">Detail
					Notes</label>
				<div class="col-md-9">
					<form:input path="detailsNotes"
						value="${roomAmenities.detailsNotes}" cssClass="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label for="amenitie_type" class="col-md-3 control-label">Amenity Type</label>
				<div class="col-md-9">
					<form:select path="amenitie_type" cssClass="form-control" required="true">
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
					<form:button Class="btn btn-primary">Submit</form:button>
				</div>
			</div>
		</form:form>

	</div>

	<jsp:include page="/common/footer.jsp"></jsp:include>

</body>
</html>
