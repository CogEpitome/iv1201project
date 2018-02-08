/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.integration;

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
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import kthknugarna.iv1201project.model.Application;

/**
 *
 * @author Jonas
 */

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class ApplicantDAO {
    @PersistenceContext(unitName = "applicantPU")
    private EntityManager em;

    public void testStore(){
        try{
            em.persist(new Role((long)1, "recruiter"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public Role getRole(long id){
        return em.find(Role.class, id);
    }
}
