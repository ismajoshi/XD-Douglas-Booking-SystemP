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
			<div class="container">
				<c:if test="${success !=null }">
					<div class="alert alert-success" role="alert">${success}</div>
				</c:if>
				<c:if test="${error !=null }">
					<div class="alert alert-danger" role="alert">${error}</div>
				</c:if>
			</div>

		</div>

	</div>
	<jsp:include page="/common/footer.jsp"></jsp:include>
	
	

</body>

</html>