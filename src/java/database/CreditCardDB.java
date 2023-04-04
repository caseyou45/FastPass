package database;

import business.CreditCard;
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
public class CreditCardDB {

    public static void createNewCreditCard(CreditCard creditCard) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);
        connection.setAutoCommit(false);

        String query = "INSERT INTO creditcard" + "(cc_number, cc_type, "
                + "cc_cvv, cc_expiration, cc_firstname, cc_lastname, passenger_id) " + "VALUES (?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, creditCard.getCcNumber());
        preparedStatement.setString(2, creditCard.getCcType());
        preparedStatement.setString(3, creditCard.getCcCvv());
        preparedStatement.setString(4, creditCard.getCcExpiration());
        preparedStatement.setString(5, creditCard.getCcFirstname());
        preparedStatement.setString(6, creditCard.getCcLastname());
        preparedStatement.setInt(7, creditCard.getPassengerId());

        preparedStatement.executeUpdate();
        connection.commit();

        preparedStatement.close();
        connection.close();

    }

    public static List<CreditCard> getPassengerCreditCard(int passengerID) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);
        connection.setAutoCommit(false);

        List<CreditCard> creditCards = new ArrayList<>();

        String query = "Select * from creditcard where passenger_id = ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, passengerID);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            CreditCard creditCard = new CreditCard();
            creditCard.setCcId(resultSet.getInt("cc_id"));
            creditCard.setCcNumber(resultSet.getString("cc_number"));
            creditCard.setCcType(resultSet.getString("cc_type"));
            creditCard.setCcCvv(resultSet.getString("cc_cvv"));
            creditCard.setCcExpiration(resultSet.getString("cc_expiration"));
            creditCard.setCcFirstname(resultSet.getString("cc_firstname"));
            creditCard.setCcLastname(resultSet.getString("cc_lastname"));
            creditCard.setPassengerId(resultSet.getInt("passenger_id"));
            creditCards.add(creditCard);
        }

        preparedStatement.close();
        connection.close();

        return creditCards;

    }

}
