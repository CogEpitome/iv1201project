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
import kthknugarna.iv1201project.model.dto.InputDTO;

/**
 *
 * @author Jonas
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class RegisterController {
    @EJB ApplicantDAO dao;
    
    public String register(InputDTO input){
        dao.testStore(input);
        Person person = getPerson(input.getUsername());
        if(person != null)
            return person.getName();
        else
            return "failure";
    }
    
    public Person getPerson(String username){
        return dao.getPerson(username);
    }
    
}
