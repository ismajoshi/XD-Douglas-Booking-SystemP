<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




	<jsp:include page="/common/header.jsp"></jsp:include>

			<c:if test="${ userSessionErrorMessage !=null }">
				<div class="alert alert-warning" id="warning" role="alert">${userSessionErrorMessage}</div>
			</c:if>

			<div class="jumbotron hpJumbo">
			
				<div class="container">
				<h1 class="display-4 text-black hpText"><strong>Douglas Booking System</strong></h1>
				<p class="text-black hpText h5">If you need to reserve a room or even just check
					what equipment it has, this is the place for you! Make an account,
					log in, view room schedules, reserve a room for use here.</p>			

				
				<hr class="my-4">	
					<h4 class="text-black hpText">Menu</h4>
					<div class="row p-3 align-items-center">
						<div class="col">
							<a href="${pageContext.request.contextPath}/dbsHome" class="btn btn-primary hpBtn">DBS Home Page</a>
						</div>
						<div class="col">
							<a href="${pageContext.request.contextPath}/RoomMGMT/showAllRooms" class="btn btn-primary hpBtn">Room Management</a>
						</div>
						<div class="col">	
							<a href="${pageContext.request.contextPath}/UserMGMT/showUsers" class="btn btn-primary hpBtn">User Management</a>
						</div>
					</div>
					<div class="row p-3 align-items-center">
						<div class="col">	
							<a href="${pageContext.request.contextPath}/base_calendar/showAll" class="btn btn-primary hpBtn">Calendar Management</a>
						</div>
						<div class="col">	
							<a href="${pageContext.request.contextPath}/showRoomAmenities/" class="btn btn-primary hpBtn">Room Amenities</a>
						</div>
						<div class="col">	
							<a href="${pageContext.request.contextPath}/reservation/showAllReservations/" class="btn btn-primary hpBtn">Reservations</a>
						</div>
					</div>

					<div class="row p-3 align-items-center">
						<div class="col">
							<a href="${pageContext.request.contextPath}/room_search/initialize/" class="btn btn-primary hpBtn">Room Search</a>
						</div>
						<div class="col">
							<a href="${pageContext.request.contextPath}/showRoomListSchedule/" class="btn btn-primary hpBtn">Room Schedule</a>
						</div>
						<div class="col">
							<a href="${pageContext.request.contextPath}/room_det_sch_report/initialize/" class="btn btn-primary hpBtn">Room Detail & Schedule Report</a>
						</div>						
						
					</div>
					<div class="row p-3 align-items-center">
						<div class="col">
							
						</div>
						<div class="col">
							<a href="${pageContext.request.contextPath}/SessionMGMT/ShowSessions" class="btn btn-primary hpBtn">Session Management</a>
						</div>
						<div class="col">
							
						</div>						
						
					</div>
				
			</div>
	</div>
<%-- 
	<div class="p-5">
		<div class="container col-md-8 col-md-offset-3 "
			style="overflow: auto">
			<h1>Login</h1>
			<form action="<%=request.getContextPath()%>/login" method="post">

				<div class="form-group">
					<label for="username">User Name:</label> <input type="text"
						class="form-control" id="username" placeholder="User Name"
						name="username" required>
				</div>

				<div class="form-group">
					<label for="password">Password:</label> <input type="password"
						class="form-control" id="password" placeholder="Password"
						name="password" required>
				</div>


				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
	</div>
	
	--%>
	
	<div class="p-5">
		<div class="container col-md-8 col-md-offset-3 "
			style="overflow: auto">
			<c:if test="${lsuccess !=null }">
			<h1>${lsuccess}</h1>
		</c:if>
		</div>
	</div>

	<jsp:include page="/common/footer.jsp"></jsp:include>

