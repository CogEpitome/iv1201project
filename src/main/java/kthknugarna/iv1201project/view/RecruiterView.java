/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.view;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import kthknugarna.iv1201project.controller.RecruiterController;
import kthknugarna.iv1201project.controller.RegisterController;
import kthknugarna.iv1201project.model.Application;
import kthknugarna.iv1201project.model.Input;
import kthknugarna.iv1201project.model.dto.ApplicationInfoDTO;
import kthknugarna.iv1201project.model.dto.InputDTO;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 * 
 * List applications
 * -List x (less than 1000 || default_value) apps based on time period, date, competence, name
 * -Show name, surname, date
 * Sort applications
 * 
 * Show application
 * -show more detailed view, along with accept/deny
 * Accept applicant
 * Deny applicant
 */

@ManagedBean
@SessionScoped
@Named("recruiterView")
public class RecruiterView implements Serializable{
    @EJB
    private RecruiterController controller;
    private InputDTO input;
    private List<ApplicationInfoDTO> applications;
    
    public String getApplications(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String user = (String)session.getAttribute("username");
        applications = controller.getApplicationInfoList();
        return "List size: "+applications.size();
    }
}
