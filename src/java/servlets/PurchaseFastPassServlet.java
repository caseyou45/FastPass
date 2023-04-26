package servlets;

import business.CreditCard;
import business.FastPass;
import business.Passenger;
import database.CreditCardDB;
import database.FastPassDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author CWilson
 */
public class PurchaseFastPassServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        String userMessage = "";
        String URL = "/PurchaseFastPass.jsp";

        String fastPassAmount = request.getParameter("amount-select");

        if (passenger.isAuthenticated()) {
            List<CreditCard> creditCards = null;

            try {
                creditCards = CreditCardDB.getPassengerCreditCard(passenger.getId());

                if (creditCards == null || creditCards.isEmpty()) {
                    userMessage += "Unable to find payment method<br>";
                }

            } catch (SQLException | ClassNotFoundException ex) {
                userMessage += ex.getMessage();
            }

            if (fastPassAmount.isEmpty() || fastPassAmount == null) {
                userMessage += "Choose a selection below";
            }

            if (userMessage.isEmpty()) {

                int fastPassAmountINT = Integer.valueOf(fastPassAmount);

                FastPass fastPass = null;

                try {
                    fastPass = FastPassDB.getFastPassByPassengerID(passenger.getId());

                    if (fastPass == null) {
                        String fastPassVerifcationNumber = FastPassDB.createNewFastPass(passenger.getId(), fastPassAmountINT);
                        fastPass = FastPassDB.getFastPassByVerificationNumber(fastPassVerifcationNumber);
                    } else {
                        FastPassDB.updateFastPassAmountLeft(fastPass, fastPass.getFastPassAmountLeft() + fastPassAmountINT);
                    }

                    URL = "/FastPassStart.jsp";

                } catch (SQLException | ClassNotFoundException ex) {
                    userMessage += ex.getMessage();
                }

            }

            request.setAttribute("userMessage", userMessage);

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(URL);

            requestDispatcher.forward(request, response);
        }

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
