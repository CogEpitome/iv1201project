package kthknugarna.iv1201project.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import kthknugarna.iv1201project.integration.ApplicantDAO;
import kthknugarna.iv1201project.model.Person;
import kthknugarna.iv1201project.model.VerificationHandler;
import kthknugarna.iv1201project.model.dto.InputDTO;

/**
 * @author Jonas
 * @author Anton
 * @author Benjamin
 * 
 * The controller class connected to the RegisterView class. 
 * Handles all interactions between the view and other layers when registering a new account.
 * 
 * @see RegisterView
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class RegisterController {
    @EJB ApplicantDAO dao;
    @EJB VerificationHandler verifier;
    
    /**
     * Registers a user if the username is not already taken
     * @param       input DTO with registration input information from the user
     * @return      String with success message or error message
     * @throws java.lang.Exception
     */
    public String register(InputDTO input) throws Exception{
        try{
            InputDTO safeInput = verifier.verifyInput(input);
            Person person = getPerson(safeInput.getUsername());
            if(person != null)
                return "A user with the username :"+person.getUsername()+" already exists. Please try a different username.";
            else {
                person = new Person(safeInput.getFirstName(), safeInput.getSurname(), safeInput.getDateOfBirth(), safeInput.getPassword(), safeInput.getUsername(), dao.getRole(0));
                dao.persist(person);
                return "success";
            }
        } catch (Exception e){
            throw new Exception(e);
        }
            
    }

    public Person getPerson(String username) throws Exception{
        String safeUsername = verifier.verifyInput(username, "string");
        return dao.getPerson(safeUsername);
    }
    
}
