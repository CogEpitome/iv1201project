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
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import kthknugarna.iv1201project.controller.LoginController;
import kthknugarna.iv1201project.integration.ApplicantDAO;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 */
@Named("loginView")
@ConversationScoped
public class LoginView implements Serializable{
    @EJB
    private LoginController controller;
    private String username;
    private String password;
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
    }
    
    public String loginButton(){
        try{
            startConversation();
            String str = controller.login(username, password);
            return str;
        } catch (Exception e){
            System.out.println("WHOOOPSIEREE");
            return "ERRORORORORORORO";
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
