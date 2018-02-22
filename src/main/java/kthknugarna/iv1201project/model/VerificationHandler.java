/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.model;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import kthknugarna.iv1201project.model.dto.InputDTO;
import kthknugarna.iv1201project.model.exceptions.VerificationException;
import org.jsoup.Jsoup;
import org.jsoup.safety.*;

/**
 *
 * @author Jonas
 */

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class VerificationHandler {
        
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
    
}
