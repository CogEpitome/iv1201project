package kthknugarna.iv1201project.model;

import kthknugarna.iv1201project.model.dto.ApplicantDTO;

/**
 * 
 * @author Jonas
 * @author Anton
 * @author Benjamin
 */
public class Applicant implements ApplicantDTO {
    
    private final String userName;
    private final String availableFrom, availableTo;
    
    public Applicant(String personId, String availableFrom, String availableTo){
        this.userName = personId;
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
