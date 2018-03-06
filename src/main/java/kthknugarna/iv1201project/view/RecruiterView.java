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
import kthknugarna.iv1201project.model.Availability;
import kthknugarna.iv1201project.model.CompetenceProfile;
import kthknugarna.iv1201project.model.Input;
import kthknugarna.iv1201project.model.Person;
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
    private final String SEARCHPARAM_TIME = "time";
    private final String SEARCHPARAM_COMPETENCE = "competence";
    private final String SEARCHPARAM_NAME = "name";
    private final long STATUSID_DENY = 1;
    private final long STATUSID_ACCEPT = 2;
    @EJB
    private RecruiterController controller;
    private InputDTO input;
    private List<ApplicationInfoDTO> applications, filteredApplications;
    private String searchFromTime, searchToTime, searchCompetence, searchName;
    private String searchParameter;
    private long currentApplication;
    
    /**
     * Gets a list of ApplicationInfo objects from data in the database and stores it in memory.
     * 
     * @return          an appropriate String message.
     */
    public String getApplications(){
        try{
            applications = controller.getApplicationInfoList();
            return applications.toString();
        } catch(Exception e){
            ViewUtils.SetWarning("An error occured while logging out, it was probably our bad! Try reloading the page or contact us for support.", e.getMessage());
            return "failure";
        }
    }
    
    /**
     * Sets the Status of the specified Application entity in the database to accepted.
     * @throws java.lang.Exception
     */
    public void acceptApplication() throws Exception{
        try{
            setStatus((long)0, STATUSID_ACCEPT);
        } catch (Exception e){
            throw new Exception(e);
        }
    }
    
    /**
     * Sets the Status of the specified Application entity in the database to denied.
     * @throws java.lang.Exception
     */
    public void denyApplication()throws Exception{
        try{
            setStatus((long)1006, STATUSID_DENY);
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    /**
     * Updates the Status of the specified Application entity in the database.
     * 
     * @param applicationId the id of the application to update.
     * @param statusId the id of the status to update to.
     */
    public void setStatus(long applicationId, long statusId) throws Exception{
        try{
            controller.setStatus(applicationId, statusId);
        } catch(Exception e){
            throw new Exception(e);
        }
    }
    
    /**
     * Fills the list of filteredApplications based on the user's search preferences.
     * This class is responsible for choosing the correct method of filtering.
     * 
     * More search criteria can be added by adding cases to this method.
     */
    private void filterApplications(){
        try{
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
        } catch (Exception e){
            ViewUtils.SetWarning("The server failed to find the requested applications!", e.getMessage());
        }
    }
    
    /**
     * Add all ApplicationInfoDTO objects with an availability period between a specified time frame
     * to the list of filtered applications.
     * 
     * @param searchFromTime the earliest starting time.
     * @param searchToTime the latest ending time.
     */
    private void addByTime(String searchFromTime, String searchToTime) throws Exception{
        try{
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
        } catch (Exception e){
            throw new Exception(e);
        }
            
    }
    
    /**
     * Add all ApplicationInfoDTO objects with a specific competence
     * to the list of filtered applications.
     * 
     * @param searchCompetence the competence to filter by.
     */
    private void addByCompetence(String searchCompetence) throws Exception{
        try{
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
        } catch (Exception e){
            throw new Exception(e);
        }
            
    }
    
    /**
     * Add all ApplicationInfoDTO objects belonging to a user with a specific name
     * to the list of filtered applications.
     * 
     * @param searchName the name to filter by.
     */
    private void addByName(String searchName) throws Exception{
        try{
            for(ApplicationInfoDTO app : applications){
                boolean add = false;
                if(app.getPersonId().getName().equals(searchName)){
                    add = true;   
                }
                if(add){
                    filteredApplications.add(app);
                }
            } 
        } catch (Exception e){
            throw new Exception(e);
        }
            
    }

    //Getters and setters
    public String getSearchFromTime() {
        return searchFromTime;
    }

    public void setSearchFromTime(String searchFromTime) {
        this.searchFromTime = searchFromTime;
    }

    public String getSearchToTime() {
        return searchToTime;
    }

    public void setSearchToTime(String searchToTime) {
        this.searchToTime = searchToTime;
    }

    public String getSearchCompetence() {
        return searchCompetence;
    }

    public void setSearchCompetence(String searchCompetence) {
        this.searchCompetence = searchCompetence;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(String searchParameter) {
        this.searchParameter = searchParameter;
    }

    public long getCurrentApplication() {
        return currentApplication;
    }

    public void setCurrentApplication(long currentApplication) {
        this.currentApplication = currentApplication;
    }
    
    
    
    
}
