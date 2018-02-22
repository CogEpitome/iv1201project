package kthknugarna.iv1201project.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import kthknugarna.iv1201project.integration.ApplicantDAO;
import kthknugarna.iv1201project.model.Person;
import kthknugarna.iv1201project.model.VerificationHandler;
import kthknugarna.iv1201project.model.dto.InputDTO;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class RegisterController {
    @EJB ApplicantDAO dao;
    @EJB VerificationHandler verifier;
    
    public String register(InputDTO input){
        Person person = getPerson(input.getUsername());
        if(person != null)
            return "A user with the username :"+person.getUsername()+" already exists. Please try a different username.";
        else {
            person = new Person(input.getFirstName(), input.getSurname(), input.getDateOfBirth(), input.getPassword(), input.getUsername(), dao.getRole(1));
            dao.persist(person);
            return "success";
        }
            
    }
    
    public Person getPerson(String username){
        return dao.getPerson(username);
    }
    
}
