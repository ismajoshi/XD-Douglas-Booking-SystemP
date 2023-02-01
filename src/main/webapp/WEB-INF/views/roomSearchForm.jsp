<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 
	View Page that presents a form to search for Rooms.

	@author Ricardo dos Santos Alves de Souza
 -->

<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Room Search</title>
	<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/static/js/jquery-3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/xd.css">
	<link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/static/img/favicon_io/apple-touch-icon.png">
	<link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/static/img/favicon_io/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/img/favicon_io/favicon-16x16.png">
	<link rel="manifest" href="${pageContext.request.contextPath}/static/img/favicon_io/site.webmanifest">
	
	<!-- Room Search JavaScript Functions -->
	<script src="${pageContext.request.contextPath}/static/js/room_search_rso_35.js"></script>
	
</head>

<body>

	<jsp:include page="/common/header.jsp"></jsp:include>
	
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>Room Search</h1>
			</div>
				<c:if test="${ successMessage !=null }">
					<hr />
					<div class="alert alert-success" role="alert">${successMessage}</div>
				</c:if>
				<c:if test="${ errorMessage !=null }">
					<hr />
					<div class="alert alert-danger" role="alert">${errorMessage}</div>
				</c:if>			
		</div>
		
		<div class="row justify-content-center">

			<!-- Room Search Fields Form -->
			<form:form action="${pageContext.request.contextPath}/room_search/search/" method="post" modelAttribute="roomSearchObj">

				<fieldset id="roomSearchFields" class="border border-primary rounded p-4" >
					<legend>Search Fields</legend>
					
						<div class="form-row align-items-center">

							<div class="form-group col-md-2">
								<form:label path="number" for="number" cssClass="control-label">Room Number</form:label>
								<form:input type="text" path="number" cssClass="form-control" placeholder="Enter a text" />
								<form:errors path="number" style="color:red" />
							</div>
							
							<div class="form-group col-md-2">
								<form:label path="floor" for="floor" cssClass="control-label">Room Floor</form:label>
								<form:input type="text" path="floor" cssClass="form-control" placeholder="Enter a text" />
								<form:errors path="floor" style="color:red" />
							</div>
							
							<div class="form-group col-md-2">
								<form:label path="building" for="building" cssClass="control-label">Room Building</form:label>				
								<form:select path="building" cssClass="form-control" >
									<form:options items="${roomBuildingsList}" />
								</form:select> 				
								<form:errors path="building" style="color:red" />					
							</div>
							
							<div class="form-group col-md-2">
								<form:label path="capacity" for="capacity" cssClass="control-label">Room Capacity</form:label>
								<form:input type="number" min="1" path="capacity" cssClass="form-control" placeholder="Enter the minimum capacity" required="true" />
								<form:errors path="capacity" style="color:red" />
							</div>
							
							<div class="form-group col-md-2">
								<form:label path="roomType" for="roomType" cssClass="control-label">Room Type</form:label>				
								<form:select path="roomType" cssClass="form-control" >
									<form:options items="${roomTypeList}" />
								</form:select> 				
								<form:errors path="roomType" style="color:red" />					
							</div>
							
							<div class="form-group col-md-2">
								<form:label path="amenitie_typeList" for="amenitie_typeList" cssClass="control-label" >Room Amenities Type</form:label>				
								<form:select path="amenitie_typeList" cssClass="form-control" multiple="true" items="${roomAmenitiesTypeList}" />
								<form:errors path="amenitie_typeList" style="color:red" />					
							</div>
						</div>
							
						<div class="form-row align-items-center">
							<div class="form-check col-md-1">
								<form:checkbox  path="onlyAvailable" cssClass="form-check-input" id="onlyAvailableChkBx" />
								<form:label path="onlyAvailable" for="onlyAvailable" cssClass="form-check-label">Only Available Rooms</form:label>				
								<form:errors path="onlyAvailable" style="color:red" />					
							</div>
						
							<div class="form-group col-md-3">
								<form:label path="desiredStartDatetime" for="desiredStartDatetime" cssClass="control-label">Desired Start Date/Time</form:label>
								<form:input type="datetime-local" path="desiredStartDatetime" cssClass="form-control" />
								<form:errors path="desiredStartDatetime" style="color:red" />
							</div>
							
							<div class="form-group col-md-3">
								<form:label path="desiredEndDatetime" for="desiredEndDatetime" cssClass="control-label" >Desired End Date/Time</form:label>
								<form:input type="datetime-local" path="desiredEndDatetime" cssClass="form-control"  />
								<form:errors path="desiredEndDatetime" style="color:red" />
							</div>

							<div class="form-group col-md-3 offset-md-2">
								<!-- Search Button -->
								<form:button class="btn btn-primary mr-2">Search</form:button>
								<!-- Clear Button -->
								<a href="${pageContext.request.contextPath}/room_search/initialize/" class="btn btn-primary" role="button">Clear</a>
							</div>
							
						</div>

				</fieldset>					
			</form:form>
		</div>
			
		<div class="row mt-4 mb-4">
					
			<!-- Table with filtered Rooms -->
			<table class="table table-striped table-bordered">
	
				<tr class="text-center">
					<td>Room ID</td>				
					<td>Room Number</td>
					<td>Room Floor</td>
					<td>Room Building</td>
					<td>Room Capacity</td>	
					<td>Room Type</td>

					<td>Room Schedule</td>
					<td>Reserve</td>
				</tr>

				<c:forEach var="room" items="${filteredRoomsList}" varStatus="loop" >
					<tr class="text-center">
						<td>${room.roomID}</td>
						<td>${room.number}</td>
						<td>${room.floor}</td>
						<td>${room.building}</td>
						<td>${room.capacity}</td>
						<td>${room.roomType}</td>
						
						<td><a href="${pageContext.request.contextPath}/room_det_sch_report/showReportFromRoomSearch/?id=${room.roomID}&startDatetime=${roomSearchObj.desiredStartDatetime}">Room Schedule</a></td>
						<td><a href="${pageContext.request.contextPath}/reservation/newFromRoomSearch/?id=${room.roomID}&startDatetime=${roomSearchObj.desiredStartDatetime}&endDatetime=${roomSearchObj.desiredEndDatetime}">Reserve</a></td>
					</tr>
				</c:forEach>
			</table>
			<h6>(*)Listing only the top 50 records. If necessary, use more filter to refine your searching.</h6>
		</div>

	</div>
	
	<!-- JavaScript Function to Enable/Disable Date/Time Fields based on the Checkbox -->	
	<script>
		$(document).ready(enableDatetime());
		$("#onlyAvailableChkBx").trigger("change");
	</script>
	
	<jsp:include page="/common/footer.jsp"></jsp:include>	
		
</body>
</html>