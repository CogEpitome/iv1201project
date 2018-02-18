/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import javax.inject.Inject;
import javax.inject.Named;
import kthknugarna.iv1201project.controller.LoginController;
import kthknugarna.iv1201project.integration.ApplicantDAO;
import kthknugarna.iv1201project.model.SessionUtils;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 */
@ManagedBean
@SessionScoped
public class LoginView implements Serializable{
    @EJB
    private LoginController controller;
    private String username;
    private String password;
    /*
    @Inject
    private Conversation conversation;
    
    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }*/
    
    public String validateUsernamePassword(){
        
           // startConversation();
            boolean valid = controller.login(username, password);
            if(valid){
                HttpSession session = SessionUtils.getSession();
                
                session.setAttribute("username", username);
                //change applicant to role, create face for each case
                return "applicant";
            } else{
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect"
                                + " username and password", "Please enter correct"
                                        + " username and password"));
                return "login";
            }
       
        
    }
    
    	//logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }
    
    public LoginController getLoginController(){
        return controller;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
