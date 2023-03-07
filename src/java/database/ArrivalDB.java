package database;

import business.Arrival;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author CWilson
 */
public class ArrivalDB {

    public static Arrival getArrivalByID(int arrivalID) throws ClassNotFoundException, SQLException {
        Arrival arrival = null;

        String dbURL = "jdbc:mysql://localhost:3306/fastpass2";
        String dbUSER = "root";
        String dbPassword = "password";

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(dbURL, dbUSER, dbPassword);
        Statement statement = connection.createStatement();

        String sqlCommand = "SELECT * FROM arrival WHERE arrival_id = " + arrivalID + ";";

        ResultSet resultSet = statement.executeQuery(sqlCommand);

        if (resultSet.next()) {
            arrival = new Arrival();
            arrival.setArrivalId(resultSet.getInt("arrival_id"));
            arrival.setArrivalAirportId(resultSet.getInt("arrival_airport_id"));
            arrival.setArrivalTerminal(resultSet.getString("arrival_terminal"));
            arrival.setArrivalGate(resultSet.getString("arrival_gate"));
            arrival.setArrivalTime(resultSet.getTimestamp("arrival_time").toLocalDateTime());
            arrival.setAirport(AirportDB.getAirportByID(resultSet.getInt("arrival_airport_id")));

        }

        resultSet.close();
        statement.close();
        connection.close();

        return arrival;
    }

}
