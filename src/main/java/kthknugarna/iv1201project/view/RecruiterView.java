package kthknugarna.iv1201project.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import kthknugarna.iv1201project.controller.RecruiterController;
import kthknugarna.iv1201project.model.Availability;
import kthknugarna.iv1201project.model.Competence;
import kthknugarna.iv1201project.model.CompetenceProfile;
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
    private final long STATUSID_DENY = 2;
    private final long STATUSID_ACCEPT = 1;
    private ApplicationInfoDTO currentApplication;
    @EJB
    private RecruiterController controller;
    private InputDTO input;
    private List<ApplicationInfoDTO> applications, filteredApplications;
    private List<String> competences;
    private String searchFromTime, searchToTime, searchCompetence, searchName;
    private String searchParameter;
    

    
    /**
     * Gets a list of ApplicationInfo objects from data in the database and stores it in memory.
     * 
     */
    public void getApplicationsList(){
        try{
            applications = controller.getApplicationInfoList();
            filteredApplications = null;
            populateCompetences();
            
        } catch(Exception e){
            ViewUtils.SetWarning("An error occured while logging out, it was probably our bad! Try reloading the page or contact us for support.", e.getMessage());
        }
    }
    
    /**
     * Sets the Status of the specified Application entity in the database to accepted.
     * @return A string for facescontext to reload the page
     */
    public String acceptApplication() {
        try{
            setStatus(currentApplication.getApplicationId(), STATUSID_ACCEPT);
            return "reload";
        } catch (Exception e){
            ViewUtils.SetWarning(e.getMessage(), e.getMessage());
            return "failure";
        }
    }
    
    /**
     * Sets the Status of the specified Application entity in the database to denied.
     * @return A string for facescontext to reload the page
     */
    public String denyApplication() {
        try{
            setStatus(currentApplication.getApplicationId(), STATUSID_DENY);
            return "reload";
        } catch (Exception e){
            ViewUtils.SetWarning(e.getMessage(), e.getMessage());
            return "failure";
        }
    }
    
    /**
     * Fills the list competences with all Competence object names in the database.
     */
    public void populateCompetences(){
        try{
            if(competences == null)
                competences = new ArrayList<>();
            else
                competences.clear();
            
            for(Competence c : controller.getCompetences()){
                competences.add(c.getName());
            }
        } catch (Exception e){
            ViewUtils.SetWarning("An error occurred while getting the list of competences.", e.getMessage());
        }
    }

    /**
     * Updates the Status of the specified Application entity in the database.
     * 
     * @param applicationId the id of the application to update.
     * @param statusId the id of the status to update to.
     * @throws java.lang.Exception
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
    public void filterApplications(String searchParameter){
        try{
            if(filteredApplications == null)
                filteredApplications = new ArrayList<>();
            else
                filteredApplications.clear();
            
            switch(searchParameter){
                case SEARCHPARAM_TIME:
                    addByTime();
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
    private void addByTime() throws Exception{
        try{
            if(searchFromTime.isEmpty()) searchFromTime = "0";
            if(searchToTime.isEmpty()) searchToTime = String.valueOf(Integer.MAX_VALUE);
            int fromTime = Integer.valueOf(searchFromTime);
            int toTime = Integer.valueOf(searchToTime); 
            for(ApplicationInfoDTO app : applications){
                boolean add = false;
                for(Availability av : app.getAvailabilityList()){
                    if(Integer.valueOf(av.getFromDate()) > fromTime && Integer.valueOf(av.getToDate()) < toTime){
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
    
    /**
     * 
     * @param appInfo
     * @return 
     */
    public String reviewButton(ApplicationInfoDTO appInfo) {
        setCurrentApplication(appInfo);
        return "review";
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

    public ApplicationInfoDTO getCurrentApplication() {
        return currentApplication;
    }

    public void setCurrentApplication(ApplicationInfoDTO currentApplication) {
        this.currentApplication = currentApplication;
    }
   
    
    public List<ApplicationInfoDTO> getApplications(){
        return applications;
    }
    public void setApplications(List<ApplicationInfoDTO> list) {
        this.applications = list;
    }
    public List<ApplicationInfoDTO> getFilteredApplications(){
        return filteredApplications;
    }
    public void setFilteredApplications(List<ApplicationInfoDTO> list) {
        this.filteredApplications = list;
    }
    
    public void setCompetences(List<String> competences) {
        this.competences = competences;
    }

    public List<String> getCompetences() {
        return competences;
    }
    
    
    
    
    
    
}
