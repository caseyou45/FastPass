<!-- author: Regina -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Southwest Airlines | Fast Pass</title>
        <style><%@include file="/css/mainPage.css"%></style>
        <link rel="icon" type="image/x-icon" href="images/favicon.png">

    </head>


    <body>
        <header>
            <div class="header_useracc">
                <form action="LogIn.jsp" method="post">
                    <button type="submit" class="LogInEnroll" value="logIn"> Log In/Enroll</button>
                </form>
            </div>
            <img src="images/swa_logo_dark.svg" class="header_logo"/>
        </header>


        <div class="banner"></div>
        <article>
            <div class="bannertext">
                <h1>Skip the Headache</h1>
                <h1>m,ore text</h1>
                <h1>button or some such</h1>
                <h1>block text</h1>
            </div>
            
            <div class="availiability">
                <br><br>
                <a href='StartFastPass'>See Availability</a>
                <br><br><br><br>
                <p class="user-message">${userMessage}</p>
            </div>
        </article>



        <div class="heartlogo">
        </div>


    </body>
</html>