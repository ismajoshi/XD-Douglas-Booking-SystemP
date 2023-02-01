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
				<p class="lead text-black">User Management</p>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			
			<h1>Edit User</h1>
			<form:form
				action="${pageContext.request.contextPath}/UserMGMT/EditUser/"
				class="form-horizontal" method="post" modelAttribute="user">
				<div class="form-group">
				<label style="display: inline-block;">id:</label>
						<form:input style="display: inline-block;" path="id" class="form-control" type="text" readonly="true"/>
					<label style="display: inline-block;">First Name:</label>
						<form:input style="display: inline-block;" path="fName" class="form-control" type="text" placeholder="First Name"/>
					<label style="display: inline-block;">Last Name:</label>
						<form:input style="display: inline-block;" path="LName" class="form-control" type="text" placeholder="Last Name"/>
					<label style="display: inline-block;">email:</label>
						<form:input style="display: inline-block;" path="email" class="form-control" type="email" placeholder="mail@mail.com"/>
					<label>Phone Number:</label>
						<form:input path="phone" class="form-control" type="tel" placeholder="###-###-###"/>
					<label>New Password:</label>
						<form:input path="password" class="form-control" type="password" placeholder="**********"/>
                    <br>
					<label  class="col-md-3 controllabel">Choose a type:</label>
					<form:select path="type">
					<form:option value="admin">Admin</form:option>
					<form:option value="professor">Professor</form:option>
					<form:option value="student">Student</form:option>
					</form:select>
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button Class="btn btn-primary" type="submit">Save Changes</button>
						</div>

				</div>

			</form:form>

		</div>
	</div>





	<jsp:include page="/common/footer.jsp"></jsp:include>

</body>

</html>