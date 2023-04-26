<%--
    Document   : AccountInfo
    Created on : Mar 13, 2023, 4:38:28 PM
    Author     : CWilson

    additional art pass: john
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/css/default.css"%></style>
        <style><%@include file="/css/FastPassOptions.css"%></style>
        <style><%@include file="/css/header.css"%></style>

        <link rel="icon" type="image/x-icon" href="images/favicon.png">
        <title>Fast Pass | Account </title>
    </head>
    <body>
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
            <h1>Actives Passes for ${passenger.firstName}</h1>
            <p>Unused FastPasses Left: ${fastPassCount}</p>
            <a href='PurchaseFastPass.jsp'>Buy FastPass</a>
            <a href='FastPassStart.jsp'>Connect FastPass</a>

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
                            <a
                                href="DisplayFastPass?fastPassVerificationNumber=${flightFPTicketDTO.fastPassVerificationNumber}&ticketNumber=${flightFPTicketDTO.ticketNumber}" class="DisplayPass">Display Pass</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </main>
    <body>
    </body>
</html>
