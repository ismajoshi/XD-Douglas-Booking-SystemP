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


	
		<div class="jumbotron hpJumbo">
			<div class="container">
				<h1 class="display-4 text-black hpText">
					<strong>Douglas Booking System</strong>
				</h1>
								
				<button type="button" class="btn btn-primary hpBtn" data-toggle="modal" data-target="#addRoom">
					Add Room
				</button>
				<div class="modal fade" id="addRoom" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  					<div class="modal-dialog modal-dialog-centered" role="document">
    					<div class="modal-content">
      						<div class="modal-header">
        						<h5 class="modal-title" id="exampleModalLongTitle">Add a Room!</h5>
        						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          							<span aria-hidden="true">&times;</span>
       		 					</button>
      						</div>
      						<div class="modal-body">
        						<form:form action="${pageContext.request.contextPath}/RoomMGMT/createNewRoom/"
											cssClass="form-horizontal" method="post" modelAttribute="room">
									<div class="form-group">
									
										<label for="roomId" class="col-md-3 controllabel">Number</label>
										<div class="col-md-9">
											<form:input path="number" cssClass="form-control" />
										</div>
				
										<label for="roomId" class="col-md-3 controllabel">Floor</label>
										<div class="col-md-9">
											<form:input path="floor" cssClass="form-control" />
										</div>
					
										<label for="roomId" class="col-md-3 controllabel">Building</label>
										<div class="col-md-9">
											<form:input path="building" cssClass="form-control" />
										</div>
				
										<label for="roomId" class="col-md-3 controllabel">Capacity</label>
										<div class="col-md-9">
											<form:input path="capacity" cssClass="form-control" />
										</div>
				
										<label for="roomId" class="col-md-3 controllabel">Room Type</label>
										<div class="col-md-9">
											<form:select path="roomType" cssClass="form-control">
												<option value="classroom">Classroom</option>
												<option value="laboratory">Laboratory</option>
												<option value="Office">Office</option>
											</form:select>
										</div>
										<label for="baseCalendar" class="col-md-3 controllabel">Base Calendar</label>
										<div class="col-md-9">
											<form:select path="baseCalendar" cssClass="form-control">
												<form:options items="${baseCalendarList}" />
											</form:select>
										</div>
										<div class="form-group">
											<!-- Button -->
											<div class="col-md-offset-3 col-md-9">
												<form:button class="btn btn-primary">Add Room</form:button>
											</div>
										</div>
									</div>
								</form:form>
      						</div>
      						<div class="modal-footer">
       							<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
     						</div>
    					</div>
  					</div>
				</div>
				
				<button type="button" class="btn btn-primary hpBtn" data-toggle="modal" data-target="#updateRoom">
					Update a Room
				</button>
				<div class="modal fade" id="updateRoom" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  					<div class="modal-dialog modal-dialog-centered" role="document">
    					<div class="modal-content">
      						<div class="modal-header">
        						<h5 class="modal-title" id="exampleModalLongTitle">Update a Room!</h5>
        						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          							<span aria-hidden="true">&times;</span>
       		 					</button>
      						</div>
      						<div class="modal-body">
        						<form:form action="${pageContext.request.contextPath}/RoomMGMT/updateRoom/"
											cssClass="form-horizontal" method="post" modelAttribute="room">
									<div class="form-group">
					
										<label for="roomId" class="col-md-3 controllabel">Room ID</label>
										<div class="col-md-9">
											<form:input path="roomID" cssClass="form-control" />
										</div>
										
										<label for="roomId" class="col-md-3 controllabel">Number</label>
										<div class="col-md-9">
											<form:input path="number" cssClass="form-control" />
										</div>
				
										<label for="roomId" class="col-md-3 controllabel">Floor</label>
										<div class="col-md-9">
											<form:input path="floor" cssClass="form-control" />
										</div>
					
										<label for="roomId" class="col-md-3 controllabel">Building</label>
										<div class="col-md-9">
											<form:input path="building" cssClass="form-control" />
										</div>
				
										<label for="roomId" class="col-md-3 controllabel">Capacity</label>
										<div class="col-md-9">
											<form:input path="capacity" cssClass="form-control" />
										</div>
				
										<label for="roomId" class="col-md-3 controllabel">Room Type</label>
										<div class="col-md-9">
											<form:select path="roomType" cssClass="form-control">
								<option value="classroom">Classroom</option>
								<option value="laboratory">Laboratory</option>
								<option value="Office">Office</option>
							</form:select>
										</div>
										
										<label for="roomId" class="col-md-3 controllabel">Base Calendar ID</label>
										<div class="col-md-9">
											<form:select path="baseCalendar" cssClass="form-control">
												<form:options items="${baseCalendarList}" />
											</form:select>
										</div>
										
										<div class="form-group">
											<!-- Button -->
											<div class="col-md-offset-3 col-md-9">
												<form:button class="btn btn-primary">Update Room</form:button>
											</div>
										</div>
									</div>
								</form:form>
      						</div>
      						<div class="modal-footer">
       							<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
     						</div>
    					</div>
  					</div>
				</div>
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
			<h3>List of all Rooms</h3>
			<table class="table  table-bordered table-hover">
				<thead class="thead-dark">
					<tr class="text-center">
						<td>Room ID</td>
						<td>Room Number</td>
						<td>Floor</td>
						<td>Building</td>
						<td>Capacity</td>
						<td>Room Type</td>
						<td>Base Calendar ID</td>
						<td>Update</td>
						<td>Delete</td>
					</tr>
				</thead>

				<c:forEach var="room" items="${allRoomsList}">
					<tr class="text-center">
						<td>${room.roomID}</td>
						<td>${room.number}</td>
						<td>${room.floor}</td>
						<td>${room.building}</td>
						<td>${room.capacity}</td>
						<td>${room.roomType}</td>
						<td>${room.baseCalendar}</td>
						<td><a href="${pageContext.request.contextPath}/RoomMGMT/update/?id=${room.roomID}">Update</a></td>
						<td><a href="${pageContext.request.contextPath}/RoomMGMT/delete/?id=${room.roomID}">Delete</a></td>
					</tr>
				</c:forEach>

				<form:form action="${pageContext.request.contextPath}/RoomMGMT/createNewRoom/"
					cssClass="form-horizontal" method="post" modelAttribute="room">
					<tr class="text-center">
						<td class="text-center">Room ID</td>
						<td><form:input path="number" cssClass="form-control" /></td>
						<td><form:input path="floor" cssClass="form-control" /></td>
						<td><form:input path="building" cssClass="form-control" /></td>
						<td><form:input path="capacity" cssClass="form-control" /></td>
						<td><form:select path="roomType" cssClass="form-control">
								<option value="classroom">Classroom</option>
								<option value="laboratory">Laboratory</option>
								<option value="Office">Office</option>
							</form:select></td>
						<td>
							<form:select path="baseCalendar" cssClass="form-control">
								<form:options items="${baseCalendarList}" />
							</form:select>
						</td>
						<td colspan="2"><form:button class="btn btn-primary">Add Room</form:button></td>
					</tr>
				</form:form>

			</table>
		</div>
	</div>
	
	<jsp:include page="/common/footer.jsp"></jsp:include>

</body>

</html>