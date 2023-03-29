<%--
    Document   : Testing
    Created on : Mar 8, 2023, 7:18:01 AM
    Author     : CWilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Testing</title>
    </head>
    <body>
        <h1>${userMessage}</h1>

        <h2>Flight DB</h2>
        <p>getFlightByFlightId : ${getFlightByFlightId}</p>
        <p>getFlightByFlightNumber : ${getFlightByFlightNumber}</p>



        <h2>CreditCard DB</h2>
        <p>getPassengerCreditCard : ${getPassengerCreditCard}</p>
        <p>createNewCreditCard : ${createNewCreditCard}</p>
    </body>
</html>
