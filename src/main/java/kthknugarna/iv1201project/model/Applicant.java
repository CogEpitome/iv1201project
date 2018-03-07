package kthknugarna.iv1201project.model;

import kthknugarna.iv1201project.model.dto.ApplicantDTO;

/**
 * 
 * @author Jonas
 * @author Anton
 * @author Benjamin
 * 
 * Contains the information entered by a user when creating an application.
 */
public class Applicant implements ApplicantDTO {
    
    private final String userName;
    private final String availableFrom, availableTo;
    
    public Applicant(String userName, String availableFrom, String availableTo){
        this.userName = userName;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getAvailableFrom() {
        return availableFrom;
    }

    @Override
    public String getAvailableTo() {
        return availableTo;
    }
    
    
    
}
