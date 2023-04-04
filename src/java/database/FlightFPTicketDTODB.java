/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import dto.FlightFPTicketDTO;
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
public class FlightFPTicketDTODB {

    public static List<FlightFPTicketDTO> getFlightFPTicketDTOByPassengerID(int passengerID) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        List<FlightFPTicketDTO> flightFPTicketDTOList = new ArrayList<>();

        String query = "Select t.ticket_seat, t.ticket_number, fp.fastpass_verification_number, fl.flight_id from ticket t join flight fl on t.flight_id = fl.flight_id join fastpass fp on fp.fastpass_id = t.fastpass_id where t.passenger_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, passengerID);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            FlightFPTicketDTO flightFPTicketDTO = new FlightFPTicketDTO();
            flightFPTicketDTO.setTicketNumber(resultSet.getString("ticket_seat"));
            flightFPTicketDTO.setTicketNumber(resultSet.getString("ticket_number"));
            flightFPTicketDTO.setFastPassVerificationNumber(resultSet.getString("fastpass_verification_number"));
            flightFPTicketDTO.setFlight(FlightDB.getFlightByFlightId(resultSet.getInt("flight_id")));
            flightFPTicketDTOList.add(flightFPTicketDTO);

        }

        preparedStatement.close();
        connection.close();

        return flightFPTicketDTOList;

    }
}
