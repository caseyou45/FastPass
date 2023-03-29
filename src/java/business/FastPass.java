package business;

/**
 *
 * @author CWilson
 */
public class FastPass {

    private int fastPassId;
    private String fastPassVerificationNumber;
    private int fastPassAmountLeft;
    private int passengerId;

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

    public int getFastPassAmountLeft() {
        return fastPassAmountLeft;
    }

    public void setFastPassAmountLeft(int fastPassAmountLeft) {
        this.fastPassAmountLeft = fastPassAmountLeft;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

}
