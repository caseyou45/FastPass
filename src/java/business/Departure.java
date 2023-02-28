package business;

import java.sql.Date;

/**
 *
 * @author CWilson
 */
public class Departure {

    private int departureId;
    private int departureAirportId;
    private String departureTerminal;
    private String departureGate;
    private Date departureTime;

    public int getDepartureId() {
        return departureId;
    }

    public void setDepartureId(int departureId) {
        this.departureId = departureId;
    }

    public int getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(int departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public String getDepartureTerminal() {
        return departureTerminal;
    }

    public void setDepartureTerminal(String departureTerminal) {
        this.departureTerminal = departureTerminal;
    }

    public String getDepartureGate() {
        return departureGate;
    }

    public void setDepartureGate(String departureGate) {
        this.departureGate = departureGate;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Departure{" + "departureId=" + departureId + ", departureAirportId=" + departureAirportId + ", departureTerminal=" + departureTerminal + ", departureGate=" + departureGate + ", departureTime=" + departureTime + '}';
    }

}
