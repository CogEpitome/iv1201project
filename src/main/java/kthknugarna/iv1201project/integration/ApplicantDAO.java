/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.integration;

import static java.util.Collections.list;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import kthknugarna.iv1201project.model.Application;

/**
 *
 * @author Jonas
 */
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class ApplicantDAO {
    /*
    public List<Application> listApplications(){
       
    }
    */    
}
