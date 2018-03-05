package kthknugarna.iv1201project.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import kthknugarna.iv1201project.integration.ApplicantDAO;
import kthknugarna.iv1201project.model.Application;
import kthknugarna.iv1201project.model.ApplicationInfo;
import kthknugarna.iv1201project.model.Availability;
import kthknugarna.iv1201project.model.Competence;
import kthknugarna.iv1201project.model.CompetenceProfile;
import kthknugarna.iv1201project.model.Person;
import kthknugarna.iv1201project.model.Status;
import kthknugarna.iv1201project.model.VerificationHandler;
import kthknugarna.iv1201project.model.dto.ApplicantDTO;
import kthknugarna.iv1201project.model.dto.ApplicationInfoDTO;

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
    
    public List<ApplicationInfoDTO> getApplicationInfoList(){
        List<ApplicationInfoDTO> dtos = new ArrayList();
        List<Application> applications = getApplications();
        for(Application app : applications){
            dtos.add(getApplicationInfo(app));
        }
        return dtos;
    }
    
    public ApplicationInfoDTO getApplicationInfo(Application application){
        Person person = application.getPersonId();
        List<Availability> aList = getAvailabilityList(person);
        List<CompetenceProfile> cList = getCompetenceProfileList(person);
        ApplicationInfoDTO dto = new ApplicationInfo(application.getApplicationId(), aList, cList, person, application.getStatusId());
        return dto;
    }

    
    private List<Availability> getAvailabilityList(Person person){
        return dao.getAvailabilityList(person);
    }
    private List<CompetenceProfile> getCompetenceProfileList(Person person){
        return dao.getCompetenceProfileList(person);
    }
    
    public Person getPerson(long id){
        return dao.getPerson(id);
    }
    public Person getPerson(String username){
        return dao.getPerson(username);
    }
    
    public Competence getCompetence(long id){
        return dao.getCompetence(id);
    }
    
    public Competence getCompetence(String name){
        return dao.getCompetence(name);
    }
    
    public List<Application> getApplications(){
        return dao.getApplicationList();
    }
}
