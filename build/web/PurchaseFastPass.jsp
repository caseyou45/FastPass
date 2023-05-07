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
        <style><%@include file="/css/header.css"%></style>
        <style><%@include file="/css/shared_ui.css"%></style>
        <title>Purchase Fast Pass</title>
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
                        <form action="LogIn.jsp" method="post" style="display: block; padding: 0; float: left;">
                            <button type="submit" class="LogInEnroll" value="logIn">
                                <img src="images/login_icon.png" height="15" width="auto">
                                <span> Log In</span>
                            </button>
                        </form>
                        <a href='SignUp.jsp' class="CreateAccount" style="float: left;">Create account</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </main>


    </body>
</html>
