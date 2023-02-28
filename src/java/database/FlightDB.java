package database;

import business.Flight;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author CWilson
 */
public class FlightDB {

    public static Flight getFlightByFlightNumber(String passengerEmail) throws SQLException, ClassNotFoundException {

        Flight flight = null;

        String dbURL = "jdbc:mysql://localhost:3306/fastpass2";
        String dbUSER = "root";
        String dbPassword = "password";

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(dbURL, dbUSER, dbPassword);
        Statement statement = connection.createStatement();

        String sqlCommand = "SELECT * FROM flight WHERE flight_number = '" + passengerEmail + "'";

        ResultSet resultSet = statement.executeQuery(sqlCommand);

        if (resultSet.next()) {
            flight = new Flight();
            flight.setFlightId(resultSet.getInt("flight_id"));
            flight.setFlightCode(resultSet.getString("flight_code"));
            flight.setFlightNumber(resultSet.getString("flight_number"));
            flight.setFlightStatus(resultSet.getString("flight_status"));
            flight.setFlightDuration(resultSet.getInt("flight_duration"));
            flight.setAircraftCode(resultSet.getString("aircraft_code"));
            flight.setAirlineCode(resultSet.getString("airline_code"));
            flight.setArrival(ArrivalDB.getArrivalByID(resultSet.getInt("arrival_id")));
            flight.setDeparture(DepartureDB.getDepartureByID(resultSet.getInt("departure_id")));

        }

        resultSet.close();
        statement.close();
        connection.close();

        return flight;
    }
}
