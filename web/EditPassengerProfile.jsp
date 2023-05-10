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
        
   
        <style><%@include file="/css/header.css"%></style>
        <style><%@include file="/css/default.css"%></style>
        <style><%@include file="/css/SignUp.css"%></style>
        <style><%@include file="/css/shared_ui.css"%></style>
        <style><%@include file="/css/AccountInfo.css"%></style>

        <link rel="icon" type="image/x-icon" href="images/favicon.png">
        <title>Edit Personal Information </title>
    </head>
    <body>
     
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
         <main><div class="mainContentTrim">
                 
                  <p class="user-message">${userMessage}</p>
                 <form action="PassengerEditServlet?accountNumber=${passenger.accountNumber}" method="post">
                     <section> 
                     <section class="inputBox">
                          <h1 class="formHeading">Edit Personal Information</h1>   
                <div class="form-row">
                     
                </div>
          
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
                        <label>Last Name</label> <input type="text"
                                                        name="passengerLastName" id="passengerLastName" value="${passenger.lastName}">
                    </div>
                 </div>
                    
                    <div class="form-row">

                        <div>
                           
                        <label>Email</label> <input type="text" name="passengerEmail"
                                                    id="passengerEmail" value="${passenger.email}">
                        </div>
                   
                    <div>
                        <label>Password</label> <input type="password" value="${passenger.password}"
                                                       name="passengerPassword" id="passengerPassword">
                    </div>
                        
                          <div>
                        <label for="passengerPasswordRETYPE">Retype Password</label> <input type="password"
                                                              name="passengerPasswordRETYPE" id="passengerPasswordRETYPE">
                    </div>
                      
                    </div>
                                                       
                    <div class="form-row">
                    
                         <div>
                        <label>DOB</label> <input type="text" name="passengerDob"
                                                  id="passengerDob" value="${passenger.displayDob}">
                    </div>
                </div>
                    
                     <div>
                        
                            <button type="submit" class="ActionablePrimary" value="Save">Save</button>  
     
                             <a href="PassengerProfileServlet?accountNumber=${passenger.accountNumber}" class="cancel"> Cancel</a>
                    
                  </div>
               </section>
                             </section>
                </form>
                    
                

             </div></main>

         </body>
</html>
