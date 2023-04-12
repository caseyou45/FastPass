/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import business.FastPass;
import business.Passenger;
import business.Ticket;
import database.FastPassDB;
import database.TicketDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author CWilson
 */
@WebServlet(name = "ConnectFastPassServlet", urlPatterns = {"/ConnectFastPass"})
public class ConnectFastPassServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");

        String flightID = request.getParameter("flightId");

        int flightIDINT = Integer.parseInt(flightID);

        String userMessage = "";

        String URL = "/";

        if (passenger.isAuthenticated()) {

            try {
                FastPass fastPass = FastPassDB.getFastPassByPassengerID(passenger.getId());

                if (fastPass != null) {

                    if (fastPass.getFastPassAmountLeft() > 0) {

                        Ticket ticket = TicketDB.getTicketByFlightAndPassenger(passenger.getId(), flightIDINT);

                        if (ticket != null && ticket.getPassengerId() == passenger.getId()) {

                            if (ticket.getFastPassId() == 0) {
                                boolean success = FastPassDB.connectFastPassToTicket(fastPass, ticket.getTicketId());

                                if (success) {

                                    URL = "/AccountInfo?accountNumber=" + passenger.getAccountNumber();

                                    FastPassDB.updateFastPassAmountLeft(fastPass, fastPass.getFastPassAmountLeft() - 1);
                                    request.getSession().setAttribute("fastPassCount", fastPass.getFastPassAmountLeft() - 1);

                                } else {
                                    userMessage = "Something went wrong. Try again later";
                                }

                            } else {
                                userMessage = "FastPass already found";

                            }

                        } else {
                            userMessage = "No Ticket Found";

                        }

                    } else {
                        userMessage = "Purchase more FastPasses to complete selection";

                    }
                } else {
                    userMessage = "Purchase a FastPass to complete selection";
                }
            } catch (SQLException | ClassNotFoundException ex) {

                userMessage = "Something went wrong. Try again later" + ex.getMessage();

            }

        } else {
            userMessage = "Something went wrong. Try again later.";

        }

        request.setAttribute("userMessage", userMessage);

        if (!userMessage.isEmpty()) {
            URL = "/FastPassOptions.jsp";

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
