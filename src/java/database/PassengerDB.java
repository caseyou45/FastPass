package database;

import business.Passenger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author CWilson
 */
public class PassengerDB {

    public static Passenger passengerLogIn(String passengerEmail, String passwordAttempt) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        Statement statement = connection.createStatement();

        String sqlCommand = "SELECT * FROM passenger WHERE passenger_email = '" + passengerEmail + "'";

        ResultSet resultSet = statement.executeQuery(sqlCommand);

        Passenger passenger = null;

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
        }

        resultSet.close();
        statement.close();
        connection.close();

        return passenger;
    }

    public static void passengerSignUp(Passenger passenger) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);
        connection.setAutoCommit(false);

        String query = "INSERT INTO passenger" + "(passenger_accountnumber, passenger_lastname, "
                + "passenger_firstname, passenger_middlename, passenger_dob, "
                + "passenger_email, passenger_password) " + "VALUES (?, ?, ?, ?, ?, ?, ?);";

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

    }

    public static boolean passengerExistsByAccountNumber(String passengerAccountNumber) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        Statement statement = connection.createStatement();

        String sqlCommand = "SELECT * FROM passenger WHERE passenger_accountnumber = '" + passengerAccountNumber + "'";

        ResultSet resultSet = statement.executeQuery(sqlCommand);

        boolean passengerExits = false;

        if (resultSet.next()) {
            passengerExits = true;
        }

        resultSet.close();
        statement.close();
        connection.close();

        return passengerExits;
    }

    public static boolean passengerExistsByEmail(String passengerEmail) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        Statement statement = connection.createStatement();

        String sqlCommand = "SELECT * FROM passenger WHERE passenger_email = '" + passengerEmail + "'";

        ResultSet resultSet = statement.executeQuery(sqlCommand);

        boolean passengerExits = false;

        if (resultSet.next()) {
            passengerExits = true;
        }

        resultSet.close();
        statement.close();
        connection.close();

        return passengerExits;
    }

}
