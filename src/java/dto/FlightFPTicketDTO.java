/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import business.Flight;

/**
 *
 * @author CWilson
 */
public class FlightFPTicketDTO {

    private String ticketSeat;
    private String ticketNumber;
    private String fastPassVerificationNumber;
    private Flight flight;

    public String getTicketSeat() {
        return ticketSeat;
    }

    public void setTicketSeat(String ticketSeat) {
        this.ticketSeat = ticketSeat;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getFastPassVerificationNumber() {
        return fastPassVerificationNumber;
    }

    public void setFastPassVerificationNumber(String fastPassVerificationNumber) {
        this.fastPassVerificationNumber = fastPassVerificationNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

}
