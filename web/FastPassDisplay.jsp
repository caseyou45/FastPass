<%--
    Document   : FastPassDisplay
    Created on : Apr 7, 2023, 12:25:47 PM
    Author     : CWilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/css/default.css"%></style>
        <style><%@include file="/css/FastPass.css"%></style>

        <title>FastPass</title>
    </head>
    <body>
        <section>
            <div class="fastpass">
                <h1>Southwest FastPass</h1>
                <div class="cities">
                    <span>${flightFPTicketDTO.flight.departure.airport.airportCity}</span>
                    <span>${flightFPTicketDTO.flight.arrival.airport.airportCity}</span>
                </div>

                <div class="airports">
                    <span>${flightFPTicketDTO.flight.departure.airport.airportIataCode}</span>
                    <span>&#9992;</span>
                    <span>${flightFPTicketDTO.flight.arrival.airport.airportIataCode}</span>
                </div>

                <div class="passenger">
                    <span>Passenger</span>
                    <br>
                    <span>${passenger.fullName}</span>
                </div>

                <div class="qr">
                    <img src="data:image/png;base64,${imageAsBase64}"/>
                </div>
            </div>
        </section>
    </body>
</html>
