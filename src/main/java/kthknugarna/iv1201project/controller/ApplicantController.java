package kthknugarna.iv1201project.controller;

import java.math.BigDecimal;
import java.sql.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import kthknugarna.iv1201project.integration.ApplicantDAO;
import kthknugarna.iv1201project.model.Application;
import kthknugarna.iv1201project.model.Availability;
import kthknugarna.iv1201project.model.Competence;
import kthknugarna.iv1201project.model.CompetenceProfile;
import kthknugarna.iv1201project.model.Person;
import kthknugarna.iv1201project.model.Status;
import kthknugarna.iv1201project.model.VerificationHandler;
import kthknugarna.iv1201project.model.dto.ApplicantDTO;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ApplicantController {
    @EJB ApplicantDAO dao;
    @EJB VerificationHandler verifier;
    
    /*public String apply(ApplicantDTO applicant){
        Person person = getPerson(applicant.getUserName());
        if(person == null)
            return "Error:  A user with this id could not be found.";
        else {
            int[] expertise = applicant.getExpertise();
            int[] yearsOfExperience = applicant.getYearsOfExperience();
            if(expertise.length == yearsOfExperience.length){
                for(int i = 0; i < expertise.length; i++){
                    Competence comp = getCompetence(expertise[i]);
                    CompetenceProfile comPro = new CompetenceProfile(comp, BigDecimal.valueOf(yearsOfExperience[i]));
                    comPro.setPersonId(person);
                    Application app = new Application(comPro, dao.getStatus((long)0));
                    dao.persist(comPro);
                    dao.persist(app);
                }
            }else {
                return "Error: The amount of 'years of experience' does not equal the amount of 'experiences'";
            }
            String[] from = applicant.getAvailableFrom();
            String[] to = applicant.getAvailableTo();
            if(from.length == to.length){
                for(int i = 0; i < from.length; i++){
                    Availability availability = new Availability(from[i], to[i]);
                    availability.setPersonId(person);
                    dao.persist(availability);
                }
            } else {
                return "Error: The amount of 'available from' dates does not equal the amount of 'available to' dates";
            }

            return "success";
        }
    }*/
    public void createApplication(ApplicantDTO applicant){
        Person person = getPerson(applicant.getUserName());
        Application app = new Application();
        app.setPersonId(person);
        app.setStatusId(dao.getStatus((long)0));
        dao.persist(app);
        String from = applicant.getAvailableFrom();
        String to = applicant.getAvailableTo();
        Availability availability = new Availability(from, to);
        availability.setPersonId(person);
        dao.persist(availability);
    }
    
    public void createCompetenceProfile(String competenceName, String username, BigDecimal yearsOfExperience){
        Competence comp;
        if(getCompetence(competenceName) == null){
            comp = new Competence(competenceName);
            dao.persist(comp);
        }
        else
            comp = getCompetence(competenceName);
        
        CompetenceProfile cp = new CompetenceProfile(comp, yearsOfExperience);
        cp.setPersonId(getPerson(username));
        dao.persist(cp);
    }
    
    public Person getPerson(String username){
        return dao.getPerson(username);
    }
    public Person getPerson(long id){
        return dao.getPerson(id);
    }
    
    public Competence getCompetence(long id){
        return dao.getCompetence(id);
    }
    
    public Competence getCompetence(String name){
        return dao.getCompetence(name);
    }
}
