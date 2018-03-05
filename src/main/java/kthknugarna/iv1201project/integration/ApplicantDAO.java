/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.integration;

import java.util.ArrayList;
import kthknugarna.iv1201project.model.Role;
import kthknugarna.iv1201project.model.Person;
import static java.util.Collections.list;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import kthknugarna.iv1201project.model.Application;
import kthknugarna.iv1201project.model.Availability;
import kthknugarna.iv1201project.model.Competence;
import kthknugarna.iv1201project.model.CompetenceProfile;
import kthknugarna.iv1201project.model.Input;
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
    
    public Person getPerson(String username){
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUsername", Person.class);
        query.setParameter("username", username);
        List<Person> person = query.getResultList();
        if(!person.isEmpty())
        return person.get(0);
        else return null;
    }

    public Person getPerson(long id){
        return em.find(Person.class, id);
    }
    
    public CompetenceProfile getCompetenceProfile(long id){
        return em.find(CompetenceProfile.class, id);
    }

    public Role getRole(long id){
        return em.find(Role.class, id);
    }

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

    public Availability getAvailability(long id){
        return em.find(Availability.class, id);
    }

    public List<Availability> getAvailabilityList(Person person){
        TypedQuery<Availability> query = em. createNamedQuery("Availability.findPersonId", Availability.class);
        query.setParameter(1, person);
        return query.getResultList();
    }

    public List<CompetenceProfile> getCompetenceProfileList(Person person){
        TypedQuery<CompetenceProfile> query = em. createNamedQuery("CompetenceProfile.findPersonId", CompetenceProfile.class);
        query.setParameter(1, person);
        return query.getResultList();
    }

    public Status getStatus(long id){
        return em.find(Status.class, id);
    }

    public Application getApplication(long id){
        return em.find(Application.class, id);
    }

    public List<Application> getApplicationList(){
        TypedQuery<Application> query = em. createNamedQuery("Application.findAll", Application.class);
        return query.getResultList();
    }
}
