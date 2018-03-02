package kthknugarna.iv1201project.model;

import kthknugarna.iv1201project.model.dto.ApplicantDTO;

/**
 * The Input class handles the data provided by an applicant when they apply for a job. 
 * 
 * @author Jonas
 * @author Anton
 * @author Benjamin
 */
public class Applicant implements ApplicantDTO {
    
    private final String userName;
    private final int[] expertise, yearsOfExperience;
    private final String[] availableFrom, availableTo;
    
    public Applicant(String personId, int[] expertise, int[] yearsOfExperience, String[] availableFrom, String[] availableTo){
        this.userName = personId;
        this.expertise = expertise;
        this.yearsOfExperience = yearsOfExperience;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public int[] getExpertise() {
        return expertise;
    }

    @Override
    public int[] getYearsOfExperience() {
        return yearsOfExperience;
    }

    @Override
    public String[] getAvailableFrom() {
        return availableFrom;
    }

    @Override
    public String[] getAvailableTo() {
        return availableTo;
    }
    
    
    
}
