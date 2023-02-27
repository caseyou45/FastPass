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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author CWilson
 */
public class PassengerSignUpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String passengerEmail = "", passengerPassword = "", passengerFirstName = "", passengerLastName = "",
                passengerMiddleName = "", passengerPasswordRETYPE = "", passengerDob = "";

        String userMessage = "";

        String URL = "/SignUp.jsp";

        Passenger passenger = new Passenger();

//		String dbURL = "jdbc:mysql://fastpass.cbdiyphbqzxc.us-east-2.rds.amazonaws.com/fastpass";
//		String dbUSER = "admin";
//		String dbPassword = "fastpassword";
        String dbURL = "jdbc:mysql://localhost:3306/fastpass2";
        String dbUSER = "root";
        String dbPassword = "password";

        passengerEmail = request.getParameter("passengerEmail");

        if (passengerEmail.isEmpty()) {

            userMessage += "Email is missing <br>";

        } else {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection connection = DriverManager.getConnection(dbURL, dbUSER, dbPassword);
                Statement statement = connection.createStatement();

                String checkDBForEmail = "SELECT * FROM passenger WHERE passenger_email = '" + passengerEmail + "'";

                ResultSet resultSet = statement.executeQuery(checkDBForEmail);

                if (resultSet.next()) {
                    userMessage += "Email is already in use <br>";

                } else {
                    passenger.setEmail(passengerEmail);

                }

                resultSet.close();
                statement.close();
                connection.close();

            } catch (ClassNotFoundException | SQLException e) {

                e.printStackTrace();
            }
        }

        passengerFirstName = request.getParameter("passengerFirstName");

        if (!passengerFirstName.isEmpty()) {
            passenger.setFirstName(passengerFirstName);
        } else {
            userMessage += "First name is missing <br>";
        }

        passengerMiddleName = request.getParameter("passengerMiddleName");

        if (!passengerMiddleName.isEmpty()) {
            passenger.setMiddleName(passengerMiddleName);
        } else {
            userMessage += "Middle name is missing <br>";
        }

        passengerLastName = request.getParameter("passengerLastName");

        if (!passengerLastName.isEmpty()) {
            passenger.setLastName(passengerLastName);
        } else {
            userMessage += "Last name is missing <br>";

        }

        passengerPassword = request.getParameter("passengerPassword");
        passengerPasswordRETYPE = request.getParameter("passengerPasswordRETYPE");

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

        passengerDob = request.getParameter("passengerDob");

        if (!passengerDob.isEmpty()) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

            java.util.Date utilDate = null;

            try {

                utilDate = format.parse(passengerDob);

                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                passenger.setDob(sqlDate);

            } catch (ParseException e) {

                userMessage += "Unable to save DOB. Try again <br>";

            }
        } else {
            userMessage += "DOB is missing <br>";

        }

        passenger.setAccountNumber(generateAccountNumber());

        if (userMessage.isEmpty()) {

            String query = "INSERT INTO passenger" + "(passenger_accountnumber, passenger_lastname, "
                    + "passenger_firstname, passenger_middlename, passenger_dob, "
                    + "passenger_email, passenger_password) " + "VALUES (?, ?, ?, ?, ?, ?, ?);";

            try {
                Connection connection = DriverManager.getConnection(dbURL, dbUSER, dbPassword);
                connection.setAutoCommit(false);

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, passenger.getAccountNumber());
                preparedStatement.setString(2, passenger.getLastName());
                preparedStatement.setString(3, passenger.getFirstName());
                preparedStatement.setString(4, passenger.getMiddleName());
                preparedStatement.setDate(5, passenger.getDob());
                preparedStatement.setString(6, passenger.getEmail());
                preparedStatement.setString(7, passenger.getPassword());

                preparedStatement.executeUpdate();
                connection.commit();

                preparedStatement.close();
                connection.close();

                URL = "/Main.jsp";

                Cookie enteredID = new Cookie("passengerEmail", passengerEmail);
                enteredID.setMaxAge(60 * 5);
                enteredID.setPath("/");

                response.addCookie(enteredID);

                passenger.setPasswordAttempt(passenger.getPassword());

                request.getSession().setAttribute("passenger", passenger);

            } catch (Exception e) {
                userMessage += e.getMessage() + "<br>";
            }

        }

        if (!userMessage.isEmpty()) {

            userMessage = "Error: <br>" + userMessage;
            request.setAttribute("passenger", passenger);

        }

        request.setAttribute("userMessage", userMessage);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(URL);

        requestDispatcher.forward(request, response);

    }

    private String generateAccountNumber() {
        String dbURL = "jdbc:mysql://localhost:3306/fastpass";
        String dbUSER = "root";
        String dbPassword = "password";

//		String dbURL = "jdbc:mysql://fastpass.cbdiyphbqzxc.us-east-2.rds.amazonaws.com/fastpass";
//		String dbUSER = "admin";
//		String dbPassword = "fastpassword";
        String accountNumber = "";

        try {

            long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;

            accountNumber = String.valueOf(number);

            Connection connection = DriverManager.getConnection(dbURL, dbUSER, dbPassword);
            Statement statement = connection.createStatement();

            String checkDBForEmail = "SELECT * FROM passenger WHERE passenger_accountnumber = '" + accountNumber + "'";

            ResultSet resultSet = statement.executeQuery(checkDBForEmail);

            if (resultSet.next()) {
                accountNumber = generateAccountNumber();
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountNumber;
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
