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
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/xd.css">



<title>Douglas Booking System</title>

</head>

<body>

	<jsp:include page="/common/header.jsp"></jsp:include>


	<div class="container">
		<div class="row">
			<div class="jumbotron">
				<h1 class="display-4 text-black">
					<strong>Douglas Booking System</strong>
				</h1>
				<p class="lead text-black">User Session Management</p>
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
			<table class="table table-stripped table-bordered">
				<tr>
					<td>Table Session ID</td>
					<td>Session_ID</td>
					<td>StartDateTime</td>
					<td>End Date Time</td>
					<td>STATUS</td>
					<td>USER ID</td>
					
					<td style="background-color:#B2BABB"></td>
					

				</tr>

				<c:forEach var="session" items="${SessionList}">
					<tr>
						<td>${session.sessionid}</td>
						<td>${session.session_code}</td>
						<td>${session.start_datetime}</td>
						<td>${session.end_datetime}</td>
						<td>${session.status}</td>
						<td>${session.userid}</td>
						
						<td> <c:if test="${session.status =='active' }">
			<a href="<c:url value="/SessionMGMT/InvalidateSession/?id=${session.sessionid}"/>">Invalidate</a>
		</c:if></td>

					</tr>
				</c:forEach>


			</table>
			

		</div>
	</div>





	<jsp:include page="/common/footer.jsp"></jsp:include>

</body>

</html>