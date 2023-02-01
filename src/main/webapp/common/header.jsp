<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/xd.css">

<title>Douglas Booking System</title>

</head>

<body>
	<header>
		<div class="collapse bg-dark" id="navbarHeader">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-md-7 py-2">
						<h4 class="text-white">Douglas Booking System</h4>
						<p class="text-muted">The Douglas Room Reservation Hub</p>
					</div>
					<div class="col-sm-4 offset-md-1 py-2">
						<h4 class="text-white">Menu</h4>
						<ul class="list-unstyled">

							<li><a href="${pageContext.request.contextPath}/dbsHome"
								class="text-white">DBS Home Page</a></li>
							<li><a
								href="${pageContext.request.contextPath}/RoomMGMT/showAllRooms"
								class="text-white">Room Management</a></li>
							<li><a
								href="${pageContext.request.contextPath}/UserMGMT/showUsers"
								class="text-white">User Management</a></li>
							<li><a
								href="${pageContext.request.contextPath}/base_calendar/showAll"
								class="text-white">Base Calendar Management</a></li>
							<li><a
								href="${pageContext.request.contextPath}/showRoomAmenities/"
								class="text-white">Room Amenities</a></li>
							<li><a
								href="${pageContext.request.contextPath}/reservation/showAllReservations/"
								class="text-white">Reservations</a></li>	
							<li><a
								href="${pageContext.request.contextPath}/room_search/initialize/"
								class="text-white">Room Search</a></li>
							<li><a
								href="${pageContext.request.contextPath}/showRoomListSchedule/"
								class="text-white">Room Schedule</a></li>								
							<li><a
								href="${pageContext.request.contextPath}/room_det_sch_report/initialize/"
								class="text-white">Room Detail & Schedule Report</a></li>									
							<li><a href="${pageContext.request.contextPath}/SessionMGMT/ShowSessions" class="text-white">Session Management</a></li>	
						</ul>
					</div>
				</div>
			</div>
		</div>
	<div class="navbar navbar-dark bg-dark box-shadow">
		<div class="container float-left">
			<a href="${pageContext.request.contextPath}/dbsHome" class="navbar-brand d-flex align-items-center">
				<img class="float-left" src="${pageContext.request.contextPath}/static/img/douglas_logo.png" height="60px"/>
				<strong style="margin-left:10px" class ="text-black">Booking <br> System</strong>
			</a>

				<button class="navbar-toggler float-right" type="button"
					data-toggle="collapse" data-target="#navbarHeader"
					aria-controls="navbarHeader" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
			</div>
		</div>
	</header>