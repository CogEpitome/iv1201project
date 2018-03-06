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
    public String username;
    private String password;
    
    public String validateUsernamePassword(){
            try{
                boolean valid = controller.login(username, password);
                String role;
                if(valid){
                    HttpSession session = SessionUtils.getSession();

                    session.setAttribute("username", username);
                    role = controller.getRoleName(username);
                    return role;
                } else{
                    ViewUtils.SetWarning("Incorrect"
                                    + " username and password", "Please enter correct"
                                            + " username and password");
                    return "login";
                }
            } catch (Exception e){
                ViewUtils.SetWarning("An error occured while logging in, it was probably our bad! Try reloading the page or contact us for support.", e.getMessage());
                return "login";
            }
       
        
    }
    
    //logout event, invalidate session
    public String logout() {
        try{
            HttpSession session = SessionUtils.getSession();
            session.invalidate();
            return "login";
        } catch(Exception e){
            ViewUtils.SetWarning("An error occured while logging out, it was probably our bad! Try reloading the page or contact us for support.", e.getMessage());
            return "login";
        }
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
