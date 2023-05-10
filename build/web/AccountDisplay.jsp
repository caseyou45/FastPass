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
        <style><%@include file="/css/shared_ui.css"%></style>

        <link rel="icon" type="image/x-icon" href="images/favicon.png">
        <title>Fast Pass | Account </title>
    </head>
    
    <body><div class="mainContentTrim">
        <header>
            <c:choose>
                <c:when test="${passenger.authenticated}">
                    <div class="header_useracc">
                        <div class="greeting-container">
                        
                            <p class="greeting"> Hi,${passenger.firstName}</p>
                        <a class="profile-details-link"href="PassengerProfileServlet?accountNumber=${passenger.accountNumber}">Profile Details</a> 
                        </div>
                        
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
                        <span class="LogInTip">I don't think you should be here</span>
                    </div>
                </c:otherwise>
            </c:choose>
            <a href='mainpage.jsp'><img src="images/swa_logo_dark.svg" class="header_logo"/></a>
        </header>
            
            <br>
        <main>
            <p class="user-message">${userMessage}</p>
            <h1 class="formHeading">Actives Passes for ${passenger.firstName}</h1>
            <p style="margin-bottom: 5px;">Unused FastPasses Left: ${fastPassCount}</p>
            <div class="RowForm">
                <form action="PurchaseFastPass.jsp" method="post" style="padding: 5px 0;">
                    <button type="submit" class="ActionablePrimary" value="">Buy FastPass</button>
                </form>
                <form action="FastPassStart.jsp" method="post" style="padding: 5px 0;">
                    <button type="submit" class="ActionablePrimary" value="">Connect FastPass</button>
                </form>
            </div>

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
    </div></body>
</html>
