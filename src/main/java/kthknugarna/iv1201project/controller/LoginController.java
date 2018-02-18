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
import kthknugarna.iv1201project.model.Role;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class LoginController {
    @EJB ApplicantDAO dao;
    
    public String getRole(long id){
        return dao.getRole(id).getName();
    }
    
    public boolean login(String username, String password){
        if(dao.getPerson(username) != null){
            if(dao.getPerson(username).getUsername().equals(username) && dao.getPerson(username).getPassword().equals(password))
                //log in the user
                return true;
            else
                return false;
        }
        else
        return false;
    }
    
    private Person getPerson(String username){
       return dao.getPerson(username);
    }
    
    private Role getRole(Person person){
        return person.getRoleId();
    }
    
    public String GetRoleName(String username){
        return getRole(getPerson(username)).getName();    
    }
    
}
