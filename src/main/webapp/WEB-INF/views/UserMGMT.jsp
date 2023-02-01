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
					<td>User ID</td>
					<td>First Name</td>
					<td>Last Name</td>
					<td>Email</td>
					<td>Phone Number</td>
					<td>Password</td>
					<td>User Type</td>
					<td>Registration Date</td>
					<td>Status</td>
					<td style="background-color:#B2BABB"></td>
					<td style="background-color:#B2BABB"></td>

				</tr>

				<c:forEach var="user" items="${UsersList}">
					<tr>
						<td>${user.id}</td>
						<td>${user.fName}</td>
						<td>${user.LName}</td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
						<td>${user.password}</td>
						<td>${user.type}</td>
						<td>${user.regDate}</td>
						<td>${user.status}</td>
						<td><a
							href="<c:url value="/UserMGMT/deleteUser/?id=${user.id}"/>">Delete</a></td>
							<td><a
							href="<c:url value="/UserMGMT/EditUser/?id=${user.id}"/>">Edit</a></td>

					</tr>
				</c:forEach>


			</table>
			<h1>Create New user</h1>
			<form:form
				action="${pageContext.request.contextPath}/UserMGMT/createNewUser/"
				class="form-horizontal" method="post" modelAttribute="user">
				<div class="form-group">
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
							<button Class="btn btn-primary" type="submit">Add New User</button>
						</div>

				</div>

			</form:form>

		</div>
	</div>





	<jsp:include page="/common/footer.jsp"></jsp:include>

</body>

</html>