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
        <style><%@include file="/css/Purchase.css"%></style>
        <style><%@include file="/css/LogIn.css"%></style>
        <title>Purchase Fast Pass</title>
    </head>
    <body style="margin:0;">
        <c:choose><c:when test="${passenger.authenticated}"><div class="pagebase"></c:when><c:otherwise ><div class="greyout"></c:otherwise></c:choose><div>
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
                    <a href='mainpage.jsp'><img src="images/swa_logo_dark.svg" class="header_logo"/></a>
                </c:when>
                <c:otherwise >
                    <div class="header_useracc">
                        <!--these have to be in reverse order i dont really know why but it works - john -->
                        <!--these links are disabled since this is all meant to be grayed out, same as the rest of the page-->
                        <a style="font: bold 12px/14px Arial; color: #304cb2; cursor: default; float: right; text-align: center; line-height: 32px;">Create account</a>
                        <form action="LogIn.jsp" method="post">
                            <button type="submit" class="LogInEnroll" value="logIn" disabled="true" style="box-shadow: none; border: none; cursor: default;">
                                <img src="images/login_icon.png" height="15" width="auto">
                                <span> Log In</span>
                            </button>
                        </form>
                        <span class="LogInTip" style="user-select: none;">Log in to purchase fast pass</span>
                    </div>
                    <a style="cursor:default;"><img src="images/swa_logo_dark.svg" class="header_logo"/></a>
                </c:otherwise>
            </c:choose>
        </header>
        <main><div class="mainContentTrim">
            <c:choose>
                <c:when test="${passenger.authenticated}">
                    <p class="user-message">${userMessage}</p>
                    <h1 class="formHeading" style="width: auto;">Buy Fast Pass Now 3 for $100 or 1 for $40</h1>
                </c:when>
                <c:otherwise ><p></p><h1 class="formHeading" style="width: auto; user-select: none;">Buy Fast Pass Now 3 for $100 or 1 for $40</h1></c:otherwise>
            </c:choose>
            <div>
                <form action="PurchaseFastPass"  method="POST">
                    <c:choose>
                        <c:when test="${passenger.authenticated}"><select  name="amount-select" id="amount-select"></c:when>
                        <c:otherwise ><select  name="amount-select" id="amount-select" disabled="true"></c:otherwise>
                    </c:choose>
                        <option value="">Select an Amount:</option>
                        <option  value="1">1 for $40</option>
                        <option  value="3">3 for $100</option>
                    </select>
                    <c:choose>
                        <c:when test="${passenger.authenticated}"><button type="submit" class="ActionablePrimary" value="Purchase"></c:when>
                        <c:otherwise ><button type="submit" class="ActionablePrimary" value="Purchase" disabled="true" style="box-shadow: none; border: none; outline: none; cursor: default;"></c:otherwise>
                    </c:choose>
                        Purchase
                    </button>
                </form>
            </div>
        </div></main>
    </div></div>
            <!-- the entire login page just kinda dropped in if the user isnt logged in -->
            <c:choose>
                <c:when test="${passenger.authenticated}"></c:when>
                <c:otherwise >
                    <div style="position:absolute;background-color: white;margin-top: 40px;left: 50%;width: 313px;margin-left: -156px; border-radius: 5px;">
                    <div class="loginBox" style="max-width: fit-content;max-height: fit-content;margin: 0 10px;margin-top: 10px;">
                        <h1 class="loginHeading">Log In</h1>
                        <div class="loginForm">
                            <form action="PassengerLogin" method="post">
                                <div class="loginField">
                                    <label for="passengerEmail">
                                        <span>
                                            ACCOUNT EMAIL ADDRESS
                                        </span>
                                        <div>
                                            <input type="text" name="passengerEmail" id="passengerEmail">
                                        </div>
                                    </label>
                                </div>
                                <div class="loginField" style="margin-bottom: 5px; margin-top: 15px;">
                                    <label for="passengerPassword">
                                        <span>
                                            PASSWORD
                                        </span>
                                        <div>
                                            <input type="password" name="passengerPassword" id="passengerPassword">
                                        </div>
                                    </label>
                                </div>
                                <div class="loginSubmit" style="margin-left: 25px;">
                                    <div style="height: 13px;"></div>
                                    <div class="createAccount">
                                        <p>Not a Member?</p>
                                        <a href='SignUp.jsp'>Enroll now</a>
                                    </div>
                                    <div class="loginButton">
                                        <input type="submit" value="Log In" style="left: -15px;">
                                    </div>
                                </div>
                                <div class="loginSubmit"><div class="createAccount" style="top: -305px; left: 255px;"><a href="javascript:window.history.back();">x</a></div></div>
                                <br>
                            </form>
                        </div>
                    </div></div>
                </c:otherwise>
            </c:choose>
    </body>
</html>
