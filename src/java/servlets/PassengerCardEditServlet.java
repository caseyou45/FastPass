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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import validation.CreditCardEntry;

/**
 *
 * @author Regna
 */
@WebServlet(name = "PassengerCardEditServlet", urlPatterns = {"/PassengerCardEditServlet"})
public class PassengerCardEditServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String userMessage = "";
        String URL = "/AddCard.jsp";
    
        
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");       
       
        CreditCard creditCard = new CreditCard();

        
        try {
             userMessage += CreditCardEntry.CreditCardEntryValidation(creditCard, request);
             
            if (userMessage.isEmpty()) {
                creditCard.setPassengerId(passenger.getId());
                CreditCardDB.createNewCreditCard(creditCard);                          
                userMessage += "Credit card has been successfully added!";
                request.getSession().setAttribute("passenger", passenger);
                request.getSession().setAttribute("creditCard", creditCard);
               URL = "/AddCard.jsp";
            }
        } catch (ClassNotFoundException | SQLException e) {
            userMessage += e.getMessage() + "<br>";         
        }
        
        if (!userMessage.isEmpty()) {
            request.getSession().setAttribute("passenger", passenger);
            
        } else {
            URL = "/AddCard.jsp";
        }
      
        request.setAttribute("creditCard", creditCard);
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
