<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 
	View Page to show all the Base Calendar records in the system and provide link to create, modify, or delete rows.

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
</head>

<body>

	<jsp:include page="/common/header.jsp"></jsp:include>

	<div class="container">

		<h1>Base Calendar</h1>

		<hr />
		<c:if test="${ successMessage !=null }">
			<div class="alert alert-success" role="alert">${successMessage}</div>
		</c:if>
		<c:if test="${ errorMessage !=null }">
			<div class="alert alert-danger" role="alert">${errorMessage}</div>
		</c:if>		
		
		
		<table class="table table-striped table-bordered">

			<tr class="text-center">
				<td>Id</td>
				<td>Name</td>
				<td>Week Days - Start Time</td>
				<td>Week Days - End Time</td>
				<td>Weekend Days - Start Time</td>
				<td>Weekend Days - End Time</td>
				<td>Notes</td>													
				<td>Edit</td>
				<td>Delete</td>
			</tr>
			<c:forEach var="base_calendar" items="${baseCalendarList}">
				<tr>
					<td class="text-center">${base_calendar.id}</td>
					<td>${base_calendar.name}</td>
					<td class="text-center"><fmt:formatDate type = "time" pattern="HH:mm" value="${base_calendar.weekdaysStartTime}" /></td>
					<td class="text-center"><fmt:formatDate type = "time" pattern="HH:mm" value="${base_calendar.weekdaysEndTime}" /></td>
					<td class="text-center"><fmt:formatDate type = "time" pattern="HH:mm" value="${base_calendar.weekenddaysStartTime}" /></td>
					<td class="text-center"><fmt:formatDate type = "time" pattern="HH:mm" value="${base_calendar.weekenddaysEndTime}" /></td>															
					<td>${base_calendar.notes}</td>															
					<td><A HREF="${pageContext.request.contextPath}/base_calendar/edit/?id=${base_calendar.id}">Edit</A></td>					
					<td><A HREF="${pageContext.request.contextPath}/base_calendar/delete/?id=${base_calendar.id}">Delete</A></td>
				</tr>
			</c:forEach>

		</table>
		
		<div class="text-center">
			<a href="${pageContext.request.contextPath}/base_calendar/new" class="btn btn-primary mb-3" role="button">Create New Base Calendar</a>
		</div>			

	</div>
	
	<jsp:include page="/common/footer.jsp"></jsp:include>
		
</body>
</html>
