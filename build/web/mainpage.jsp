<!-- author: Regina -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Southwest.com</title>
        <style><%@include file="/css/mainPage.css"%></style>
        <link rel="icon" type="image/x-icon" href="images/favicon.png">

    </head>


    <body>
        <img src="images/SWLogo.png" width="auto" height="90" />

        <form action="LogIn.jsp" method="post">
            <button type="submit" class="LogInEnroll" value="logIn"> Log In/Enroll</button>
        </form>


        <div class="header">
            <h1>Puchases a Fast Pass Today!</h1>
        </div>

        <p class="user-message">${userMessage}</p>

        <a href='StartFastPass' class="search">See Availability</a>



        <div class="heartlogo">
        </div>


    </body>
</html>