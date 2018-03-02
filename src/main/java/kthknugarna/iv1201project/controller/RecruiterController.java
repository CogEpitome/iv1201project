package kthknugarna.iv1201project.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import kthknugarna.iv1201project.integration.ApplicantDAO;
import kthknugarna.iv1201project.model.Applicant;
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
public class RecruiterController {
    @EJB ApplicantDAO dao;
    @EJB VerificationHandler verifier;
    
    public ApplicantDTO getApplication(String user){
        Person person = getPerson(user);
        
        if(person == null)
            return null;
        else {
            Application application = getApplication(person.getPersonId());
            CompetenceProfile comPro = getCompetenceProfile(application.getCompetenceProfileId().getCompetenceProfileId());
            Competence competence = getCompetence(comPro.getCompetenceId().getCompetenceId());
            Availability availability = getAvailability(person.getPersonId());
            ApplicantDTO applicantData = new Applicant(user, new int[]{competence.getCompetenceId().intValue()}, new int[]{comPro.getYearsOfExperience().intValue()},new String[]{availability.getFromDate()}, new String[]{availability.getToDate()});            
            return applicantData;
        }
        
            
    }
    
    public Person getPerson(String username){
        return dao.getPerson(username);
    }
    public Person getPerson(long id){
        return dao.getPerson(id);
    }
    
    public Application getApplication(long id){
        return dao.getApplication(id);
    }
    
    public CompetenceProfile getCompetenceProfile(long id){
        return dao.getCompetenceProfile(id);
    }
    
    public Competence getCompetence(long id){
        return dao.getCompetence(id);
    }
    
    public Availability getAvailability(long id){
        return dao.getAvailability(id);
    }
    
}
