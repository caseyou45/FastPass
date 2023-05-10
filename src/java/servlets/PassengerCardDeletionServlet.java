/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import business.CreditCard;
import business.Passenger;
import database.CreditCardDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Regina
 */
@WebServlet(name = "PassengerCardDeletionServlet", urlPatterns = {"/PassengerCardDeletionServlet"})
public class PassengerCardDeletionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
             
         String URL =  "/PassengerProfile.jsp";
         String userMessage = "";
         
         
         Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
          
         
           int ccId = Integer.parseInt(request.getParameter("cc_id"));
           int passengerId = Integer.parseInt(request.getParameter("passenger_id"));
         

            try {
                CreditCardDB.deleteCreditCard(ccId);
                List<CreditCard> creditCards = CreditCardDB.getPassengerCreditCard(passengerId);
                request.setAttribute("creditCards", creditCards);
                URL = "/PassengerProfile.jsp";
                request.setAttribute("userMessage", "Card has been successfully deleted");
             
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PassengerCardDeletionServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("userMessage", "An error occurred while deleting the credit card");
            }
            
             request.setAttribute("deleteSuccess", true);
             RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(URL);
             dispatcher.forward(request, response);
  
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
