package business;

/**
 *
 * @author CWilson
 */
import java.sql.Date;

public class Passenger {

    private int id;
    private String accountNumber;
    private String lastName;
    private String firstName;
    private String middleName;
    private Date dob;
    private String email;
    private String password;
    private String passwordAttempt;

    public Passenger() {
    }

    public boolean isAuthenticated() {
        if (this.getPassword() != null && this.getPassword().length() > 0) {
            if (this.password.equals(this.passwordAttempt)) {
                return true;
            }
        }
        return false;
    }

    public String getPasswordAttempt() {
        return passwordAttempt;
    }

    public void setPasswordAttempt(String passwordAttempt) {
        this.passwordAttempt = passwordAttempt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFullName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.firstName);

        if (!this.middleName.isEmpty()) {
            stringBuilder.append(" ");
            stringBuilder.append(String.valueOf(this.middleName.charAt(0)));
        }

        if (!this.lastName.isEmpty()) {
            stringBuilder.append(" ");
            stringBuilder.append(this.lastName);
        }

        return stringBuilder.toString();

    }

    public Date getDob() {
        return dob;
    }

    public String getDisplayDob() {
        String displayDob = "";

        if (dob != null) {
            displayDob = dob.toString().replace('-', '/');
        }

        return displayDob;

    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Passenger [id=" + id + ", accountNumber=" + accountNumber + ", lastName=" + lastName + ", firstName="
                + firstName + ", middleName=" + middleName + ", dob=" + dob + ", email=" + email + ", password="
                + password + ", passwordAttempt=" + passwordAttempt + "]";
    }

}
