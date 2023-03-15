<%--
    Document   : FastPassStart
    Created on : Mar 3, 2023, 12:15:54 PM
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
        <title>Fast Pass | Search </title>
    </head>
    <body>
        <header>
            <a  href="/FastPass"><img src="images/SWLogo.png" width="340" height="auto" /></a>
                <c:choose>
                    <c:when test="${passenger.authenticated}">
                    <div>
                        <a href='AccountInfo?accountNumber=${passenger.accountNumber}' class="Account">Account</a>
                        <a href="PassengerLogOut" class="logOut">Log Out</a>
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
        <main>
            <p class="user-message">${userMessage}</p>

            <form action="SearchFastPass"  method="post">
                <div class="checkpasscontainer">
                    <h1 class="checkpass">Check Fast Pass Availability </h1>
                    <c:if test="${passenger.authenticated}">
                        <div class="form-row">
                            <div>
                                <label for="ticketNum">Ticket Number</label>
                                <input id="ticketNum" name="ticketNum" type="text" />
                            </div>
                        </div>
                    </c:if>
                    <br>
                    <div class="form-row">
                        <div>
                            <label for="date">Date</label>
                            <input id="date" name="date" type="date"  />
                        </div>
                        <div>

                            <label for="airport-select">Departure Airport</label>
                            <select id="airport-select" name="airport-select" >
                                <option value="">--Choose An Airport--</option>
                                <c:forEach var="airport" items="${allAirports}">
                                    <option value="${airport.airportCode}" >${airport.airportName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="flightNum">Flight Number (optional)</label>
                            <input id="flightNum" name="flightNum" type="text" maxlength="4"/>
                        </div>
                    </div>
                </div>

                <input type="submit" class="search" value="Search"></button>
                </div>
                </main>
            </form>
    </body>
</html>
