package servlets;

import business.Passenger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        String sqlCommand = "";

        // Passenger fields
        Passenger passenger;
        String passengerEmail = "";
        String passwordAttempt = "";

        // Local database connection
        String dbURL = "jdbc:mysql://localhost:3306/fastpass2";
        String dbUSER = "root";
        String dbPassword = "password";

        // AWS database connection

//String dbURL = "jdbc:mysql://fastpass.cbdiyphbqzxc.us-east-2.rds.amazonaws.com/fastpa//ss";
//        String dbUSER //= "admin";
//        String dbPassword = "fastpassword";
        passengerEmail = request.getParameter("passengerEmail").trim();

        if (passengerEmail.isEmpty()) {
            userMessage += "Email is missing <br>";
        }

        passwordAttempt = request.getParameter("passengerPassword").trim();

        if (passwordAttempt.isEmpty()) {
            userMessage += "Password is missing <br>";
        }

        if (userMessage.isEmpty()) {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection connection = DriverManager.getConnection(dbURL, dbUSER, dbPassword);
                Statement statement = connection.createStatement();

                sqlCommand = "SELECT * FROM passenger WHERE passenger_email = '" + passengerEmail + "'";

                ResultSet resultSet = statement.executeQuery(sqlCommand);

                if (resultSet.next()) {
                    passenger = new Passenger();
                    passenger.setId(resultSet.getInt("passenger_id"));
                    passenger.setAccountNumber(resultSet.getString("passenger_accountnumber"));
                    passenger.setLastName(resultSet.getString("passenger_lastname"));
                    passenger.setFirstName(resultSet.getString("passenger_firstname"));
                    passenger.setMiddleName(resultSet.getString("passenger_middlename"));
                    passenger.setDob(resultSet.getDate("passenger_dob"));
                    passenger.setEmail(resultSet.getString("passenger_email"));
                    passenger.setPassword(resultSet.getString("passenger_password"));
                    passenger.setPasswordAttempt(passwordAttempt);

                    if (passenger.isAuthenticated()) {
                        URL = "/Main.jsp";

                        Cookie enteredID = new Cookie("passengerEmail", passengerEmail);
                        enteredID.setMaxAge(60 * 5);
                        enteredID.setPath("/");
                        response.addCookie(enteredID);

                    } else {
                        userMessage = "You have entered an invalid Email or Password";
                    }

                    request.getSession().setAttribute("passenger", passenger);

                } else {
                    userMessage = "You have entered an invalid Email or Password";
                }

                resultSet.close();
                statement.close();
                connection.close();

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
