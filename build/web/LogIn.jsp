
<!-- author: Casey -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/css/LogIn.css"%></style>

        <link rel="icon" type="image/x-icon" href="images/favicon.png">
        <title>Fast Pass | Log In</title></head>
    <body>
    <header>
        <a  href="/FastPass"><img src="images/SWLogo.png" width="340" height="auto" /></a>

    </header>
    <h1>Fast Pass Log In</h1>
    <main>
        <p class="user-message">${userMessage}</p>

        <form action="PassengerLogin" method="post">
            <div>
                <label>Email</label> <input type="text" name="passengerEmail"
                                            id="passengerEmail" value="SophroniaWitherspoon@mail.com">
            </div>


            <div>
                <label>Password</label> <input type="password"
                                               name="passengerPassword" id="passengerPassword" value="witherspoon">
            </div>
            <input type="submit" value="Log In">
        </form>
    </main>
    <a href="/FastPass/SignUp.jsp">Sign Up</a>

</body>
</html>