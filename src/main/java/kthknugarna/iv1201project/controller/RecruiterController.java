package kthknugarna.iv1201project.controller;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import kthknugarna.iv1201project.integration.ApplicantDAO;
import kthknugarna.iv1201project.model.Application;
import kthknugarna.iv1201project.model.ApplicationInfo;
import kthknugarna.iv1201project.model.Availability;
import kthknugarna.iv1201project.model.Competence;
import kthknugarna.iv1201project.model.CompetenceProfile;
import kthknugarna.iv1201project.model.Person;
import kthknugarna.iv1201project.model.Status;
import kthknugarna.iv1201project.model.VerificationHandler;
import kthknugarna.iv1201project.model.dto.ApplicationInfoDTO;

/**
 *
 * @author Jonas
 * @author Anton
 * @author Benjamin
 * 
 * The controller class connecting RecruiterView to the model and integration layers.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class RecruiterController {
    @EJB ApplicantDAO dao;
    @EJB VerificationHandler verifier;
    
    /**
     * Returns a list of ApplicationInfoDTOs representing all Applications in the database
     * 
     * @return the list of ApplicationInfoDTO objects.
     * @throws Exception 
     */
    public List<ApplicationInfoDTO> getApplicationInfoList() throws Exception{
        List<ApplicationInfoDTO> dtos = new ArrayList();
        List<Application> applications = getApplications();
        for(Application app : applications){
            dtos.add(getApplicationInfo(app));
        }
        return dtos;
    }
    
    /**
     * Returns the information associated with an application in the database in the form of an ApplicationDTO.
     * @param application the application to return information of.
     * @return an ApplicationInfoDTO with the information.
     * @throws Exception 
     */
    public ApplicationInfoDTO getApplicationInfo(Application application) throws Exception{
        Person person = application.getPersonId();
        List<Availability> aList = getAvailabilityList(person);
        List<CompetenceProfile> cList = getCompetenceProfileList(person);
        ApplicationInfoDTO dto = new ApplicationInfo(application.getApplicationId(), aList, cList, person, application.getStatusId());
        return dto;
    }
    
    /**
     * Sets the status of a specific application in the database to the desired value.
     * @param applicationId the application to update.
     * @param statusId      the value of the desired status.
     * @see RecruiterView
     * @throws Exception 
     */
    public void setStatus(long applicationId, long statusId) throws Exception{
        Application app = dao.getApplication(applicationId);
        Status stat = dao.getStatus(statusId);
        app.setStatusId(stat);
        dao.persist(app);
    }
    
    private List<Availability> getAvailabilityList(Person person) throws Exception{
        return dao.getAvailabilityList(person);
    }
    private List<CompetenceProfile> getCompetenceProfileList(Person person) throws Exception{
        return dao.getCompetenceProfileList(person);
    }
    
    public Person getPerson(long id) throws Exception{
        return dao.getPerson(id);
    }
    public Person getPerson(String username) throws Exception{
        return dao.getPerson(username);
    }
    
    public Competence getCompetence(long id) throws Exception{
        return dao.getCompetence(id);
    }
    
    public Competence getCompetence(String name) throws Exception{
        return dao.getCompetence(name);
    }
    
    public List<Competence> getCompetences() throws Exception{
        return dao.getCompetences();
    }
    
    public List<Application> getApplications() throws Exception{
        return dao.getApplicationList();
    }
}
