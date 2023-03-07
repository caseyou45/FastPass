package database;

import business.Airport;
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
public class AirportDB {

    public static Airport getAirportByID(int airportID) throws SQLException {

        Airport airport = null;

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        String query = "select * from airport a where a.airport_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, airportID);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            airport = new Airport();
            airport.setAirportId(resultSet.getInt("airport_id"));
            airport.setAirportName(resultSet.getString("airport_name"));
            airport.setAirportIataCode(resultSet.getString("airport_iata_code"));
            airport.setAirportCode(resultSet.getString("airport_code"));
            airport.setAirportLat(resultSet.getDouble("airport_lat"));
            airport.setAirportLng(resultSet.getDouble("airport_lng"));
            airport.setAirportCountryCode(resultSet.getString("airport_country_code"));
            airport.setAirportCity(resultSet.getString("airport_city"));
            airport.setAirportState(resultSet.getString("airport_state"));

        }

        preparedStatement.close();
        connection.close();

        return airport;
    }

    public static List<Airport> getAllAirports() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        List<Airport> allAirports = new ArrayList<>();

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM airport");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Airport airport = new Airport();
            airport.setAirportId(resultSet.getInt("airport_id"));
            airport.setAirportName(resultSet.getString("airport_name"));
            airport.setAirportIataCode(resultSet.getString("airport_iata_code"));
            airport.setAirportCode(resultSet.getString("airport_code"));
            airport.setAirportLat(resultSet.getDouble("airport_lat"));
            airport.setAirportLng(resultSet.getDouble("airport_lng"));
            airport.setAirportCountryCode(resultSet.getString("airport_country_code"));
            airport.setAirportCity(resultSet.getString("airport_city"));
            airport.setAirportState(resultSet.getString("airport_state"));
            allAirports.add(airport);

        }

        preparedStatement.close();
        connection.close();

        return allAirports;
    }

}
