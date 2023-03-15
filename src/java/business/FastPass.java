package business;

/**
 *
 * @author CWilson
 */
public class FastPass {

    private int fastPassId;
    private String fastPassVerificationNumber;
    private int fastPassAmountUsed;
    private int passengerId;
    private int flightId;
    private int ticketId;
    private Ticket ticket;
    private Flight flight;

    public int getFastPassId() {
        return fastPassId;
    }

    public void setFastPassId(int fastPassId) {
        this.fastPassId = fastPassId;
    }

    public String getFastPassVerificationNumber() {
        return fastPassVerificationNumber;
    }

    public void setFastPassVerificationNumber(String fastPassVerificationNumber) {
        this.fastPassVerificationNumber = fastPassVerificationNumber;
    }

    public int getFastPassAmountUsed() {
        return fastPassAmountUsed;
    }

    public void setFastPassAmountUsed(int fastPassAmountUsed) {
        this.fastPassAmountUsed = fastPassAmountUsed;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

}
