package kthknugarna.iv1201project.controller;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import kthknugarna.iv1201project.model.Application;
import kthknugarna.iv1201project.model.dto.ApplicationDTO;

/**
 *
 * @author Jonas
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class LoginController {
    
    public String Login(ApplicationDTO application){
        if(application.GetStatusId() == 1)
            return "applicant";
        else
            return "failure";
    }
    
    public void Login(String username){
        
    }
    
}
