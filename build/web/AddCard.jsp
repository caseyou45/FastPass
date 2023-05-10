<%-- 
 
    Author     : Regina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/css/default.css"%></style>
        <style><%@include file="/css/SignUp.css"%></style>
        <style><%@include file="/css/header.css"%></style>
        <style><%@include file="/css/FastPassOptions.css"%></style>
        <style><%@include file="/css/shared_ui.css"%></style>
        
        
        <link rel="icon" type="image/x-icon" href="images/favicon.png">
        <title>Add credit/debit card</title>
    </head>
    <body>
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
            <main><div class="formHeadingContainer">
        
                <p class="user-message">${userMessage}</p>
                <form action="PassengerCardEditServlet?accountNumber=${passenger.accountNumber}" method="post">
        <section>
          <section class="inputBox">
               <h1 class="formHeading">Add credit/debit card</h1>
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
    
                    <div>
                        <button type="submit" class="ActionablePrimary" value="Save">Save</button>
                                                                   
                   
                      <a href="PassengerProfileServlet?accountNumber=${passenger.accountNumber}" class="cancel">Cancel</a>                  
                  </div>
                    </section>
                    </section>
                </form>
            </main>

         </body></div>
</html>
