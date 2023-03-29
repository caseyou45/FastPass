package servlets;

import business.CreditCard;
import business.Passenger;
import database.CreditCardDB;
import database.PassengerDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import validation.CreditCardEntry;
import validation.PassengerSignUpEntry;

/**
 *
 * @author CWilson
 */
public class PassengerSignUpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String URL = "/SignUp.jsp";

        Passenger passenger = new Passenger();
        CreditCard creditCard = new CreditCard();
        Passenger loggedInPassenger = null;

        String userMessage = "";

        try {

            userMessage += PassengerSignUpEntry.passsengerSignUpDataValidation(passenger, request);

            if (userMessage.isEmpty()) {
                PassengerDB.passengerSignUp(passenger);
                loggedInPassenger = PassengerDB.passengerLogIn(passenger.getEmail(), passenger.getPassword());
            }

            userMessage += CreditCardEntry.CreditCardEntryValidation(creditCard, request);

            if (userMessage.isEmpty() && loggedInPassenger != null) {
                creditCard.setPassengerId(loggedInPassenger.getId());
                CreditCardDB.createNewCreditCard(creditCard);
            }

        } catch (ClassNotFoundException | SQLException e) {
            userMessage += e.getMessage() + "<br>";
        }

        if (!userMessage.isEmpty()) {
            request.getSession().setAttribute("passenger", passenger);
            userMessage = "Error: <br>" + userMessage;

        } else {
            request.getSession().setAttribute("passenger", loggedInPassenger);
            URL = "/FastPassStart.jsp";

            Cookie enteredID = new Cookie("passengerEmail", loggedInPassenger.getEmail());
            enteredID.setMaxAge(60 * 5);
            enteredID.setPath("/");

            response.addCookie(enteredID);

        }
        request.getSession().setAttribute("creditCard", creditCard);
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
