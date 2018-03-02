/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import kthknugarna.iv1201project.controller.RecruiterController;
import kthknugarna.iv1201project.model.dto.ApplicantDTO;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 * 
 * List applications
 * -List x (less than 1000 || default_value) apps based on time period, date, competence, name
 * -Show name, surname, date
 * Sort applications
 * 
 * Show application
 * -show more detailed view, along with accept/deny
 * Accept applicant
 * Deny applicant
 */
@ManagedBean
@SessionScoped
@Named("recruiterView")
public class RecruiterView implements Serializable{
    @EJB
    private RecruiterController controller;
    private ApplicantDTO applicant;
    private int[] areaOfExpertise;
    private int[] yearsOfExperience;
    private String[] availableFrom;
    private String[] availableTo;
    
    public String getApplication(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String user = (String)session.getAttribute("username");
        ApplicantDTO application = controller.getApplication(user);
        
        areaOfExpertise = application.getExpertise();
        yearsOfExperience = application.getYearsOfExperience();
        availableFrom = application.getAvailableFrom();
        availableTo = application.getAvailableTo();
        
        return areaOfExpertise + " : " + yearsOfExperience + " : " + availableFrom + " : " + availableTo;
    }

    public RecruiterController getController() {
        return controller;
    }

    public void setController(RecruiterController controller) {
        this.controller = controller;
    }

    public ApplicantDTO getApplicant() {
        return applicant;
    }

    public void setApplicant(ApplicantDTO applicant) {
        this.applicant = applicant;
    }

    public int[] getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(int[] areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }

    public int[] getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int[] yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String[] getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String[] availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String[] getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(String[] availableTo) {
        this.availableTo = availableTo;
    }
    
    
}
