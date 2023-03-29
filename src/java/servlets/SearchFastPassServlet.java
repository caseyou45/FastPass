package servlets;

import business.Flight;
import business.Passenger;
import business.Ticket;
import database.FlightDB;
import database.TicketDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CWilson
 */
@WebServlet(name = "StartFastPassServlet", urlPatterns = {"/StartFastPass"})
public class SearchFastPassServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userMessage = "";
        String date = request.getParameter("date");
        String flightNumber = request.getParameter("flightNum");
        String ticketNumber = request.getParameter("ticketNum");
        String airportCode = request.getParameter("airport-select");

        String URL = "/FastPassStart.jsp";

        List<Flight> flights = new ArrayList<>();

        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");

        Ticket ticket;

        if (ticketNumber != null && !ticketNumber.isEmpty()) {
            try {

                ticket = TicketDB.getTicketByNumber(ticketNumber);

                if (ticket != null && passenger.getId() == ticket.getPassengerId()) {
                    Flight flight = FlightDB.getFlightByFlightId(ticket.getFlightId());

                    flights.add(flight);

                    request.setAttribute("flights", flights);
                    URL = "/FastPassOptions.jsp";

                } else {
                    userMessage += "Invalid Ticket Number. <br>";

                }

            } catch (SQLException | ClassNotFoundException ex) {
                userMessage += "Something Went Wrong. Try again. <br>";
            }

        } else {

            if (airportCode.isEmpty()) {
                userMessage += "Airport Selection Is Missing <br>";
            }

            if (!date.isEmpty()) {

                LocalDate today = LocalDate.now();

                LocalDate enteredDate = LocalDate.parse(date);

                if (!enteredDate.isEqual(today) && !enteredDate.isAfter(today)) {

                    userMessage += "Date Needs To Be For Today Or A Later Date <br>";

                }

            } else {
                userMessage += "Need A Date To Process Request. <br>";

            }

            if (!flightNumber.isEmpty() && !flightNumber.matches("[0-9]+")) {
                userMessage += "That Flight Number Doesn't Look Right. Try Again. <br>";
            }

            if (!userMessage.isEmpty()) {
                userMessage = "Error: <br>" + userMessage;
            } else {
                try {
                    flights = FlightDB.getByFlightNumAndDateAndDepAir(date, airportCode, flightNumber);

                    request.setAttribute("flights", flights);

                    URL = "/FastPassOptions.jsp";

                } catch (SQLException | ClassNotFoundException ex) {

                    userMessage += "Something Went Wrong. Try again. <br>";
                }
            }
        }

        request.setAttribute("userMessage", userMessage);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(URL);

        requestDispatcher.forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
