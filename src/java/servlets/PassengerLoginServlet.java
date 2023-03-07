package servlets;

import business.Passenger;
import database.PassengerDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author CWilson
 */
public class PassengerLoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Control variables
        String URL = "/LogIn.jsp";
        String userMessage = "";

        // Passenger fields
        Passenger passenger;

        String passengerEmail = request.getParameter("passengerEmail").trim();

        if (passengerEmail.isEmpty()) {
            userMessage += "Email is missing <br>";
        }

        String passwordAttempt = request.getParameter("passengerPassword").trim();

        if (passwordAttempt.isEmpty()) {
            userMessage += "Password is missing <br>";
        }

        if (userMessage.isEmpty()) {

            try {

                passenger = PassengerDB.passengerLogIn(passengerEmail, passwordAttempt);

                if (passenger != null && passenger.isAuthenticated()) {
                    URL = "/FastPassStart.jsp";
                    Cookie enteredID = new Cookie("passengerEmail", passengerEmail);
                    enteredID.setMaxAge(60 * 5);
                    enteredID.setPath("/");
                    response.addCookie(enteredID);

                    request.getSession().setAttribute("passenger", passenger);

                } else {
                    userMessage = "You have entered an invalid Email or Password";
                }

            } catch (SQLException sQLException) {
                userMessage += "Connection Error: " + sQLException.getMessage();
            } catch (ClassNotFoundException classNotFoundException) {
                userMessage += "ClassNotFoundException Error: " + classNotFoundException.getMessage();
            }
        }

        if (!userMessage.isEmpty()) {
            userMessage = "Error: <br>" + userMessage;

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
