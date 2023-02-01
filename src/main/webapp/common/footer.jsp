<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<footer class="bg-secondary">
	<div class="container p-5">
		<p class="float-right">
			<a href="#">Top</a>
		</p>

		<img class="float-left"
			src="<c:url value="/static/img/douglas_logo.png" />" height="60px"
			style="margin-right: 10px" /> <strong class="text-black"
			style="margin-left: 10px">Booking</strong><br> <strong
			class="text-black" style="margin-left: 10px">System</strong> <br>
		<br>

		<div class="row">
			<div class="col">
				<p class="text-center">CSIS 3275 001</p>
			</div>
			<div class="col">
				<p class="text-center">
					<a href="<c:url value="/xdTeamDescription"/>" class="text-black">XD
						Development Team</a>
				</p>
			</div>
			<div class="col">
				<p class="text-center">
					<a href="https://github.com/skoden/XD-Douglas-Booking-System/wiki/0.-Home"
						class="text-black">Git Hub Wiki</a>
				</p>
			</div>
			<div class="col">
				<p class="text-center">
					<a href="https://www.youtube.com/watch?v=M6b_wvbfgU0" class="text-black">Iteration #1 Video</a>
				</p>
			</div>
			
			<div class="col">
				<p class="text-center">
					<a href="${pageContext.request.contextPath}/login/out" class="text-black">logout</a>
				</p>
			</div>
		</div>
	</div>
</footer>




<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

</body>

</html>