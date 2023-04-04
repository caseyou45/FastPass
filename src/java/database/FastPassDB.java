package database;

import business.FastPass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CWilson
 */
public class FastPassDB {

    public static String createNewFastPass(int passengerID, int startingAmount) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);
        connection.setAutoCommit(false);

        String query = "INSERT INTO fastpass" + "(fastpass_verification_number, fastpass_amountleft, "
                + "passenger_id) " + "VALUES (?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        String verificationNumber = generateFastPassVerificationNumber();

        preparedStatement.setString(1, verificationNumber);
        preparedStatement.setInt(2, startingAmount);
        preparedStatement.setInt(3, passengerID);

        preparedStatement.executeUpdate();
        connection.commit();

        preparedStatement.close();
        connection.close();

        return verificationNumber;

    }

    public static int getFastPassCountByFlight(int flightId) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        int count = -1;

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        String query = "select count(*) as count from ticket where flight_id = ? and fastpass_id is not NULL;";

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

    public static List<FastPass> getFastPassesByPassengerID(int passengerID) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        List<FastPass> fastPassList = new ArrayList<>();

        String query = "select * from fastpass f where f.passenger_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, passengerID);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            fastPassList.add(resultSetToFastPass(resultSet));
        }

        preparedStatement.close();
        connection.close();

        return fastPassList;

    }

    public static boolean updateFastPassAmountLeft(FastPass fastPass, int newAmount) throws SQLException {

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        String query = "Update fastpass f set f.fastpass_amountleft = ? where f.fastpass_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, newAmount);
        preparedStatement.setInt(2, fastPass.getFastPassId());

        int result = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return result > 0;

    }

    public static FastPass getFastPassByVerificationNumber(String verificationNumber) throws SQLException, ClassNotFoundException {

        FastPass fastPass = null;

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        String query = "select * from fastpass f where f.fastpass_verification_number = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, verificationNumber);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            fastPass = resultSetToFastPass(resultSet);
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

    private static FastPass resultSetToFastPass(ResultSet resultSet) throws SQLException, ClassNotFoundException {

        FastPass fastPass = new FastPass();
        fastPass.setFastPassId(resultSet.getInt("fastpass_id"));
        fastPass.setPassengerId(resultSet.getInt("passenger_id"));
        fastPass.setFastPassAmountLeft(resultSet.getInt("fastpass_amountleft"));
        fastPass.setFastPassVerificationNumber(resultSet.getString("fastpass_verification_number"));
        return fastPass;

    }
}
