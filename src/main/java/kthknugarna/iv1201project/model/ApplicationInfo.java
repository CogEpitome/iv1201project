/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.model;

import java.util.List;
import kthknugarna.iv1201project.model.dto.ApplicationInfoDTO;

/**
 * This class contains the information of a single application.
 * It contains references to all Availability and CompetenceProfile objects associated with the application,
 * as well as the creator of the application and the application's status (unreviewed, accepted, or denied).
 * 
 * Used for transferring data pertaining to an application to the view.
 * 
 * @author Jonas
 * @author Benjamin
 * @author Anton
 */
public class ApplicationInfo implements ApplicationInfoDTO{
    private final long applicationId;
    private final List<Availability> availabilityList;
    private final List<CompetenceProfile> competenceProfileList;
    private final Person personId;
    private final Status statusId;
    
    public ApplicationInfo(long applicationId, List<Availability> availabilityList, List<CompetenceProfile> competenceProfileList, Person personId, Status statusId){
        this.applicationId = applicationId;
        this.availabilityList = availabilityList;
        this.competenceProfileList = competenceProfileList;
        this.personId = personId;
        this.statusId = statusId;
    }
     

    @Override
    public long getApplicationId() {
        return applicationId;
    }
    
    @Override
    public List<Availability> getAvailabilityList() {
        return availabilityList;
    }

    @Override
    public List<CompetenceProfile> getCompetenceProfileList() {
        return competenceProfileList;
    }

    @Override
    public Person getPersonId() {
        return personId;
    }

    @Override
    public Status getStatusId() {
        return statusId;
    }
    
    
}
