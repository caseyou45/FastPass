
<!-- author: Casey -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/css/LogIn.css"%></style>
        <style><%@include file="/css/header.css"%></style>

        <link rel="icon" type="image/x-icon" href="images/favicon.png">
        <title>Fast Pass | Log In</title></head>
    <body>
        <header>
            <div class="header_useracc">
                <a href='SignUp.jsp' class="CreateAccount" style="margin-top: 0;">Create account</a>
            </div>
            <a href='mainpage.jsp'><img src="images/swa_logo_dark.svg" class="header_logo"/></a>
        </header>
    <div class="loginBox">
        <h1 class="loginHeading">Log In</h1>
        <div class="loginForm">
            <form action="PassengerLogin" method="post">
                <div class="loginField">
                    <label for="passengerEmail">
                        <span>
                            ACCOUNT EMAIL ADDRESS
                        </span>
                        <div>
                            <input type="text" name="passengerEmail" id="passengerEmail" value="SophroniaWitherspoon@mail.com">
                        </div>
                    </label>
                </div>
                <div class="loginField" style="margin-bottom: 5px; margin-top: 15px;">
                    <label for="passengerPassword">
                        <span>
                            PASSWORD
                        </span>
                        <div>
                            <input type="password" name="passengerPassword" id="passengerPassword" value="witherspoon">
                        </div>
                    </label>
                </div>
                <div class="loginSubmit">
                    <div style="height: 13px;"></div>
                    <div class="createAccount">
                        <p>Not a Member?</p>
                        <a href='SignUp.jsp'>Enroll now</a>
                    </div>
                    <div class="loginButton">
                        <input type="submit" value="Log In">
                    </div>
                </div>
            </form>
        </div>
        <p class="user-message">${userMessage}</p>
    </div>
</body>
</html>