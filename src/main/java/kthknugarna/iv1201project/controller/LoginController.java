package kthknugarna.iv1201project.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import kthknugarna.iv1201project.integration.ApplicantDAO;

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
    
    public String login(String username, String password){
        if(dao.getPerson(username) != null){
            if(dao.getPerson(username).getUsername().equals(username));
                return "Success!";
        }
        else
        return "No user with that username exists";
    }
    
}
