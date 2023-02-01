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
			</div>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="col">
					<h1>${headerMessage}</h1>
				</div>
			</div>
	
			<div class="row">
				<div class="col">
				<form:form action="${pageContext.request.contextPath}/reservation/${crudFunction}/"
					cssClass="form-horizontal" method="post" modelAttribute="reservation">
					
					
						<form:hidden path="reservationID" />
						<div class="form-row" >			
							<div class="form-group col-md-4">
								<form:label path="title" for="title" cssClass="control-label">Title</form:label>
								<form:input path="title" cssClass="form-control" />
							</div>
							<div class="form-group col-md-4">
								<form:label path="ateendees" for="ateendees" cssClass="control-label">Number of Ateendees</form:label>
								<form:input path="ateendees" cssClass="form-control" />
							</div>
							<div class="form-group col-md-4">
								<form:label path="type" for="type" cssClass="control-label">Type of Reservation</form:label>
								<form:select path="type" cssClass="form-control" required="true" multiple="false">
									<form:options items="${reservationTypeList}"/>
								</form:select>
							</div>
						</div>
						
						<div class="form-row">			
							<div class="form-group col-md-6">
								<form:label path="start" for="start" cssClass="control-label">Start Time and Date</form:label>
								<form:input type="datetime-local" path="start" cssClass="form-control" required="true" />
							</div>
						
							<div class="form-group col-md-6">
								<form:label path="end" for="end" cssClass="control-label">End Time and Date</form:label>
								<form:input type="datetime-local" path="end" cssClass="form-control" required="true" />
							</div>
						</div>
						
						<div class="form-row" >			
							<div class="form-group col-md-4">
								<form:label path="status" for="status" cssClass="control-label">Status</form:label>
								<form:select path="status" cssClass="form-control" required="true" multiple="false">
									<form:options items="${statusList}"/>
								</form:select>
							</div>
							<div class="form-group col-md-4">
								<form:label path="userID" for="userID" cssClass="control-label">User ID</form:label>
								<form:select path="userID" cssClass="form-control" required="true" multiple="false">
									<form:options items="${users}" />
								</form:select>
							</div>
							<div class="form-group col-md-4">
								<form:label path="roomID" for="roomID" cssClass="control-label">Room ID</form:label>
								<form:select path="roomID" cssClass="form-control" required="true" multiple="false">
									<form:options items="${rooms}" itemValue="roomID" itemLabel="number"/>
								</form:select>
							</div>
						</div>
						
						<div class="form-row">
							<div class="form-group col-md-4"></div>
							<div class="form-group col-md-4"><form:button class="btn btn-primary">${headerMessage}</form:button></div>
							<div class="form-group col-md-4"></div>
						</div>
					
				</form:form>
</div>
			</div>
		</div>
	
	
	
	<jsp:include page="/common/footer.jsp"></jsp:include>
	
	

</body>

</html>