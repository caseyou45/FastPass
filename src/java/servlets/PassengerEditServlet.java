
package servlets;

import business.CreditCard;
import business.Passenger;
import database.PassengerDB;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author Regina
 */
public class PassengerEditServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String userMessage = "";
        
        String ccNumber = "", ccType = "", ccCvv = "", ccExpiration = "", ccFirstname = "", ccLastname = "";
        boolean passengerUpdated = true; 
          
        String accountNumber = request.getParameter("accountNumber");
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        
        List<CreditCard> creditCards = null;
        
   
        String firstName = request.getParameter("passengerFirstName");
        String lastName = request.getParameter("passengerLastName");
        String middleName = request.getParameter("passengerMiddleName");
        String email = request.getParameter("passengerEmail");
        String passengerPassword  = request.getParameter("passengerPassword");
        String passengerPasswordRETYPE = request.getParameter("passengerPasswordRETYPE");
        String dobStr = request.getParameter("passengerDob");
        
        if (!firstName.isEmpty()){
             passenger.setFirstName(firstName);            
        } else  {          
            userMessage += "First name is missing <br>";
        }           
        if (!lastName.isEmpty()) {
            passenger.setLastName(lastName); 
        } else {
           userMessage += "Last name is missing <br>";
        }       
        if (!middleName.isEmpty()) {
            passenger.setMiddleName(middleName); 
        } else {
           userMessage += "Middle name is missing <br>";
        }         
           if(!email.isEmpty()) {
               passenger.setEmail(email);
           } else if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
           userMessage += "Please provide a valid email address. ";
         } else  {
               userMessage += "Email is missing <br>";
           }  
    

        if (!passengerPassword.isEmpty()) {

            if (!passengerPasswordRETYPE.isEmpty()) {

                if (passengerPassword.equals(passengerPasswordRETYPE)) {

                    passenger.setPassword(passengerPassword);
                } else {
                    userMessage += "Passwords Do Not Match <br>";
                }

            } else {
                userMessage += "Retype Password <br>";
            }

            passenger.setPassword(passengerPasswordRETYPE);
        } else {
            userMessage += "Password is missing <br>";
        }
               
        if (!dobStr.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
             java.sql.Date dob = null;
            try {          
            java.util.Date utilDate = format.parse(dobStr);
            dob = new java.sql.Date(utilDate.getTime());
             passenger.setDob(dob);
        } catch (ParseException e) {
            userMessage += "Please provide a valid date of birth in yyyy/MM/dd format. ";
        } 
        } else {
            userMessage += "DOB is missing <br> ";  
        }
        
        try {
            passengerUpdated = PassengerDB.passengerUpdate(passenger);
            if(userMessage.isEmpty()) {
                
             userMessage += "Profile updated successfully.";
             request.setAttribute("passenger", passenger);           
            } else {
             userMessage = "Error: <br>" + userMessage;            
        }
       } catch (SQLException sQLException) {
                userMessage += "Connection Error: " + sQLException.getMessage();
            } catch (ClassNotFoundException classNotFoundException) {
                 userMessage += "ClassNotFoundException Error: " + classNotFoundException.getMessage();
            }
 
         request.setAttribute("creditCards", creditCards);
         request.setAttribute("userMessage", userMessage);
         RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/EditPassengerProfile.jsp");
         requestDispatcher.forward(request, response);  
    }
         

         
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
