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
        try{
        startConversation();
        input = new Input(firstName, surname, email, dateOfBirth, username, password);
        return controller.register(input);
        } catch (Exception e){
            ViewUtils.SetWarning("Registration failed.", e.getMessage());
            return "failure";
        }
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

    public void setInput(InputDTO input) {
        this.input = input;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
