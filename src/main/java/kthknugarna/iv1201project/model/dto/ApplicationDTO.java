package kthknugarna.iv1201project.model.dto;

import java.sql.Date;
import kthknugarna.iv1201project.model.AvailabilityPeriod;
import kthknugarna.iv1201project.model.CompetenceProfile;

/**
 *
 * @author Jonas
 */
public interface ApplicationDTO {
    /**                                     
     * Gets the Competence profile for this application.
     *
     * @return the Competence Profile object.
     */
    CompetenceProfile GetCompetenceProfile();
    /**                                     
     * Gets the availability periods for this application.
     *
     * @return the availability periods.
     */
    AvailabilityPeriod[] GetAvailabilityPeriod();
    /**                                     
     * Gets the status of this application.
     *
     * @return the status id number.
     */
    int GetStatusId();
}
