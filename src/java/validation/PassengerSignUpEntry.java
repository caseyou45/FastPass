package validation;

import business.Passenger;
import database.PassengerDB;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author CWilson
 */
public class PassengerSignUpEntry {

    public static String passsengerSignUpDataValidation(Passenger passenger, HttpServletRequest request) {
        String userMessage = "";
        String passengerEmail = "", passengerPassword = "", passengerFirstName = "", passengerLastName = "",
                passengerMiddleName = "", passengerPasswordRETYPE = "", passengerDob = "";

        passengerEmail = request.getParameter("passengerEmail");

        if (passengerEmail.isEmpty()) {

            userMessage += "Email is missing <br>";

        } else {

            try {

                boolean emailUsed = PassengerDB.passengerExistsByEmail(passengerEmail);

                if (emailUsed) {
                    userMessage += "Email is already in use <br>";

                } else {
                    passenger.setEmail(passengerEmail);

                }

            } catch (ClassNotFoundException | SQLException e) {
                userMessage += e.getMessage() + "<br>";

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

        try {
            passenger.setAccountNumber(generateAccountNumber());
        } catch (ClassNotFoundException | SQLException e) {
            userMessage += e.getMessage() + "<br>";

        }

        return userMessage;

    }

    /* Generates a random number and checks if that number is in use. If in use, it then repeats the process till
    an un-used number is generated*/
    private static String generateAccountNumber() throws ClassNotFoundException, SQLException {

        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        String accountNumber = String.valueOf(number);
        boolean accountNumberUsed = PassengerDB.passengerExistsByAccountNumber(accountNumber);

        if (accountNumberUsed) {
            accountNumber = generateAccountNumber();
        }

        return accountNumber;
    }

}
