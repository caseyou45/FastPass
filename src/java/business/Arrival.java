package business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author CWilson
 */
public class Arrival {

    private int arrivalId;
    private int arrivalAirportId;
    private String arrivalTerminal;
    private String arrivalGate;
    private LocalDateTime arrivalTime;
    private Airport airport;

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

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDisplayDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return arrivalTime.format(dateTimeFormatter);
    }

    public String getDisplayTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        return arrivalTime.format(dateTimeFormatter);
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    @Override
    public String toString() {
        return "Arrival{" + "arrivalId=" + arrivalId + ", arrivalAirportId=" + arrivalAirportId + ", arrivalTerminal=" + arrivalTerminal + ", arrivalGate=" + arrivalGate + ", arrivalTime=" + arrivalTime + '}';
    }

}
