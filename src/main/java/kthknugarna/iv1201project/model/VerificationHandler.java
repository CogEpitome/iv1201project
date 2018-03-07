package kthknugarna.iv1201project.model;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import kthknugarna.iv1201project.model.dto.ApplicantDTO;
import kthknugarna.iv1201project.model.dto.InputDTO;
import kthknugarna.iv1201project.model.exceptions.VerificationException;
import org.jsoup.Jsoup;
import org.jsoup.safety.*;

/**
 * @author Jonas
 * @author Anton
 * @author Benjamin
 * 
 * This class is used to return validated input to prevent incorrect input and XSS.
 */

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class VerificationHandler {
        
    /**
     * Returns a string containing input validated according to a specified data type.
     * @param in    The input data to validate.
     * @param type  Which data type to validate as. Valid options are string and long.
     * @return      The validated input, as a String.
     * @throws VerificationException if validation failed, containing a suitable message.
     */
    public String verifyInput(String in, String type) throws VerificationException{
        if(inputIsType(in, type)){
            String safe = Jsoup.clean(in, Whitelist.basic());
            if(!safe.isEmpty()){
                return safe; 
            } else {
                throw new VerificationException("Sanitized input returned empty.");
            }
        } else {
            throw new VerificationException("Invalid input type.");
        }
    }
    
    /**
     * Returns true if the submitted input is of a given data type.
     * @param in    The input to check
     * @param type  The data type of the input, valid types are string and long.
     * @return      A boolean, true if the input is of the required type, otherwise false.
     */
    private boolean inputIsType(String in, String type) {
        try{
            if(type.equalsIgnoreCase("string")){
                return true;
            } else if(type.equalsIgnoreCase("long")){
                Long.parseLong(in);
            } else {
                return false;
            }
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    
    /**
     * Used to verify all data in an InputDTO object and return a validated version that is safe to use.
     * 
     * @param in    The InputDTO to sanitize and validate.
     * @return      A new InputDTO containing the validated data.
     * @throws VerificationException 
     */
    public InputDTO verifyInput(InputDTO in) throws VerificationException {
        String safeFirstName = verifyInput(in.getFirstName(), "String");
        String safeSurname = verifyInput(in.getSurname(), "String");
        String safeEmail = verifyInput(in.getEmail(), "String");
        String safeDateOfBirth = verifyInput(in.getDateOfBirth(), "String");
        String safeUsername = verifyInput(in.getUsername(), "String");
        String safePassword = verifyInput(in.getPassword(), "String");
        InputDTO safeIn = new Input(safeFirstName, safeSurname, safeEmail, safeDateOfBirth, safeUsername, safePassword);
        return safeIn; 
    }
    
    /**
     * Used to verify all data in an ApplicantDTO object and return a validated version that is safe to use.
     * 
     * @param in    The ApplicantDTO to sanitize and validate.
     * @return      A new ApplicantDTO containing the validated data.
     * @throws      VerificationException 
     */
    public ApplicantDTO verifyApplicant(ApplicantDTO in) throws VerificationException {
        String safeFromTime = verifyInput(in.getAvailableFrom(), "long");
        String safeToTime = verifyInput(in.getAvailableTo(), "long");
        String safeUserName = verifyInput(in.getUserName(), "String");
        ApplicantDTO safeIn = new Applicant(safeUserName, safeFromTime, safeToTime);
        return safeIn; 
    }
    
}
