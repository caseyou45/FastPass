package database;

import business.Flight;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import service.FastPassService;

/**
 *
 * @author CWilson
 */
public class FlightDB {

    public static Flight getFlightByFlightId(int flightId) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Flight flight = null;

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);
        Statement statement = connection.createStatement();

        String sqlCommand = "SELECT * FROM flight WHERE flight_id = " + flightId;

        ResultSet resultSet = statement.executeQuery(sqlCommand);

        if (resultSet.next()) {
            flight = ResultSetToFlight(resultSet);

        }

        resultSet.close();
        statement.close();
        connection.close();

        return flight;
    }

    public static Flight getFlightByFlightNumber(String flightNumber) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Flight flight = null;

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);
        Statement statement = connection.createStatement();

        String sqlCommand = "SELECT * FROM flight WHERE flight_number = '" + flightNumber + "'";

        ResultSet resultSet = statement.executeQuery(sqlCommand);

        if (resultSet.next()) {
            flight = ResultSetToFlight(resultSet);

        }

        resultSet.close();
        statement.close();
        connection.close();

        return flight;
    }

    public static List<Flight> getTodaysFlightsByDepAirport(String airportCode) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        List<Flight> flights = new ArrayList<>();

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        String query = "select * from flight f join departure d "
                + "on f.departure_id = d.departure_id join airport a "
                + "on a.airport_id = d.departure_airport_id  "
                + "where DATE(d.departure_time) = CURDATE() "
                + "and a.airport_code = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, airportCode);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            flights.add(ResultSetToFlight(resultSet));

        }

        preparedStatement.close();
        connection.close();

        return flights;
    }

    public static List<Flight> getByFlightNumAndDateAndDepAir(String date, String airportCode, String flightNumber) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        List<Flight> flights = new ArrayList<>();

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        String query = "select * from flight f join departure d "
                + "on f.departure_id = d.departure_id join airport a "
                + "on a.airport_id = d.departure_airport_id  "
                + "where DATE(d.departure_time) = ? "
                + "and a.airport_code = ?";

        if (!flightNumber.isEmpty()) {
            query += "and f.flight_number = ?";
        }

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, date);
        preparedStatement.setString(2, airportCode);

        if (!flightNumber.isEmpty()) {
            preparedStatement.setString(3, flightNumber);

        }

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            flights.add(ResultSetToFlight(resultSet));

        }

        preparedStatement.close();
        connection.close();

        return flights;
    }

    private static Flight ResultSetToFlight(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        Flight flight = new Flight();

        flight.setFlightId(resultSet.getInt("flight_id"));
        flight.setFlightCode(resultSet.getString("flight_code"));
        flight.setFlightNumber(resultSet.getString("flight_number"));
        flight.setFlightStatus(resultSet.getString("flight_status"));
        flight.setFlightDuration(resultSet.getInt("flight_duration"));
        flight.setFlightCapacity(resultSet.getInt("flight_capacity"));
        flight.setFlightHeadcount(resultSet.getInt("flight_headcount"));
        flight.setAircraftCode(resultSet.getString("aircraft_code"));
        flight.setAirlineCode(resultSet.getString("airline_code"));
        flight.setArrival(ArrivalDB.getArrivalByID(resultSet.getInt("arrival_id")));
        flight.setDeparture(DepartureDB.getDepartureByID(resultSet.getInt("departure_id")));

        flight.setFlightPassStatusText(FastPassService.calculateFastPassRemainingText(flight));
        flight.setFlightPassAmountRemaining(FastPassService.calculateFastPassRemainingCount(flight));

        return flight;

    }

}
