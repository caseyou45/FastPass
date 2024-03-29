package business;

/**
 *
 * @author CWilson
 */
public class Ticket {

    private int ticketId;
    private int passengerId;
    private double ticketCost;
    private String ticketSeat;
    private int flightId;
    private int fastPassId;
    private String ticketNumber;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public double getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(double ticketCost) {
        this.ticketCost = ticketCost;
    }

    public String getTicketSeat() {
        return ticketSeat;
    }

    public void setTicketSeat(String ticketSeat) {
        this.ticketSeat = ticketSeat;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getFastPassId() {
        return fastPassId;
    }

    public void setFastPassId(int fastPassId) {
        this.fastPassId = fastPassId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketId=" + ticketId + ", passengerId=" + passengerId + ", ticketCost=" + ticketCost + ", ticketSeat=" + ticketSeat + ", flightId=" + flightId + ", fastPassId=" + fastPassId + ", ticketNumber=" + ticketNumber + '}';
    }

}
