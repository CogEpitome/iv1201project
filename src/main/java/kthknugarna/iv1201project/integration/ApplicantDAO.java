/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.integration;

import kthknugarna.iv1201project.model.Role;
import kthknugarna.iv1201project.model.Person;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import kthknugarna.iv1201project.model.Application;
import kthknugarna.iv1201project.model.Availability;
import kthknugarna.iv1201project.model.Competence;
import kthknugarna.iv1201project.model.CompetenceProfile;
import kthknugarna.iv1201project.model.Status;
import kthknugarna.iv1201project.model.dto.InputDTO;

/**
 * ApplicantDAO is a data access object that stores and retrieves data from a database.
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 */

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class ApplicantDAO {
    @PersistenceContext(unitName = "applicantPU")
    private EntityManager em;

    /**
     * Method to test the storing of a person.
     * @param inputDTO DTO for the input class
     */
    public void testStore(InputDTO inputDTO){
        try{
            Person person = new Person(inputDTO.getFirstName(), inputDTO.getSurname(),"10", inputDTO.getPassword(), inputDTO.getUsername(), getRole(0));
            em.persist(person);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * TODO: add exception handling.
     * Method to persist an entity to the database.
     * @param obj The object to persist
     */
    public void persist(Object obj){
        try{
            em.persist(obj);
        } catch (Exception e){
        }
    }
    
    /**
     * Returns a Person object for authentication. the username argument is entered 
     * from the client, and if the username exists the person that holds the 
     * username is returned. If no person has the username, the method 
     * returns null.
     * @param username the chosen name of a registered user
     * @return         the person with the specified username
     * @see            Person
     */
    public Person getPerson(String username){
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUsername", Person.class);
        query.setParameter("username", username);
        List<Person> person = query.getResultList();
        if(!person.isEmpty())
        return person.get(0);
        else return null;
    }
    /**
     * Returns a Person object based on a personId. the id argument matches the
     * personId of a competence profile.
     * @param id personId belonging to a competenceProfile
     * @return   the person with the specified id
     * @see      Person
     */
    public Person getPerson(long id){
        return em.find(Person.class, id);
    }
    
    /**
     * Returns a competenceProfile object for recruiting purposes. The id
     * argument is the id of the profile to retrieve.
     * @param id the id of the competence profile
     * @return   the competence profile with specified id
     * @see      CompetenceProfile
     */
    public CompetenceProfile getCompetenceProfile(long id){
        return em.find(CompetenceProfile.class, id);
    }
    /**
     * Returns a Role object for web navigation and verification of users.
     * The id argument is either 1 or 0, which corresponds to recruiter or 
     * applicant.
     * @param id the id of a role
     * @return   the role with the specified id
     * @see      Role
     */
    public Role getRole(long id){
        return em.find(Role.class, id);
    }
    
    /**
     * Returns a Competence object to retrieve the name of a competence tied
     * to a specified id. The id argument is the id of the requested competence.
     * @param id the id of a Competence
     * @return   the competence with the specified id
     * @see      Competence
     */
    public Competence getCompetence(long id){
        return em.find(Competence.class, id);
    }
    
    public Competence getCompetence(String name){
        TypedQuery<Competence> query = em.createNamedQuery("Competence.findByName", Competence.class);
        query.setParameter("name", name);
        List<Competence> competence = query.getResultList();
        if(!competence.isEmpty())
        return competence.get(0);
        else return null;
    }
    
    /**
     * Returns an Availability object for recruiting purposes. The id argument
     * refers to the id of a person.
     * @param id the id of Person
     * @return   the availability period of an applicant
     * @see      Availability
     */
    public Availability getAvailability(long id){
        return em.find(Availability.class, id);
    }
    
    /**
     * Returns a list of all Availability objects corresponding to a person
     * @param person the person the availability corresponds to
     * @return   the availability period of an applicant
     * @see      Availability
     */
    public List<Availability> getAvailabilityList(Person person){
        TypedQuery<Availability> query = em.createNamedQuery("Availability.findPersonId", Availability.class);
        query.setParameter(1, person);
        return query.getResultList();
    }
    
    /**
     * Returns a list of all CompetenceProfile objects corresponding to a person
     * @param person the person the competence profile corresponds to
     * @return   the availability period of an applicant
     * @see      Availability
     */
    public List<CompetenceProfile> getCompetenceProfileList(Person person){
        TypedQuery<CompetenceProfile> query = em.createNamedQuery("CompetenceProfile.findPersonId", CompetenceProfile.class);
        query.setParameter(1, person);
        return query.getResultList();
    }
    
    /**
     * Returns an Availability object for recruiting purposes. The id argument
     * refers to the id of a person.
     * @param id the id of Person
     * @return   the availability period of an applicant
     * @see      Availability
     */
    public Status getStatus(long id){
        return em.find(Status.class, id);
    }
    
    /**
     * Returns an Application object for displaying to the requiters. The id argument
     * refers to the id of an application.
     * @param id the id of Application
     * @return   the Application object mathing the id
     * @see      Application
     */
    public Application getApplication(long id){
        return em.find(Application.class, id);
    }
    
    /**
     * Returns an Application object for displaying to the recruiters.
     * @return   the Application object mathing the id
     * @see      Application
     */
    public List<Application> getApplicationList(){
        TypedQuery<Application> query = em. createNamedQuery("Application.findAll", Application.class);
        return query.getResultList();
    }
}
