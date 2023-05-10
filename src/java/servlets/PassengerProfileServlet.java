
package servlets;

import business.CreditCard;
import business.Passenger;
import database.CreditCardDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakata.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 *
 * @author regin
 */
public class PassengerProfileServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
       
        String accountNumber = request.getParameter("accountNumber");
        String userMessage = "";

       
        List<CreditCard> creditCards = null;
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        
        if (passenger.getAccountNumber().equals(accountNumber)) {
            try {
                
                creditCards = CreditCardDB.getPassengerCreditCard(passenger.getId());
            } catch (SQLException ex) {
               userMessage += "Error: " + ex.getMessage();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PassengerProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            userMessage += "Unauthorized Access: Unable to complete request";

        }
        
        request.setAttribute("creditCards", creditCards);      
        request.setAttribute("userMessage", userMessage);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/PassengerProfile.jsp");
        requestDispatcher.forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
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
