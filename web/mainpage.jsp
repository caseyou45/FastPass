<!-- author: Regina -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>southwest.com</title>
        <style><%@include file="/css/mainPage.css"%></style>
        <link rel="icon" type="image/x-icon" href="images/favicon.png">

    </head>


    <body>
        <img src="images/SWLogo.png" width="auto" height="90" />

        <form action="LogIn.jsp" method="post">
            <button type="submit" class="LogInEnroll" value="logIn"> Log In/Enroll</button>
        </form>


        <div class="header">
            <h1>Get a Fast Pass Today.</h1>
        </div>





        <form action=""  method="post">
            <div class="checkpasscontainer">
                <h1 class="checkpass">Check Pass Availability </h1>
                <table>
                    <tr>
                        <td>Date</td>
                        <td>Flight</td>
                    </tr>
                    <tr>
                        <td><input id="Date" type="text" /></td>
                        <td><input id="name" type="text" /></td>
                    </tr>
                </table>
                <button type="button" class="search">Search</button>
            </div>
        </form>


        <div class="heartlogo">
        </div>







    </body>
</html>