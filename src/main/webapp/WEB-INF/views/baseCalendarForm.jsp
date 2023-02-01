<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 
	View Page that presents a form to create a new BaseCalendar record or edit an existing one.

	@author Ricardo dos Santos Alves de Souza
 -->

<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Base Calendar</title>
	<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/static/js/jquery-3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/xd.css">
	<link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/static/img/favicon_io/apple-touch-icon.png">
	<link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/static/img/favicon_io/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/img/favicon_io/favicon-16x16.png">
	<link rel="manifest" href="${pageContext.request.contextPath}/static/img/favicon_io/site.webmanifest">
	
	<!-- jQuery Timepicker -->	
	<link href="${pageContext.request.contextPath}/static/css/jquery.timepicker.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/static/js/jquery.timepicker.min.js"></script>
	
	<!-- base_calendar_mngmt_rso_35 JavaScript Validation functions -->
	<script src="${pageContext.request.contextPath}/static/js/base_calendar_mngmt_rso_35.js" ></script>	
		
</head>

<body>

	<jsp:include page="/common/header.jsp"></jsp:include>
	
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>${headerMessage}</h1>
			</div>
		</div>

		<div class="row">
			<div class="col">
				
				<form:form action="${pageContext.request.contextPath}/base_calendar/${crudFunction}/" method="post" modelAttribute="baseCalendarObj">
				
					<form:hidden path="id" />
					
					<div class="form-group">
						<form:label path="name" for="name" cssClass="control-label">Name</form:label>
						<form:input path="name" cssClass="form-control" required="true" placeholder="Enter a name here" />
						<form:errors path="name" style="color:red" />
					</div>
					
					<div class="form-row" >			
						<div class="form-group col-md-4">
							<form:label path="weekdaysStartTime" for="weekdaysStartTime" cssClass="control-label" >Weekdays Start Time</form:label>
							<form:input type="time" path="weekdaysStartTime" cssClass="form-control timepicker weekdaysTime" required="true" />
							<form:errors path="weekdaysStartTime" style="color:red" />
						</div>
						
						<div class="form-group col-md-4">
							<form:label path="weekdaysEndTime" for="weekdaysEndTime" cssClass="control-label">Weekdays End Time</form:label>
							<form:input type="time" path="weekdaysEndTime" cssClass="form-control timepicker weekdaysTime" required="true" />
							<form:errors path="weekdaysEndTime" style="color:red" />
						</div>					
					</div>	
					
					<div class="form-row">			
						<div class="form-group col-md-4">
							<form:label path="weekenddaysStartTime" for="weekenddaysStartTime" cssClass="control-label">Weekend Start Time</form:label>
							<form:input type="time" path="weekenddaysStartTime" cssClass="form-control timepicker weekenddaysTime" required="true" />
							<form:errors path="weekenddaysStartTime" style="color:red" />
						</div>
						
						<div class="form-group col-md-4">
							<form:label path="weekenddaysEndTime" for="weekenddaysEndTime" cssClass="control-label">Weekend End Time</form:label>
							<form:input type="time" path="weekenddaysEndTime" cssClass="form-control timepicker weekenddaysTime" required="true" />
							<form:errors path="weekenddaysEndTime" style="color:red" />
						</div>					
					</div>	
		
					<div class="form-group">
						<form:label path="notes" for="notes" cssClass="control-label">Notes</form:label>
						<form:textarea path="notes" cssClass="form-control" placeholder="Write here some notes..." />
						<form:errors path="notes" style="color:red" />
					</div>
					
					<div class="form-row text-center mt-2 mb-4">
						<div class="col">
							<form:button class="btn btn-primary">Save</form:button>
						</div>
						<div class="col">	
							<a href="${pageContext.request.contextPath}/base_calendar/showAll/" class="btn btn-primary" role="button">Cancel</a>
						</div>
					</div>						
								
				</form:form>
			</div>
		</div>
		
		<!-- Unavailable Day -->
		<div class="row">
			<div class="col">
				<h3>Unavailable Days</h3>
			</div>
		</div>

		<div class="row">
			<div class="col">
		
				<!-- Unavailable Days List -->
				<table class="table table-striped table-bordered">
		
					<tr class="text-center">
						<td>Id</td>
						<td>Name</td>
						<td>Date</td>
						<td>Unavailability- Start Time</td>
						<td>Unavailability - End Time</td>
						<td>Notes</td>													
						<td>Delete</td>
					</tr>
					<c:forEach var="unavailableDay" items="${baseCalendarUnavailableDayList}">
						<tr>
							<td class="text-center">${unavailableDay.id}</td>
							<td>${unavailableDay.name}</td>
							<td class="text-center"><fmt:formatDate type = "time" pattern="MM/dd/YYYY" value="${unavailableDay.date}" /></td>
							<td class="text-center"><fmt:formatDate type = "time" pattern="HH:mm" value="${unavailableDay.unavailableStartTime}" /></td>
							<td class="text-center"><fmt:formatDate type = "time" pattern="HH:mm" value="${unavailableDay.unavailableEndTime}" /></td>
							<td>${unavailableDay.notes}</td>															
							<td><A HREF="${pageContext.request.contextPath}/base_calendar/removeUnavailableDay/?id=${unavailableDay.id}&baseCalendarId=${unavailableDay.baseCalendarId}">Delete</A></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<div class="row justify-content-center">
			<div class="col-md-6">		
				<!-- Unavailable Days Form -->
				<form:form action="${pageContext.request.contextPath}/base_calendar/addUnavailableDay/" method="post" modelAttribute="baseCalendarUnavailableDayObj">

					<!--  Check if it is a new Base Calendar creation. If so, disable this form -->
					<c:set var = "disableUnavailableForm" value = ""/>
					<c:if test="${ crudFunction == 'create' }" >
						<c:set var = "disableUnavailableForm" value = "disabled"/>
					</c:if>							
				
					<fieldset id="unavailableDay" class="border border-primary rounded p-4" ${disableUnavailableForm} >
						<legend>Add Unavailable Day</legend>
					
						<form:hidden path="id" />
						<form:hidden path="baseCalendarId" />
	
						<div class="form-row">					
							<div class="form-group col-sm">
								<form:label path="name" for="name" cssClass="control-label">Name</form:label>
								<form:input path="name" cssClass="form-control" required="true" placeholder="Enter a name here" />
								<form:errors path="name" style="color:red" />
							</div>
						</div>
	
						<div class="form-row">					
							<div class="form-group col-sm">
								<form:label path="date" for="date" cssClass="control-label">Date</form:label>
								<form:input type="date" path="date" cssClass="form-control" required="true" />
								<form:errors path="date" style="color:red" />
							</div>
						</div>					
						
						<div class="form-row">			
							<div class="form-group col-sm">
								<form:label path="unavailableStartTime" for="unavailableStartTime" cssClass="control-label">Unavailability- Start Time</form:label>
								<form:input type="time" path="unavailableStartTime" cssClass="form-control timepicker unavailableTime" required="true" />
								<form:errors path="unavailableStartTime" style="color:red" />
							</div>
							
							<div class="form-group col-sm">
								<form:label path="unavailableEndTime" for="unavailableEndTime" cssClass="control-label">Unavailability- End Time</form:label>
								<form:input type="time" path="unavailableEndTime" cssClass="form-control timepicker unavailableTime" required="true" />
								<form:errors path="unavailableEndTime" style="color:red" />
							</div>					
						</div>	
	
						<div class="form-row">					
							<div class="form-group col-sm">
								<form:label path="notes" for="notes" cssClass="control-label">Notes</form:label>
								<form:textarea path="notes" cssClass="form-control" placeholder="Write some notes here..." />
								<form:errors path="notes" style="color:red" />
							</div>
						</div>					
	
						<div class="form-row text-center mt-2" >
							<div class="col">				
								<form:button class="btn btn-primary">Add</form:button>
							</div>
						</div>
	
					</fieldset>					
				</form:form>
			</div>
		</div>
	</div>
	<div id="Trigger">Trigger</div>
	
	<!-- Timepicker JS call -->
	<script>$('input.timepicker').timepicker({timeFormat: 'HH:mm', interval: 30, startTime: '06:00', dynamic: false});</script>

	<!-- Check Start/End times JS call -->	
	<!--
	<script>
		$('input.weekdaysTime').change(checkWeekdaysTimes()).change();
		$('input.weekenddaysTime').change(checkWeekenddaysTime()).change();
		$('input.unavailableTime').change(checkUnavailableTime()).change();
	</script>
	 -->	
	
	<jsp:include page="/common/footer.jsp"></jsp:include>	

</body>
</html>