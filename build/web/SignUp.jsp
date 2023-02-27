
<!-- author: Casey -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <style><%@include file="/css/SignUp.css"%></style>
  <link rel="icon" type="image/x-icon" href="images/favicon.png">
  <title>Fast Pass | Sign Up</title>
</head>
<body>
	<header>	
		<a  href="/FastPass"><img src="images/SWLogo.png" width="340" height="auto" /></a>
	</header>
	<h1>Fast Pass Sign Up</h1>

	<main>
	<p class="user-message">${userMessage}</p>

	<form action="PassengerSignUp" method="post">

		<div class="form-row">
			<div>
				<label>First Name</label> <input type="text"
					name="passengerFirstName" id="passengerEmail"  value="${passenger.firstName}">
			</div>


			<div>
				<label>Middle Name</label> <input type="text"
					name="passengerMiddleName" id="passengerEmail" value="${passenger.middleName}">
			</div>


			<div>
				<label>Last Name</label> <input type="text" name="passengerLastName"
					id="passengerLastName" value="${passenger.lastName}">
			</div>

		</div>

		<div class="form-row">

			<div>
				<label>Email</label> <input type="text" name="passengerEmail"
					id="passengerEmail" value="${passenger.email}">
			</div>


			<div>
				<label>Password</label> <input type="password"
					name="passengerPassword" id="passengerPassword">
			</div>

			<div>
				<label>Retype Password</label> <input type="password"
					name="passengerPasswordRETYPE" id="passengerPasswordRETYPE">
			</div>

		</div>

		<div class="form-row">
			<div>
				<label>DOB</label> <input type="text" name="passengerDob"
					id="passengerDob" placeholder="YYYY/MM/DD" value="${passenger.displayDob}">
			</div>
		</div>
		<div class="form-row">
			<input type="submit" value="Sign Up">
			<a href="/FastPass/LogIn.jsp">Log In</a>
			
		</div>


	</form>
	</main>
</body>
</html>