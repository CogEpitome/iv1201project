/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.model;

import kthknugarna.iv1201project.model.dto.InputDTO;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 */
public class Input implements InputDTO {
    
    private final String firstName, surname, email, dateOfBirth, username, password;
    
    public Input(String firstName, String surname, String email, String dateOfBirth, String username, String password){
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
    }
    
    public Input(InputDTO dto){
        this.firstName = dto.getFirstName();
        this.surname = dto.getSurname();
        this.email = dto.getEmail();
        this.dateOfBirth = dto.getDateOfBirth();
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
    
    
    
}
