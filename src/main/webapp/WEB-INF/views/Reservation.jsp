<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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


	
		<div class="jumbotron hpJumbo">
			<div class="container">
				<h1 class="display-4 text-black hpText">
					<strong>Douglas Booking System</strong>
				</h1>
								
				<a href="${pageContext.request.contextPath}/reservation/new/">
					<button type="button" class="btn btn-primary hpBtn">
						Add Reservation
					</button>
				</a>
				
					
			</div>
		</div>
		
	<div class="container">
		<c:if test="${success !=null }">
			<div class="alert alert-success" role="alert">${success}</div>
		</c:if>
		<c:if test="${error !=null }">
			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
	</div>

	<div class="container">
		<div class="row">
			<h3>List of all Reservations</h3>
			<table class="table  table-bordered table-hover">
				<thead class="thead-dark">
					<tr class="text-center">
						<td>ID</td>
						<td>Title</td>
						<td>Number of Atteendees</td>
						<td>Type</td>
						<td>Start Time</td>
						<td>End Time</td>
						<td>Status</td>
						<td>User ID</td>
						<td>Room ID</td>
						<td>Update</td>
						<td>Delete</td>
					</tr>
				</thead>

				<c:forEach var="reservation" items="${allReservations}">
					<tr class="text-center">
						<td>${reservation.reservationID }</td>
						<td>${reservation.title}</td>
						<td>${reservation.ateendees}</td>
						<td>${reservation.type}</td>
						
						<td class="text-center"><fmt:formatDate type = "date" pattern="yyyy-MM-dd HH:mm" value="${reservation.start}" /></td>
						<td class="text-center"><fmt:formatDate type = "date" pattern="yyyy-MM-dd HH:mm" value="${reservation.end}" /></td>
						<td>${reservation.status}</td>
						<td>${reservation.userID}</td>
						<td>${reservation.roomID}</td>
						<td><a href="${pageContext.request.contextPath}/reservation/edit/?id=${reservation.reservationID}">Update</a></td>
						<td><a href="${pageContext.request.contextPath}/reservation/delete/?id=${reservation.reservationID}">Delete</a></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="form-group col-md-4"></div>
			<div class="form-group col-md-4">
				<a href="${pageContext.request.contextPath}/reservation/new/">
					<button type="button" class="btn btn-primary hpBtn">
						Add Reservation
					</button>
				</a>
			</div>
			<div class="form-group col-md-4"></div>
		</div>
	</div>
	
	<jsp:include page="/common/footer.jsp"></jsp:include>
	
	

</body>

</html>