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



<title>Login</title>

</head>


	<jsp:include page="/common/header.jsp"></jsp:include>

		<c:if test="${ lerror !=null }">
			<div class="alert alert-warning" id="warning" role="alert">${lerror}</div>
		</c:if>


			<div class="jumbotron hpJumbo">
			
				<div class="container">
				<h1 class="display-4 text-black hpText"><strong>Douglas Booking System</strong></h1>
				<p class="text-black hpText h5">If you need to reserve a room or even just check
					what equipment it has, this is the place for you! Make an account,
					log in, view room schedules, reserve a room for use here.</p>			
				
				<hr class="my-4">	
				
				
				
			</div>
	</div>
 
	<div class="p-5">
		<div class="container col-md-8 col-md-offset-3 "
			style="overflow: auto">
			<h1>Login</h1>
			<form:form action="${pageContext.request.contextPath}/login" method="post" modelAttribute="login">

				<div class="form-group">
					<label>Email:</label> 
					<form:input style="display: inline-block;" path="lmail" class="form-control" type="email" placeholder="mail@mail.com" required="true"/>
				</div>

				<div class="form-group">
					<label>Password:</label>
					<form:input path="lpassword" class="form-control" type="password" placeholder="**********" required="true"/>
				</div>


				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>
		</div>
	</div>
	
	

	<jsp:include page="/common/footer.jsp"></jsp:include>

</html>