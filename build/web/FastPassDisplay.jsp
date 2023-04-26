<%--
    Document   : FastPassDisplay
    Created on : Apr 7, 2023, 12:25:47 PM
    Author     : CWilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/css/default.css"%></style>
        <style><%@include file="/css/FastPass.css"%></style>

        <title>FastPass</title>
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
        <section>
            <div class="fastpass">
                <h1>Southwest FastPass</h1>
                <div class="cities">
                    <span>${flightFPTicketDTO.flight.departure.airport.airportCity}</span>
                    <span>${flightFPTicketDTO.flight.arrival.airport.airportCity}</span>
                </div>

                <div class="airports">
                    <span>${flightFPTicketDTO.flight.departure.airport.airportIataCode}</span>
                    <span>&#9992;</span>
                    <span>${flightFPTicketDTO.flight.arrival.airport.airportIataCode}</span>
                </div>

                <div class="passenger">
                    <span>Passenger</span>
                    <br>
                    <span>${passenger.fullName}</span>
                </div>

                <div class="qr">
                    <img src="data:image/png;base64,${imageAsBase64}"/>
                </div>
            </div>
        </section>
    </body>
</html>
