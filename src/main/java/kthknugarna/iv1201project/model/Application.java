package kthknugarna.iv1201project.model;

import java.sql.Date;
import kthknugarna.iv1201project.model.dto.ApplicationDTO;

/**
 *
 * @author Jonas
 */
public class Application implements ApplicationDTO {
    private final CompetenceProfile competenceProfile;
    private final AvailabilityPeriod[] availabilityPeriod;
    private final int statusId;
    
    public Application(CompetenceProfile competenceProfile, AvailabilityPeriod[] availabilityPeriod, int statusId){
        this.competenceProfile = competenceProfile;
        this.availabilityPeriod = availabilityPeriod;
        this.statusId = statusId;
    }
    
    //TEMPORARY THING
    public Application(){
        this.competenceProfile = null;
        this.availabilityPeriod = null;
        this.statusId = 1;
    }
    
    @Override
    public CompetenceProfile GetCompetenceProfile(){
        return competenceProfile;
    }
    
    @Override
    public AvailabilityPeriod[] GetAvailabilityPeriod(){
        return availabilityPeriod;
    }
    
    @Override
    public int GetStatusId(){
        return statusId;
    }
}
