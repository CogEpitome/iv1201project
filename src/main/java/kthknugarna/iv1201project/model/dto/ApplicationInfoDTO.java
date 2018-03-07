package kthknugarna.iv1201project.model.dto;

import java.util.List;
import kthknugarna.iv1201project.model.Availability;
import kthknugarna.iv1201project.model.CompetenceProfile;
import kthknugarna.iv1201project.model.Person;
import kthknugarna.iv1201project.model.Status;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 * 
 * @see ApplicationInfo
 */
public interface ApplicationInfoDTO {
    /**                                     
     * Gets the user's unique id.
     *
     * @return a long with the applicant's person Id.
     */
    long getApplicationId();
    /**                                     
     * Gets the user's unique id.
     *
     * @return a long with the applicant's person Id.
     */
    List<Availability> getAvailabilityList();
    /**                                     
     * Gets the applicant's availability period from dates.
     *
     * @return an int array with the from dates.
     */
    List<CompetenceProfile> getCompetenceProfileList();
    /**                                     
     * Gets the applicant's availability period to dates.
     *
     * @return an int array with the to dates.
     */
    Person getPersonId();
    
    /**                                     
     * Gets the applicant's availability period to dates.
     *
     * @return an int array with the to dates.
     */
    Status getStatusId();
}
