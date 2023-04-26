<%--
    Document   : PurchaseFastPass
    Created on : Mar 26, 2023, 9:36:04 AM
    Author     : CWilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/css/default.css"%></style>
        <title>Purchase Fast Pass</title>
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

            <h1>Buy Fast Pass Now 3 for $100 or 1 for $40</h1>
            <c:choose>
                <c:when test="${passenger.authenticated}">
                    <div>
                        <form action="PurchaseFastPass"  method="POST">
                            <select  name="amount-select" id="amount-select">
                                <option value="">Select an Amount:</option>
                                <option  value="1">1 for $40</option>
                                <option  value="3">3 for $100</option>
                            </select>
                            <input type="submit" value="Purchase">
                        </form>
                    </div>
                </c:when>
                <c:otherwise >
                    <div>
                        <h2>Log in or Sign Up to get your FastPass</h2>
                        <a href="LogIn.jsp" class="login">Log In</a>
                        <a href="SignUp.jsp" class="signup">Create Account</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </main>


    </body>
</html>
