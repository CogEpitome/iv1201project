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
import kthknugarna.iv1201project.controller.RegisterController;
import kthknugarna.iv1201project.model.Input;
import kthknugarna.iv1201project.model.dto.InputDTO;

/**
 * @author Jonas
 * @author Anton
 * @author Benjamin
 */
@Named("registerView")
@ConversationScoped
public class RegisterView implements Serializable{
    @EJB
    private RegisterController controller;
    private InputDTO input;
    private String firstName, surname, email, dateOfBirth, username, password;
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

    public String register(){
        startConversation();
        input = new Input("<script>alert('test');</script>", "hedberg", "jonas@gmail.se", "0160939", "anbenjon", "gillarponnys");
        return controller.register(input);
    }
    
    public RegisterController getRegisterController(){
        return controller;
    }
    public InputDTO getInputDTO(){
        return input;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
    
}
