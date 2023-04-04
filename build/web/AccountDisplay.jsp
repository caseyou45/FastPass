<%--
    Document   : AccountInfo
    Created on : Mar 13, 2023, 4:38:28 PM
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
        <title>Fast Pass | Account </title>
    </head>
    <body>
        <header>
            <a href="/"><img src="images/SWLogo.png" width="340" height="auto" /></a>
                <c:choose>
                    <c:when test="${passenger.authenticated}">
                    <div>
                        <a  href="AccountInfo?accountNumber=${passenger.accountNumber}" class="account">Account</a>
                        <a  href="PassengerLogOut" class="logout">Logout</a>
                    </div>
                </c:when>
                <c:otherwise >
                    <div>
                        <a href="LogIn.jsp" class="login">Log In</a>
                        <a href="SignUp.jsp" class="signup">Create Account</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </header>
        <main>
            <p class="user-message">${userMessage}</p>
            <h1>Fast Passes for ${passenger.firstName}</h1>
            <div>
                <p>Check Flight(s) Below For Details </p>
            </div>
            <table>
                <tr>
                    <th>FastPass Number</th>
                    <th>Flight Number</th>
                    <th>Flight Departure Time</th>
                    <th>Flight Destination</th>
                    <th>Action</th>

                </tr>
                <c:forEach var="flightFPTicketDTO" items="${flightFPTicketDTOs}">

                    <tr>
                        <td>${flightFPTicketDTO.fastPassVerificationNumber}</td>
                        <td>${flightFPTicketDTO.flight.flightNumber}</td>
                        <td>${flightFPTicketDTO.flight.departure.displayTime}</td>
                        <td>${flightFPTicketDTO.flight.arrival.airport.airportCity}</td>

                        <td>
                            <a href="DisplayPass.jsp" class="DisplayPass">Display Pass</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </main>
    <body>
    </body>
</html>
