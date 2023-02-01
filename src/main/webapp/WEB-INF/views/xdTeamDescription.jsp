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


<title>CSIS 3275 Section 01 - Assignment 02 - XD Group</title>



</head>

<body>



	<jsp:include page="/common/header.jsp"></jsp:include>

	<div class="bg">
		<div class="container p-4">
			<div class="row">
				<h1 class="text-black font-weight-bold">XD Development Team</h1>
			</div>
		</div>

		<div class="container p-5">
			<div class="row align-items-center">
				<div class="col-md ">
					<img class="img-thumbnail" class="mx-auto"
						src="static/img/self_photo_rso_35.jpeg"
						alt="Ricardo Souza - self photo" />
					<p class="text-center text-white">Ricardo dos Santos Aleves de Souza</p>
				</div>
				<div class="col-md">
					<img class="img-thumbnail" class="mx-auto"
						src="static/img/Scott_sli_15.jpg" alt="Scott Linden - photo" />
					<p class="text-center text-white">Scott Linden</p>
				</div>
				<div class="col-md">
					<img class="img-thumbnail" class="mx-auto"
						src="static/img/ismaelimg.jpeg" alt="Ismael Moreno Photo" />
					<p class="text-center text-white">Ismael Moreno</p>
				</div>
				<div class="col-md">
					<img class="img-thumbnail" class="mx-auto"
						src="static/img/alex_pic.jpg" alt="Alex Cascabulho Photo" />
					<p class="text-center text-white">Alex Cascabulho</p>
				</div>
			</div>
		</div>
	</div>

	<section class="jumbotron text-center">
		<div class="container">
			<h1 class="jumbotron-heading text-white">XD Development Team</h1>
			<p class="lead text-white" id="groupDesc">"The XD Project Group was formed in the Fall of 2020. We were selected from a large pool of highly skilled software developers to created
				Douglas College's best development team. Our members come from 3 different countries with varied backgrounds and experiences. Our diverse and rich history 
				helps make us a better team than others. We operate with the latest and greatest technologies available to us and always look for the next generation technology
				to keep us on the bleeding edge!"</p>

		</div>
	</section>

	<jsp:include page="/common/footer.jsp"></jsp:include>

</body>

</html>