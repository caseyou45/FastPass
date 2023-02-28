package business;

import java.sql.Date;

/**
 *
 * @author CWilson
 */
public class Arrival {

    private int arrivalId;
    private int arrivalAirportId;
    private String arrivalTerminal;
    private String arrivalGate;
    private Date arrivalTime;

    public int getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(int arrivalId) {
        this.arrivalId = arrivalId;
    }

    public int getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(int arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    public String getArrivalTerminal() {
        return arrivalTerminal;
    }

    public void setArrivalTerminal(String arrivalTerminal) {
        this.arrivalTerminal = arrivalTerminal;
    }

    public String getArrivalGate() {
        return arrivalGate;
    }

    public void setArrivalGate(String arrivalGate) {
        this.arrivalGate = arrivalGate;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Arrival{" + "arrivalId=" + arrivalId + ", arrivalAirportId=" + arrivalAirportId + ", arrivalTerminal=" + arrivalTerminal + ", arrivalGate=" + arrivalGate + ", arrivalTime=" + arrivalTime + '}';
    }

}
