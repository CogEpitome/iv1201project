/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.model.dto;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
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
