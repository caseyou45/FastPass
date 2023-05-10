package servlets;

import business.CreditCard;
import business.Flight;
import database.CreditCardDB;
import database.FlightDB;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CWilson
 */
public class TestingServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String userMessage = "";
        String URL = "/Testing.jsp";

        Flight flight = null;

        try {
            flight = FlightDB.getFlightByFlightId(8193);

            if (flight != null) {
                request.setAttribute("getFlightByFlightId", true);

            } else {
                request.setAttribute("getFlightByFlightId", false);

            }

            flight = null;

            flight = FlightDB.getFlightByFlightNumber("1557");

            if (flight != null) {
                request.setAttribute("getFlightByFlightNumber", true);

            } else {
                request.setAttribute("getFlightByFlightNumber", false);

            }

            CreditCard creditCard = new CreditCard();
            creditCard.setCcId(1);
            creditCard.setCcNumber("1");
            creditCard.setCcType("1");
            creditCard.setCcCvv("1");
            creditCard.setCcExpiration("1");
            creditCard.setCcFirstname("1");
            creditCard.setCcLastname("1");
            creditCard.setPassengerId(1000);

            CreditCardDB.createNewCreditCard(creditCard);

            request.setAttribute("createNewCreditCard", true);

            List<CreditCard> creditCards = CreditCardDB.getPassengerCreditCard(1000);

            if (!creditCards.isEmpty()) {
                request.setAttribute("getPassengerCreditCard", true);

            } else {
                request.setAttribute("getPassengerCreditCard", false);

            }

        } catch (ClassNotFoundException | SQLException e) {
            userMessage += e;

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
