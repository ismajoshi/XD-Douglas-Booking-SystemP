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
<link rel="apple-touch-icon" sizes="180x180"
	href="/static/img/favicon_io/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="/static/img/favicon_io/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="/static/img/favicon_io/favicon-16x16.png">
<link rel="manifest" href="/static/img/favicon_io/site.webmanifest">


<title>Douglas Booking System</title>

</head>

<body>

	<jsp:include page="/common/header.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<h1>${headerMessage}</h1>

			<hr />
			<c:if test="${ message != null }">
				<div class="alert alert-success" role="alert">${message}</div>
			</c:if>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			
			
			<div class="dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			    	Pick a Room
			  	</button>
			  	<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			  		<form:form action="${pageContext.request.contextPath}/showRoomListSchedule" method="post">
			    		<button class="dropdown-item" >All Rooms</button></form:form>
			  		<c:forEach var="roomID" items="${IDroomsList}">
			  			<form:form action="${pageContext.request.contextPath}/displayRoomReservation/?id=${roomID}" method="post">
			    			<button class="dropdown-item" >${roomID}</button></form:form>
			    	</c:forEach>
			  	</div>
			</div>
		</div>
	</div>
	
	
	<div class="container">
		<div class="row">
			<table class="table table-striped table-bordered">
	
				<tr>
					<td>Reservation ID</td>
					<td>Reservation Title</td>
					<td>Expected Attendees</td>
					<td>Room Reservation</td>
					<td>Start date time</td>
					<td>End date time</td>
					<td>Status</td>
					<td>User ID</td>
					<td>Room ID</td>
				</tr>
				<c:forEach var="roomListSchedule" items="${roomsList}">
					<tr>
						<td>${roomListSchedule.reservationID}</td>
						<td>${roomListSchedule.reservation_title}</td>
						<td>${roomListSchedule.expected_attendees}</td>
						<td>${roomListSchedule.reservation_type}</td>
						<td>${roomListSchedule.reservation_dateTime}</td>
						<td>${roomListSchedule.reservation_end_DateTimeDate}</td>
						<td>${roomListSchedule.status}</td>
						<td>${roomListSchedule.userID}</td>
						<td>${roomListSchedule.roomID}</td>
						
					</tr>
				</c:forEach>
			</table>

		
		</div>
	</div>


	<jsp:include page="/common/footer.jsp"></jsp:include>

</body>

</html>