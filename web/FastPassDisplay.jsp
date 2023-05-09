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
        <style><%@include file="/css/header.css"%></style>

        <title>FastPass</title>
    </head>
    <body>
        <header>
            <c:choose>
                <c:when test="${passenger.authenticated}">
                    <div class="header_useracc">
                        <a href="PassengerLogOut" class="CreateAccount">Logout</a>
                        <form action="AccountInfo?accountNumber=${passenger.accountNumber}" method="post">
                            <button type="submit" class="LogInEnroll" value="account">
                                <img src="images/login_icon.png" height="15" width="auto">
                                <span> Account</span>
                            </button>
                        </form>
                    </div>
                </c:when>
                <c:otherwise >
                    <div class="header_useracc">
                        <!--these have to be in reverse order i dont really know why but it works - john -->
                        <a href='SignUp.jsp' class="CreateAccount">Create account</a>
                        <form action="LogIn.jsp" method="post">
                            <button type="submit" class="LogInEnroll" value="logIn">
                                <img src="images/login_icon.png" height="15" width="auto">
                                <span> Log In</span>
                            </button>
                        </form>
                        <span class="LogInTip">You haven't escaped, you know.</span>
                    </div>
                </c:otherwise>
            </c:choose>
            <a href='mainpage.jsp'><img src="images/swa_logo_dark.svg" class="header_logo"/></a>
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
