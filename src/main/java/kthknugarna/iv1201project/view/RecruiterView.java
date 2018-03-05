/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.view;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import kthknugarna.iv1201project.controller.RecruiterController;
import kthknugarna.iv1201project.controller.RegisterController;
import kthknugarna.iv1201project.model.Application;
import kthknugarna.iv1201project.model.Input;
import kthknugarna.iv1201project.model.dto.ApplicationInfoDTO;
import kthknugarna.iv1201project.model.dto.InputDTO;

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
    private InputDTO input;
    private List<ApplicationInfoDTO> applications;
    
<<<<<<< HEAD
=======
    /**
     * Gets a list of ApplicationInfo objects from data in the database and stores it in memory.
     * 
     * @return          an appropriate String message.
     */
>>>>>>> localJonas
    public String getApplications(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String user = (String)session.getAttribute("username");
        applications = controller.getApplicationInfoList();
<<<<<<< HEAD
        return "List size: "+applications.size();
=======
        return applications.toString();
    }

    /**
     * Updates the Status of the specified Application entity in the database.
     * 
     * @param applicationId the id of the application to update.
     * @param statusId the id of the status to update to.
     */
    public void setStatus(long applicationId, long statusId){
        controller.setStatus(applicationId, statusId);
    }
    
    /**
     * Fills the list of filteredApplications based on the user's search preferences.
     * This class is responsible for choosing the correct method of filtering.
     * 
     * More search criteria can be added by adding cases to this method.
     */
    private void filterApplications(){
        filteredApplications.clear();    
        switch(searchParameter){
            case SEARCHPARAM_TIME:
                addByTime(searchFromTime, searchToTime);
                break;
            case SEARCHPARAM_COMPETENCE:
                addByCompetence(searchCompetence);
                break;
            case SEARCHPARAM_NAME:
                addByName(searchName);
                break;
        }
    }
    
    /**
     * Add all ApplicationInfoDTO objects with an availability period between a specified time frame
     * to the list of filtered applications.
     * 
     * @param searchFromTime the earliest starting time.
     * @param searchToTime the latest ending time.
     */
    private void addByTime(String searchFromTime, String searchToTime){
        int fromTime = Integer.valueOf(searchFromTime);
        int toTime = Integer.valueOf(searchToTime); 
        for(ApplicationInfoDTO app : applications){
            boolean add = false;
            for(Availability av : app.getAvailabilityList()){
                if(searchFromTime.isEmpty() || searchToTime.isEmpty()){
                    if(searchToTime.isEmpty() && Integer.valueOf(av.getFromDate()) > fromTime){
                        add = true;
                    } else if(searchFromTime.isEmpty() && Integer.valueOf(av.getToDate()) < toTime){
                        add = true;
                    } else if(Integer.valueOf(av.getFromDate()) > fromTime && Integer.valueOf(av.getToDate()) < toTime){
                        add = true;   
                        }
                    }
                }
            if(add){
                filteredApplications.add(app);
            }
        }
            
    }
    
    /**
     * Add all ApplicationInfoDTO objects with a specific competence
     * to the list of filtered applications.
     * 
     * @param searchCompetence the competence to filter by.
     */
    private void addByCompetence(String searchCompetence){
        for(ApplicationInfoDTO app : applications){
            boolean add = false;
            for(CompetenceProfile comPro : app.getCompetenceProfileList()){
                if(comPro.getCompetenceId().getName().equals(searchCompetence)){
                    add = true;  
                    }
                }
            if(add){
                filteredApplications.add(app);
            }
        }
            
    }
    
    /**
     * Add all ApplicationInfoDTO objects belonging to a user with a specific name
     * to the list of filtered applications.
     * 
     * @param searchName the name to filter by.
     */
    private void addByName(String searchName){
        for(ApplicationInfoDTO app : applications){
            boolean add = false;
            if(app.getPersonId().getName().equals(searchName)){
                add = true;   
            }
            if(add){
                filteredApplications.add(app);
            }
        }
            
>>>>>>> localJonas
    }
}
