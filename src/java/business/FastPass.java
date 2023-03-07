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

}
