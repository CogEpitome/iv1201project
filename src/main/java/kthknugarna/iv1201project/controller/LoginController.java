package kthknugarna.iv1201project.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import kthknugarna.iv1201project.integration.ApplicantDAO;
import kthknugarna.iv1201project.model.Person;
import kthknugarna.iv1201project.model.Role;
import kthknugarna.iv1201project.model.VerificationHandler;
import kthknugarna.iv1201project.model.exceptions.VerificationException;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 * 
 * The controller class connected to the LoginView class. 
 * Handles all interactions between the view and other layers when logging in.
 * 
 * @see LoginView
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class LoginController {
    @EJB ApplicantDAO dao;
    @EJB VerificationHandler verificationHandler;
    
    /**
     * Validates that the credentials entered are valid and matches a user in the database
     * @param username user's username
     * @param password user's password
     * 
     * @throws Exception if verification or validation failed.
     * 
     * @return true if credentials are valid, returns false otherwise
     */
    public boolean login(String username, String password) throws Exception{
        try{
            String validUsername = verificationHandler.verifyInput(username, "string");
            String validPassword = verificationHandler.verifyInput(password, "string");
            if(dao.getPerson(username) != null){
                if(dao.getPerson(validUsername).getUsername().equals(validUsername) && dao.getPerson(validPassword).getPassword().equals(validPassword))
                    //log in the user
                    return true;
                else
                    return false;
            }
            else
            return false;
        } catch (VerificationException ve){
            throw new Exception(ve);
        } catch (Exception e){
            throw new Exception(e);
        }
    }
    
    
    private Person getPerson(String username) throws Exception{
       return dao.getPerson(username);
    }
    
    private Role getRole(Person person){
        return person.getRoleId();
    }
    
    public String getRoleName(String username) throws Exception{
        return getRole(getPerson(username)).getName();    
    }
    
    public String getRole(long id) throws Exception{
        return dao.getRole(id).getName();
    }
}
