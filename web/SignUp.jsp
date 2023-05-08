
<!-- author: Casey -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/css/SignUp.css"%></style>
        <style><%@include file="/css/header.css"%></style>
        <style><%@include file="/css/shared_ui.css"%></style>
        <link rel="icon" type="image/x-icon" href="images/favicon.png">
        <title>Fast Pass | Sign Up</title>
    </head>
    <body>
        <header>
            <div class="header_useracc">
                <!--these have to be in reverse order i dont really know why but it works - john -->
                <form action="LogIn.jsp" method="post">
                    <button type="submit" class="LogInEnroll" value="logIn">
                        <img src="images/login_icon.png" height="15" width="auto">
                        <span> Log In</span>
                    </button>
                </form>
            </div>
            <a href='mainpage.jsp'><img src="images/swa_logo_dark.svg" class="header_logo"/></a>
        </header>

    <main><div class="mainContentTrim">
        <p class="user-message">${userMessage}</p>

        <form action="PassengerSignUp" method="post">
            <section class="inputBox">
                <div class="form-row">
                    <h1 class="formHeading" style="margin-bottom: -0.8rem;">Create Account</h1>
                </div>
                <div class="form-row">
                    <div>
                        <label for="passengerFirstName">First Name</label> <input type="text"
                                                         name="passengerFirstName" id="passengerFirstName"  value="${passenger.firstName}">
                    </div>


                    <div>
                        <label for="passengerMiddleName">Middle Name</label> <input type="text"
                                                          name="passengerMiddleName" id="passengerMiddleName" value="${passenger.middleName}">
                    </div>


                    <div>
                        <label for="passengerLastName">Last Name</label> <input type="text" name="passengerLastName"
                                                        id="passengerLastName" value="${passenger.lastName}">
                    </div>

                </div>

                <div class="form-row">

                    <div>
                        <label for="passengerEmail">Email</label> <input type="text" name="passengerEmail"
                                                    id="passengerEmail" value="${passenger.email}">
                    </div>


                    <div>
                        <label for="passengerPassword">Password</label> <input type="password"
                                                       name="passengerPassword" id="passengerPassword">
                    </div>

                    <div>
                        <label for="passengerPasswordRETYPE">Retype Password</label> <input type="password"
                                                              name="passengerPasswordRETYPE" id="passengerPasswordRETYPE">
                    </div>

                </div>

                <div class="form-row">
                    <div>
                        <label for="passengerDob">Date of Birth</label>
                        <input id="passengerDob" name="passengerDob" type="date" value="${passenger.displayDob}" />
                    </div>
                </div>
            </section>
            <br>
            <section class="inputBox">
                <div class="form-row">
                    <div>
                        <label for="ccType">Card Type</label>
                        <select id="ccType" name="ccType" >
                            <option value="" selected="${creditCard.ccType == ""}">--Select--</option>
                            <option value="AmericanExpress" ${creditCard.ccType == "AmericanExpress" ? "selected" : ""} >American Express</option>
                            <option value="Discover" ${creditCard.ccType == "Discover" ? "selected" : ""} >Discover</option>
                            <option value="MasterCard"  ${creditCard.ccType == "MasterCard" ? "selected" : ""} >MasterCard</option>
                            <option value="Visa" ${creditCard.ccType == "Visa" ? "selected" : ""}>Visa</option>
                        </select>
                    </div>
                    <div>
                        <label for="ccNumber">Credit Card #</label>
                        <input id="ccNumber" name="ccNumber" type="tel" inputmode="numeric" pattern="[0-9\s]{13,19}"
                               autocomplete="cc-number"
                               maxlength="19" placeholder="xxxx xxxx xxxx xxxx"
                               value="${creditCard.ccNumber}">
                    </div>

                    <div>
                        <label for="ccExpiration">Expiration Date</label> <input type="text" name="ccExpiration" id="ccExpiration" placeholder="MM/YYYY"  value="${creditCard.ccExpiration}">
                    </div>

                </div>
                <div class="form-row">
                    <div>
                        <label for="ccFirstname">First Name on Card</label> <input type="text"
                                                                 name="ccFirstname" id="ccFirstname"  value="${creditCard.ccFirstname}">
                    </div>

                    <div>
                        <label for="ccLastname">Last Name on Card</label> <input type="text" name="ccLastname" id="ccLastname" value="${creditCard.ccLastname}">
                    </div>
                    <div>
                        <label for="ccCvv">CVV</label> <input type="text" name="ccCvv" id="ccCvv" maxlength="3" placeholder="xxx"  value="${creditCard.ccCvv}" >
                    </div>
                </div>
                <div class="form-row" style="gap: 8px; margin-top: -0.8rem;">
                    <div>
                        <button type="submit" class="ActionablePrimary" value="Sign Up" style="margin-left: 2px; width: 200px; height: 35px;">Create Account</button>
                    </div>
                    <div >
                        <a href="LogIn.jsp" class="loginlink">Log In</a>
                    </div>
                </div>
            </section>


        </form>
    </div></main>
</body>
</html>