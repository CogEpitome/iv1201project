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
import javax.inject.Inject;
import javax.inject.Named;
import kthknugarna.iv1201project.controller.LoginController;
import kthknugarna.iv1201project.model.Application;
import kthknugarna.iv1201project.model.dto.ApplicationDTO;

/**
 *
 * @author Jonas
 */
@Named("loginView")
@ConversationScoped
public class LoginView implements Serializable{
    @EJB
    private LoginController controller;
    //@Inject
    //private Conversation conversation;
    
    public String LoginButton(){
        ApplicationDTO application = new Application();
        String str = controller.Login(application);
        return str;
    }
    
}
