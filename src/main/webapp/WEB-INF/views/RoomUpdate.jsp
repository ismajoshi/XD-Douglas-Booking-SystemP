<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="static/css/xd.css">



<title>Douglas Booking System</title>

</head>

<body>

	<jsp:include page="/common/header.jsp"></jsp:include>

	<div class="container p-5">
		<div class="row">
			<div class="col">
				<h1>Update Room</h1>
			</div>
		</div>

		<div class="row">
			<div class="col">

				<form:form
					action="${pageContext.request.contextPath}/RoomMGMT/updateRoom/"
					cssClass="form-horizontal" method="post" modelAttribute="room">
					
					<form:hidden path="roomID" />					
					
					<div class="form-row m-2">
						
						<div class="form-group col-md-4">
							<label for="roomId" class=" controllabel">Number</label>
							<form:input path="number" cssClass="form-control" />
						</div>
						<div class="form-group col-md-4">
							<label for="roomId" class="controllabel">Floor</label>
							<form:input path="floor" cssClass="form-control" />
						</div>

							<div class="col">
								<label for="roomId" class="controllabel">Building</label>
								<form:input path="building" cssClass="form-control" />
							</div>
					</div>
					<div class="form-row m-2">
						<div class="form-group col-md-4">	
							<label for="roomId" class="controllabel">Capacity</label>
							<form:input path="capacity" cssClass="form-control" />
						</div>
						<div class="form-group col-md-4">
							<label for="roomId" class="controllabel">Room Type</label>
							
							<form:select path="roomType" cssClass="form-control">
								<option value="classroom">Classroom</option>
								<option value="laboratory">Laboratory</option>
								<option value="Office">Office</option>
							</form:select>
						</div>
						<div class="form-group col-md-4">
							<label for="roomId" class="controllabel">Base Calendar ID</label>
							<form:select path="baseCalendar" cssClass="form-control">
								<form:options items="${baseCalendarList}" />
							</form:select>
						</div>
					</div>
					
					<div class="form-row">
						<div class="col-md-5"></div>
						<div class="col-md-2">
							<form:button class="btn btn-primary">Update Room</form:button>
						</div>
						<div class="col-md-5"></div>
					</div>
				</form:form>
			</div>
		</div>
	</div>



	<jsp:include page="/common/footer.jsp"></jsp:include>

</body>

</html>