<%--
    Document   : FastPassOptions
    Created on : Mar 4, 2023, 7:42:35 AM
    Author     : CWilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/css/default.css"%></style>
        <style><%@include file="/css/FastPassOptions.css"%></style>

        <link rel="icon" type="image/x-icon" href="images/favicon.png">
        <title>Fast Pass | Availability</title>
    </head>
    <header>
        <a  href="/FastPass"><img src="images/SWLogo.png" width="340" height="auto" /></a>
            <c:choose>
                <c:when test="${passenger.authenticated}">
                <form action="PassengerLogOut"  method="GET">
                    <input type="submit" value="Log Out" class="logOut">
                </form>
            </c:when>
            <c:otherwise >
                <div>
                    <a href="/FastPass/LogIn.jsp" class="SignIn">Log In</a>
                    <a href="/FastPass/SignUp.jsp" class="SignUp">Create Account</a>
                </div>
            </c:otherwise>
        </c:choose>

    </header>
    <main>
        <p class="user-message">${userMessage}</p>
        <h1>Fast Pass Availability</h1>
        <body>
            <div>
                <h2>&#9992 Depart: <span> ${flights.get(0).getDeparture().getAirport().getAirportName()}</span></h2>
                <h3>FastPass Status:  </h3>
                <p>Check Flight(s) Below For Details </p>
            </div>
            <table>
                <tr>
                    <th>Flight Number</th>
                    <th>Departure Time</th>
                    <th>Flight Destination</th>
                    <th>FastPass Availability</th>

                </tr>
                <c:forEach var="flight" items="${flights}">
                    <tr>
                        <td>SW${flight.flightNumber}</td>
                        <td>${flight.departure.displayTime}</td>
                        <td>${flight.arrival.airport.airportIataCode}</td>
                        <td>${passenger.authenticated ?   flight.flightPassAmountRemaining : flight.flightPassStatusText } FastPasses Available</td>

                        <td></td>

                    </tr>
                </c:forEach>
            </table>
    </main>
</body>
</html>
