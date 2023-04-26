/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import business.Passenger;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import database.FlightFPTicketDTODB;
import dto.FlightFPTicketDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import javax.imageio.ImageIO;

/**
 *
 * @author CWilson
 */
public class DisplayFastPassServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String fastPassVerificationNumber = request.getParameter("fastPassVerificationNumber");
        String ticketNumber = request.getParameter("ticketNumber");

        String userMessage = "";
        String URL = "/AccountDisplay.jsp";

        FlightFPTicketDTO flightFPTicketDTO = null;
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");

        if (!fastPassVerificationNumber.isEmpty() && passenger != null && !ticketNumber.isEmpty()) {
            try {

                flightFPTicketDTO = FlightFPTicketDTODB.getOneFlightFPTicketDTOByPassengerID(passenger.getId(), fastPassVerificationNumber, ticketNumber);

                if (flightFPTicketDTO != null) {
                    try {

                        ByteArrayOutputStream output = new ByteArrayOutputStream();
                        ImageIO.write(generateQRCodeImage(flightFPTicketDTO.getFastPassVerificationNumber()), "png", output);
                        String imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());

                        request.setAttribute("imageAsBase64", imageAsBase64);
                        request.setAttribute("flightFPTicketDTO", flightFPTicketDTO);
                        URL = "/FastPassDisplay.jsp";
                    } catch (Exception ex) {
                        userMessage += "Whoops! Something went wrong";
                    }

                } else {
                    userMessage += "Unauthorized Access: Unable to complete request";
                }
            } catch (SQLException | ClassNotFoundException ex) {
                userMessage += "Whoops! Something went wrong";
            }

        } else {
            userMessage += "Unauthorized Access: Unable to complete request";

        }

        request.setAttribute("userMessage", userMessage);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(URL);

        requestDispatcher.forward(request, response);

    }

    private static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix
                = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
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
