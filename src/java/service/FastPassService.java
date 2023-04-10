package service;

import business.Flight;
import database.FastPassDB;
import java.sql.SQLException;

/**
 *
 * @author CWilson
 */
public class FastPassService {

    public static int calculateFastPassRemainingCount(Flight flight) throws SQLException, ClassNotFoundException {
        int maxToBeSold = (int) (flight.getFlightCapacity() * .10);
        maxToBeSold -= FastPassDB.getFastPassCountByFlight(flight.getFlightId());

        return maxToBeSold;
    }

    public static String calculateFastPassRemainingText(Flight flight) throws SQLException, ClassNotFoundException {

        int cap = flight.getFlightCapacity();

        int max = (int) (cap * .10);
        int mid = (int) (cap * .5);
        int low = (int) (cap * .5);

        String text = "";
        int actual = max - FastPassDB.getFastPassCountByFlight(flight.getFlightId());

        if (actual > low) {
            return "Limited";
        } else if (actual > mid) {
            return "Some";
        }

        return "Many";

    }
}
