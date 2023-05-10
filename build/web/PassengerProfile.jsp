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
        <style><%@include file="/css/FastPassOptions.css"%></style>
        <style><%@include file="/css/header.css"%></style>
        <style><%@include file="/css/shared_ui.css"%></style>
         <style><%@include file="/css/AccountInfo.css"%></style>
                
                
        <link rel="icon" type="image/x-icon" href="images/favicon.png">
        <title>Personal Profile</title>
    </head>
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
                        <a href='SignUp.jsp' class="CreateAccount">Create account</a>
                        <form action="LogIn.jsp" method="post">
                            <button type="submit" class="LogInEnroll" value="logIn">
                                <img src="images/login_icon.png" height="15" width="auto">
                                <span> Log In</span>
                            </button>
                        </form>
                        <span class="LogInTip">You don't even care. Do you?</span>
                    </div>
                </c:otherwise>
            </c:choose>
            <a href='mainpage.jsp'><img src="images/swa_logo_dark.svg" class="header_logo"/></a>
        </header>
        
            <br>
        <main><div class="formHeadingContainer">
         <h1 class="formHeading">Personal Information</h1>
          <form class="my-form" action="PassengerEditServlet?accountNumber=${passenger.accountNumber}" method="post">
            <a class="my-account-button" href="EditPassengerProfile.jsp" class="edit">Edit</a>                                                  
           </form>          
            </div>
            
         <br>
             
                     <table>    
                <thead>
                    <tr>
                        <th>FIRST NAME</th>
                         <th>LAST NAME</th>
                        <th>MIDDLE NAME</th>                       
                    </tr>                  
                </thead>
               <tbody>
      
                        <tr>
                            <td> ${passenger.firstName}</td>
                            <td>${passenger.lastName}</td>
                            <td>${passenger.middleName}</td>
                        </tr>
                          <tr>
                        <th>EMAIL</th>
                        <th>PASSWORD</th>
                        <th>DOB</th>
                    </tr>
                        <tr>
                            <td>${passenger.email}</td>
                            <td>***${fn:substring(passenger.password, 0, 0)}</td>
                            
                            <td>${passenger.displayDob}</td>
                        </tr>                  
              </tbody>
            </table>
             
            <br>        
            <br>  
                <div class="formHeadingContainer">  
                   <h1 class="formHeading">Payment Information</h1>
                   
                   <form class="my-form" action="PassengerCardEditServlet?accountNumber=${passenger.accountNumber}" method="post">
                     <input type="hidden" name="passenger_id" value="${creditCard.passengerId}" />
                     <a class="my-account-button" href="AddCard.jsp" >Add</a>
                 </form>
                </div>
                     <br>
                  <c:choose>
                    <c:when test="${empty creditCards}">
                      <p style="margin-bottom: 5px;">No credit cards to display</p>
               
                   </c:when>
                 <c:otherwise>
                   <c:forEach var="creditCard" items="${creditCards}"> 
                        
        
                       
            <table>  
                
               
                <thead>
                    <tr>
                        <th>CREDIT/DEBIT CARD #</th>
                         <th>CARD TYPE</th>
                        <th>CVV</th>                        
                    </tr>
                  
                </thead>
               <tbody>
                    
                        
                        <tr>
                            <td> **** **** ${fn:substring(creditCard.ccNumber,12,-4)}</td>
                            <td>${creditCard.ccType}</td>
                            <td>***${fn:substring(creditCard.ccCvv, 0, 0)}</td>
                        </tr>
                          <tr>
                        <th>EXPIRATION DATE</th>
                        <th>FIRST NAME ON CARD</th>
                        <th>LAST NAME ON CARD</th>
                    </tr>
                        <tr>
                            <td>${creditCard.ccExpiration}</td>
                            <td>${creditCard.ccFirstname}</td>
                            <td>${creditCard.ccLastname}</td>
                        </tr>
                        
                   </tbody>
                      </table>   
                            
                    <div class="formHeadingContainer">
                 <form class="my-form" action="PassengerCardDeletionServlet?accountNumber=$passenger.accountNumber"
                              method="post" onsubmit="return confirmDelete();" >
                             <input type="hidden" name="cc_id" value="${creditCard.ccId}" />
                             <input type="hidden" name="passenger_id" value="${creditCard.passengerId}" />
                        <button class="my-account-button-delete" type="submit">Delete</button>
                        </form>
                 </div>    
                 </c:forEach>            
          
        </c:otherwise>
             </c:choose>
                  
                      <script>
                          
                          function confirmDelete() {
                              var result = confirm("Are you sure you want to delete this card?");
                              if(result) {
                                  showPopup();
                              }
                              return result;
                          }
                      
                      </script>
                      
                      
                    
                      <br>
                      
                      
                   
                 
              
               </main>

    </body></div>
</html>
