package validation;

import business.CreditCard;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author CWilson
 */
public class CreditCardEntry {

    public static String CreditCardEntryValidation(CreditCard creditCard, HttpServletRequest request) {
        String userMessage = "";
        String ccNumber = "", ccType = "", ccCvv = "", ccExpiration = "", ccFirstname = "", ccLastname = "";

        ccNumber = request.getParameter("ccNumber");

        if (!ccNumber.isEmpty()) {
            creditCard.setCcNumber(ccNumber);
        } else {
            userMessage += "Credit Card Number is missing <br>";
        }

        ccType = request.getParameter("ccType");

        if (!ccType.isEmpty()) {
            creditCard.setCcType(ccType);
        } else {
            userMessage += "Credit Card Type is missing <br>";
        }

        ccCvv = request.getParameter("ccCvv");

        if (!ccCvv.isEmpty()) {
            creditCard.setCcCvv(ccCvv);
        } else {
            userMessage += "Credit Card CVV is missing <br>";
        }

        ccExpiration = request.getParameter("ccExpiration");

        if (!ccExpiration.isEmpty()) {
            creditCard.setCcExpiration(ccExpiration);
        } else {
            userMessage += "Credit Card Expiration Date is missing <br>";
        }

        ccFirstname = request.getParameter("ccFirstname");

        if (!ccFirstname.isEmpty()) {
            creditCard.setCcFirstname(ccFirstname);
        } else {
            userMessage += "Credit Card First Name is missing <br>";
        }

        ccLastname = request.getParameter("ccLastname");

        if (!ccLastname.isEmpty()) {
            creditCard.setCcLastname(ccLastname);
        } else {
            userMessage += "Credit Card Last Name is missing <br>";
        }

        return userMessage;
    }

}
