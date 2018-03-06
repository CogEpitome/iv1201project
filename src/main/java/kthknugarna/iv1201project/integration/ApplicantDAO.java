package kthknugarna.iv1201project.integration;

import kthknugarna.iv1201project.model.Role;
import kthknugarna.iv1201project.model.Person;
import java.util.List;
import javax.ejb.EJBException;
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

/**
 * @author Jonas
 * @author Anton
 * @author Benjamin
 * 
 * ApplicantDAO is a data access object that stores and retrieves data from the Applications database.
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class ApplicantDAO {
    @PersistenceContext(unitName = "applicantPU")
    private EntityManager em;
    
    /**
     * Method to persist an entity to the database.
     * @param obj The object to persist
     * @throws java.lang.Exception
     */
    public void persist(Object obj) throws Exception{
        try{
            em.persist(obj);
        } catch (EJBException ee){
            throw new Exception(ee);
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
     * @throws java.lang.Exception
     */
    public Person getPerson(String username) throws Exception{
        try{
            TypedQuery<Person> query = em.createNamedQuery("Person.findByUsername", Person.class);
            query.setParameter("username", username);
            List<Person> person = query.getResultList();
            if(!person.isEmpty())
            return person.get(0);
            else return null;
        } catch (EJBException ee){
            throw new Exception("Error in getPerson of DAO"+ee);
        }
    }
    
    /**
     * Returns a Person object based on a personId. the id argument matches the
     * personId of a competence profile.
     * @param id personId belonging to a competenceProfile
     * @return   the person with the specified id
     * @throws java.lang.Exception
     * @see      Person
     */
    public Person getPerson(long id) throws Exception{
        try{
            return em.find(Person.class, id);
        } catch (EJBException ee){
            throw new Exception(ee);
        }
    }
    
    /**
     * Returns a CompetenceProfile object for recruiting purposes. The id
     * argument is the id of the profile to retrieve.
     * @param id the id of the competence profile
     * @return   the competence profile with specified id
     * @throws java.lang.Exception
     * @see      CompetenceProfile
     */
    public CompetenceProfile getCompetenceProfile(long id) throws Exception{
        try{
            return em.find(CompetenceProfile.class, id);
        } catch (EJBException ee){
            throw new Exception(ee);
        }
    }
    
    /**
     * Returns a Role object for web navigation and verification of users.
     * The id argument is either 1 or 0, which corresponds to recruiter or 
     * applicant.
     * @param id the id of a role
     * @return   the role with the specified id
     * @throws java.lang.Exception
     * @see      Role
     */
    public Role getRole(long id) throws Exception{
        try{
            return em.find(Role.class, id);
        } catch (EJBException ee){
            throw new Exception(ee);
        }
    }
    
    /**
     * Returns a Competence object to retrieve the name of a competence tied
     * to a specified id. The id argument is the id of the requested competence.
     * @param id the id of a Competence
     * @return   the competence with the specified id
     * @throws java.lang.Exception
     * @see      Competence
     */
    public Competence getCompetence(long id) throws Exception{
        try{
        return em.find(Competence.class, id);
        } catch (EJBException ee){
            throw new Exception(ee);
        }
    }
    
    /**
     * Returns a Competence object to retrieve the name of a competence tied
     * to a specified name. The name argument is the name of the requested competence.
     * @param name the name of a Competence
     * @return   the competence with the specified name
     * @throws java.lang.Exception
     * @see      Competence
     */
    public Competence getCompetence(String name) throws Exception{
        try{
            TypedQuery<Competence> query = em.createNamedQuery("Competence.findByName", Competence.class);
            query.setParameter("name", name);
            List<Competence> competence = query.getResultList();
            if(!competence.isEmpty())
            return competence.get(0);
            else return null;
        } catch (Exception e){
            throw new Exception(e);
        }
    }
    
    /**
     * Returns a list of all Competence objects
     * @return   a list of all Competence objects in the database
     * @throws java.lang.Exception
     * @see      Competence
     */
    public List<Competence> getCompetences() throws Exception{
        try{
        TypedQuery<Competence> query = em.createNamedQuery("Competence.findAll", Competence.class);
        return query.getResultList();
        } catch (Exception e){
            throw new Exception(e);
        }
    }
    
    /**
     * Returns an Availability object for recruiting purposes. The id argument
     * refers to the id of a person.
     * @param id the id of Person
     * @return   the availability period of an applicant
     * @throws java.lang.Exception
     * @see      Availability
     */
    public Availability getAvailability(long id) throws Exception{
        try{
            return em.find(Availability.class, id);
        } catch (Exception e){
            throw new Exception(e);
        }
    }
    
    /**
     * Returns a list of all Availability objects corresponding to a person
     * @param person the person the availability corresponds to
     * @return   the availability period of an applicant
     * @throws java.lang.Exception
     * @see      Availability
     */
    public List<Availability> getAvailabilityList(Person person) throws Exception{
        try{
            TypedQuery<Availability> query = em.createNamedQuery("Availability.findPersonId", Availability.class);
            query.setParameter(1, person);
            return query.getResultList();
        } catch (Exception e){
            throw new Exception(e);
        }
    }
    
    /**
     * Returns a list of all CompetenceProfile objects corresponding to a person
     * @param person the person the competence profile corresponds to
     * @return   the list of CompetenceProfiles corresponding to the person
     * @throws java.lang.Exception
     * @see      CompetenceProfile
     */
    public List<CompetenceProfile> getCompetenceProfileList(Person person) throws Exception{
        try{
        TypedQuery<CompetenceProfile> query = em.createNamedQuery("CompetenceProfile.findPersonId", CompetenceProfile.class);
        query.setParameter(1, person);
        return query.getResultList();
        } catch (Exception e){
            throw new Exception(e);
        }
    }
    
    /**
     * Returns an Availability object for recruiting purposes. The id argument
     * refers to the id of a person.
     * @param id the id of Person
     * @return   the availability period of an applicant
     * @throws java.lang.Exception
     * @see      Availability
     */
    public Status getStatus(long id) throws Exception{
        try{
        return em.find(Status.class, id);
        } catch (EJBException e){
            throw new Exception(e);
        }
    }
    
    /**
     * Returns an Application object for displaying to the requiters. The id argument
     * refers to the id of an application.
     * @param id the id of Application
     * @return   the Application object mathing the id
     * @throws java.lang.Exception
     * @see      Application
     */
    public Application getApplication(long id) throws Exception{
        try{
            return em.find(Application.class, id);
        } catch (EJBException ee){
            throw new Exception(ee);
        }
    }
    
    /**
     * Returns a list of Application objects for displaying to the recruiters.
     * @return   a List of all Application objects
     * @throws java.lang.Exception
     * @see      Application
     */
    public List<Application> getApplicationList() throws Exception{
        try{
        TypedQuery<Application> query = em. createNamedQuery("Application.findAll", Application.class);
        return query.getResultList();
        } catch(Exception e){
            throw new Exception(e);
        }
    }
}
