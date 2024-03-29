package servlets;

import business.Passenger;
import database.FastPassDB;
import database.FlightFPTicketDTODB;
import dto.FlightFPTicketDTO;
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
public class AccountInfoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accountNumber = request.getParameter("accountNumber").trim();
        String userMessage = "";
        String URL = "/AccountDisplay.jsp";

        List<FlightFPTicketDTO> flightFPTicketDTOs = null;
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");

        if (passenger.getAccountNumber().equals(accountNumber)) {
            try {

                flightFPTicketDTOs = FlightFPTicketDTODB.getListFlightFPTicketDTOByPassengerID(passenger.getId());

                request.setAttribute("flightFPTicketDTOs", flightFPTicketDTOs);

                int count = FastPassDB.getFastPassCountByPassengerID(passenger.getId());

                request.setAttribute("fastPassCount", count);

            } catch (SQLException | ClassNotFoundException ex) {
                userMessage += "Error: " + ex.getMessage();
            }

        } else {
            userMessage += "Unauthorized Access: Unable to complete request";

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
