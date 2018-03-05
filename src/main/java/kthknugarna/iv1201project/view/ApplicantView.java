/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import kthknugarna.iv1201project.controller.ApplicantController;
import kthknugarna.iv1201project.model.Applicant;
import kthknugarna.iv1201project.model.CompetenceProfile;
import kthknugarna.iv1201project.model.Item;
import kthknugarna.iv1201project.model.dto.ApplicantDTO;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 */
@ManagedBean
@SessionScoped
@Named("applicantView")
public class ApplicantView implements Serializable{
    @EJB
    private ApplicantController controller;
    private ApplicantDTO applicant;
    private int[] areaOfExpertise;
    private int[] yearsOfExperience;
    private String availableFrom;
    private String availableTo;
    
    private List<Item> items;
    
  /*  public String apply(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        
        String user = (String)session.getAttribute("username");
        //applicant = new Applicant("jaboki", areaOfExpertise, yearsOfExperience, availableFrom, availableTo);
        applicant = new Applicant(user, new int[] {0}, new int[] {1}, new String[] {"2018-09-19"}, new String[] {"2019-10-20"});
        return controller.apply(applicant);
    }*/

    public ApplicantController getController() {
        return controller;
    }

    public void setController(ApplicantController controller) {
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

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(String availableTo) {
        this.availableTo = availableTo;
    }
    
    

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void add() {
        items.add(new Item());
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public void save() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        
        String user = (String)session.getAttribute("username");
        int i = 0;
        for(Item item : items){
            controller.createCompetenceProfile(items.get(i).getValue(), user, BigDecimal.valueOf(items.get(i).getExperience()));
            //System.out.println(user + " " + items.get(i).getValue() + " " + BigDecimal.valueOf(items.get(i).getExperience()));
            i++;
        }
        System.out.println(getAvailableFrom() + " " + getAvailableTo());
        applicant = new Applicant(user, availableFrom, availableTo);
        controller.createApplication(applicant);
    }

    public List<Item> getItems() {
        return items;
    }

}
    

