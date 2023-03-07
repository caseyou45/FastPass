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
    private int flightCapacity;
    private int flightHeadcount;
    private String flightPassStatusText;
    private int flightPassAmountRemaining;

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

    public int getFlightCapacity() {
        return flightCapacity;
    }

    public void setFlightCapacity(int flightCapacity) {
        this.flightCapacity = flightCapacity;
    }

    public int getFlightHeadcount() {
        return flightHeadcount;
    }

    public void setFlightHeadcount(int flightHeadcount) {
        this.flightHeadcount = flightHeadcount;
    }

    public String getFlightPassStatusText() {
        return flightPassStatusText;
    }

    public void setFlightPassStatusText(String flightPassStatusText) {
        this.flightPassStatusText = flightPassStatusText;
    }

    public int getFlightPassAmountRemaining() {
        return flightPassAmountRemaining;
    }

    public void setFlightPassAmountRemaining(int flightPassAmountRemaining) {
        this.flightPassAmountRemaining = flightPassAmountRemaining;
    }

}
