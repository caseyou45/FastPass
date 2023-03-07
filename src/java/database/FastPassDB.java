package database;

import business.FastPass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CWilson
 */
public class FastPassDB {

    public static String createNewFastPass(int passengerID) throws SQLException, ClassNotFoundException {

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);
        connection.setAutoCommit(false);

        String query = "INSERT INTO fastpass" + "(fastpass_verification_number, fastpass_amountused, "
                + "passenger_id) " + "VALUES (?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        String verificationNumber = generateFastPassVerificationNumber();

        preparedStatement.setString(1, verificationNumber);
        preparedStatement.setInt(2, 1);
        preparedStatement.setInt(3, passengerID);

        preparedStatement.executeUpdate();
        connection.commit();

        preparedStatement.close();
        connection.close();

        return verificationNumber;

    }

    public static int getFastPassCountByFlight(int flightId) throws SQLException {

        int count = -1;

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        String query = "select count(*) as count from fastpass f where f.flight_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, flightId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            count = resultSet.getInt("count");

        }

        preparedStatement.close();
        connection.close();

        return count;

    }

    public static FastPass getFastPassByVerificationNumber(String verificationNumber) throws SQLException {

        FastPass fastPass = null;

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        String query = "select * from fastpass f where f.ticket_number = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, verificationNumber);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            fastPass = new FastPass();
            fastPass.setFastPassId(resultSet.getInt("fastpass_id"));
            fastPass.setPassengerId(resultSet.getInt("passenger_id"));
            fastPass.setFlightId(resultSet.getInt("flight_id"));
            fastPass.setFastPassAmountUsed(resultSet.getInt("fastpass_amountused"));
            fastPass.setFastPassVerificationNumber(resultSet.getString("fastpass_verification_number"));

        }

        preparedStatement.close();
        connection.close();

        return fastPass;

    }

    private static String generateFastPassVerificationNumber() throws ClassNotFoundException, SQLException {

        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        String verificationNumber = String.valueOf(number);
        FastPass fp = getFastPassByVerificationNumber(verificationNumber);

        if (fp != null) {
            verificationNumber = generateFastPassVerificationNumber();
        }

        return verificationNumber;
    }

}
