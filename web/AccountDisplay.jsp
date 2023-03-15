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
        <link rel="icon" type="image/x-icon" href="images/favicon.png">
        <title>Fast Pass | Account </title>
    </head>
    <body>
        <header>
            <a  href="/FastPass"><img src="images/SWLogo.png" width="340" height="auto" /></a>
                <c:choose>
                    <c:when test="${passenger.authenticated}">
                    <div>
                        <a href="AccountInfo" class="Account">Account</a>
                        <form action="PassengerLogOut"  method="GET">
                            <input type="submit" value="Log Out" class="logOut">
                        </form>
                    </div>
                </c:when>
                <c:otherwise >
                    <div>
                        <a href="/FastPass/LogIn.jsp" class="SignIn">Log In</a>
                        <a href="/FastPass/SignUp.jsp" class="SignUp">Create Account</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </header>

        <c:forEach var="fastPass" items="${fastPasses}">
            <table>
                <tr>
                    <th>FastPass Number</th>
                    <th>Flight Number</th>
                    <th>Flight Destination</th>
                    <th>Flight Destination</th>
                </tr>
                <tr>
                    <td>SW${fastPass.fastPassVerificationNumber}</td>
                    <td>${fastPass.flight.flightNumber}</td>
                    <td>${fastPass.flight.departure.displayTime}</td>
                    <td>${fastPass.flight.arrival.airport.airportIataCode}</td>
                </tr>
            </c:forEach>
        </table>
    <body>
    </body>
</html>
