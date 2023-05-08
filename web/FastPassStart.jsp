<%--
    Document   : FastPassStart
    Created on : Mar 3, 2023, 12:15:54 PM
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
        <style><%@include file="/css/header.css"%></style>
        <style><%@include file="/css/shared_ui.css"%></style>
        <link rel="icon" type="image/x-icon" href="images/favicon.png">
        <title>Fast Pass | Search </title>
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
                        <!-- <span class="LogInTip">Log in to purchase fast pass</span> -->
                    </div>
                </c:otherwise>
            </c:choose>
            <a href='mainpage.jsp'><img src="images/swa_logo_dark.svg" class="header_logo"/></a>
        </header>
        <main style="padding: 15px 0;">
            <div class="mainContentTrim">
                <p class="user-message">${userMessage}</p>
                <form action="SearchFastPass"  method="post" style="padding: 0 20px;">
                    <div class="form-row">
                        <h1 class="formHeading" style="margin-bottom: 1.7rem; margin-top: 12px;">Check Availability </h1>
                    </div>
                    <div class="checkpasscontainer">
                        <c:if test="${passenger.authenticated}">
                            <div class="form-row">
                                <div>
                                    <label for="ticketNum">Ticket Number</label>
                                    <input id="ticketNum" name="ticketNum" type="text" />
                                </div>
                            </div>
                            <br>
                        </c:if>
                            <div class="form-row" style="gap:2.5rem;"><!<!-- slightly smaller gap to avoid overflowing max width -->
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
                    <br>
                    <button type="submit" class="ActionablePrimary" value="search" style="margin-left: 2px; width: 200px; height: 35px;">Search</button>
                    </div>
                </form>
            </div>
        </main>


            <script>
                document.getElementById('date').valueAsDate = new Date();
                const aiportOption = document.getElementById('airport-select');

                fetch("/AllAirports").then(response => response.json())
                        .then(airports => {

                            for (const airport of airports) {
                                let opt = document.createElement('option');
                                opt.value = airport.airportCode;
                                opt.innerHTML = airport.airportName + " (" + airport.airportCode + ")";
                                aiportOption.appendChild(opt);
                            }
                        });

            </script>
    </body>
</html>
