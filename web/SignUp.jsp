
<!-- author: Casey -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/css/SignUp.css"%></style>
        <style><%@include file="/css/header.css"%></style>
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
    <h1>Fast Pass Sign Up</h1>

    <main>
        <p class="user-message">${userMessage}</p>

        <form action="PassengerSignUp" method="post">
            <section class="inputBox">

                <div class="form-row">
                    <div>
                        <label>First Name</label> <input type="text"
                                                         name="passengerFirstName" id="passengerFirstName"  value="${passenger.firstName}">
                    </div>


                    <div>
                        <label>Middle Name</label> <input type="text"
                                                          name="passengerMiddleName" id="passengerMiddleName" value="${passenger.middleName}">
                    </div>


                    <div>
                        <label>Last Name</label> <input type="text" name="passengerLastName"
                                                        id="passengerLastName" value="${passenger.lastName}">
                    </div>

                </div>

                <div class="form-row">

                    <div>
                        <label>Email</label> <input type="text" name="passengerEmail"
                                                    id="passengerEmail" value="${passenger.email}">
                    </div>


                    <div>
                        <label>Password</label> <input type="password"
                                                       name="passengerPassword" id="passengerPassword">
                    </div>

                    <div>
                        <label>Retype Password</label> <input type="password"
                                                              name="passengerPasswordRETYPE" id="passengerPasswordRETYPE">
                    </div>

                </div>

                <div class="form-row">
                    <div>
                        <label>DOB</label> <input type="text" name="passengerDob"
                                                  id="passengerDob" placeholder="YYYY/MM/DD" value="${passenger.displayDob}">
                    </div>
                </div>
            </section>
            <br>
            <section class="inputBox">
                <div class="form-row">
                    <div>
                        <label>Card Type</label>
                        <select id="ccType" name="ccType" >
                            <option value="" selected="${creditCard.ccType == ""}">--Select--</option>
                            <option value="AmericanExpress" ${creditCard.ccType == "AmericanExpress" ? "selected" : ""} >American Express</option>
                            <option value="Discover" ${creditCard.ccType == "Discover" ? "selected" : ""} >Discover</option>
                            <option value="MasterCard"  ${creditCard.ccType == "MasterCard" ? "selected" : ""} >MasterCard</option>
                            <option value="Visa" ${creditCard.ccType == "Visa" ? "selected" : ""}>Visa</option>
                        </select>
                    </div>
                    <div>
                        <label>Credit Card #</label>
                        <input id="ccNumber" name="ccNumber" type="tel" inputmode="numeric" pattern="[0-9\s]{13,19}"
                               autocomplete="cc-number"
                               maxlength="19" placeholder="xxxx xxxx xxxx xxxx"
                               value="${creditCard.ccNumber}">
                    </div>

                    <div>
                        <label>Expiration Date</label> <input type="text" name="ccExpiration" id="ccExpiration" placeholder="MM/YYYY"  value="${creditCard.ccExpiration}">
                    </div>

                </div>
                <div class="form-row">
                    <div>
                        <label>First Name on Card</label> <input type="text"
                                                                 name="ccFirstname" id="ccFirstname"  value="${creditCard.ccFirstname}">
                    </div>

                    <div>
                        <label>Last Name on Card</label> <input type="text" name="ccLastname" id="ccLastname" value="${creditCard.ccLastname}">
                    </div>
                    <div>
                        <label>CVV</label> <input type="text" name="ccCvv" id="ccCvv" maxlength="3" placeholder="xxx"  value="${creditCard.ccCvv}" >
                    </div>
                </div>
                <div class="form-row">
                    <div>
                        <input type="submit" value="Sign Up">
                    </div>
                    <div >
                        <a href="LogIn.jsp">Log In</a>
                    </div>
                </div>
            </section>


        </form>
    </main>
</body>
</html>