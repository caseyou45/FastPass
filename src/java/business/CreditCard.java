package business;

/**
 *
 * @author CWilson
 */
public class CreditCard {

    private int ccId;
    private int passengerId;
    private String ccNumber;
    private String ccType;
    private String ccCvv;
    private String ccExpiration;
    private String ccFirstname;
    private String ccLastname;

    public int getCcId() {
        return ccId;
    }

    public void setCcId(int ccId) {
        this.ccId = ccId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcType() {
        return ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public String getCcCvv() {
        return ccCvv;
    }

    public void setCcCvv(String ccCvv) {
        this.ccCvv = ccCvv;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcFirstname() {
        return ccFirstname;
    }

    public void setCcFirstname(String ccFirstname) {
        this.ccFirstname = ccFirstname;
    }

    public String getCcLastname() {
        return ccLastname;
    }

    public void setCcLastname(String ccLastname) {
        this.ccLastname = ccLastname;
    }

}
