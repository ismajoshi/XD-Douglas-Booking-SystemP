<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 
	View Page that presents a form to select a Room and a Date and presents the Detail and Schedule Report.

	@author Ricardo dos Santos Alves de Souza
 -->

<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Room Detail & Schedule Report</title>
	<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/static/js/jquery-3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
		crossorigin="anonymous">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/xd.css">
	
	<!-- Room Detail & Schedule JavaScript Functions -->
	<script src="${pageContext.request.contextPath}/static/js/room_detail_schedule_report_rso_35.js"></script>	
	
</head>

<body>

	<jsp:include page="/common/header.jsp"></jsp:include>
	
	<div id="roomDetailScheduleReport_Form_Section" class="container">
		<div class="row">
			<div class="col">
				<h1>Room Detail & Schedule Report</h1>
			</div>
		</div>

		<!-- Feedback Messages -->		
		<c:if test="${ successMessage !=null }">
			<div class="alert alert-success" role="alert">${successMessage}</div>
		</c:if>
		<c:if test="${ warningMessage !=null }">
			<div class="alert alert-warning" role="alert">${warningMessage}</div>
		</c:if>
		<c:if test="${ errorMessage !=null }">
			<div class="alert alert-danger" role="alert">${errorMessage}</div>
		</c:if>		
		
		
		<!-- Room and Data Selection Fields Form -->
		<form:form action="${pageContext.request.contextPath}/room_det_sch_report/showReport/" method="post" modelAttribute="roomDetailScheduleReportFilterObj">

				<fieldset id="roomDetailScheduleReportFilters" class="border border-primary rounded p-4" >
					<legend>Selection Fields</legend>

					<div class="form-row align-items-center">
						
						<div class="form-group col-md-3">
							<form:label path="roomId" for="roomId">Room</form:label>
							<form:select path="roomId" cssClass="form-control" required="true" >
								<form:option value="${null}">&nbsp;</form:option>
								<form:options items="${currentRoomList}" itemValue="roomID" itemLabel="number" />
							</form:select> 				
							<form:errors path="roomId" style="color:red" />
						</div>
						
						<div class="form-group col-md-3">
							<form:label path="selectedDate" for="selectedDate">Selected Date</form:label>
							<form:input type="date" path="selectedDate" cssClass="form-control" required="true" />
							<form:errors path="selectedDate" style="color:red" />
						</div>					
						
						<div class="col-md-3 offset-md-2">
							<!-- Show Report Button -->
							<form:button class="btn btn-primary mr-2" name="show_report" >Show Report</form:button>
							<!-- Clear Button -->
							<a href="${pageContext.request.contextPath}/room_det_sch_report/initialize/" class="btn btn-primary" role="button">Clear</a>
						</div>

					</div>

					<hr class="my-3"/>

					<!-- Navigation Buttons Section -->					
					<div class="form-row justify-content-md-center">
						<div class="col-md-auto">
							<form:button class="btn btn-primary mr-2" name="show_previous_day" >Show Previous Day</form:button>
						</div>
						<div class="col-md-auto">
							<a href="javascript: printRoomDetailScheduleReport();" class="btn btn-primary" role="button">Print</a>
						</div>
						<div class="col-md-auto">
							<form:button class="btn btn-primary mr-2" name="show_next_day" >Show Next Day</form:button>
						</div>						
					</div>					
					
				</fieldset>
		</form:form>
		
	</div>

	<hr class="my-3"/>	
	
	<!-- Report Section -->
	<div id="roomDetailScheduleReport_Report_Section" class="container bg-light text-dark p-5">

		<!--  Empty report -->
		<c:if test="${ roomDetailScheduleReportCompositeObj == null }">
			<div class="row justify-content-center">
				<p class="h4"> Select a Room and Date and click Show Report to present a Report... </p>
			</div>
		</c:if>
		
		<!--  Not empty report -->		
		<c:if test="${ roomDetailScheduleReportCompositeObj != null }">		
		
			<!-- Report Title -->
			<div class="row justify-content-center">
				<p class="h2">Room Detail & Schedule Report</p>
			</div>
			<div class="row justify-content-center">				
				<p class="h4">
					Room Number: ${roomDetailScheduleReportCompositeObj.roomObj.number} - 
					Date: <fmt:formatDate pattern="EEEE - MMM dd, yyyy" value = "${roomDetailScheduleReportCompositeObj.reportDate}" />
				</p>
			</div>
			
			<!-- Details -->
			<div class="row mt-2">

				<!-- Room Info -->
				<div class="col">
					<table class="table table-sm table-bordered">
						<thead class="thead-light">
							<tr><th colspan="2" scope="col" class="text-center">Room Information</th></tr>							
						</thead>
						<tbody>
							<tr><th scope="row">ID</th><td>${roomDetailScheduleReportCompositeObj.roomObj.roomID}</td></tr>
							<tr><th scope="row">Number</th><td>${roomDetailScheduleReportCompositeObj.roomObj.number}</td></tr>
							<tr><th scope="row">Floor</th><td>${roomDetailScheduleReportCompositeObj.roomObj.floor}</td></tr>
							<tr><th scope="row">Building</th><td>${roomDetailScheduleReportCompositeObj.roomObj.building}</td></tr>
							<tr><th scope="row">Capacity</th><td>${roomDetailScheduleReportCompositeObj.roomObj.capacity}</td></tr>						
							<tr><th scope="row">Type</th><td>${roomDetailScheduleReportCompositeObj.roomObj.roomType}</td></tr>
						</tbody>						
					</table> 
				</div>
				
				<!-- Base Calendar -->
				<div class="col">
					<table class="table table-sm table-bordered">
						<thead class="thead-light">
							<tr><th colspan="2" scope="col" class="text-center">Base Calendar</th></tr>							
						</thead>
						<tbody>
							<tr><th scope="row">ID</th><td>${roomDetailScheduleReportCompositeObj.baseCalendarObj.id}</td></tr>
							<tr><th scope="row">Name</th><td>${roomDetailScheduleReportCompositeObj.baseCalendarObj.name}</td></tr>
							<tr><th scope="row">Weekdays Start Time</th><td>${roomDetailScheduleReportCompositeObj.baseCalendarObj.weekdaysStartTime}</td></tr>
							<tr><th scope="row">Weekdays End Time</th><td>${roomDetailScheduleReportCompositeObj.baseCalendarObj.weekdaysEndTime}</td></tr>
							<tr><th scope="row">Weekend Start Time</th><td>${roomDetailScheduleReportCompositeObj.baseCalendarObj.weekenddaysStartTime}</td></tr>
							<tr><th scope="row">Weekend End Time</th><td>${roomDetailScheduleReportCompositeObj.baseCalendarObj.weekenddaysEndTime}</td></tr>							
						</tbody>						
					</table> 
				</div>

				<!-- Room Amenities -->
				<div class="col">
					<table class="table table-sm table-bordered">
						<thead class="thead-light">
							<tr><th colspan="2" scope="col" class="text-center">Room Amenities</th></tr>
							<tr><th scope="col" class="text-center">Type</th><th scope="col" class="text-center">Name</th></tr>
						</thead>
						<tbody>
							<c:forEach var="roomAmenityItem" items="${roomDetailScheduleReportCompositeObj.roomAmenitiesList}" varStatus="loop" >						
								<tr>
									<td>${roomAmenityItem.amenitie_type}</td>
									<td>${roomAmenityItem.name}</td>
								</tr>
							</c:forEach>
						</tbody>						
					</table> 
					
				</div>						
			</div>
			
			<!-- Schedule -->
			<div class="row mt-2">
				<table class="table table-bordered">
					<thead class="thead-light">
						<tr>
							<th colspan="5" scope="col" class="text-center">
								Room Schedule - 
								<fmt:formatDate pattern="E, yyyy/MM/dd" value = "${roomDetailScheduleReportCompositeObj.reportDate}" />
							</th>
						</tr>
						<tr>
							<th scope="col" class="text-center">Time</th>
							<th scope="col" class="text-center">Availability</th>
							<th scope="col" class="text-center">Reservation Title</th>
							<th scope="col" class="text-center">Reservation Type</th>
							<th scope="col" class="text-center">Reservation Attendees</th>													
						</tr>
					</thead>
					<tbody>
						<c:forEach var="timeSlotItem" items="${roomDetailScheduleReportCompositeObj.roomScheduleTimeSlotList}" varStatus="loop" >						
							<tr>
								<td class="text-center">
									<fmt:formatDate pattern="HH:mm" value = "${timeSlotItem.timeSlotStartDate}" />
								</td>
								<td class="text-center">${timeSlotItem.roomAvailability}</td>
								<td class="text-center">${timeSlotItem.reservationTitle}</td>
								<td class="text-center">${timeSlotItem.reservationType}</td>
								<td class="text-center">${timeSlotItem.reservationAttendees}</td>
							</tr>
						</c:forEach>
					</tbody>						
				</table>
			</div>
			
			<!-- Report Footer -->
			<div class="row mt-2 justify-content-end">
				<p class="font-weight-light"> Generated By Douglas Booking System (DBS) - Copyright &copy; 2020 XD Group</p>
			</div>			
		
		</c:if>
	
	</div>	
	
	<hr class="my-3"/>		
	
	<jsp:include page="/common/footer.jsp"></jsp:include>	

</body>
</html>