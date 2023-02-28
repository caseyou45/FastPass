package business;

/**
 *
 * @author CWilson
 */
public class Flight {

    private int flightId;
    private String flightCode;
    private String flightNumber;
    private String flightStatus;
    private int flightDuration;
    private String aircraftCode;
    private String airlineCode;
    private Arrival arrival;
    private Departure departure;

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    public int getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(int flightDuration) {
        this.flightDuration = flightDuration;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "Flight{" + "flightId=" + flightId + ", flightCode=" + flightCode + ", flightNumber=" + flightNumber + ", flightStatus=" + flightStatus + ", flightDuration=" + flightDuration + ", aircraftCode=" + aircraftCode + ", airlineCode=" + airlineCode + ", arrival=" + arrival + ", departure=" + departure + '}';
    }

}
