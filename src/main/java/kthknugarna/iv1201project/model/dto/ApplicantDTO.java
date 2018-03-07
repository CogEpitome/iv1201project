package kthknugarna.iv1201project.model.dto;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 * 
 * @see Applicant
 */
public interface ApplicantDTO {
/**                                     
     * Gets the user's unique id.
     *
     * @return a long with the applicant's person Id.
     */
    String getUserName();
    /**                                     
     * Gets the applicant's availability period from dates.
     *
     * @return an int array with the from dates.
     */
    String getAvailableFrom();
    /**                                     
     * Gets the applicant's availability period to dates.
     *
     * @return an int array with the to dates.
     */
    String getAvailableTo();
}
