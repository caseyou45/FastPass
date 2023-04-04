package database;

import business.Departure;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author CWilson
 */
public class DepartureDB {

    public static Departure getDepartureByID(int departureID) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Departure departure = null;

        String dbURL = "jdbc:mysql://localhost:3306/fastpass2";
        String dbUSER = "root";
        String dbPassword = "password";

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(dbURL, dbUSER, dbPassword);
        Statement statement = connection.createStatement();

        String sqlCommand = "SELECT * FROM departure WHERE departure_id = " + departureID + ";";

        ResultSet resultSet = statement.executeQuery(sqlCommand);

        if (resultSet.next()) {
            departure = new Departure();
            departure.setDepartureId(resultSet.getInt("departure_id"));
            departure.setDepartureAirportId(resultSet.getInt("departure_airport_id"));
            departure.setDepartureTerminal(resultSet.getString("departure_terminal"));
            departure.setDepartureGate(resultSet.getString("departure_gate"));
            departure.setDepartureTime(resultSet.getTimestamp("departure_time").toLocalDateTime());
            departure.setAirport(AirportDB.getAirportByID(resultSet.getInt("departure_airport_id")));

        }

        resultSet.close();
        statement.close();
        connection.close();

        return departure;
    }

}
