<!-- author: Regina -->
<!-- art pass: john -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Southwest Airlines | Fast Pass</title>
        <style><%@include file="/css/mainPage.css"%></style>
        <style><%@include file="/css/header.css"%></style>
        <style><%@include file="/css/shared_ui.css"%></style>
        <link rel="icon" type="image/x-icon" href="images/favicon.png">

    </head>


    <body>
        <header class="anim">
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
            <img src="images/swa_logo_dark.svg" class="header_logo"/>
        </header>

        <div class="banner"><div></div></div>
        <article>
            <div class="bannertext">
                <p>Travel plans?</p>
                <h1>Skip the line.</h1>
                <div><form action="PurchaseFastPass.jsp" method="post">
                    <button type="submit" class="ActionablePrimary" value="learnmore">
                        <span>See Pricing</span>
                    </button>
                </form></div>
            </div>
            <div class="contentMain">
                <div class="availiability">
                    <br><br>
                    <a href='StartFastPass'>See Availability</a>
                    <a href='PurchaseFastPass.jsp'>Buy FastPass</a>
                    
                    <a href='AccountDisplay.jsp'>AccountDisplay</a>
                    <a href='FastPassDisplay.jsp'>FastPassDisplay</a>
                    <a href='FastPassOptions.jsp'>FastPassOptions</a>
                    <br><br><br><br>
                    <p class="user-message">${userMessage}</p>
                </div>
            </div>
        </article>



        <div class="heartlogo">
        </div>


    </body>
</html>