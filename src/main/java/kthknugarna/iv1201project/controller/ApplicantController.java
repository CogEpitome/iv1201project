package kthknugarna.iv1201project.controller;

import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import kthknugarna.iv1201project.integration.ApplicantDAO;
import kthknugarna.iv1201project.model.Application;
import kthknugarna.iv1201project.model.Availability;
import kthknugarna.iv1201project.model.Competence;
import kthknugarna.iv1201project.model.CompetenceProfile;
import kthknugarna.iv1201project.model.Person;
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
    
    public void createApplication(ApplicantDTO applicant) throws Exception{
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
    /**
     * Creates a competence profile which holds information about
     * a specific competence and the years of experience a specific
     * user has in that competence
     * @param competenceName name of the competence
     * @param username username of the user
     * @param yearsOfExperience years of experience the user has in this competence
     * @throws java.lang.Exception
     */
    public void createCompetenceProfile(String competenceName, String username, BigDecimal yearsOfExperience) throws Exception{
        try{
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
        } catch (Exception e){
            throw new Exception(e);
        }
    }
    
    public Person getPerson(String username) throws Exception{
        return dao.getPerson(username);
    }
    public Person getPerson(long id) throws Exception{
        return dao.getPerson(id);
    }
    
    public Competence getCompetence(long id) throws Exception{
        return dao.getCompetence(id);
    }
    
    public Competence getCompetence(String name) throws Exception{
        return dao.getCompetence(name);
    }
}
