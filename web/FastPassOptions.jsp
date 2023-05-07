<%--
    Document   : FastPassOptions
    Created on : Mar 4, 2023, 7:42:35 AM
    Author     : CWilson

    additional art pass: john
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/css/FastPassOptions.css"%></style>
        <style><%@include file="/css/mainPage.css"%></style>
        <style><%@include file="/css/header.css"%></style>

        <link rel="icon" type="image/x-icon" href="images/favicon.png">
        <title>Fast Pass | Availability</title>
    </head>
<<<<<<< HEAD
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
            <h1>Fast Pass Availability</h1>
=======
    <header>
        <c:choose>
            <c:when test="${passenger.authenticated}">
                <div>
                    <!-- TODO!! -->
                    <a  href="AccountInfo?accountNumber=${passenger.accountNumber}" class="account">Account</a>
                    <a  href="PassengerLogOut" class="logout">Logout</a>
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
                    <span class="LogInTip">Log in to purchase fast pass</span>
                </div>
            </c:otherwise>
        </c:choose>
        <a href='mainpage.jsp'><img src="images/swa_logo_dark.svg" class="header_logo"/></a>
    </header>
    <main>
        <p class="user-message">${userMessage}</p>
        <h1>Fast Pass Availability</h1>
        <body>
>>>>>>> 26d90f145081fe3c333ef1e69a27a42b8367148a
            <c:if test="${passenger.authenticated}">
                <p>
                    FastPasses available for ${passenger.fullName}: ${fastPassCount}
                </p>
            </c:if>

            <div>
                <h2>&#9992 Depart: <span> ${flights.get(0).departure.airport.airportName}</span></h2>
                <h3>FastPass Status:  </h3>
                <p>Check Flight(s) Below For Details </p>
            </div>
            <table>
                <tr>
                    <th>Flight Number</th>
                    <th>Departure Time</th>
                    <th>Flight Destination</th>
                    <th>FastPass Availability</th>
                        <c:if test="${passenger.authenticated}">
                        <th>
                            Options
                        </th>
                    </c:if>

                </tr>
                <c:forEach var="flight" items="${flights}">
                    <tr>
                        <td>SW${flight.flightNumber}</td>
                        <td>${flight.departure.displayTime}</td>
                        <td>${flight.arrival.airport.airportIataCode}</td>
                        <td>${passenger.authenticated ?   flight.flightPassAmountRemaining : flight.flightPassStatusText } Available</td>
                        <c:if test="${passenger.authenticated}">
                            <th>
                                <form action="ConnectFastPass" method="post">
                                    <input class="fastpass_select" type="submit" value="Connect FastPass">
                                    <input type="hidden" name="flightId" id="flightID" value="${flight.flightId}">
                                </form>
                            </th>
                        </c:if>

                    </tr>
                </c:forEach>
            </table>
        </main>
    </body>
</html>
