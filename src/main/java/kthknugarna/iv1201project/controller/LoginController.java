package kthknugarna.iv1201project.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import kthknugarna.iv1201project.integration.ApplicantDAO;
import kthknugarna.iv1201project.integration.Person;
import kthknugarna.iv1201project.model.Application;
import kthknugarna.iv1201project.model.dto.ApplicationDTO;

/**
 *
 * @author Jonas
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class LoginController {
    @EJB ApplicantDAO dao;
    
    public String login(ApplicationDTO application){
        if(application.GetStatusId() == 1)
            return getRole(1);
        else
            return "failure";
    }
    
    public String getRole(long id){
        return dao.getRole(id).getName();
    }
    
    public String login(String username, String password){
        return username + " " + password;
    }
    
}
