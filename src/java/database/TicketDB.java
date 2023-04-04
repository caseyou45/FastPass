package database;

import business.Ticket;
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
public class TicketDB {

    public static Ticket getTicketByNumber(String ticketNumber) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Ticket ticket = null;

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        String query = "select * from ticket t where t.ticket_number = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, ticketNumber);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            ticket = new Ticket();
            ticket.setTicketId(resultSet.getInt("ticket_id"));
            ticket.setPassengerId(resultSet.getInt("passenger_id"));
            ticket.setTicketCost(resultSet.getDouble("ticket_cost"));
            ticket.setTicketSeat(resultSet.getString("ticket_seat"));
            ticket.setFlightId(resultSet.getInt("flight_id"));
            ticket.setFastPassId(resultSet.getInt("fastpass_id"));
            ticket.setTicketNumber(resultSet.getString("ticket_number"));

        }

        preparedStatement.close();
        connection.close();

        return ticket;
    }

    public static Ticket getTicketByID(int ticketID) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Ticket ticket = null;

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        String query = "select * from ticket t where t.ticket_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, ticketID);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            ticket = new Ticket();
            ticket.setTicketId(resultSet.getInt("ticket_id"));
            ticket.setPassengerId(resultSet.getInt("passenger_id"));
            ticket.setTicketCost(resultSet.getDouble("ticket_cost"));
            ticket.setTicketSeat(resultSet.getString("ticket_seat"));
            ticket.setFlightId(resultSet.getInt("flight_id"));
            ticket.setFastPassId(resultSet.getInt("fastpass_id"));
            ticket.setTicketNumber(resultSet.getString("ticket_number"));

        }

        preparedStatement.close();
        connection.close();

        return ticket;
    }

    public static List<Ticket> getTicketsByPassenger(int passengerID) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        List<Ticket> tickets = new ArrayList<>();

        Connection connection = DriverManager.getConnection(DBUtil.LOCAL_URL, DBUtil.LOCAL_USER, DBUtil.LOCAL_PASSWORD);

        String query = "select * from ticket t where t.passenger_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, passengerID);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Ticket ticket = new Ticket();
            ticket.setTicketId(resultSet.getInt("ticket_id"));
            ticket.setPassengerId(resultSet.getInt("passenger_id"));
            ticket.setTicketCost(resultSet.getDouble("ticket_cost"));
            ticket.setTicketSeat(resultSet.getString("ticket_seat"));
            ticket.setFlightId(resultSet.getInt("flight_id"));
            ticket.setFastPassId(resultSet.getInt("fastpass_id"));
            ticket.setTicketNumber(resultSet.getString("ticket_number"));
            tickets.add(ticket);

        }

        preparedStatement.close();
        connection.close();

        return tickets;
    }
}
